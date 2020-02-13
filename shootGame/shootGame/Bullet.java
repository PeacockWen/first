package cn.kgc.shootGame;
/**
 * @author Administrator
 * 子弹类
 */
public class Bullet extends FlyingObject{
    /**
     * 添加自己私有的属性
     */
	private int speed=3;//子弹的速度
	
	/**
	 * 因为子弹的左边是根据英雄机的坐标来变化的，所以我们用有参构造来进行初始化
	 */
	public Bullet(int x,int y){
		image=ShootGame.bullet;//初始化飞机的图片
		//可以通过image得到该飞机的宽和高
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void step() {
		y-=speed;
	}

    /**
     * 子弹是否越界的方法
     */
	@Override
	public boolean outOfBounds() {
		/*
		 * 满足子弹的y坐标小于等于负的本身的图片的高
		 */
		return this.y<=-this.height;
	}

}
