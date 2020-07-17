import java.awt.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
 
public class lineDrawer extends Canvas {
	Graphics2D gFrame; 
	int width =500;
	int height=500;
	int n;
	int[] array = new int[4];
	//int[] datalines = new int[4];
	//String file = "/Users/madelinedonlan/Desktop/datalines.rtf";
	
    public void paint(Graphics g) {
    	//User input for number of lines
    	Scanner reader = new Scanner(System.in);  
    	/*System.out.println("How many lines would you like to make? ");
    	int n = reader.nextInt();
    	
    	*/
    	System.out.println("Welcome to linedrawer. What is the name of the file with the coordinate information?");
    	
    	String file = "/Users/madelinedonlan/Desktop/"+ reader.nextLine();
    	int numLines = 2;
		try {
			numLines = numLines();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Welcome to linedrawer. What is the name of the file with the coordinate information?");
    	lineDrawer newLine = new lineDrawer();
    	//apply transformation try
    	int[] datalines = new int[4];
    	try{ReadFile(g);}
    	catch(Exception e)
    	{System.out.println("exception");}
    	System.out.println("The number of lines in your file is: " + numLines);
    	/*System.out.println("Which example would you like to see? "
    			+ "Basic Rotate, "
    			+ "Basic Translate, "
    			+ "Basic Scale, "
    			+ "Scale, "
    			+ "Rotate");*/
    	String input = reader.nextLine();
    	double x1 = 10;
	    double y1 = 10;
	    double x2 = 20;
	    double y2 = 30;
    	
    	
      	for(int j = 0; j<numLines; j++)
    	{
    				 x1 =
    				Math.random()*width;
    	    		 y1 =
    				Math.random()*height; 
    	    		 x2 = 
    				Math.random()*width;
    	    		 y2 = 
    	    		Math.random()*height;	
			//newLine.experiment(g);
			//newLine.basicAlg(g, x1, y1, x2, y2);
    	}	
    	
    	//bresenham
    	for(int j = 0; j<numLines; j++)
    	{
    		/*double x1 =
			Math.random()*width;
    		double y1 =
			Math.random()*height; 
    		double x2 = 
			Math.random()*width;
    		double y2 = 
    		Math.random()*height;
    		//newLine.experiment(g);*/
    		
    		x1 = 30;
    	    y1 = 30;
    	    x2 = 100;
    	    y2 = 100;
    	   // newLine.bresenhamAlg(g, (int)x1, (int)y1, (int)x2, (int)y2);
    	    int tx = 3;
    	    int ty = 5;
    	   newLine.bresenhamAlg(g, (int)x1, (int)y1, (int)x2, (int)y2); 
    	   //BasicTranslate(g, (int)x1,(int)y1,(int)x2,(int)y2,20,100);
    	  // BasicScale(g, (int)x1,(int)y1,(int)x2,(int)y2,tx,ty);
    	  // newLine.bresenhamAlg(g, (int)x1, (int)y1, (int)x2, (int)y2);
    	  // BasicRotate(g,(int)x1,(int)y1,(int)x2,(int)y2, 10);
    	   
    	    Scale (g, (int) x1, (int) y1, (int) x2, (int) y2, (int) tx, (int) ty, 60, 60);
    	    //Rotate(g, (int)x1,(int)y1,(int)x2,(int)y2,45,60,60);
    	    //newLine.experiment(g);
    		//newLine.bresenhamAlg(g,x1, y1, x2, y2);
    		
    	}	
    	
    }
    
    public void basicAlg(Graphics g, double x1, double y1, double x2, double y2) {

			//random coordinates
			/*double x1 = Math.random()*width;
			double y1 = Math.random()*height; 
			double x2 = Math.random()*width;
			double y2 = Math.random()*height;*/
			if(x1 == x2)
			{
				x2 = x2 + 1;
			}
			else if (y1 == y2)
			{
				y2 = y2 + 1;
			}
			
			//slope and intercept
			double m = (y2-y1)/(x2-x1);
			double b = y1 - m*x1;
			
			
			//starting point
			double x = x1; 
			double y = y1;;
			double startX = 0;
			double startY = 0;
			if(x2>x1) {
				startX = x1;
			}
			else {
				startX = x2;
			}
			if (y2>y1){
				startY = y1;
			}
			else 
				startY = y2;
			//do I have to do the absolute value if i find the startx, and y
			double deltaX = Math.abs(x2-x1);
			double deltaY = Math.abs(y2-y1);
			
			
			//for horizontal stretching
			if(deltaX>=deltaY) {
				//System.out.println("horizontal stretching");
				double yInc = m;
				x = startX;
				y = startY;
				for(int i = 0; i < (deltaX-1); i++) {
					//System.out.println("horizontal stretching for loop");
					x = x+1;
					y = yInc + y;
					g.drawLine((int)x,(int)y,(int)x,(int)y);
					}
				}
			//for vertical stretching
			else {
				//System.out.println("vartical stretching");
				for(int i = 0; i < (deltaY-1); i++) {
					//System.out.println("vartical stretchingfor loop");
					y = startY+i;
					x= (y-b)/m;
					y = Math.floor(y);
					g.drawLine((int)x,(int)y,(int)x,(int)y);
					}
				
			}
			
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
   
    public int[] BasicTranslate(Graphics g, int x, int y, int w, int z, int tex, int tye)
    {
    	int[] array = {x,y,w,z};
    	int Tx = tex;
    	int Ty = tye; 
    	
    	//multiply
    	
    	array[0] += Tx;
    	array[1] += Ty;
    	array[2] += Tx;
    	array[3] += Ty;
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);	
    	return array;
    }
    public int[] BasicScale(Graphics g, int x, int y, int w, int z, int sX, int sY)
    {
    	int[] array = {x,y,w,z};
    	int Sx = sX;
    	int Sy = sY; 
    	
    	//multiply
    	
    	array[0] *= Sx;
    	array[1] *= Sy;
    	array[2] *= Sx;
    	array[3] *= Sy;
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    	return array;
    }
    public int[] BasicRotate(Graphics g, int x, int y, int w, int z, int ang)
    {
    	int[] array = {x,y,w,z};
    	int angle = ang;
    	int temp = 0;
    	//multiply
    	temp = array[0];
    	array[0] = (int) (array[0]*Math.cos(Math.toRadians(angle))+ array[1]*Math.sin(Math.toRadians(angle)));
    	array[1] = (int) (-temp*Math.sin(Math.toRadians(angle))+ array[1]*Math.cos(Math.toRadians(angle)));
    	temp = array[2];
    	array[2] = (int) (array[2]*Math.cos(Math.toRadians(angle))+ array[3]*Math.sin(Math.toRadians(angle)));
    	array[3] = (int) (-temp*Math.sin(Math.toRadians(angle))+ array[3]*Math.cos(Math.toRadians(angle)));
    	
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    	return array;
    }
    public void Scale (Graphics g, int x, int y, int w, int z, int sX, int sY, int cX, int cY)
    {
    	int[] array = {x,y,w,z};
    	System.out.println(array[0]);
    	
    	int Sx = sX;
    	int Sy = sY;
    	array = BasicTranslate(g, x, y, w, z, cX, cY);
    	System.out.println(array[0]);
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    	
    	//multiply
    	array = BasicScale(g, array[0], array[1], array[2], array[3], Sx, Sy);
    	System.out.println(array[0]);
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    	array = BasicTranslate(g, array[0], array[1], array[2], array[3], -cX, -cY);
    	System.out.println(array[0]);
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);	
    	
    }
    public void Rotate(Graphics g, int x, int y, int w, int z, int ang, int cX, int cY)
    {
    	int[] array = {x,y,w,z};
    	int angle = ang;
    	int temp = 0;
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    	array = BasicTranslate(g,array[0], array[1], array[2], array[3], cX, cY);
    	array = BasicRotate(g, array[0], array[1], array[2], array[3], ang);
    	array = BasicTranslate(g,array[0], array[1], array[2], array[3], -cX, -cY);
    	
    	bresenhamAlg(g, array[0], array[1], array[2], array[3]);
    }
    
