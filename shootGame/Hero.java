package cn.com.ckg.shootGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {

	/**
	 * 添加英雄机自己私有的属性
	 */
	private int life;// 英雄机的声明值
	private int doubleFire;// 英雄机的双倍火力值
	private int superFire;// 超级火力
	private BufferedImage[] images;// 用来存放两个英雄机图片的数组
	private BufferedImage[] imagesEX;
	private int index;// 用来辅助英雄机切换图片的下标

	public Hero() {
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 450;
		life = 3;
		doubleFire = 50;// 默认英雄机出场有50发的双倍火力值
//		superFire = 50;
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 };//
		imagesEX = new BufferedImage[] { ShootGame.hero2, ShootGame.hero3,
				ShootGame.hero4, ShootGame.hero5 };//
		// 两张图切换
		// 默认下标是0
		index = 0;

	}

	@Override
	public void step() {
		// 英雄机的移动:只跟鼠标有关系

		// 该方法,可以用来做英雄机图片的切换
		/**
		 * 因为英雄机的图片是hero0和hero1两张,辅助的index要能满足在0和1之间切换 1.index值递增 2.index取余数组的长度
		 */

		image = images[index++ % images.length];

	}

	/**
	 * 英雄机随鼠标移动的方法
	 * 
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y) {
		/*
		 * 通过传入的鼠标的x坐标和y坐标来计算英雄机的坐标
		 */
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	/**
	 * 英雄机发射子弹的方法
	 * 
	 * @return
	 */
	public Bullet[] shoot() {
		//
		int xStep = this.width / 4;// 四分之一的英雄宽
		int yStep = 20;// 表示英雄机的固定y值
		// 判断何时发射双倍火力值
		if (doubleFire > 0) {// 发射双倍火力
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x + xStep - 11, this.y - yStep + 60);// 左子弹
			bs[1] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60);// 右发子弹
			// 发射了双倍火力之后
			doubleFire -= 2;
			return bs;
		} else if (superFire > 0) {
			Bullet[] bs = new Bullet[5];
			bs[0] = new Bullet(this.x + 2 * xStep - 2, this.y - yStep + 15);// 中子弹
			bs[1] = new Bullet(this.x + xStep - 11, this.y - yStep + 60, 45);// 左子弹
			bs[2] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60, 90);// 右子弹
			bs[3] = new Bullet(this.x + xStep - 11, this.y - yStep + 60);// 第一发子弹
			bs[4] = new Bullet(this.x + 3 * xStep + 5, this.y - yStep + 60);// 第二发子弹
			
			superFire -= 4;
			return bs;
		} else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x + 2 * xStep - 2, this.y - yStep + 15);// 第一发子弹
			return bs;
		}

	}

	/**
	 * 英雄机永不越界
	 */
	@Override
	public boolean outOfBounds() {
		return false;
	}

	/**
	 * 英雄机碰撞
	 */
	public boolean hit(FlyingObject f) {
		//以英雄机中心点为参照物
		//找到中心点的x和y坐标
		int x0 = this.x+this.width/2;
		int y0 = this.y+this.height/2;
		
		int x1 = f.x - this.width/2;// 找到撞击区域左上角的点x坐标
		int y1 = f.y - this.height/2;// 找到撞击区域左上角y坐标

		int x2 = f.x + f.width + this.width/2;// 右上角x
		int y2 = f.y + f.height + this.height/2;// 左下角y

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
	 * 奖励生命
	 */
	public void addLife() {
		life++;
	}

	/**
	 * 奖励超级火力值
	 */
	public void addSuperFire() {
		superFire += 30;
	}

	/**
	 * 奖励超级火力值
	 */
	public void addDoubleFire() {
		doubleFire += 20;
	}
	/**
	 * 撞机了
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
