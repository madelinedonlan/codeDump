import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

public class WorldCoordinateSystem extends Canvas{

	public void paint(Graphics g) {
    
    	cubeBuilder(g);
    	
    	
    	
    }
	
	public double [][] cubeBuilder(Graphics g)
	{
		double d = 90;
		double s = 30;
		double[][] array = 
			{
					{-1,1,-1,1},
					{1, 1, -1, 1},
					{1, -1, -1, 1},
					{-1, -1, -1, 1},
					{-1, 1, 1, 1}, 
					{1, 1, 1, 1},
					{1, -1, 1, 1},
					{-1, -1, 1, 1}
			};
		double[][] n = {{d/s, 0, 0, 0},
		    	 		{0, d/s, 0, 0},
		    	 		{0, 0, 1, 0}, 
		    	 		{0, 0, 0, 1}};
		double [][]t1 = 
			{
			{ 1 ,0,   0,0},
			{ 0, 1,   0,0},
			{ 0, 0,   1,0},
			{-6,-8,-7.5,1}
			};
				
		double [][]t2 = 
			{
			{1 ,0, 0, 0},
			{0, 0,-1, 0},
			{0, 1, 0, 0},
			{0, 0, 0, 1}
			};
		double [][]t3 = 
			{
			{ -.8 ,0, .6,0},
			{   0, 1,  0,0},
			{ -.6, 0,-.8,0},
			{   0, 0,  0,1}
			};
				
		double [][]t4 = 
			
			{
			{ 1 ,0, 0 ,0},
			{   0, .8, .6,0},
			{ 0, -.6,.8,0},
			{   0, 0,  0,1}}
			;
		double [][]t5 = 
				
			{
			{ 1, 0, 0,0},
			{ 0, 1, 0,0},
			{ 0, 0,-1,0},
			{ 0, 0, 0,1}}
			;
		
		//matrix multiplication
		double v[][] = matrixMultiplication(t1,t2);
		double v3[][] = matrixMultiplication(v,t3);
		double v4[][] =matrixMultiplication(v3,t4); 
		double v5[][] =matrixMultiplication(v4,t5); 
		double vn[][] =matrixMultiplication(v5,n); 
		double [][]vvnx = matrixMultiplication(array,vn);
		/*for(int i = 0; i <4; i++)
		/*{
			for(int k = 0; k<4; k++)
			{
				
				System.out.println(vvnx[i][k]);
			}
		}*/
		double xc;
		double yc; 
		double zc;
		double[][] xsys = new double[8][2];
		double ys;
		double vsx;
		double vcx;
		double vcy;
		double vsy;
		
		
		
		for(int l = 0; l<8; l++)
		{
			System.out.println("xc: " + vvnx[l][0] + "  zc: " + vvnx[l][2]);
		
			xsys[l][0] = ((vvnx[l][0]/vvnx[l][2])*500)+500;
		
			System.out.println("xs: " + xsys[l][0]);
			
			System.out.println("yc: " + vvnx[l][1] + "  zc: " + vvnx[l][2]);	
			xsys[l][1] = ((vvnx[l][1]/vvnx[l][2])*500)+500;
			System.out.println("ys: " + xsys[l][1]);
		}			
		int pointer = 0;
		for(int e = 0; e<3; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+1][0],(int)xsys[pointer+1][1]);
			bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+5][0],(int)xsys[pointer+5][1]);
			pointer++;
		}
		pointer = 0;
		for(int e = 0; e<4; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+4][0],(int)xsys[pointer+4][1]);
			pointer++;
		}
		pointer = 0;
		bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+3][0],(int)xsys[pointer+3][1]);
		bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+7][0],(int)xsys[pointer+7][1]);
		
		//basic translate
		/*
		xsys = BasicTranslate(g, xsys, 30,50);	
		pointer = 0;
		for(int e = 0; e<3; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+1][0],(int)xsys[pointer+1][1]);
			bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+5][0],(int)xsys[pointer+5][1]);
			pointer++;
		}
		pointer = 0;
		for(int e = 0; e<4; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+4][0],(int)xsys[pointer+4][1]);
			pointer++;
		}
		pointer = 0;
		bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+3][0],(int)xsys[pointer+3][1]);
		bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+7][0],(int)xsys[pointer+7][1]);
		*/
		
		//basic scale
		/*
		xsys = BasicScale(g, xsys, 2,2);	
		pointer = 0;
		for(int e = 0; e<3; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+1][0],(int)xsys[pointer+1][1]);
			bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+5][0],(int)xsys[pointer+5][1]);
			pointer++;
		}
		pointer = 0;
		for(int e = 0; e<4; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+4][0],(int)xsys[pointer+4][1]);
			pointer++;
		}
		pointer = 0;
		bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+3][0],(int)xsys[pointer+3][1]);
		bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+7][0],(int)xsys[pointer+7][1]);
		*/
		
		//basic rotate
		
		xsys = BasicRotate(g, xsys,10);	
		pointer = 0;
		for(int e = 0; e<3; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+1][0],(int)xsys[pointer+1][1]);
			bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+5][0],(int)xsys[pointer+5][1]);
			pointer++;
		}
		pointer = 0;
		for(int e = 0; e<4; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+4][0],(int)xsys[pointer+4][1]);
			pointer++;
		}
		pointer = 0;
		bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+3][0],(int)xsys[pointer+3][1]);
		bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+7][0],(int)xsys[pointer+7][1]);
		
		return array;
	}
	
	
	public double[][] BasicScale(Graphics g, double[][] array, int sX, int sY)
    {
		double[][] a =  array;
    	int Sx = sX;
    	int Sy = sY; 
    	
    	//multiply
    	
    	for(int i = 0; i <8; i++)
    	{
    		a[i][0]*=Sx;
    		a[i][1]*=Sy;
    	}
    	return array;
    }
	  public double[][] BasicTranslate(Graphics g, double[][] array, int tex, int tye)
	    {
	    	double[][] a =  array;
	    	int Tx = tex;
	    	int Ty = tye; 
	    	
	    	//multiply
	    	for(int i = 0; i <8; i++)
	    	{
	    		a[i][0]+=Tx;
	    		a[i][1]+=Ty;
	    	}
	    	
	    
	    		
	    	return a;
	    }
	  
	  public double[][] BasicRotate(Graphics g, double[][] array, int ang)
	    {
		  	double[][] a =  array;
	    	int angle = ang;
	    	
	    	//multiply
	    	for(int i = 0; i <8; i++)
	    	{
	    		double temp = a[i][0];
	    		
	    		a[i][0] = (int) (array[i][0]*Math.cos(Math.toRadians(angle))+ array[i][1]*Math.sin(Math.toRadians(angle)));
	    		a[i][1]= (int) (-temp*Math.sin(Math.toRadians(angle))+ array[i][1]*Math.cos(Math.toRadians(angle)));
	    		
	    		
	    	}
	    	/*temp = array[0];
	    	array[0] = (int) (array[0]*Math.cos(Math.toRadians(angle))+ array[1]*Math.sin(Math.toRadians(angle)));
	    	array[1] = (int) (-temp*Math.sin(Math.toRadians(angle))+ array[1]*Math.cos(Math.toRadians(angle)));
	    	temp = array[2];
	    	array[2] = (int) (array[2]*Math.cos(Math.toRadians(angle))+ array[3]*Math.sin(Math.toRadians(angle)));
	    	array[3] = (int) (-temp*Math.sin(Math.toRadians(angle))+ array[3]*Math.cos(Math.toRadians(angle)));*/
	    	
	    //	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
	    	return array;
	    }
	
	public void bresenhamAlg(Graphics g, int x1, int y1, int x2, int y2) {
    	if(x1 == x2)
		{
			x2 = x2 + 1;
		}
		else if (y1 == y2)
		{
			y2 = y2 + 1;
		}
    	
    	int dx, dy, e, x, y;
    	dx = x2-x1;
    	dy = y2-y1;
    	e = 2*dy - dx;
    	x = 0;
    	y = 0;
    	int yInc = 1;
    	int xInc = 1;
    	//case 1: dx and dy are negative 
    	if(dx < 0 && dy < 0)
    	{
    		//System.out.println("case 1");
    		int tempX = x1; 
    		x1 = x2;
    		x2 = tempX;
    		int tempY = y1; 
    		y1 = y2;
    		y2 = tempY;	
    		dx = dx*-1;
    		dy = dy*-1;	
    	}
    	//case 2: dy negative dx positive 
    	else if(dx > 0 && dy < 0)
    	{
    		//case 2.1 y1 > y2
    			int tempX = x1; 
        		x1 = x2;
        		x2 = tempX;
        		int tempY = y1; 
        		y1 = y2;
        		y2 = tempY;
        		xInc = -1;
        		dy= dy*-1;
    		
    	}
    	//case 3: dx negative dy positive
    	else if(dx < 0 && dy > 0)
    	{
    		//case 3.1 x1>x2
    		
    		//	System.out.println("case 3.1");
    			int tempX = x1; 
        		x1 = x2;
        		x2 = tempX;
        		int tempY = y1; 
        		y1 = y2;
        		y2 = tempY;
        		yInc = -1;
        		dx = dx*-1;
        }
    	x = x1;
    	y = y1;
    	//Stretchy X -- given
    	
    	if(Math.abs(dx)>=Math.abs(dy)) {
    		
    	while(x!=x2 || x > Math.max(x1, x2))
    	{
    		
    		if(e>=0)
    		{
    			g.drawLine(x, y, x, y);
    			
    			y = y + yInc;
    			e = e + 2*dy - 2*dx;
    		}
    		else
    		{
    			g.drawLine(x, y, x, y);
    			
    			e = e + 2*dy;
    			
    		}
    		x = x + xInc;
    	}
    	
    	}
    	
    	//stretchy Y
    	else{
    		
    	
    	while(y!=y2 || y> Math.max(y1, y2))
    	{
    		
    		if(e>=0)
    		{
    			g.drawLine(x, y, x, y);
    			
    			x = x + xInc;
    			
    			e = e + 2*dx - 2*dy;
    		}
    		else
    		{
    			g.drawLine(x, y, x, y);
    		
    			e = e + 2*dx;
    			
    		}
    		y = y + yInc;
    	}
    	}
    }
   
	public double [][] cubeBuilderPlay(Graphics g)
	{
		double d = 60;
		double s = 30;
		double[][] array = 
			{
					{-1,1,-1,1},
					{1, 1, -1, 1},
					{1, -1, -1, 1},
					{-1, -1, -1, 1},
					{-1, 1, 1, 1}, 
					{1, 1, 1, 1},
					{1, -1, 1, 1},
					{-1, -1, 1, 1}
			};
		double[][] n = {{4, 0, 0, 0},
		    	 		{0, 4, 0, 0},
		    	 		{0, 0, 1, 0}, 
		    	 		{0, 0, 0, 1}};
		double [][]t1 = 
			{
			{ 1 ,0,   0,0},
			{ 0, 1,   0,0},
			{ 0, 0,   1,0},
			{-6,-8,-7.5,1}
			};
				
		double [][]t2 = 
			{
			{1 ,0, 0, 0},
			{0, 0,-1, 0},
			{0, 1, 0, 0},
			{0, 0, 0, 1}
			};
		double [][]t3 = 
			{
			{ -.8 ,0, .6,0},
			{   0, 1,  0,0},
			{ -.6, 0,-.8,0},
			{   0, 0,  0,1}
			};
				
		double [][]t4 = 
				
			{
			{ 1 ,0, 0 ,0},
			{   0, .8, .6,0},
			{ 0, -.6,.8,0},
			{   0, 0,  0,1}}
			;
		double [][]t5 = 
				
			{
			{ 1, 0, 0,0},
			{ 0, 4, 0,0},
			{ 0, 0,-4,0},
			{ 0, 0, 0,1}}
			;
		
		//matrix multiplication
		double v[][] = matrixMultiplication(t1,t2);
		double v3[][] = matrixMultiplication(v,t3);
		double v4[][] =matrixMultiplication(v3,t4); 
		double v5[][] =matrixMultiplication(v4,t5); 
		double vn[][] =matrixMultiplication(v5,n); 
		double [][]vvnx = matrixMultiplication(array,vn);
		/*for(int i = 0; i <4; i++)
		/*{
			for(int k = 0; k<4; k++)
			{
				
				System.out.println(vvnx[i][k]);
			}
		}*/
		double xc;
		double yc; 
		double zc;
		double[][] xsys = new double[8][2];
		double ys;
		double vsx;
		double vcx;
		double vcy;
		double vsy;
		
		
		
		for(int l = 0; l<8; l++)
		{
			System.out.println("xc: " + vvnx[l][0] + "  zc: " + vvnx[l][2]);
		
			xsys[l][0] = ((vvnx[l][0]/vvnx[l][2])*100)+100;
		
			
			System.out.println("yc: " + vvnx[l][1] + "  zc: " + vvnx[l][2]);	
			xsys[l][1] = ((vvnx[l][1]/vvnx[l][2])*100)+100;
			System.out.println("xs: " + xsys[l][0] + "  ys: " + xsys[l][1]);
		}			
		int pointer = 0;
		for(int e = 0; e<3; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+1][0],(int)xsys[pointer+1][1]);
			bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+5][0],(int)xsys[pointer+5][1]);
			pointer++;
		}
		pointer = 0;
		for(int e = 0; e<4; e++)
		{
			bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+4][0],(int)xsys[pointer+4][1]);
			pointer++;
		}
		pointer = 0;
		bresenhamAlg(g, (int) xsys[pointer][0],(int)xsys[pointer][1], (int)xsys[pointer+3][0],(int)xsys[pointer+3][1]);
		bresenhamAlg(g, (int) xsys[pointer+4][0],(int)xsys[pointer+4][1], (int)xsys[pointer+7][0],(int)xsys[pointer+7][1]);
			
		
		
		return array;
	}
	 
	public double[][] matrixMultiplication(double [][] a, double [][] b)
	{
		int rowsInA = a.length;
	       int columnsInA = a[0].length;
	       int columnsInB = b[0].length;
	       double[][] c = new double[rowsInA][columnsInB];
	       for (int i = 0; i < rowsInA; i++) {
	           for (int j = 0; j < columnsInB; j++) {
	               for (int k = 0; k < columnsInA; k++) {
	                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
	               }
	           }
	       }
	       return c;
	}
		
	
	public static void main (String [] args) throws Exception
	{
		Canvas canvas= new WorldCoordinateSystem();
        JFrame jf = new JFrame();
        jf.setSize(1000,1000);
        jf.setVisible(true);
        jf.add(new WorldCoordinateSystem());
        jf.setVisible(true);
        canvas.setSize(1000,1000); 
        
	}
	
	
}
