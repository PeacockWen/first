package cn.kgc.shootGame;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Administrator
 * 飞机大战的启动类
 * 因为启动类中我们需要画游戏窗口
 * JPanel画板类
 */
public class ShootGame extends JPanel{
	private static final long serialVersionUID = 1L;
	//设置游戏的窗口宽和高(设置成常量)
	public static final int WIDTH=400;//width 游戏窗口的宽
	public static final int HEIGHT=650;//height 游戏窗口的高
	
	//定义图片变量，用来做提前的加载
	public static BufferedImage background;//游戏的背景图
	public static BufferedImage airplane;//小敌机的图片
	public static BufferedImage bee;//小蜜蜂的图片
	public static BufferedImage bullet;//子弹的图片
	public static BufferedImage hero0;//英雄机0
	public static BufferedImage hero1;//英雄机1
	public static BufferedImage start;//游戏开始的图片
	public static BufferedImage pause;//游戏暂停的图片
	public static BufferedImage gameover;//游戏结束的图片
	
	/**
	 * 创建英雄机，敌机，子弹对象
	 */
	private Hero hero=new Hero();
	private FlyingObject[] flyings={};//敌人数组对象
    private Bullet[] bullets={};//子弹数组对象
    
    public ShootGame(){
    	flyings=new FlyingObject[2];//假设只有两个敌人
    	flyings[0]=new Airplane();
    	flyings[1]=new Bee();
    	bullets=new Bullet[1];
    	bullets[0]=new Bullet(100,200);
    }
    
