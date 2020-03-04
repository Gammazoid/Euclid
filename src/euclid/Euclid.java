package euclid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.stage.Stage;

public class Euclid{

	
	
	
	static Point[] ptsShow=null;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Shape s = Shape.TRIANGLE;
		
		//javafx.application.Platform.startup(new Show());
	
		
		
		/*
		 *  triangle to triangle :0.038
		 *  triangle to square 0.024
		 *  triangle to pantagon  0.028
		 *  
		 *  square to triangle 0.098
		 *  square to square 0.0125
		 *  square to pantagon 0.0119
		 *  
		 *  pantagon to triangle 0.134
		 *  pantagon to square 0.019
		 *  pantagon to pantagon  0.017
		 *  
		 *  
		 */
		
		BufferedImage test = loadImage("img/pentagon1.PNG");
		
		Point[] pts = IMGtoPoint(test);
		
		Point origin = expectedPoint(pts); 					//System.out.println("expected center "+origin.show() );
		
		Point[] shiftedPts = shiftPoints(pts , origin);
		
		Point[] ptsPolar = CartesianToPolar(shiftedPts);

		Point[] ptsPolarUnitized = unitizePolar(ptsPolar);
	
		
		//ThreeReg(ptsPolarUnitized);
		
		/*
		for(Point pt : ptsPolarUnitized) {
			System.out.println(pt.show() );
		}
		*/

		
		//int n=6;
		//findParam(Math.cos(8*Math.PI/5) , Math.cos((0)*Math.PI/5) , Math.sin( 8*Math.PI/5 ) , Math.sin((0)*Math.PI/5) );
		
		
		//shapeComparison(ptsPolarUnitized , Shape.PENTAGON);
		
