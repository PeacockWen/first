package cn.kgc.shootGame;

import java.util.Random;

/**
 * @author Administrator
 * 小敌机的类
 */
public class Airplane extends FlyingObject implements Enemy{
	/**
	 * 小敌机自己私有的属性
	 */
	private int speed=2;//表示小敌机下落的速度
	/**
	 * 使用构造方法进行初始化
	 */
	public Airplane(){
		image=ShootGame.airplane;//初始化飞机的图片
		//可以通过image得到该飞机的宽和高
		width=image.getWidth();
		height=image.getHeight();
		//设置小敌机的出场位置
		y=-this.height;//敌机出现的位置，在屏幕的上方(相当于是自己的高度)
		/*
		 * x的坐标是有范围的：0~屏幕的宽-飞机的宽
		 * 而这个坐标值是随机生成的：生成随机数
		 * Math.random()*(屏幕的宽-飞机的宽)
		 * 或者
		 * Random rand=new Random();
		 * rand.nextInt(int num); 表示随机生成0~num之间的整数
		 */
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		
	}
	
	
	/**
	 * 表示小敌机移动的方法
	 */
	@Override
	public void step() {
		//小敌机下落的坐标值和y有关系(相当于移动到下一个y坐标值=当前的y坐标值+移动的速度)
		y+=speed;//小敌机往下落，y的值是越来越大的
	}
	/**
	 * 打掉小敌机获取固定的分值
	 */
	@Override
	public int getScore() {
		return 3;
	}

    /**
     * 小敌机是否越界的方法
     */
	@Override
	public boolean outOfBounds() {
		/*
		 * 小敌机的y坐标如果大于等于游戏屏幕的高，则说明越界了
		 */
		return this.y>=ShootGame.HEIGHT;
	}
     
}
