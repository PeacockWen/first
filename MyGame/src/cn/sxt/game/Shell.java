package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 炮弹类
 * @author 1
 *
 */
public class Shell extends GameObject{
  
	double degree;
    
	public Shell() {
		x=250;//炮弹起始位置
		y=250;//炮弹起始位置
		width=10;
		height=10;
		speed=3;
		degree=Math.random()*Math.PI*2;//炮弹角度
	}
  
	public void draw(Graphics g) {//g:画笔
		Color c=g.getColor();//得到颜色
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x, (int)y, width, height);//填充椭圆
		
		//炮弹沿着任意角度去飞
		x+=speed*Math.cos(degree);
		y+=speed*Math.sin(degree);
		
		if (x<0 || x>Constant.getGameWidth()-width) {//炮弹碰到边框返回
			degree=Math.PI-degree;
		}
		if (y<30 || y>Constant.getGameWidth()-height) {
			degree=-degree;
		}
		
		g.setColor(c);//恢复颜色
	}
}
