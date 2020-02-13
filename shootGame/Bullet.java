package cn.com.ckg.shootGame;

import java.awt.Graphics;

/**
 * 子弹类
 * 
 * @author 1
 *
 */

public class Bullet extends FlyingObject {

	/**
	 * 添加子弹私有属性
	 */
	private int speed = 5;// 子弹的速度
	private int degree;

	/**
	 * 因为子弹的坐标是根据英雄机的坐标来变化额,所以我们用有参构造来进行初始化
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
			// double degree = Math.random() * Math.PI * 2;// 炮弹角度
			x -= speed * Math.cos(degree);
			y -= speed * Math.sin(135 - degree);
		}

	}

	/**
	 * 子弹是否越界的方法
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