    /*public void Displaypixels(Graphics g) throws IOException
    {
    	try {
			ReadFile(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int[] array = ReadFile(g);
    	int i = 0; 
    	for(int j = 0; j<numLines()*4; j+=4)
    	{
    		System.out.println(array[j]);
    		bresenhamAlg(g, array[j], array[j+1], array[j+2], array[j+3]);
    	}
    	
    }*/
    public int numLines() throws FileNotFoundException
    {
    	
   		int counter= 0;
   		Scanner scan = new Scanner(new File("/Users/madelinedonlan/Desktop/datalines.txt"));
		while (scan.hasNextLine()) 
		{
			counter++;
			scan.nextLine();
		//	System.out.println(counter);
		}
		return counter;
    }
    
    public int[] ReadFile(Graphics g) throws IOException
    {
    		String s;
    		int i = numLines();
    		Scanner scan = new Scanner(new File("/Users/madelinedonlan/Desktop/datalines.txt"));
    		
    		int[] datalines = new int[i*4];
    		i= 0;
    		
    		for(int j = 0; j<numLines()*4; j++)
    		{
    			s = scan.next();
    			//System.out.println("data file " + s);
    			int integer = Integer.parseInt(s);
    			
    			datalines[i] = integer;
    			
    			//System.out.println("datalines[i]" + datalines[i]);
    			i++;
    			//System.out.println("j counter" + j);
    			
    		}
    		
    		
    		int c = 0;
    		int m = 0;
    		while(c<numLines())
    		{
    			//System.out.println(c);
    			//System.out.println(datalines[m]);System.out.println(datalines[m+1]);
    			//System.out.println(datalines[m+2]);System.out.println(datalines[m+3]);
    			
    			
    			
    			//bresenhamAlg(g, datalines[m], datalines[m+1], datalines[m+2], datalines [m+3]);
    			c++;
    			m+=4;
    		}
    		scan.close();
    		return datalines;
    }
   
    public void experiment(Graphics g) {
    	int x, y, width, height;
    	 x = 30;
    	 y = 30;
    	 width = 80;
    	 height = 80;
    	 int xInc = x;
    	 int yInc = y;
    	 int xDec = x + width;
    	 int yDec = y + height;
    	g.drawRect(x, y, width, height);
    	bresenhamAlg(g, x,y+height,x+width, y);
    	//basicAlg(g,x,y+height,x+width,y);
    	while (xInc<= x+width && yInc<=y+height) {
    		bresenhamAlg(g, x,yInc,xInc,y);
    		bresenhamAlg(g, xDec,y+height,x+width,yDec);
    		/*basicAlg(g,x,yInc,xInc,y);
    		basicAlg(g,xDec,y+height,x+width,yDec);*/
    		xInc ++;
    		yInc ++;
    		xDec--;
    		yDec--;
    		
    	}
    	
    	
    }
    public static void main(String[] args) throws Exception {
    	
    	Canvas canvas= new lineDrawer();
        JFrame jf = new JFrame();
        jf.setSize(900,900);
        jf.setVisible(true);
        jf.add(new lineDrawer());
        jf.setVisible(true);
        canvas.setSize(900,900); 
        
        
    }
    
    
}