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
	public void draw(Graphics g) {//g:����
		Color c=g.getColor();//�õ���ɫ
		g.setColor(Color.red);
		
		g.fillOval((int)x, (int)y, width, height);//�����Բ
		
		//�ڵ���������Ƕ�ȥ��
		x+=speed;
		y+=speed;
		
		g.setColor(c);//�ָ���ɫ
	}
}
