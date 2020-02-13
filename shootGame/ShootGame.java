package cn.com.ckg.shootGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
 * 飞机大战的启动类
 * 因为启动类中我们需要画游戏窗口
 * JPanel画板类
 * 
 * @author 1
 *
 */
public class ShootGame extends JPanel{
	private static final long serialVersionUID = 1L;
	//设置游戏的窗口宽和高(设置成常量)
	public static final int WIDTH = 400;//width
	public static final int HEIGHT = 650;//height
	
	public static final int START = 0;//表示启动状态
	public static final int RUNNING = 1;//表示运行状态
	public static final int PAUSE = 2;//表示暂停状态
	public static final int GAME_OVER = 3;//表示游戏结束状态
	
	//定义图片变量,用来做提前的加载
	private int state = START;
	public static BufferedImage background;
	public static BufferedImage airplane0;//小敌机
	public static BufferedImage bee0;
	public static BufferedImage bullet;
	public static BufferedImage hero0;//英雄机0
	public static BufferedImage hero1;//英雄机1
	public static BufferedImage start;
	public static BufferedImage pause;//游戏暂停的图片
	public static BufferedImage gameover;
	public static BufferedImage hero2;
	public static BufferedImage hero3;
	public static BufferedImage hero4;
	public static BufferedImage hero5;
	public static BufferedImage airplane1;
	public static BufferedImage airplane2;
	public static BufferedImage airplane3;
	public static BufferedImage airplane4;
	public static BufferedImage bee1;
	public static BufferedImage bee2;
	public static BufferedImage bee3;
	public static BufferedImage bee4;
	
	
	/**
	 * 创建英雄机,敌机,子弹对象
	 */
	
	private Hero hero = new Hero();
	private FlyingObject[] flyings = {};//敌机数组对象
	private Bullet[] bullets = {};//子弹数组对象
//	private Airplane[] airplanesEX = {};
	
//	public ShootGame() {
//		flyings = new FlyingObject[2];//假设只有两个敌人
//		flyings[0] = new Airplane(); 
//		flyings[1] = new Bee();
//		bullets = new Bullet[1];
//		bullets[0] = new Bullet();
//		bullets=new Bullet[1];
//    	bullets[0]=new Bullet(100,200);
//	}
	
