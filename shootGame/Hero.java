package cn.com.ckg.shootGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {

	/**
	 * ���Ӣ�ۻ��Լ�˽�е�����
	 */
	private int life;// Ӣ�ۻ�������ֵ
	private int doubleFire;// Ӣ�ۻ���˫������ֵ
	private int superFire;// ��������
	private BufferedImage[] images;// �����������Ӣ�ۻ�ͼƬ������
	private BufferedImage[] imagesEX;
	private int index;// ��������Ӣ�ۻ��л�ͼƬ���±�

	public Hero() {
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 450;
		life = 3;
		doubleFire = 50;// Ĭ��Ӣ�ۻ�������50����˫������ֵ
//		superFire = 50;
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 };//
		imagesEX = new BufferedImage[] { ShootGame.hero2, ShootGame.hero3,
				ShootGame.hero4, ShootGame.hero5 };//
		// ����ͼ�л�
		// Ĭ���±���0
		index = 0;

	}

	@Override
	public void step() {
		// Ӣ�ۻ����ƶ�:ֻ������й�ϵ

		// �÷���,����������Ӣ�ۻ�ͼƬ���л�
		/**
		 * ��ΪӢ�ۻ���ͼƬ��hero0��hero1����,������indexҪ��������0��1֮���л� 1.indexֵ���� 2.indexȡ������ĳ���
		 */

		image = images[index++ % images.length];

	}

	/**
	 * Ӣ�ۻ�������ƶ��ķ���
	 * 
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y) {
		/*
		 * ͨ�����������x�����y����������Ӣ�ۻ�������
		 */
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	/**
	 * Ӣ�ۻ������ӵ��ķ���
	 * 
	 * @return
	 */
	public Bullet[] shoot() {
		//
		int xStep = this.width / 4;// �ķ�֮һ��Ӣ�ۿ�
		int yStep = 20;// ��ʾӢ�ۻ��Ĺ̶�yֵ
		// �жϺ�ʱ����˫������ֵ
		if (doubleFire > 0) {// ����˫������
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x + xStep - 11, this.y - yStep + 60);// ���ӵ�
			bs[1] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60);// �ҷ��ӵ�
			// ������˫������֮��
			doubleFire -= 2;
			return bs;
		} else if (superFire > 0) {
			Bullet[] bs = new Bullet[5];
			bs[0] = new Bullet(this.x + 2 * xStep - 2, this.y - yStep + 15);// ���ӵ�
			bs[1] = new Bullet(this.x + xStep - 11, this.y - yStep + 60, 45);// ���ӵ�
			bs[2] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60, 90);// ���ӵ�
			bs[3] = new Bullet(this.x + xStep - 11, this.y - yStep + 60);// ��һ���ӵ�
			bs[4] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60);// �ڶ����ӵ�
			
			superFire -= 4;
			return bs;
		} else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x + 2 * xStep - 2, this.y - yStep + 15);// ��һ���ӵ�
			return bs;
		}

	}

	/**
	 * Ӣ�ۻ�����Խ��
	 */
	@Override
	public boolean outOfBounds() {
		return false;
	}

	/**
	 * Ӣ�ۻ���ײ
	 */
	public boolean hit(FlyingObject f) {
		//��Ӣ�ۻ����ĵ�Ϊ������
		//�ҵ����ĵ��x��y����
		int x0 = this.x+this.width/2;
		int y0 = this.y+this.height/2;
		
		int x1 = f.x - this.width/2;// �ҵ�ײ���������Ͻǵĵ�x����
		int y1 = f.y - this.height/2;// �ҵ�ײ���������Ͻ�y����

		int x2 = f.x + f.width + this.width/2;// ���Ͻ�x
		int y2 = f.y + f.height + this.height/2;// ���½�y

		return x0 > x1 && x0 < x2 && y0 > y1 && y0 <y2;

	}

	int count = 1;

	@Override
	public void explode(Graphics g) {
		if (count < 6) {
			g.drawImage(imagesEX[count], this.x, this.y, null);
			count++;
		}
	}

	/**
	 * ��������
	 */
	public void addLife() {
		life++;
	}

	/**
	 * ������������ֵ
	 */
	public void addSuperFire() {
		superFire += 30;
	}

	/**
	 * ������������ֵ
	 */
	public void addDoubleFire() {
		doubleFire += 20;
	}
	/**
	 * ײ����
	 */
	public void substtacLife() {
		life--;
	}
	
	public int getLife() {
		return life;
	}

	public void clearDoubleFire() {
		doubleFire = 0;
		superFire = 0;
	}

}
