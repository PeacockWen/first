package cn.com.ckg.shootGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


/**
 * 
 * @author 1 С�л�����
 */
public class Airplane extends FlyingObject implements Enemy {
	/**
	 * С�л��Լ�˽�е�����
	 */

	private int speed = 2;// С�л�������ٶ�
	private int score = 0;
	static Image[] imgs = { ShootGame.airplane1, ShootGame.airplane2, ShootGame.airplane3, ShootGame.airplane4 };
	
	public Airplane() {
		image = ShootGame.airplane0;// ��ʼ���ɻ���ͼƬ
		// ����ͨ��image�õ��÷ɻ��Ŀ�͸�
		width = image.getWidth();
		height = image.getHeight();

		y = 0;// �л����ֵ�λ��,����Ļ���Ϸ�(�൱�����Լ��ĸ߶�)
		/**
		 * x���������з�Χ��:0~��Ļ�Ŀ��-�ɻ��Ŀ�� ���������ֵ��������ɵ�:��������� Math.random()*(��Ļ�Ŀ�-�ɻ��Ŀ�)
		 * ���� Random random = new Random(); random.nextInt(int
		 * num);��ʾ�������0~num֮�������
		 */
		x = new Random().nextInt(ShootGame.WIDTH - this.width);
	}

	/**
	 * ��ʾ�л��ƶ��ķ���
	 */
	@Override
	public void step() {
		// С�л����������ֵ��y�й�ϵ(�൱���ƶ�����һ������ֵ=��ǰ��y����ֵ+�ƶ����ٶ�)
		y += speed;// С�л�������,y��ֵ��Խ��Խ���

	}

	/**
	 * ���С�л���ȡһ���̶���ֵ
	 */
	@Override
	public int getScore() {
		return 3;
	}

	/**
	 * С�л��Ƿ�Խ��ķ���
	 */
	@Override
	public boolean outOfBounds() {
		/*
		 * С�л���y����,������ڵ�����Ϸ��Ļ�ĸ�,��˵��Խ����
		 */
		return this.y >= ShootGame.HEIGHT;
	}

	int count = 1;
	@Override
	public void explode(Graphics g) {
		// TODO Auto-generated method stub
		if (count<=4) {
			g.drawImage(imgs[count], x, y, null);
			count++;
		}
	}
	
	

}