	/**
	 * 使用静态块将图片加载进来
	 * @param args
	 */
	static{
		try {
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
		  	 airplane0 = ImageIO.read(ShootGame.class.getResource("airplane0.png"));
		   	 airplane1 = ImageIO.read(ShootGame.class.getResource("airplane1.png"));
		  	 airplane2 = ImageIO.read(ShootGame.class.getResource("airplane2.png"));
		  	 airplane3 = ImageIO.read(ShootGame.class.getResource("airplane3.png"));
		  	 airplane4 = ImageIO.read(ShootGame.class.getResource("airplane4.png"));
		  	
			      bee0 = ImageIO.read(ShootGame.class.getResource("bee0.png"));
			    bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			     hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			     hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			     start = ImageIO.read(ShootGame.class.getResource("start.png"));
			     pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			  gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重写画板的方法(该方法来自于JPanel类)
	 * Graphics g:相当于是画笔
	 * 注意:frame.setVisible(true);:会自动调用paint()方法
	 */
	public void paint(Graphics g) {
		//画背景图
		g.drawImage(background, 0, 0, null);
		//画英雄机
		DrawHero(g);
		//画敌机
		DrawFlyingObject(g);
		//画子弹
		DrawBullets(g);
		//画状态
		paintState(g);
		//画英雄生命分数
		DrawLifeAndScore(g);
	}
	
	/**
	 * 画英雄机生命和分数
	 * @param g
	 */
	private void DrawLifeAndScore(Graphics g) {
		g.setColor(Color.gray);
		Font font = new Font("宋体", Font.BOLD, 15);//创建字体类
		g.setFont(font);
		g.drawString("我机生命:"+hero.getLife(),15 ,25);
		g.drawString("我机分数:"+score,15 ,45);
		
	}
	/**
	 * 画游戏状态
	 * @param g
	 */
	private void paintState(Graphics g) {
		switch (state) {
		case START:
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:
			g.drawImage(gameover,0,0,null);
			break;
		}
	}
	/**
	 * 画子弹
	 * @param g
	 */
	public void DrawBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	/**
	 * 画敌机
	 * @param g
	 */
	public void DrawFlyingObject(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
	}
	/**
	 * 画英雄机的方法
	 * @param args
	 */
	public void DrawHero(Graphics g) {

			g.drawImage(hero.image, hero.x, hero.y, null);
			
	}
	
	/**
	 * <随机生成敌人>的方法:方法要求必须要有返回值,会随机返回一个敌人的对象
	 * 可能是小敌机对象,可能是小蜜蜂对象
	 * @param args
	 */
	public FlyingObject nextOne() {
		Random random = new Random();
		//假设现在随机生成一个30以内的整数
		int num = random.nextInt(30);
		if (num < 10) {//如果生成的随机数小于10
			return new Bee();//则生成小蜜蜂对象
		}else {
			return new Airplane();
		}
	}
	
	/**
	 * 定时器的方法
	 * @param args
	 */
	
	public void action() {
		// 创建一个监听器,来监听鼠标的移动
		MouseAdapter m = new MouseAdapter() {
			// 鼠标的移动事件(重写原有的方法)
			public void mouseMoved(MouseEvent e) {// 监听到的鼠标对象为变量e
				if (state == RUNNING) {
					// 通过监听到的鼠标对象(e)来获取鼠标的x和y坐标
					int x = e.getX();
					int y = e.getY();
					// 通过鼠标的坐标值,获得英雄的坐标值
					hero.moveTo(x, y);
				}
			}

			// 鼠标点击事件
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					state = START;
					score = 0;
					hero = new Hero();
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					break;
				}
			}

			/**
			 * 鼠标移入
			 */
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

			/**
			 * 鼠标移出
			 */
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		};
		// 将监听到的鼠标移动事件添加到游戏窗口中
		this.addMouseMotionListener(m);
		// 将监听到的鼠标点击事件,以及鼠标移入事件添加到游戏窗口中
		this.addMouseListener(m);
		// 添加定时器,定时器的类是Timer
		Timer timer = new Timer();
		// TimerTask抽象类:不能直接创建它的对象
		/**
		 * timer.schedule(TimerTask对象, t1, t2); t1:表示的是启动定时器到第一次触发run方法的时间间隔
		 * t2:第二到第三.第三道第四触发的时间间隔
		 */
		timer.schedule(new TimerTask() {// 匿名内部类
					@Override
					// 当启动定时器之后会自动调用该run方法
					public void run() {// 重写TimerTask里面的抽象方法
						// 英雄机图片的切换
						hero.step();

						// 如果当前的状态是RUNNING,才进行如下
						if (state == RUNNING) {
							// 子弹的入场
							ShootAction();
							// 敌机入场(专门写一个敌人的方法,用来扩容敌机数组)
							enterAction();
							// 飞行物走一步的方法
							stepAction();
							// 删除越界飞行物(子弹和敌机)
							outOfBoundsAction();
							// 子弹和敌机的判断
							boomAction();
							// 英雄机
							checkGameOverAction();
							// 在定时器中手动调用重绘的方法
						}
						repaint();
					}

				}, 10, 10);
	}
	
	
	/**
	 * 敌机和英雄机碰撞方法
	 * 	-英雄机的名是否小于0
	 * 		-如果满足,游戏结束
	 * 			-将游戏结束的状态设置为GAME_OVER
	 * 		-如果不满足
	 * 			-删除敌机,英雄机双倍火力清零
	 */
	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER;
		}
	}

	public boolean isGameOver() {
		// 遍历敌机数组判断是否撞上
		for (int i = 0; i < flyings.length; i++) {
			if (hero.hit(flyings[i])) {
				hero.clearDoubleFire();
				hero.substtacLife();
				if (hero.getLife() > 0) {
					// 如果英雄机生命>0,将被撞敌机清除
					FlyingObject t = flyings[i];
					flyings[i] = flyings[flyings.length - 1];
					flyings[flyings.length - 1] = t;
					flyings = Arrays.copyOf(flyings, flyings.length - 1);
				}
			}
		}
		return hero.getLife() <= 0;
	}
	
