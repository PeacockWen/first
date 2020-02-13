package cn.kgc.shootGame;
/**
 * @author Administrator
 * �ӵ���
 */
public class Bullet extends FlyingObject{
    /**
     * ����Լ�˽�е�����
     */
	private int speed=3;//�ӵ����ٶ�
	
	/**
	 * ��Ϊ�ӵ�������Ǹ���Ӣ�ۻ����������仯�ģ������������вι��������г�ʼ��
	 */
	public Bullet(int x,int y){
		image=ShootGame.bullet;//��ʼ���ɻ���ͼƬ
		//����ͨ��image�õ��÷ɻ��Ŀ�͸�
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void step() {
		y-=speed;
	}

    /**
     * �ӵ��Ƿ�Խ��ķ���
     */
	@Override
	public boolean outOfBounds() {
		/*
		 * �����ӵ���y����С�ڵ��ڸ��ı����ͼƬ�ĸ�
		 */
		return this.y<=-this.height;
	}

}
