package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * ��ը��
 * @author 1
 *
 */
public class Explode {
	double x,y;
	static Image[] imgs = new Image[9];
	static{
		for (int i = 0; i < 9; i++) {
			
			imgs[i] = GameUtil.getImage("images2/e0_0"+(i+1)+".gif");
			imgs[i].getWidth(null);
	    }
	}

	int count;
	
	public void draw(Graphics g) {
		if (count<=8) {
			g.drawImage(imgs[count], (int)x, (int)y, null);
			count++;
		}
		
	}
	
	public Explode (double x,double y) {
		this.x=x;
		this.y=y;
	}

}
