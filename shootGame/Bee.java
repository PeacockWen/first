package cn.com.ckg.shootGame;

import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author 1 小蜜蜂的类
 */
public class Bee extends FlyingObject implements Award {

	/**
	 * 小蜜蜂自己私有的属性
	 */
	private int xSpeed = 2;
	private int ySpeed = 2;
	private int awardType;// 表示小蜜蜂的奖励类型0或者1

	public Bee() {
		image = ShootGame.bee0;
		width = image.getWidth();
		height = image.getHeight();
		y = 0;// 敌机出现的位置,在屏幕的上方(相当于是自己的高度)
		Random random = new Random();
		x = random.nextInt(ShootGame.WIDTH - this.width);
		awardType = random.nextInt(6);//随机生成一个奖励
	}

	/**
	 * 小蜜蜂移动的方法 1.当小蜜蜂的x坐标值>=游戏屏幕的宽-蜜蜂图片的宽(说明小蜜蜂碰到了最右侧)
	 * 那么像蜜蜂应该向左移动,可以采用xSpeed=-2的方式 2.如果当小蜜蜂的x坐标值<=0(说明小蜜蜂到了最右边)
	 * 那么小蜜蜂应该折返,往屏幕的右边移动,可以把xSpeed的值改回2,实现x坐标的递增
	 */

	@Override
	public void step() {
		if (x >= ShootGame.WIDTH - this.width) {
			xSpeed = -2;
		}
		if (x <= 0) {
			xSpeed = 2;

		}
		x += xSpeed;
		y += ySpeed;

	}

	/**
	 * 获取奖励类型的方法
	 * 
	 * @return
	 */
	@Override
	public int getType() {
		return awardType;
	}

	@Override
	public boolean outOfBounds() {
		
		return this.y>=ShootGame.HEIGHT;
	}


	@Override
	public void explode(Graphics g) {
//		for (int i = 0; i < imagesEX.length; i++) {
//			g.drawImage(imagesEX[i], x, y, null);
//		}
		
	}
}
