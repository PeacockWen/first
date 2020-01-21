package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ڵ���
 * @author 1
 *
 */
public class Shell extends GameObject{
  
	double degree;
    
	public Shell() {
		x=250;//�ڵ���ʼλ��
		y=250;//�ڵ���ʼλ��
		width=10;
		height=10;
		speed=3;
		degree=Math.random()*Math.PI*2;//�ڵ��Ƕ�
	}
  
	public void draw(Graphics g) {//g:����
		Color c=g.getColor();//�õ���ɫ
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x, (int)y, width, height);//�����Բ
		
		//�ڵ���������Ƕ�ȥ��
		x+=speed*Math.cos(degree);
		y+=speed*Math.sin(degree);
		
		if (x<0 || x>Constant.getGameWidth()-width) {//�ڵ������߿򷵻�
			degree=Math.PI-degree;
		}
		if (y<30 || y>Constant.getGameWidth()-height) {
			degree=-degree;
		}
		
		g.setColor(c);//�ָ���ɫ
	}
}
