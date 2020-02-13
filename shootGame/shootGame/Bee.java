package cn.kgc.shootGame;

import java.util.Random;

/**
 * @author Administrator
 * 小蜜蜂的类
 */
public class Bee extends FlyingObject implements Award{
    /**
     * 小蜜蜂自己私有的属性
     */
	private int xSpeed=2;//x坐标的移动速度
	private int ySpeed=2;//y坐标的移动速度
	/*
	 * 0表示获取双倍火力值
	 * 1表示获取生命值 
	 */
	private int awardType;//表示小蜜蜂的奖励类型0或者1
	
	public Bee(){
		image=ShootGame.bee;
		width=image.getWidth();
		height=image.getHeight();
		y=-this.height;
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
	}
	
	
	/**
	 * 小蜜蜂移动的方法
	 * 做出判断：
	 * 1.如果当小蜜蜂的x坐标值>=游戏的屏幕宽-小蜜蜂的图片的宽(说明小蜜蜂碰到了游戏屏幕的最右侧)
	 *   那么小蜜蜂应该折回往屏幕左边移动，此时可以采用将xSpeed=-2的方式，来实现x坐标的递减
	 * 2.如果当小蜜蜂的x坐标值<=0(说明小蜜蜂碰到了游戏屏幕的最左边)
	 *   那么小蜜蜂应该折回往屏幕的右边移动，此时我们可以将xSpeed的值改回2，来实现x坐标的递增
	 */
	@Override
	public void step() {
		if(x>=ShootGame.WIDTH-this.width){
			xSpeed=-2;
		}
		if(x<=0){
			xSpeed=2;
		}
		x+=xSpeed;
		y+=ySpeed;
	}
	/**
	 * 获取奖励类型的方法
	 */
	@Override
	public int getType() {
		return awardType;
	}

	@Override
	public boolean outOfBounds() {
		return this.y>=ShootGame.HEIGHT;
	}

}
