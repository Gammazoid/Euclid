package euclid;

public enum Shape {
	
	TRIANGLE( new double[][] { {-Math.sqrt(3)/3 , Math.sqrt(3)/3} , {Double.POSITIVE_INFINITY, 0} , {Math.sqrt(3)/3 , -Math.sqrt(3)/3} } , 
				new double[] {2*Math.PI/3 , 4*Math.PI/3 , 0} ),
	SQUARE(  new double[][] { {-1,1}, {1,1} , {-1,-1} , {1,-1} } , new double[]{ Math.PI/2 , Math.PI , 3*Math.PI/2 , 0} ),
	PENTAGON( new double[][] { {-1.376 , 1.376} , { 0.325 , 0.85 } , {Double.POSITIVE_INFINITY, 0} , { -0.325 , -0.85 } , {1.376 , -1.376} } ,
				new double[] { 2*Math.PI/5 , 4*Math.PI/5 , 6*Math.PI/5,  8*Math.PI/5 , 0});
	
	
	double[][] parameters;
	double[] range;
	
	Shape(double[][] param , double[] range){
		this.parameters=param;
		this.range=range;
	}

	
	public double[][] getParameters() {
		return this.parameters;
	}
	
	public double[] getRange() {
		return this.range;
	}
	
}
