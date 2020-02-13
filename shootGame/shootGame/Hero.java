package cn.kgc.shootGame;

import java.awt.image.BufferedImage;

/**
 * @author Administrator
 * Ӣ�ۻ�����
 */
public class Hero extends FlyingObject{
    /**
     * ���Ӣ�ۻ��Լ�˽�е�����
     */
	private int life;//Ӣ�ۻ�������ֵ
	private int doubleFire;//Ӣ�ۻ���˫������ֵ
	private BufferedImage[] images;//�����������Ӣ�ۻ�ͼƬ������
	private int index;//��������Ӣ�ۻ�ͼƬ�л����±�
	
	public Hero(){
		image=ShootGame.hero0;//��ʼ��Ӣ�ۻ���ͼƬ
		//����ͨ��image�õ���Ӣ�ۻ��Ŀ�͸�
		width=image.getWidth();
		height=image.getHeight();
		//Ӣ�۵���ʼ�����ǹ̶���
		x=150;
		y=400;
		life=3;
		doubleFire=100;//Ĭ��Ӣ�ۻ���������100����˫������ֵ
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		//Ĭ���±�Ϊ0
		index=0;
	}
	
	
	
	@Override
	public void step() {
		//Ӣ�ۻ����ƶ���ֻ������й�ϵ
		//�÷�����������ʵ��Ӣ�ۻ�ͼƬ���л�
		/**
		 * ��ΪӢ�ۻ���ͼƬ��hero0��hero1���ţ��������Ǹ�����index����������0��1֮�������л�
		 * ��֪�̶�������2,���ͨ��index����0��1
		 * 1.indexֵ����
		 * 2.indexȡ��2
		 *  ���磺0ȡ��2---0
		 *       1ȡ��2---1
		 *       2ȡ��2---0
		 *       3ȡ��2---1 
		 *       
		 */
		image=images[index++%images.length];
	}
	
	/**
	 * @param x :��ʾ���������x����
	 * @param y :��ʾ���������y����
	 * Ӣ�ۻ���������ƶ��ķ���
	 * 
	 */
	public void moveTo(int x,int y){
		/*
		 *ͨ�����������x��y�����������Ӣ�ۻ�������
		 */
		this.x=x-this.width/2;
		this.y=y-this.height/2;
		
	}
	
	/**
	 * @return
	 * Ӣ�ۻ������ӵ��ķ���
	 */
	public Bullet[] shoot(){
		//
		int xStep=this.width/4;//��ʾ4��֮1��Ӣ�ۻ��Ŀ�
		int yStep=20;//��ʾ�ӵ�����Ӣ�ۻ��Ĺ̶�yֵ
		//�жϺ�ʱ����˫������ֵ
		if(doubleFire>0){//����˫������
			Bullet[] bs=new Bullet[2];
			bs[0]=new Bullet(this.x+xStep,this.y-yStep);//��һ���ӵ�������
			bs[1]=new Bullet(this.x+3*xStep,this.y-yStep);//�ڶ����ӵ�������
			//������˫������֮�����Ӧ�Ļ���ֵ����2
			doubleFire-=2;
			return bs;
		}else{
			Bullet[] bs=new Bullet[1];
			bs[0]=new Bullet(this.x+2*xStep,this.y-yStep);
			return bs;
		}
		
		
	}


    /**
     * Ӣ�ۻ��Ƿ�Խ��ķ���
     */
	@Override
	public boolean outOfBounds() {
		return false;//����Խ��
	}
	
	
	
	
	
	

}
