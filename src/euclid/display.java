package euclid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class display extends JFrame{
	
	static int x=50, y=50;
	
	// shows a buffered image
	public display(BufferedImage bimg) {
		
		this.setTitle("fenetre 1");
		this.setLocation(x,y);
		int wih=0, heh=0;
		if(bimg.getWidth()<250) {wih=270;}
		else {wih=bimg.getWidth()+20;}
		
		if(bimg.getHeight()<150) {heh=220;}
		else {heh=bimg.getHeight()+80;}
		
		
		this.setSize(wih, heh);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);

		
		//x=(x+110)%1650;
	//	if(x%1050==0) {y=y+150;}
		
		JPanel pan=new JPanel() {	public void paintComponent(Graphics g) {g.drawImage(bimg, 0,0, this);}};
		
		this.setContentPane(pan);
		this.setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	









	
	
	
	
	
	
	
	
	public display(BufferedImage bimg, String str) {
		
		this.setTitle("fenetre 1");
		this.setLocation(x,y);
		int wih=0, heh=0;
		if(bimg.getWidth()<250) {wih=270;}
		else {wih=bimg.getWidth()+20;}
		
		if(bimg.getHeight()<150) {heh=220;}
		else {heh=bimg.getHeight()+80;}
		
		
		this.setSize(wih, heh);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);

		
		//x=(x+110)%1650;
	//	if(x%1050==0) {y=y+150;}
		
		JPanel pan=new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bimg, 0,0, this);
				g.setColor(Color.RED);
				g.drawString(str, 0, 20);
			}};
		
		this.setContentPane(pan);
		this.setVisible(true);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	int xPt=50, yPt=50;
	// shows the points in the array
	public display(Point[] arrPt) {
		
		this.setTitle("edge");
		this.setLocation(xPt,yPt);;
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		double minX=100, minY=1000, maxY=0, maxX=0;
		for(Point pt: arrPt) {
			if(pt.getX()<minX) {minX=pt.getX();}
			
			if(pt.getX()>maxX) {maxX=pt.getX();}
			
			if(pt.getY()<minY) {minY=pt.getY();}
			
			if(pt.getY()>maxX) {maxY=pt.getY();}
		}
		
		
		int weh=(int)(maxX-minX);
		int heh=(int)(maxY-minY);
		
		BufferedImage bimg=Euclid.makeWhite(weh, heh);
		
		
		
		JPanel pan=new JPanel() {
			public void paintComponent(Graphics g) {
				for(int h=0;h<arrPt.length;h++) {
					if(arrPt[h]!=null){g.drawRect((int)arrPt[h].getX(), (int)arrPt[h].getY(), 1, 1);
					}
				}
			}
		};
		
		this.setContentPane(pan);
		this.setVisible(true);
		
	}
	
	
	
	
	
	
	
	//use a radius of 50
	
	public display( Shape s , Point[] pts ) {
		
		

		this.setTitle("edge");
		this.setLocation(xPt,yPt);;
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int[] xArr=null;
		int[] yArr=null;
		int n=0;
		
		
		
		
		JPanel pan=new JPanel() {
			public void paintComponent(Graphics g) {
				
			
				int[] xArr=null;
				int[] yArr=null;
				int n=0;
				
				if( s == Shape.TRIANGLE ) {
					xArr = new int[]{ 50+150 , -25+150  , -25+150 };
					yArr = new int[]{ 0+150, -43+150 , 43+150};
					n = 3;
				}
				else if( s == Shape.SQUARE) {
					xArr = new int[]{ 50+150 , 0+150 , -50+150 , 0+150 };
					yArr = new int[]{ 0+150 , 50+150 , 0+150 , -50+150 };
					n = 4;
				}
				else if( s == Shape.PENTAGON) {
					xArr = new int[]{ 50+150 , 15+150 , -40+150 , -40+150 , 15+150};
					yArr = new int[]{ 0+150 , -48+150, -29+150 , 29+150 , 48+150 };
					n = 5;
				}
				g.drawPolygon( xArr , yArr , n );
				
				g.setColor(Color.RED);
				
				for( Point pt : pts) {
					//System.out.println();
					g.drawRect( (int)Math.floor(pt.getX()*50+150) , (int)Math.floor(pt.getY()*50+150) , 1 , 1);

				}
				
				g.setColor(Color.GREEN);
				g.drawRect( 150 , 150 , 1 , 1);
				
			}
		};
		
		this.setContentPane(pan);
		this.setVisible(true);
	}
	
	
	
	
	
	
	
	
	

}
