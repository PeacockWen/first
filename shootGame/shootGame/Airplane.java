package cn.kgc.shootGame;

import java.util.Random;

/**
 * @author Administrator
 * С�л�����
 */
public class Airplane extends FlyingObject implements Enemy{
	/**
	 * С�л��Լ�˽�е�����
	 */
	private int speed=2;//��ʾС�л�������ٶ�
	/**
	 * ʹ�ù��췽�����г�ʼ��
	 */
	public Airplane(){
		image=ShootGame.airplane;//��ʼ���ɻ���ͼƬ
		//����ͨ��image�õ��÷ɻ��Ŀ�͸�
		width=image.getWidth();
		height=image.getHeight();
		//����С�л��ĳ���λ��
		y=-this.height;//�л����ֵ�λ�ã�����Ļ���Ϸ�(�൱�����Լ��ĸ߶�)
		/*
		 * x���������з�Χ�ģ�0~��Ļ�Ŀ�-�ɻ��Ŀ�
		 * ���������ֵ��������ɵģ����������
		 * Math.random()*(��Ļ�Ŀ�-�ɻ��Ŀ�)
		 * ����
		 * Random rand=new Random();
		 * rand.nextInt(int num); ��ʾ�������0~num֮�������
		 */
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		
	}
	
	
	/**
	 * ��ʾС�л��ƶ��ķ���
	 */
	@Override
	public void step() {
		//С�л����������ֵ��y�й�ϵ(�൱���ƶ�����һ��y����ֵ=��ǰ��y����ֵ+�ƶ����ٶ�)
		y+=speed;//С�л������䣬y��ֵ��Խ��Խ���
	}
	/**
	 * ���С�л���ȡ�̶��ķ�ֵ
	 */
	@Override
	public int getScore() {
		return 3;
	}

    /**
     * С�л��Ƿ�Խ��ķ���
     */
	@Override
	public boolean outOfBounds() {
		/*
		 * С�л���y����������ڵ�����Ϸ��Ļ�ĸߣ���˵��Խ����
		 */
		return this.y>=ShootGame.HEIGHT;
	}
     
}
