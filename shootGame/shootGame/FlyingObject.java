package cn.kgc.shootGame;

import java.awt.image.BufferedImage;

/**
 * @author Administrator
 * ��������ࣺ��ʾ���з�����ĸ���
 */
public abstract class FlyingObject {
    public int x;//��ʾx����
    public int y;//��ʾy����
    public BufferedImage image;//ר�Ŵ��ͼƬ������
    public int width;//ͼƬ�Ŀ�
    public int height;//ͼƬ�ĸ�
    
    /*
     * ���н����ϵķ�������һ���ķ���
     */
    public abstract void step();
    /*
     * �жϷ������Ƿ�Խ��
     */
    public abstract boolean outOfBounds();
    
    /**
     * ��֤�ӵ��͵л��Ƿ�ײ��
     * �������true�������ײ����
     */
    public boolean shootBy(Bullet bullet){
    	int x1=this.x;//��ǰ�л������Ͻǵ�x����
    	int y1=this.y;//��ǰ�л������Ͻǵ�y����
    	int x2=this.x+this.width;//��ǰ�л����Ͻǵ�x����
    	int y2=this.y+this.height;//��ǰ�л����½ǵ�y����
    	int x0=bullet.x;//�ӵ���x����
    	int y0=bullet.y;//�ӵ���y����
    	return x0>x1 && x0<x2 && y0>y1 && y0<y2;
    }
    
    
    
    
    
    
}
