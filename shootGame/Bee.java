package cn.com.ckg.shootGame;

import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author 1 С�۷����
 */
public class Bee extends FlyingObject implements Award {

	/**
	 * С�۷��Լ�˽�е�����
	 */
	private int xSpeed = 2;
	private int ySpeed = 2;
	private int awardType;// ��ʾС�۷�Ľ�������0����1

	public Bee() {
		image = ShootGame.bee0;
		width = image.getWidth();
		height = image.getHeight();
		y = 0;// �л����ֵ�λ��,����Ļ���Ϸ�(�൱�����Լ��ĸ߶�)
		Random random = new Random();
		x = random.nextInt(ShootGame.WIDTH - this.width);
		awardType = random.nextInt(6);//�������һ������
	}

	/**
	 * С�۷��ƶ��ķ��� 1.��С�۷��x����ֵ>=��Ϸ��Ļ�Ŀ�-�۷�ͼƬ�Ŀ�(˵��С�۷����������Ҳ�)
	 * ��ô���۷�Ӧ�������ƶ�,���Բ���xSpeed=-2�ķ�ʽ 2.�����С�۷��x����ֵ<=0(˵��С�۷䵽�����ұ�)
	 * ��ôС�۷�Ӧ���۷�,����Ļ���ұ��ƶ�,���԰�xSpeed��ֵ�Ļ�2,ʵ��x����ĵ���
	 */

	@Override
	public void step() {
		if (x >= ShootGame.WIDTH - this.width) {
			xSpeed = -2;
		}
		if (x <= 0) {
			xSpeed = 2;

		}
		x += xSpeed;
		y += ySpeed;

	}

	/**
	 * ��ȡ�������͵ķ���
	 * 
	 * @return
	 */
	@Override
	public int getType() {
		return awardType;
	}

	@Override
	public boolean outOfBounds() {
		
		return this.y>=ShootGame.HEIGHT;
	}


	@Override
	public void explode(Graphics g) {
//		for (int i = 0; i < imagesEX.length; i++) {
//			g.drawImage(imagesEX[i], x, y, null);
//		}
		
	}
}
