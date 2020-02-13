package cn.kgc.shootGame;

import java.util.Random;

/**
 * @author Administrator
 * С�۷����
 */
public class Bee extends FlyingObject implements Award{
    /**
     * С�۷��Լ�˽�е�����
     */
	private int xSpeed=2;//x������ƶ��ٶ�
	private int ySpeed=2;//y������ƶ��ٶ�
	/*
	 * 0��ʾ��ȡ˫������ֵ
	 * 1��ʾ��ȡ����ֵ 
	 */
	private int awardType;//��ʾС�۷�Ľ�������0����1
	
	public Bee(){
		image=ShootGame.bee;
		width=image.getWidth();
		height=image.getHeight();
		y=-this.height;
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
	}
	
	
	/**
	 * С�۷��ƶ��ķ���
	 * �����жϣ�
	 * 1.�����С�۷��x����ֵ>=��Ϸ����Ļ��-С�۷��ͼƬ�Ŀ�(˵��С�۷���������Ϸ��Ļ�����Ҳ�)
	 *   ��ôС�۷�Ӧ���ۻ�����Ļ����ƶ�����ʱ���Բ��ý�xSpeed=-2�ķ�ʽ����ʵ��x����ĵݼ�
	 * 2.�����С�۷��x����ֵ<=0(˵��С�۷���������Ϸ��Ļ�������)
	 *   ��ôС�۷�Ӧ���ۻ�����Ļ���ұ��ƶ�����ʱ���ǿ��Խ�xSpeed��ֵ�Ļ�2����ʵ��x����ĵ���
	 */
	@Override
	public void step() {
		if(x>=ShootGame.WIDTH-this.width){
			xSpeed=-2;
		}
		if(x<=0){
			xSpeed=2;
		}
		x+=xSpeed;
		y+=ySpeed;
	}
	/**
	 * ��ȡ�������͵ķ���
	 */
	@Override
	public int getType() {
		return awardType;
	}

	@Override
	public boolean outOfBounds() {
		return this.y>=ShootGame.HEIGHT;
	}

}
