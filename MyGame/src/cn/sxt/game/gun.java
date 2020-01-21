package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

public class gun extends GameObject{
    double x,y;
    
	public gun() {
		
		width=10;
		height=10;
		speed=8;
		
	}
	public void draw(Graphics g) {//g:画笔
		Color c=g.getColor();//得到颜色
		g.setColor(Color.red);
		
		g.fillOval((int)x, (int)y, width, height);//填充椭圆
		
		//炮弹沿着任意角度去飞
		x+=speed;
		y+=speed;
		
		g.setColor(c);//恢复颜色
	}
}
