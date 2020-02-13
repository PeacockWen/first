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
 * �ɻ���ս��������
 * ��Ϊ��������������Ҫ����Ϸ����
 * JPanel������
 */
public class ShootGame extends JPanel{
	private static final long serialVersionUID = 1L;
	//������Ϸ�Ĵ��ڿ�͸�(���óɳ���)
	public static final int WIDTH=400;//width ��Ϸ���ڵĿ�
	public static final int HEIGHT=650;//height ��Ϸ���ڵĸ�
	
	//����ͼƬ��������������ǰ�ļ���
	public static BufferedImage background;//��Ϸ�ı���ͼ
	public static BufferedImage airplane;//С�л���ͼƬ
	public static BufferedImage bee;//С�۷��ͼƬ
	public static BufferedImage bullet;//�ӵ���ͼƬ
	public static BufferedImage hero0;//Ӣ�ۻ�0
	public static BufferedImage hero1;//Ӣ�ۻ�1
	public static BufferedImage start;//��Ϸ��ʼ��ͼƬ
	public static BufferedImage pause;//��Ϸ��ͣ��ͼƬ
	public static BufferedImage gameover;//��Ϸ������ͼƬ
	
	/**
	 * ����Ӣ�ۻ����л����ӵ�����
	 */
	private Hero hero=new Hero();
	private FlyingObject[] flyings={};//�����������
    private Bullet[] bullets={};//�ӵ��������
    
    public ShootGame(){
    	flyings=new FlyingObject[2];//����ֻ����������
    	flyings[0]=new Airplane();
    	flyings[1]=new Bee();
    	bullets=new Bullet[1];
    	bullets[0]=new Bullet(100,200);
    }
    
