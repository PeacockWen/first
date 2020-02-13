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
 * �ɻ���ս��������
 * ��Ϊ��������������Ҫ����Ϸ����
 * JPanel������
 * 
 * @author 1
 *
 */
public class ShootGame extends JPanel{
	private static final long serialVersionUID = 1L;
	//������Ϸ�Ĵ��ڿ�͸�(���óɳ���)
	public static final int WIDTH = 400;//width
	public static final int HEIGHT = 650;//height
	
	public static final int START = 0;//��ʾ����״̬
	public static final int RUNNING = 1;//��ʾ����״̬
	public static final int PAUSE = 2;//��ʾ��ͣ״̬
	public static final int GAME_OVER = 3;//��ʾ��Ϸ����״̬
	
	//����ͼƬ����,��������ǰ�ļ���
	private int state = START;
	public static BufferedImage background;
	public static BufferedImage airplane0;//С�л�
	public static BufferedImage bee0;
	public static BufferedImage bullet;
	public static BufferedImage hero0;//Ӣ�ۻ�0
	public static BufferedImage hero1;//Ӣ�ۻ�1
	public static BufferedImage start;
	public static BufferedImage pause;//��Ϸ��ͣ��ͼƬ
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
	 * ����Ӣ�ۻ�,�л�,�ӵ�����
	 */
	
	private Hero hero = new Hero();
	private FlyingObject[] flyings = {};//�л��������
	private Bullet[] bullets = {};//�ӵ��������
//	private Airplane[] airplanesEX = {};
	
//	public ShootGame() {
//		flyings = new FlyingObject[2];//����ֻ����������
//		flyings[0] = new Airplane(); 
//		flyings[1] = new Bee();
//		bullets = new Bullet[1];
//		bullets[0] = new Bullet();
//		bullets=new Bullet[1];
//    	bullets[0]=new Bullet(100,200);
//	}
	
	/**
	 * ʹ�þ�̬�齫ͼƬ���ؽ���
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
	 * ��д����ķ���(�÷���������JPanel��)
	 * Graphics g:�൱���ǻ���
	 * ע��:frame.setVisible(true);:���Զ�����paint()����
	 */
	public void paint(Graphics g) {
		//������ͼ
		g.drawImage(background, 0, 0, null);
		//��Ӣ�ۻ�
		DrawHero(g);
		//���л�
		DrawFlyingObject(g);
		//���ӵ�
		DrawBullets(g);
		//��״̬
		paintState(g);
		//��Ӣ����������
		DrawLifeAndScore(g);
	}
	
	/**
	 * ��Ӣ�ۻ������ͷ���
	 * @param g
	 */
	private void DrawLifeAndScore(Graphics g) {
		g.setColor(Color.gray);
		Font font = new Font("����", Font.BOLD, 15);//����������
		g.setFont(font);
		g.drawString("�һ�����:"+hero.getLife(),15 ,25);
		g.drawString("�һ�����:"+score,15 ,45);
		
	}
	/**
	 * ����Ϸ״̬
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
	 * ���ӵ�
	 * @param g
	 */
	public void DrawBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	/**
	 * ���л�
	 * @param g
	 */
	public void DrawFlyingObject(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
	}
	/**
	 * ��Ӣ�ۻ��ķ���
	 * @param args
	 */
	public void DrawHero(Graphics g) {

			g.drawImage(hero.image, hero.x, hero.y, null);
			
	}
	
	/**
	 * <������ɵ���>�ķ���:����Ҫ�����Ҫ�з���ֵ,���������һ�����˵Ķ���
	 * ������С�л�����,������С�۷����
	 * @param args
	 */
	public FlyingObject nextOne() {
		Random random = new Random();
		//���������������һ��30���ڵ�����
		int num = random.nextInt(30);
		if (num < 10) {//������ɵ������С��10
			return new Bee();//������С�۷����
		}else {
			return new Airplane();
		}
	}
	
	/**
	 * ��ʱ���ķ���
	 * @param args
	 */
	
