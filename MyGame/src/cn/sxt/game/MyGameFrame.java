package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.sql.Date;
import java.util.Date;

import javax.swing.JFrame;

import org.w3c.dom.css.Counter;

/**
 * �ɻ���Ϸ��������
 * @author 1
 * 
 */

public class MyGameFrame extends JFrame {
	
	Image bg=GameUtil.getImage("images/bg2.jpg");
	Image planeImg=GameUtil.getImage("images/plane1.png");
	
    Plane plane=new Plane(planeImg,Math.random()*Constant.getGameWidth(),Math.random()*Constant.getGameHeight());//�ɻ������ص��ͼ�����
	Shell[] shells=new Shell[50];//50���ڵ�
 	
	Explode bao;//��ը
    Date startTime = new Date();
	Date endTime;
	int period;//��Ϸ������ʱ��
    
	@Override
	public void paint(Graphics g){//�Զ������൱����һ֧����
		Color c=g.getColor();//��ȡ��ʼ��ɫ
		
	    g.drawImage(bg, 0, 0, null);//������ͼƬ
	    
	    plane.drawSelf(g);//���ɻ�
	    
	    /**
	     * �ɻ��Լ����ڵ�(����)
	     */
//	    while (plane.live) {
//	    	gun gun=new gun();
//	    	gun.x=plane.x+8;
//	    	gun.y=plane.y+8;
//		}
	    
	    for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);//���ڵ�
			
			//�ɻ����ڵ�����ײ���
			boolean peng=shells[i].getRect().intersects(plane.getRect());//intersects:�жϾ��α߿�;��α߿��Ƿ񽻲�
			if (peng) {
				plane.live=false;
				if (bao == null) {
				    bao=new Explode(plane.x, plane.y);	
				    
				    endTime=new Date();//�������ʱ��
				    period=(int)(endTime.getTime()-startTime.getTime())/1000;//������ʱ��
				}
				
				bao.draw(g);//����ըЧ��
			}
			 
			if(!plane.live) {//����ɻ�����
				g.setColor(Color.red);
				Font font=new Font("����",Font.BOLD,50);
				g.setFont(font);
				g.drawString("�ɼ�ʱ��:"+period+"��", (int)plane.x, (int)plane.y);
			}
		}
	    g.setColor(c);//�Żس�ʼ��ɫ
	}
	
	class PaintThread extends Thread{//�ػ�,ˢ��,Thread:˼·
		@Override
		public void run() {
			while(true){
				repaint();//�ػ�
				
				try {
					Thread.sleep(40);  //25֡,1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{//���̼����

		@Override
		public void keyPressed(KeyEvent e) {//����
		    plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {//̧��,KeyEvent�����¼�
			plane.minusDirection(e);
		}
		
	}
	
	/**
	 * ��ʼ������
	 */
    private void lunchFrame() {
    	this.setTitle("wzw��Ʒ");//����
    	this.setVisible(true);//�ɼ���
    	this.setSize(Constant.getGameWidth(),Constant.getGameHeight());//��Constant��ô�С
    	setLocation(100,100);//���ô�����ʼ����
    	this.addWindowListener(new WindowAdapter() {//WindowAdapter����������
    		@Override
    		public void windowClosing(WindowEvent e) {
    			System.exit(0);//�����ڹر�ϵͳ�˳�
    		}
		});
    	
    	new PaintThread().start();//���������ػ����߳�
    	addKeyListener(new KeyMonitor());//���������Ӽ��̵ļ���
    	for (int i = 0; i < shells.length; i++) {
			shells[i]=new Shell();//ѭ���������ڵ�
		}
    }
    
    public static void main(String[] args) {
		MyGameFrame f=new MyGameFrame();
		f.lunchFrame();//��ʼ������
	}
    
    /**
     * ˫����
     */
    public Image offScreenImage = null;
    
    public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage=this.createImage(Constant.getGameWidth(),Constant.getGameHeight());
		
		Graphics gOff=offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
    
}