	/**
	 * 使用静态块将图片加载进来
	 */
	static{
		try {
			/*
			 * 通过ImageIO流去读取当前ShootGame这个类路径下的.png图片
			 */
			background=ImageIO.read(ShootGame.class.getResource("background.png"));
			airplane=ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee=ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet=ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0=ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1=ImageIO.read(ShootGame.class.getResource("hero1.png"));
			start=ImageIO.read(ShootGame.class.getResource("start.png"));
			pause=ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover=ImageIO.read(ShootGame.class.getResource("gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 重写绘画的方法(该方法来自于JPanel类)
	 * Graphics g：相当于是画笔
	 * 注意:frame.setVisible(true):会自动调用该paint()方法
	 */
	public void paint(Graphics g){
		//画背景图
		g.drawImage(background,0,0,null);
		//画英雄机
		DrawHero(g);
		//画敌机
		DrawFlyingObject(g);
		//画子弹
		DrawBullets(g);
	}
	/**
	 * 画英雄机的方法
	 */
	public void DrawHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/**
	 * 画敌机的方法
	 */
	public void DrawFlyingObject(Graphics g){
		for(int i=0;i<flyings.length;i++){
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
		
	}
	/**
	 * 画子弹的方法
	 */
	public void DrawBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	
	/**
	 * 随机生成敌人的方法：方法是需要有返回值的，
	 * 因为调用该方法之后，会随机的返回一个敌人的对象
	 * 可能是小敌机对象，也可能是小蜜蜂对象
	 */
	public FlyingObject nextOne(){
		Random rand=new Random();
		//假设现在随机生成一个30以内的整数
		int num=rand.nextInt(30);
		if(num<10){//如果生成的这个随机数小于10
			return new Bee();//则返回小蜜蜂的对象
		}else{
			return new Airplane();
		}
	}
	/**
	 * 定时器的方法:
	 */
	
	public void action(){
		//创建一个监听器，来监听鼠标的移动(包含了很多方法)
		MouseAdapter m=new MouseAdapter(){
			//鼠标的移动事件(重写原有的方法)
			public void mouseMoved(MouseEvent e){//监听到的鼠标对象为变量e
				//通过监听到的鼠标对象(e)来获取鼠标的x和y坐标
				int x=e.getX();
				int y=e.getY();
				//通过鼠标的坐标值计算出英雄机的坐标值
				hero.moveTo(x, y);
			}
		};
		//将监听到的鼠标移动事件添加到该类的游戏窗口中
		this.addMouseMotionListener(m);
		//添加定时器 定时器的类是Timer
		Timer time=new Timer();
		/**
		 * time.schedule(TimerTask对象,long t1,long t2)
		 * t1:表示的是启动定时器到第一次触发run方法的时间间隔（毫秒值）
		 * t2:表示第二次触发到第三次触发的时间间隔
		 *    第三次触发到第四次触发的时间间隔
		 *    第四次触发到第五次触发的时间间隔
		 *    ....
		 */
		//抽象类：不能直接创建他的对象
		/*
		 * 匿名内部类
		 */
		time.schedule(new TimerTask(){
			@Override//重写TimerTask里面的抽象方法
			//当启动定时器之后，会自动调用该run方法
			public void run() {
				//英雄机图片的切换
				hero.step();
				//添加敌机的入场(专门写一个敌人的入场方法，来扩容敌机数组)
				enterAction();
				//飞行物走一步的方法
				stepAction();
				//添加子弹入场
				shootAction();
				//删除越界的飞行物
				outOfBoundsAction();
				//在定时器当中手动调用重绘的方法时时刻刻重新画一遍paint里面的对象
				repaint();
			}
		}, 10, 10);
	}
	
	/**
	 * 删除越界的飞行物
	 * 敌机和子弹
	 */
	public void outOfBoundsAction(){
		/*
		 * index有两层意思：
		 * 1.表示的是不越界的飞行物的个数
		 * 2.表示的是存放不越界的飞行物数组的下标值
		 */
		int index=0;
		//创建一个新数组，用来专门存放不越界的敌人,数组的长度和flyings的长度一致
		FlyingObject[] obj=new FlyingObject[flyings.length];
		//找flyings数组中，有哪些敌机没有越界
		for(int i=0;i<flyings.length;i++){
			//拿到每一架敌机
			FlyingObject f=flyings[i];
			if(!f.outOfBounds()){//找出不越界的敌人
				//找到不越界的敌人之后，将敌人放入到新的obj数组中
				obj[index]=f;
				index++;//下标值加1
			}
		}
		/*
		 * 将新创建的存放不越界的飞行物的obj数组覆盖掉原来的flyings数组
		 */
		flyings=Arrays.copyOf(obj, index);
		
		/*
		 * 删除越界的子弹
		 */
	}
	
	/**
	 * 敌机入场的方法
	 * 控制敌机的生成频率
	 */
	int flyingIndex=0;//用来控制敌机生成频率的数值
	public void enterAction(){
		flyingIndex++;
		if(flyingIndex%30==0){
			//1.随机生成一个敌人
			FlyingObject one=nextOne();
			//2.对敌机数组flyings进行扩容
			flyings=Arrays.copyOf(flyings, flyings.length+1);
			//3.将新生成的敌机放入到数组中
			flyings[flyings.length-1]=one;
		}
		
	}
	/**
	 * 飞行物走一步的方法
	 */
	public void stepAction(){
		//遍历敌机数组，然后挨个的调用step方法
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		//遍历子弹的数组，然后挨个的调用子弹的step
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
	}
	
	/**
	 * 子弹发射的方法
	 */
	int shootIndex=0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex%20==0){
			//调用英雄机发射子弹的方法
			Bullet[] bs=hero.shoot();
			//对子弹的数组进行扩容,根据实际的bs数组的长度来决定扩容多少
			bullets=Arrays.copyOf(bullets, bullets.length+bs.length);
			/*
			 *下面的if-else判断有一个不好的地方：
			 * 如果是四倍火力或者6倍火力或者更多的火力，那么if-else结构会比较冗长 
			 */
			/*if(bs.length==1){//如果shoot方法返回的是一颗子弹
				bullets[bullets.length-1]=bs[0];
			}else{//否则就是两颗子弹
				bullets[bullets.length-1]=bs[0];
				bullets[bullets.length-2]=bs[1];
			}*/
			/*
			 * 表示从bs数组开始复制，0表示从bs这个数组下标为0的位置开始复制，
			 * 复制到bullets数组中，复制的位置从bullets数组的长度-bs数组的长度开始复制，
			 * 复制bs.length个元素
			 */
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
		}
		
	}
	public static void main(String[] args) {
    	//创建游戏的窗口对象
    	JFrame frame=new JFrame("飞机大战");
    	//通过窗口对象设置窗口的大小：使用常量
    	frame.setSize(WIDTH, HEIGHT);
    	ShootGame game=new ShootGame();
    	frame.add(game);//将当前类的对象添加到窗口中
    	//设置关闭游戏界面立即结束程序
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//设置游戏窗口居中
    	frame.setLocationRelativeTo(null);
    	//设置窗口不能被放大
    	frame.setResizable(false);
    	frame.setVisible(true);//1.设置窗口可见  2.尽快的调用paint()(重绘)方法
    	game.action();//启动定时器的方法
	}
}















