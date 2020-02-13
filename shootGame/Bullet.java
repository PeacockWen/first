package cn.com.ckg.shootGame;

import java.awt.Graphics;

/**
 * �ӵ���
 * 
 * @author 1
 *
 */

public class Bullet extends FlyingObject {

	/**
	 * ����ӵ�˽������
	 */
	private int speed = 5;// �ӵ����ٶ�
	private int degree;

	/**
	 * ��Ϊ�ӵ��������Ǹ���Ӣ�ۻ����������仯��,�����������вι��������г�ʼ��
	 */
	public Bullet(int x, int y) {
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;

	}

	public Bullet(int x, int y, int degree) {
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
		this.degree = degree;
	}


	@Override
	public void step() {
		if (degree == 0) {
			y -= speed;
		} else {
			// double degree = Math.random() * Math.PI * 2;// �ڵ��Ƕ�
			x -= speed * Math.cos(degree);
			y -= speed * Math.sin(135 - degree);
		}

	}

	/**
	 * �ӵ��Ƿ�Խ��ķ���
	 */
	@Override
	public boolean outOfBounds() {
		
		return this.y<=this.height;
	}

	@Override
	public void explode(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	 

}