		polyRegression(ptsPolarUnitized ,3);
		
		
		//ptsShow= PolarDisplayedInCartesian(ptsPolarUnitized);	Application.launch();
	}

	
	
	
	public static void linearReg(Point[] pts) {
		
		double[] weights = { Math.random() , Math.random() };
		double learningRate=0.0001;
		
		
		for(Point pt : pts) {
			//System.out.println( pt.show() );
		}
		
		
		for(int ite=0;ite<15;ite++) {
			double cost = 0;
			for(Point pt : pts ) {
				
				
				cost+= Math.pow( weights[0]+weights[1]*pt.getX() - pt.getY(), 2 )/(double)pts.length;
				
				
			}
			
			
			double gradW0=0, gradW1=0;
			
			for(Point pt : pts) {
				
				gradW0+=( weights[0]+weights[1]*pt.getX()-pt.getY() )/(double)pts.length;
				gradW1+=( weights[0]+weights[1]*pt.getX()-pt.getY() )*pt.getX()/(double)pts.length;
	
			}
			
			weights[0]=weights[0]-learningRate*gradW0;
			weights[1]=weights[1]-learningRate*gradW1;
			
			System.out.println(cost+"  "+gradW0+"  "+gradW1);
		}
		
	}
	
	
	
	
	
	public static void ThreeReg(Point[] pts) {
		
		double[] weights = { Math.random() , Math.random() , Math.random() , Math.random()};
		double learningRate=0.0001;
		
		
		
		for(int ite=0;ite<18;ite++) {
			
			double cost = 0;
			
			for(Point pt : pts ) {
				
				
				cost+= Math.pow( weights[0]+weights[1]*pt.getY()+weights[2]*Math.pow(pt.getY(),2)+weights[3]*Math.pow(pt.getY(),3) -pt.getX(), 2 )/(double)pts.length;
				
				
			}
			
			
			double gradW0=0, gradW1=0, gradW2=0, gradW3=0;
			
			for(Point pt : pts) {
				gradW0+=learningRate*(weights[0]+weights[1]*pt.getY()+weights[2]*Math.pow(pt.getY(),2)+weights[3]*Math.pow(pt.getY(),3)-pt.getX())*Math.pow(pt.getY(),0)/(double)pts.length;
				gradW1+=learningRate*(weights[0]+weights[1]*pt.getY()+weights[2]*Math.pow(pt.getY(),2)+weights[3]*Math.pow(pt.getY(),3)-pt.getX())*Math.pow(pt.getY(),1)/(double)pts.length;
				gradW2+=learningRate*(weights[0]+weights[1]*pt.getY()+weights[2]*Math.pow(pt.getY(),2)+weights[3]*Math.pow(pt.getY(),3)-pt.getX())*Math.pow(pt.getY(),2)/(double)pts.length;
				gradW3+=learningRate*(weights[0]+weights[1]*pt.getY()+weights[2]*Math.pow(pt.getY(),2)+weights[3]*Math.pow(pt.getY(),3)-pt.getX())*Math.pow(pt.getY(),3)/(double)pts.length;
	
			}
			
			weights[0]=weights[0]-gradW0;
			weights[1]=weights[1]-gradW1;
			weights[2]=weights[2]-gradW2;
			weights[3]=weights[3]-gradW3;
			
			System.out.println(cost+"  and the gradient are : "+gradW0+" "+gradW1+" "+gradW2+" "+gradW3);
		}
		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void shapeComparison(Point[] ptsPolarUnitized , Shape s) {
		double t=0;
		new display(s , PolarToCartesian( ptsPolarUnitized ) );
		
		double minCost=5000;
		double angleMin = 0;
		
		while(t<2*Math.PI) {
			
			double cost = costForShape(s, rotatePoints(ptsPolarUnitized , -t ) );	//System.out.println("angle "+t+" cost "+cost);
			t+=0.1;
			
			if(cost<minCost) {
				angleMin=t;
				minCost=cost;
			}
		}
		
		System.out.println(angleMin+"  "+minCost);
		
		new display(s , PolarToCartesian(rotatePoints(ptsPolarUnitized , -angleMin )) );
	}
	
	
	
	
	
	
	
	


	public static double cost(Point[] pts , double[] weights ) {
		
		double cost=0;
		
		for(Point pt : pts) {
			
			double hypothesis=0;
			
			for(int i=0;i<weights.length;i++) {
				
				hypothesis+=Math.pow( pt.getY() , i )*weights[i];
				
			}
			
			cost+=0.5*Math.pow( hypothesis-pt.getX() , 2) / (double)pts.length;
			
		}
		
		
		return cost;
	}


	
	
	
	public static void gradDescent(Point[] pts , double[] weights ) {
		
		double learningRate=1/Math.pow(10 , weights.length+1 );
		
		for(int k=0;k<weights.length;k++) {
			
			double costDerivative=0;
		
			for(Point pt : pts) {
				
				double hypothesis=0;
				double u=0;
				
				for(int i=0;i<weights.length;i++) {
					
					hypothesis+=Math.pow( pt.getY() , i )*weights[i];
					
				}
				
				u=hypothesis-pt.getX();
				
				double hypothesisDerivative = Math.pow( pt.getY() , k );
				
				costDerivative+=u*hypothesisDerivative/(double)pts.length;
			}//for(pts)
			
			weights[k]=weights[k]- learningRate*costDerivative;
			
		}//for(k)
		
		
		
	}//END gradDescent






	
	
	
	
	
	
	
	
	public static void polyRegression(Point[] pts, int N) {
		
		
		
		// A .initialize weights
		double[] weights = new double[N+1];
		
		for(int a=0;a<N+1;a++) {
			weights[a]=Math.random();
		}
		
		double previousCost=0;
		double currentCost=1000;
		do{
			previousCost=currentCost;
			// B. find the cost
			
			currentCost=cost(pts, weights);
			
			// C. Gradient descent
			
			gradDescent(pts , weights);
			
			System.out.println(currentCost);
			
			
		}while( Math.abs( previousCost-currentCost ) > 0.001);
		
	}
	
	
	
	
	
	




	public static double[] findParam( double xn , double xn1 , double yn , double yn1 ) {
		
		double[] param = new double[2];
		
		double a = ( yn - yn1 )/( xn - xn1 );					
		double b = ( -xn1*yn+xn*yn1 )/( xn - xn1 );				System.out.println(a+" "+b);
		
		param[0]=a;	
		param[1]=b;
		
		return param;
	}
	
	
	
	
	
	
	
	public static Point[] IMGtoPoint(BufferedImage bimg) {
		
		int counter=0;
		
		for(int i=0;i<bimg.getWidth();i++) {
			for(int j=0;j<bimg.getHeight();j++) {
				if( bimg.getRGB(i,j)!=Color.WHITE.hashCode() ) {
					counter++;
				}//if(!white)
			}//for(j)
		}//for(i)
		
		Point[] pts = new Point[counter];
		
		for(int i=0;i<bimg.getWidth();i++) {
			for(int j=0;j<bimg.getHeight();j++) {
				if( bimg.getRGB(i,j)!=Color.WHITE.hashCode() ) {
					
					counter--;
					pts[counter]=new Point( i , j );
					
				}//if(!white)
			}//for(j)
		}//for(i)
		
		return pts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static Point expectedPoint(Point[] pts) {
		
		double x=0;
		double y=0;
		
		for(int a=0;a<pts.length;a++) {
			
			x+=pts[a].getX();
			y+=pts[a].getY();
			
		}
		
		
		return new Point( x/(double)pts.length , y/(double)pts.length );	
	}
	
	
	
	
	
	
	
	// **********************************  Transformation involving an array   ************************************************
	
	
		
	public static Point[] shiftPoints(Point[] pts , Point origin) {
		
		Point[] shifted = new Point[pts.length];
		
		for(int a=0;a<pts.length;a++) {
			
			shifted[a] = new Point( pts[a].getX()-origin.getX() , pts[a].getY()-origin.getY() );
			
		}
		
		
		return shifted;	
	}
	
	
	
	
	
	
	
	
	
	
	/**Rotate point in polar coordinate by the value of angle
	 * 
	 * @param pts
	 * @param angle
	 * @return
	 */
	public static Point[] rotatePoints(Point[] pts ,  double angle) {
		
		Point[] rotated = new Point[pts.length];
		
		for(int a=0;a<pts.length;a++) {
			
			rotated[a] = new Point( pts[a].getX() , (pts[a].getY()+angle+Math.PI*2)%(Math.PI*2) );   //System.out.println( rotated[a].show() );
			
		}
		
		
		return rotated;	
	}
	
	
	
	
	
	
	
	
/**Assume the array of point is centered at the origin
 * 	
 * @param pts
 * @return
 */
	public static Point[] CartesianToPolar( Point[] pts ) {
		
		Point[] ptsPolar = new Point[pts.length];
		
		for(int a=0;a<pts.length;a++) {
			
			ptsPolar[a] = CartesianToPolar(pts[a]);  
			
		}
		
		return ptsPolar;
	}
	
	
	
	
	
	public static Point[] PolarToCartesian(Point[] pts) {

		Point[] cartesianPoints = new Point[pts.length];
		
		for(int a=0;a<cartesianPoints.length;a++) {
			
			cartesianPoints[a] = PolarToCartesian(pts[a]); 	//System.out.println(cartesianPoints[a].show());
			
		}
		
		
		return cartesianPoints;
	}


	
	
	
	
	
	public static Point[] unitizePolar(Point[] pts) {
		
		Point[] unitizedPts = new Point[pts.length];
		double maxR = 0;
		
		
		for(int a=0;a<pts.length;a++) {
			
			if(pts[a].getX()>maxR) {
				maxR=pts[a].getX();
			}
			
		}
		
		
		
		for(int a=0;a<pts.length;a++) {
			
			unitizedPts[a] = new Point( pts[a].getX()/maxR , pts[a].getY() );   	//System.out.println( unitizedPts[a].show() );
			
		}
		
		 
		
		return unitizedPts;
	}
	
	
	
	
	
	
	
	
	public static Point[] PolarDisplayedInCartesian(Point[] pts) {
		
		Point[] inCartesian = new Point[pts.length];
		
		for(int a=0;a<pts.length;a++) {
			inCartesian[a] = new Point( pts[a].getY() , 1-pts[a].getX() );
		}
		
		return inCartesian;
	}
	
	
	
	
	
	// **********************************  Transformation involving a single point   ************************************************
	
	
		
	public static Point shiftPoints(Point pts , Point origin) {
		
		return new Point( pts.getX()-origin.getX() , pts.getY()-origin.getY() );

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**Rotate point in polar coordinate by the value of angle
	 * 
	 * @param pts
	 * @param angle
	 * @return
	 */
	public static Point rotatePoints(Point pts ,  double angle) {
				
		return new Point( pts.getX() , (pts.getY()+angle)%(Math.PI*2) );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**Assume the point is centered about an origin
 * 	
 * @param pts
 * @return points with angle in the range [0, 2*PI]
 */
	public static Point CartesianToPolar( Point pt ) {
		

			
		double theta = Math.atan( pt.getY() / pt.getX() );
			
		if( pt.getX()<0  &&  pt.getY()>0 ) {
			theta=Math.abs(theta)+Math.PI/2;
		}
		else if( pt.getX()<0  &&  pt.getY()<0 ) {
			theta+=Math.PI;
		}
		else if( pt.getX()>0  &&  pt.getY()<0 ) {
			theta=2*Math.PI+theta;    //theta being negative
		}
			
		
		
		double r = Math.sqrt( pt.getX()*pt.getX() + pt.getY()*pt.getY() );
		
		
		
		return new Point( r , theta );
	}
	
	
	
	
	

	
	/** Assume the point pt has angle in the range [ 0 , 2*PI ]
	 * 
	 * @param pt
	 * @return
	 */
	
	public static Point PolarToCartesian(Point pt) {
		
		double x = pt.getX()*Math.cos( pt.getY() );
		double y = pt.getX()*Math.sin( pt.getY() );			//System.out.println(x+"  "+y);
		
		return new Point(x,y);
	}
	
	
	
	
	
	
	
	

	
	
	//  **************************************  secondary methods  **********************************************
	
	
	
	
	
	
	/**Finds the distance between a point and a line by using polar coordinate
	 * 
	 * @param parameters the parameters defining a line
	 * @param pt a Point in polar coordinate with angle in range [0 , 2*PI]
	 * @return the distance
	 */
	public static double distanceTo(double[] parameters  , Point pt , Shape s) {
		
		// double y = parameters[0]*x+parameters[1];		
		
		// double r*sin(theta) = parameters[0]*r*cos(theta)+parameters[1];
		double theta=0;
		double r=0;
		if(parameters[0]==Double.POSITIVE_INFINITY  &&  s==Shape.TRIANGLE) {
			// find the coord of intersect b/w the infininty line and a line drawn from the point to 0
			
			Point ptCartesian = PolarToCartesian(pt);		//	ptCartesian should already be relative to the zero
			double a = ptCartesian.getY()/ptCartesian.getX();
			double yIntersect = a*-0.5;	//the x of interscet fo a triangle is -0.5, but this should be declared depending on the shape
			Point ptIntersect = new Point( -0.5 , yIntersect);
			Point ptIntersectPolar = CartesianToPolar(ptIntersect);
			r = ptIntersectPolar.getX();
			
		}
		else if(parameters[0]==Double.POSITIVE_INFINITY  &&  s==Shape.PENTAGON) {
			// find the coord of intersect b/w the infininty line and a line drawn from the point to 0
			
			Point ptCartesian = PolarToCartesian(pt);		//	ptCartesian should already be relative to the zero
			double a = ptCartesian.getY()/ptCartesian.getX();
			double yIntersect = a*-0.8;	//the x of intersect for a pentagon is -0.8, but this should be declared depending on the shape
			Point ptIntersect = new Point( -0.8 , yIntersect);
			Point ptIntersectPolar = CartesianToPolar(ptIntersect);
			r = ptIntersectPolar.getX();			
		}
		else {
			theta = pt.getY();
			r = parameters[1]/(Math.sin(theta)-parameters[0]*Math.cos(theta));  // the radius from origin of the line 
		}
		
	
		
		//System.out.println(r);
		
		return r-pt.getX();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// range[length-1] is always = 0 for Shape

	
	
	public static double costForShape( Shape s , Point[] pts ) {
		
		double distance=0;
		double cost=0;
		
		for(int a=0;a<pts.length;a++) {
			
			double[] parameters = null;
			if(pts[a].getY()>=0) {
				// find which range a point belong to
				int range=0;
				
				for(int b=0;b<s.getRange().length;b++) {
					
					
					
					if( b!=s.getRange().length-1  &&  pts[a].getY()<s.getRange()[b]  &&  pts[a].getY()>=s.getRange()[(b-1+s.getRange().length)%s.getRange().length] ) {
						 parameters = s.getParameters()[b];
						 range=b;
					}
					
					if( b==s.getRange().length-1  &&  pts[a].getY()<(2*Math.PI)  &&  pts[a].getY()>=s.getRange()[(b-1)%s.getRange().length] ) {
						parameters = s.getParameters()[b];
						range=b;
					}
					
					
				}//for(b)
				//System.out.println("angle: "+pts[a].getY()+" in range "+range);
				distance = distanceTo( parameters , pts[a] , s);		if( Math.abs(distance)>1) {System.out.println(pts[a].getY()+" in range "+range);}
			}
			else {
				System.out.println("Smaller than zero, this should not be possible");
			}
			
			
			// get the distance
			//System.out.println("angle "+pts[a].getY());	
			
			
			cost += Math.pow(distance,2)/(double)pts.length;
		}
		
		return cost;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**return an array with the angle of the points having radius = 1
	 * 
	 * @param pts
	 */
	public static double[] rotationToMake(Point[] pts) {
		
		
		int counter=0;
		
		for(int i=0;i<pts.length;i++) {
			if(pts[i].getX()==1) {
				counter++;
			}
		}
		
		double[] angles = new double[counter];
		
		for(int i=0;i<pts.length;i++) {
			if(pts[i].getX()==1) {
				counter--;
				angles[counter]=pts[i].getY();
			}
		}
		
		
		return angles;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//**************************************   Tertiary Methods    *********************************************
	
	
/**
 * 	
 * @param img the image to be turned into a bufferedimage
 * @return a bufferedimage generated from the image
 */
	private static BufferedImage toBufferedImage(Image img){
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Loads an image from the computer and turn it into a bufferedimage
	 * 
	 * @param name the location of the image in the computer
	 * @return the bufferedimage of the image loaded
	 */
	private static BufferedImage loadImage(String name) {
	
	
	BufferedImage bimg=null;

    try {     
    	Image img = ImageIO.read(new File(name));
		bimg=toBufferedImage(img);
    } catch (IOException e) {      
    	e.printStackTrace();    
    } 
	
	return bimg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static BufferedImage makeWhite(int wid, int heh) {
			
			BufferedImage toRet=new BufferedImage(wid, heh, BufferedImage.TYPE_INT_ARGB);
			
			for(int i=0;i<wid;i++) {
				for(int j=0;j<heh;j++) {
					toRet.setRGB(i, j, -1);
				}
			}
			
			return toRet;
		}//END makeWhite()
































	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		new Show(ptsShow);
		
		
		
		
		
	}































	
	
	
	
	
	
	
	
	
	
	
	
}