//	public void attckHero() {
//		for (int i = 0; i < flyings.length; i++) {
//			if (flyings[i].getRect().intersects(hero.getRect())) {
//				hero.substtacLife();
//				hero.clearDoubleFire();
//				FlyingObject fly = flyings[i];
//				flyings[i] = flyings[flyings.length - 1];
//				flyings[flyings.length - 1] = fly;
//				flyings = Arrays.copyOf(flyings, flyings.length - 1);
//			}
//		}
//	}
	
	/**
	 * 子弹和敌机的判断
	 */
	public void boomAction() {
		for (int j = 0; j < bullets.length; j++) {//遍历每一个子弹
			boom(bullets[j], j);
		}
	}
	
	/**
	 * 子弹撞上敌机后,相关业务的逻辑处理方法
	 */
	int score;
	public void boom(Bullet bullet, int j) {// j表示子弹下标
		// 定义一个index变量,用来表示,被子弹撞上后敌机的下标值
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			if (flyings[i].shootBy(bullet)) {
				index = i;// 将被击中的敌机的下标值赋给index变量
				break;
			}
		}
		// 做一个判断,看是否有敌机被击中
		if (index != -1) {
			FlyingObject one = flyings[index];// 找到被撞敌机,newAirplane或者bee
			// 判断被撞机是小敌机还是小蜜蜂
			if (one instanceof Enemy) {
				Enemy e = (Enemy) one;// Enemy e = new Airplane()
				e.getScore();
				score += e.getScore();
			}
			if (one instanceof Award) {
				Award a = (Award) one;
				int type = a.getType();
				switch (type) {
				case Award.SUPER_FIRE:
					hero.addSuperFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				}
			}
			/*
			 * 当敌机被击中之后,敌机和子弹同时消失
			 */
			if (one instanceof Enemy) {
				
//				FlyingObject[] c= new FlyingObject[flyings.length+4];
//
//				System.arraycopy(flyings, 0, c, 0, flyings.length);
//
//				System.arraycopy(Airplane.imgs, 0, c, flyings.length, 4);
				
			}
//			if (one instanceof Award) {
//				
//			}
			FlyingObject t = one;
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);

			Bullet b = bullets[j];// 使用到子弹的下标j
			bullets[j] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = b;
			bullets = Arrays.copyOf(bullets, bullets.length - 1);
		}
	}
	
	
	
	/*
	 *删除越界飞行物 
	 *敌人和子弹
	 */
	public void outOfBoundsAction() {
		/**
		 * index有两层意思
		 * 1.表示的是不越界的飞行物的个数
		 * 2.表示的是存放不越界飞行物的数组的下标值
		 */
		int index = 0;
		FlyingObject[] obj = new FlyingObject[flyings.length];
		// 找flyings数组中,有哪些敌机没有越界
		for (int i = 0; i < flyings.length; i++) {
			// 拿到每一架敌机
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {// 找出不越界的敌人
				// 找到不越界的敌人之后,将敌人放入到新的obj数组中
				obj[index] = f;
				index++;// 下标值加一
			}
		}
		/*
		 * 将新创建的存放不越界飞行物的obj数组,覆盖flyings数组
		 */
		flyings = Arrays.copyOf(obj, index);

		/*
		 * 子弹越界的删除方法
		 */
		int bulletNumber = 0;
		Bullet[] bulletIn = new Bullet[bullets.length];//产生一个数组用来存放不越界的子弹
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletIn[bulletNumber] = b;
				bulletNumber++;// 下标值加一
			}
		}
		bullets = Arrays.copyOf(bulletIn, bulletNumber);//用不越界子弹数组覆盖原来数组
	}
	
	/**
	 * 产生子弹
	 */
	int shootindex = 0;
	public void ShootAction() {
		shootindex++;
		if (shootindex % 20 == 0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);// 扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length);// 扩容后赋值进去
		}
	}
/**
 * <敌机&子弹入场的方法:2,遍历敌机数组>,挨个调用step方法
 */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		//遍历子弹的数组,挨个调用子弹的step
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
	}

	/**
	 * <敌机入场的方法:1,产生敌机> flyingIndex控制敌机的生成频率
	 */
	
	int flyingIndex = 0;
	public void enterAction() {
		flyingIndex++;
		
		if (flyingIndex % 40 == 0) {
			// 1.随机生成一个敌人
			FlyingObject one = nextOne();
			// 2.对敌机数组flying数组进行扩容
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			// 3.将新生成的敌人放到数组中
			flyings[flyings.length - 1] = one;
		}

	}

	public static void main(String[] args) {
		// 创建游戏的窗口对象
		JFrame frame = new JFrame("飞机大战");
		frame.setSize(WIDTH, HEIGHT);
		ShootGame game = new ShootGame();
		frame.add(game);// 将当前类的对象添加到窗口中
		// 设置窗口关闭,程序停止
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 初始窗口位置居中
		frame.setLocationRelativeTo(null);
		// 窗口大小固定
		frame.setResizable(false);
		// 1.设置窗口可见 2.尽快调用paint()(重绘)方法
		frame.setVisible(true);
		// 启动定时器的方法
		game.action();

	}

}
