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
 * 飞机游戏的主窗口
 * @author 1
 * 
 */

public class MyGameFrame extends JFrame {
	
	Image bg=GameUtil.getImage("images/bg2.jpg");
	Image planeImg=GameUtil.getImage("images/plane1.png");
	
    Plane plane=new Plane(planeImg,Math.random()*Constant.getGameWidth(),Math.random()*Constant.getGameHeight());//飞机出生地点地图内随机
	Shell[] shells=new Shell[50];//50发炮弹
 	
	Explode bao;//爆炸
    Date startTime = new Date();
	Date endTime;
	int period;//游戏持续的时间
    
	@Override
	public void paint(Graphics g){//自动调用相当于是一支画笔
		Color c=g.getColor();//获取初始颜色
		
	    g.drawImage(bg, 0, 0, null);//画背景图片
	    
	    plane.drawSelf(g);//画飞机
	    
	    /**
	     * 飞机自己的炮弹(不会)
	     */
//	    while (plane.live) {
//	    	gun gun=new gun();
//	    	gun.x=plane.x+8;
//	    	gun.y=plane.y+8;
//		}
	    
	    for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);//画炮弹
			
			//飞机和炮弹的碰撞检测
			boolean peng=shells[i].getRect().intersects(plane.getRect());//intersects:判断矩形边框和矩形边框是否交叉
			if (peng) {
				plane.live=false;
				if (bao == null) {
				    bao=new Explode(plane.x, plane.y);	
				    
				    endTime=new Date();//获得死亡时间
				    period=(int)(endTime.getTime()-startTime.getTime())/1000;//计算存活时间
				}
				
				bao.draw(g);//画爆炸效果
			}
			 
			if(!plane.live) {//如果飞机死了
				g.setColor(Color.red);
				Font font=new Font("宋体",Font.BOLD,50);
				g.setFont(font);
				g.drawString("成绩时间:"+period+"秒", (int)plane.x, (int)plane.y);
			}
		}
	    g.setColor(c);//放回初始颜色
	}
	
	class PaintThread extends Thread{//重画,刷新,Thread:思路
		@Override
		public void run() {
			while(true){
				repaint();//重画
				
				try {
					Thread.sleep(40);  //25帧,1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{//键盘监控器

		@Override
		public void keyPressed(KeyEvent e) {//按下
		    plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {//抬起,KeyEvent按键事件
			plane.minusDirection(e);
		}
		
	}
	
	/**
	 * 初始化窗口
	 */
    private void lunchFrame() {
    	this.setTitle("wzw作品");//标题
    	this.setVisible(true);//可见性
    	this.setSize(Constant.getGameWidth(),Constant.getGameHeight());//从Constant获得大小
    	setLocation(100,100);//设置窗口起始坐标
    	this.addWindowListener(new WindowAdapter() {//WindowAdapter窗口适配器
    		@Override
    		public void windowClosing(WindowEvent e) {
    			System.exit(0);//当窗口关闭系统退出
    		}
		});
    	
    	new PaintThread().start();//启动窗口重画的线程
    	addKeyListener(new KeyMonitor());//给窗口增加键盘的监听
    	for (int i = 0; i < shells.length; i++) {
			shells[i]=new Shell();//循环啊产生炮弹
		}
    }
    
    public static void main(String[] args) {
		MyGameFrame f=new MyGameFrame();
		f.lunchFrame();//初始化窗口
	}
    
    /**
     * 双缓冲
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
