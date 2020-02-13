package cn.kgc.shootGame;

import java.awt.image.BufferedImage;

/**
 * @author Administrator
 * 飞行物的类：表示所有飞行物的父类
 */
public abstract class FlyingObject {
    public int x;//表示x坐标
    public int y;//表示y坐标
    public BufferedImage image;//专门存放图片的类型
    public int width;//图片的宽
    public int height;//图片的高
    
    /*
     * 所有界面上的飞行物走一步的方法
     */
    public abstract void step();
    /*
     * 判断飞行物是否越界
     */
    public abstract boolean outOfBounds();
    
    /**
     * 验证子弹和敌机是否撞上
     * 如果返回true，则代表撞上了
     */
    public boolean shootBy(Bullet bullet){
    	int x1=this.x;//当前敌机的左上角的x坐标
    	int y1=this.y;//当前敌机的左上角的y坐标
    	int x2=this.x+this.width;//当前敌机右上角的x坐标
    	int y2=this.y+this.height;//当前敌机左下角的y坐标
    	int x0=bullet.x;//子弹的x坐标
    	int y0=bullet.y;//子弹的y坐标
    	return x0>x1 && x0<x2 && y0>y1 && y0<y2;
    }
    
    
    
    
    
    
}