	public void action() {
		// ����һ��������,�����������ƶ�
		MouseAdapter m = new MouseAdapter() {
			// �����ƶ��¼�(��дԭ�еķ���)
			public void mouseMoved(MouseEvent e) {// ��������������Ϊ����e
				if (state == RUNNING) {
					// ͨ����������������(e)����ȡ����x��y����
					int x = e.getX();
					int y = e.getY();
					// ͨ����������ֵ,���Ӣ�۵�����ֵ
					hero.moveTo(x, y);
				}
			}

			// ������¼�
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
			 * �������
			 */
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

			/**
			 * ����Ƴ�
			 */
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		};
		// ��������������ƶ��¼���ӵ���Ϸ������
		this.addMouseMotionListener(m);
		// ����������������¼�,�Լ���������¼���ӵ���Ϸ������
		this.addMouseListener(m);
		// ��Ӷ�ʱ��,��ʱ��������Timer
		Timer timer = new Timer();
		// TimerTask������:����ֱ�Ӵ������Ķ���
		/**
		 * timer.schedule(TimerTask����, t1, t2); t1:��ʾ����������ʱ������һ�δ���run������ʱ����
		 * t2:�ڶ�������.���������Ĵ�����ʱ����
		 */
		timer.schedule(new TimerTask() {// �����ڲ���
					@Override
					// ��������ʱ��֮����Զ����ø�run����
					public void run() {// ��дTimerTask����ĳ��󷽷�
						// Ӣ�ۻ�ͼƬ���л�
						hero.step();

						// �����ǰ��״̬��RUNNING,�Ž�������
						if (state == RUNNING) {
							// �ӵ����볡
							ShootAction();
							// �л��볡(ר��дһ�����˵ķ���,�������ݵл�����)
							enterAction();
							// ��������һ���ķ���
							stepAction();
							// ɾ��Խ�������(�ӵ��͵л�)
							outOfBoundsAction();
							// �ӵ��͵л����ж�
							boomAction();
							// Ӣ�ۻ�
							checkGameOverAction();
							// �ڶ�ʱ�����ֶ������ػ�ķ���
						}
						repaint();
					}

				}, 10, 10);
	}
	
	
	/**
	 * �л���Ӣ�ۻ���ײ����
	 * 	-Ӣ�ۻ������Ƿ�С��0
	 * 		-�������,��Ϸ����
	 * 			-����Ϸ������״̬����ΪGAME_OVER
	 * 		-���������
	 * 			-ɾ���л�,Ӣ�ۻ�˫����������
	 */
	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER;
		}
	}

	public boolean isGameOver() {
		// �����л������ж��Ƿ�ײ��
		for (int i = 0; i < flyings.length; i++) {
			if (hero.hit(flyings[i])) {
				hero.clearDoubleFire();
				hero.substtacLife();
				if (hero.getLife() > 0) {
					// ���Ӣ�ۻ�����>0,����ײ�л����
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
	 * �ӵ��͵л����ж�
	 */
	public void boomAction() {
		for (int j = 0; j < bullets.length; j++) {//����ÿһ���ӵ�
			boom(bullets[j], j);
		}
	}
	
	/**
	 * �ӵ�ײ�ϵл���,���ҵ����߼�������
	 */
	int score;
	public void boom(Bullet bullet, int j) {// j��ʾ�ӵ��±�
		// ����һ��index����,������ʾ,���ӵ�ײ�Ϻ�л����±�ֵ
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			if (flyings[i].shootBy(bullet)) {
				index = i;// �������еĵл����±�ֵ����index����
				break;
			}
		}
		// ��һ���ж�,���Ƿ��ел�������
		if (index != -1) {
			FlyingObject one = flyings[index];// �ҵ���ײ�л�,newAirplane����bee
			// �жϱ�ײ����С�л�����С�۷�
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
			 * ���л�������֮��,�л����ӵ�ͬʱ��ʧ
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

			Bullet b = bullets[j];// ʹ�õ��ӵ����±�j
			bullets[j] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = b;
			bullets = Arrays.copyOf(bullets, bullets.length - 1);
		}
	}
	
	
	
	/*
	 *ɾ��Խ������� 
	 *���˺��ӵ�
	 */
	public void outOfBoundsAction() {
		/**
		 * index��������˼
		 * 1.��ʾ���ǲ�Խ��ķ�����ĸ���
		 * 2.��ʾ���Ǵ�Ų�Խ��������������±�ֵ
		 */
		int index = 0;
		FlyingObject[] obj = new FlyingObject[flyings.length];
		// ��flyings������,����Щ�л�û��Խ��
		for (int i = 0; i < flyings.length; i++) {
			// �õ�ÿһ�ܵл�
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {// �ҳ���Խ��ĵ���
				// �ҵ���Խ��ĵ���֮��,�����˷��뵽�µ�obj������
				obj[index] = f;
				index++;// �±�ֵ��һ
			}
		}
		/*
		 * ���´����Ĵ�Ų�Խ��������obj����,����flyings����
		 */
		flyings = Arrays.copyOf(obj, index);

		/*
		 * �ӵ�Խ���ɾ������
		 */
		int bulletNumber = 0;
		Bullet[] bulletIn = new Bullet[bullets.length];//����һ������������Ų�Խ����ӵ�
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletIn[bulletNumber] = b;
				bulletNumber++;// �±�ֵ��һ
			}
		}
		bullets = Arrays.copyOf(bulletIn, bulletNumber);//�ò�Խ���ӵ����鸲��ԭ������
	}
	
	/**
	 * �����ӵ�
	 */
	int shootindex = 0;
	public void ShootAction() {
		shootindex++;
		if (shootindex % 20 == 0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);// ����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length);// ���ݺ�ֵ��ȥ
		}
	}
/**
 * <�л�&�ӵ��볡�ķ���:2,�����л�����>,��������step����
 */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		//�����ӵ�������,���������ӵ���step
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
	}

	/**
	 * <�л��볡�ķ���:1,�����л�> flyingIndex���Ƶл�������Ƶ��
	 */
	
	int flyingIndex = 0;
	public void enterAction() {
		flyingIndex++;
		
		if (flyingIndex % 40 == 0) {
			// 1.�������һ������
			FlyingObject one = nextOne();
			// 2.�Եл�����flying�����������
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			// 3.�������ɵĵ��˷ŵ�������
			flyings[flyings.length - 1] = one;
		}

	}

	public static void main(String[] args) {
		// ������Ϸ�Ĵ��ڶ���
		JFrame frame = new JFrame("�ɻ���ս");
		frame.setSize(WIDTH, HEIGHT);
		ShootGame game = new ShootGame();
		frame.add(game);// ����ǰ��Ķ�����ӵ�������
		// ���ô��ڹر�,����ֹͣ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ʼ����λ�þ���
		frame.setLocationRelativeTo(null);
		// ���ڴ�С�̶�
		frame.setResizable(false);
		// 1.���ô��ڿɼ� 2.�������paint()(�ػ�)����
		frame.setVisible(true);
		// ������ʱ���ķ���
		game.action();

	}

}