	/**
	 * ʹ�þ�̬�齫ͼƬ���ؽ���
	 */
	static{
		try {
			/*
			 * ͨ��ImageIO��ȥ��ȡ��ǰShootGame�����·���µ�.pngͼƬ
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
	 * ��д�滭�ķ���(�÷���������JPanel��)
	 * Graphics g���൱���ǻ���
	 * ע��:frame.setVisible(true):���Զ����ø�paint()����
	 */
	public void paint(Graphics g){
		//������ͼ
		g.drawImage(background,0,0,null);
		//��Ӣ�ۻ�
		DrawHero(g);
		//���л�
		DrawFlyingObject(g);
		//���ӵ�
		DrawBullets(g);
	}
	/**
	 * ��Ӣ�ۻ��ķ���
	 */
	public void DrawHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/**
	 * ���л��ķ���
	 */
	public void DrawFlyingObject(Graphics g){
		for(int i=0;i<flyings.length;i++){
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
		
	}
	/**
	 * ���ӵ��ķ���
	 */
	public void DrawBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	
	/**
	 * ������ɵ��˵ķ�������������Ҫ�з���ֵ�ģ�
	 * ��Ϊ���ø÷���֮�󣬻�����ķ���һ�����˵Ķ���
	 * ������С�л�����Ҳ������С�۷����
	 */
	public FlyingObject nextOne(){
		Random rand=new Random();
		//���������������һ��30���ڵ�����
		int num=rand.nextInt(30);
		if(num<10){//������ɵ���������С��10
			return new Bee();//�򷵻�С�۷�Ķ���
		}else{
			return new Airplane();
		}
	}
	/**
	 * ��ʱ���ķ���:
	 */
	
	public void action(){
		//����һ���������������������ƶ�(�����˺ܶ෽��)
		MouseAdapter m=new MouseAdapter(){
			//�����ƶ��¼�(��дԭ�еķ���)
			public void mouseMoved(MouseEvent e){//��������������Ϊ����e
				//ͨ����������������(e)����ȡ����x��y����
				int x=e.getX();
				int y=e.getY();
				//ͨ����������ֵ�����Ӣ�ۻ�������ֵ
				hero.moveTo(x, y);
			}
		};
		//��������������ƶ��¼���ӵ��������Ϸ������
		this.addMouseMotionListener(m);
		//��Ӷ�ʱ�� ��ʱ��������Timer
		Timer time=new Timer();
		/**
		 * time.schedule(TimerTask����,long t1,long t2)
		 * t1:��ʾ����������ʱ������һ�δ���run������ʱ����������ֵ��
		 * t2:��ʾ�ڶ��δ����������δ�����ʱ����
		 *    �����δ��������Ĵδ�����ʱ����
		 *    ���Ĵδ���������δ�����ʱ����
		 *    ....
		 */
		//�����ࣺ����ֱ�Ӵ������Ķ���
		/*
		 * �����ڲ���
		 */
		time.schedule(new TimerTask(){
			@Override//��дTimerTask����ĳ��󷽷�
			//��������ʱ��֮�󣬻��Զ����ø�run����
			public void run() {
				//Ӣ�ۻ�ͼƬ���л�
				hero.step();
				//��ӵл����볡(ר��дһ�����˵��볡�����������ݵл�����)
				enterAction();
				//��������һ���ķ���
				stepAction();
				//����ӵ��볡
				shootAction();
				//ɾ��Խ��ķ�����
				outOfBoundsAction();
				//�ڶ�ʱ�������ֶ������ػ�ķ���ʱʱ�̿����»�һ��paint����Ķ���
				repaint();
			}
		}, 10, 10);
	}
	
	/**
	 * ɾ��Խ��ķ�����
	 * �л����ӵ�
	 */
	public void outOfBoundsAction(){
		/*
		 * index��������˼��
		 * 1.��ʾ���ǲ�Խ��ķ�����ĸ���
		 * 2.��ʾ���Ǵ�Ų�Խ��ķ�����������±�ֵ
		 */
		int index=0;
		//����һ�������飬����ר�Ŵ�Ų�Խ��ĵ���,����ĳ��Ⱥ�flyings�ĳ���һ��
		FlyingObject[] obj=new FlyingObject[flyings.length];
		//��flyings�����У�����Щ�л�û��Խ��
		for(int i=0;i<flyings.length;i++){
			//�õ�ÿһ�ܵл�
			FlyingObject f=flyings[i];
			if(!f.outOfBounds()){//�ҳ���Խ��ĵ���
				//�ҵ���Խ��ĵ���֮�󣬽����˷��뵽�µ�obj������
				obj[index]=f;
				index++;//�±�ֵ��1
			}
		}
		/*
		 * ���´����Ĵ�Ų�Խ��ķ������obj���鸲�ǵ�ԭ����flyings����
		 */
		flyings=Arrays.copyOf(obj, index);
		
		/*
		 * ɾ��Խ����ӵ�
		 */
	}
	
	/**
	 * �л��볡�ķ���
	 * ���Ƶл�������Ƶ��
	 */
	int flyingIndex=0;//�������Ƶл�����Ƶ�ʵ���ֵ
	public void enterAction(){
		flyingIndex++;
		if(flyingIndex%30==0){
			//1.�������һ������
			FlyingObject one=nextOne();
			//2.�Եл�����flyings��������
			flyings=Arrays.copyOf(flyings, flyings.length+1);
			//3.�������ɵĵл����뵽������
			flyings[flyings.length-1]=one;
		}
		
	}
	/**
	 * ��������һ���ķ���
	 */
	public void stepAction(){
		//�����л����飬Ȼ�󰤸��ĵ���step����
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		//�����ӵ������飬Ȼ�󰤸��ĵ����ӵ���step
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
	}
	
	/**
	 * �ӵ�����ķ���
	 */
	int shootIndex=0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex%20==0){
			//����Ӣ�ۻ������ӵ��ķ���
			Bullet[] bs=hero.shoot();
			//���ӵ��������������,����ʵ�ʵ�bs����ĳ������������ݶ���
			bullets=Arrays.copyOf(bullets, bullets.length+bs.length);
			/*
			 *�����if-else�ж���һ�����õĵط���
			 * ������ı���������6���������߸���Ļ�������ôif-else�ṹ��Ƚ��߳� 
			 */
			/*if(bs.length==1){//���shoot�������ص���һ���ӵ�
				bullets[bullets.length-1]=bs[0];
			}else{//������������ӵ�
				bullets[bullets.length-1]=bs[0];
				bullets[bullets.length-2]=bs[1];
			}*/
			/*
			 * ��ʾ��bs���鿪ʼ���ƣ�0��ʾ��bs��������±�Ϊ0��λ�ÿ�ʼ���ƣ�
			 * ���Ƶ�bullets�����У����Ƶ�λ�ô�bullets����ĳ���-bs����ĳ��ȿ�ʼ���ƣ�
			 * ����bs.length��Ԫ��
			 */
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
		}
		
	}
	public static void main(String[] args) {
    	//������Ϸ�Ĵ��ڶ���
    	JFrame frame=new JFrame("�ɻ���ս");
    	//ͨ�����ڶ������ô��ڵĴ�С��ʹ�ó���
    	frame.setSize(WIDTH, HEIGHT);
    	ShootGame game=new ShootGame();
    	frame.add(game);//����ǰ��Ķ�����ӵ�������
    	//���ùر���Ϸ����������������
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//������Ϸ���ھ���
    	frame.setLocationRelativeTo(null);
    	//���ô��ڲ��ܱ��Ŵ�
    	frame.setResizable(false);
    	frame.setVisible(true);//1.���ô��ڿɼ�  2.����ĵ���paint()(�ػ�)����
    	game.action();//������ʱ���ķ���
	}
}















