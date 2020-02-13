package cn.kgc.shootGame;

import java.awt.image.BufferedImage;

/**
 * @author Administrator
 * 英雄机的类
 */
public class Hero extends FlyingObject{
    /**
     * 添加英雄机自己私有的属性
     */
	private int life;//英雄机的生命值
	private int doubleFire;//英雄机的双倍火力值
	private BufferedImage[] images;//用来存放两个英雄机图片的数组
	private int index;//用来辅助英雄机图片切换的下标
	
	public Hero(){
		image=ShootGame.hero0;//初始化英雄机的图片
		//可以通过image得到该英雄机的宽和高
		width=image.getWidth();
		height=image.getHeight();
		//英雄的起始坐标是固定的
		x=150;
		y=400;
		life=3;
		doubleFire=100;//默认英雄机出场就有100发的双倍火力值
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		//默认下标为0
		index=0;
	}
	
	
	
	@Override
	public void step() {
		//英雄机的移动：只跟鼠标有关系
		//该方法可以用来实现英雄机图片的切换
		/**
		 * 因为英雄机的图片是hero0和hero1两张，所以我们辅助的index必须满足在0和1之间来回切换
		 * 已知固定长度是2,如何通过index控制0和1
		 * 1.index值递增
		 * 2.index取余2
		 *  例如：0取余2---0
		 *       1取余2---1
		 *       2取余2---0
		 *       3取余2---1 
		 *       
		 */
		image=images[index++%images.length];
	}
	
	/**
	 * @param x :表示传入的鼠标的x坐标
	 * @param y :表示传入的鼠标的y坐标
	 * 英雄机随着鼠标移动的方法
	 * 
	 */
	public void moveTo(int x,int y){
		/*
		 *通过传入的鼠标的x和y坐标来计算出英雄机的坐标
		 */
		this.x=x-this.width/2;
		this.y=y-this.height/2;
		
	}
	
	/**
	 * @return
	 * 英雄机发射子弹的方法
	 */
	public Bullet[] shoot(){
		//
		int xStep=this.width/4;//表示4分之1的英雄机的宽
		int yStep=20;//表示子弹距离英雄机的固定y值
		//判断何时发射双倍火力值
		if(doubleFire>0){//发射双倍火力
			Bullet[] bs=new Bullet[2];
			bs[0]=new Bullet(this.x+xStep,this.y-yStep);//第一颗子弹的坐标
			bs[1]=new Bullet(this.x+3*xStep,this.y-yStep);//第二颗子弹的坐标
			//发射了双倍火力之后，相对应的火力值减少2
			doubleFire-=2;
			return bs;
		}else{
			Bullet[] bs=new Bullet[1];
			bs[0]=new Bullet(this.x+2*xStep,this.y-yStep);
			return bs;
		}
		
		
	}


    /**
     * 英雄机是否越界的方法
     */
	@Override
	public boolean outOfBounds() {
		return false;//永不越界
	}
	
	
	
	
	
	

}
