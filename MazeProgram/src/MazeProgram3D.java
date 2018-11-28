import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.Image ;
import java.awt.MediaTracker;

public class MazeProgram3D extends JPanel implements KeyListener,MouseListener
{
	JFrame frame;
	//declare an array to store the maze
	int[][] maze = new int[15][15];
	int x=20,y=20;
	int finishX=260,finishY=260;
	int facing = 0;
	int moves = 0;
	boolean finished=false;
	int flashlightOn=0;
	int cookieX;
	int cookieY;
	//facing right=0,down=1,left=2,up=3

	public MazeProgram3D()
	{
		setBoard();
		frame=new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(417,800);
		frame.setVisible(true);
		frame.addKeyListener(this);
		//this.addMouseListener(this);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);	//this will set the background color
		g.fillRect(0,0,1000,800);

		//drawBoard here!



		/* 2D Method

		for(int a=0;a<15;a++){
			for(int b=0;b<15;b++){
				if(maze[a][b]==1){
					g.setColor(Color.WHITE);
					g.fillRect(a*20,b*20,20,20);
				}
				g.setColor(Color.GREEN);
			}
		}

		g.setColor(Color.GREEN);
		g.fillOval(finishX+5,finishY+5,10,10);

			g.setColor(Color.RED);
		g.fillOval(x,y,20,20);
		g.setColor(Color.BLACK);

		switch( facing ){

			case 0:
				g.fillPolygon(new int[]{x+10,x+20,x+20},new int[]{y+10,y+5,y+15},3);
				break;
			case 1:
				g.fillPolygon(new int[]{x+10,x+5,x+15},new int[]{y+10,y+20,y+20},3);
				break;
			case 3:
				g.fillPolygon(new int[]{x+10,x+5,x+15},new int[]{y+10,y,y},3);
				break;
			case 2:
				g.fillPolygon(new int[]{x+10,x,x},new int[]{y+10,y+5,y+15},3);
				break;

		}*/

		g.setColor(Color.BLUE);
		g.fillRect(0,600,420,200);

		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(Color.GREEN);
		if(finished)
			g.drawString("YOU FINISHED!",20,625);

		g.drawString("Moves: "+moves,20,640);

		//you can also use Font.BOLD, Font.ITALIC, Font.BOLD|Font.Italic
		//g.drawOval(x,y,10,10);
		//g.fillRect(x,y,100,100);
		//g.fillOval(x,y,10,10);

		int facingX=0;
		int facingY=0;

		switch( facing ){
			case 0:
			facingX=1;
			break;
			case 1:
			facingY=1;
			break;
			case 2:
			facingX=-1;
			break;
			case 3:
			facingY=-1;
			break;
		}

		/*g.setColor(Color.GREEN);
		g.fillPolygon(new int[]{350,400,400,350},new int[]{75,0,600,525},4);
		g.fillPolygon(new int[]{0,50,50,0},new int[]{0,75,525,600},4);
		g.setColor(Color.YELLOW);
		g.fillPolygon(new int[]{50,100,100,50},new int[]{75,150,450,525},4);
		g.fillPolygon(new int[]{300,350,350,300},new int[]{150,75,525,450},4);
		g.setColor(Color.GRAY);
		g.fillPolygon(new int[]{50,100,100,50},new int[]{75,150,450,525},4);
		g.fillPolygon(new int[]{300,350,350,300},new int[]{150,75,525,450},4);*/


		g.setColor(Color.GRAY);

		boolean wallAhead=false;
		int spacesAhead=0;


		if(maze[x/20+facingX][y/20+facingY]==1){//if wall 1 space ahead
			g.fillRect(0,0,400,600);
			wallAhead=true;
		}

		g.setColor(Color.CYAN);
		g.drawRect(0,0,400,600);
		spacesAhead++;

		while((!wallAhead)&&(spacesAhead<(3+flashlightOn))){

			g.setColor(Color.GRAY);

			if(maze[x/20+(spacesAhead+1)*facingX][y/20+(spacesAhead+1)*facingY]==1){//if wall 2 spaces ahead
				g.fillRect(spacesAhead*50,spacesAhead*75,400-spacesAhead*100,600-spacesAhead*150);
				wallAhead=true;
				g.setColor(Color.CYAN);
				g.drawRect(spacesAhead*50,spacesAhead*75,400-spacesAhead*100,600-spacesAhead*150);
				g.setColor(Color.GRAY);
			}

			if(maze[x/20+spacesAhead*facingX-facingY][y/20+spacesAhead*facingY+facingX]==1){//1 space ahead, right
				g.fillPolygon(new int[]{400-spacesAhead*50,400-(spacesAhead-1)*50,400-(spacesAhead-1)*50,400-spacesAhead*50},new int[]{spacesAhead*75,(spacesAhead-1)*75,600-(spacesAhead-1)*75,600-spacesAhead*75},4);
				g.setColor(Color.CYAN);
				g.drawPolygon(new int[]{400-spacesAhead*50,400-(spacesAhead-1)*50,400-(spacesAhead-1)*50,400-spacesAhead*50},new int[]{spacesAhead*75,(spacesAhead-1)*75,600-(spacesAhead-1)*75,600-spacesAhead*75},4);
				g.setColor(Color.GRAY);
			}else{
				g.setColor(Color.WHITE);
				g.fillPolygon(new int[]{400-spacesAhead*50,450-spacesAhead*50,450-spacesAhead*50},new int[]{600-spacesAhead*75,600-spacesAhead*75,675-spacesAhead*75},3);
				g.setColor(Color.GRAY);
				g.fillRect(400-spacesAhead*50,spacesAhead*75,50,600-150*spacesAhead);
				g.setColor(Color.CYAN);
				g.drawRect(400-spacesAhead*50,spacesAhead*75,50,600-150*spacesAhead);
				g.setColor(Color.GRAY);

			}
			if(maze[x/20+spacesAhead*facingX+facingY][y/20+spacesAhead*facingY-facingX]==1){//1 space ahead, left

				g.fillPolygon(new int[]{(spacesAhead-1)*50,spacesAhead*50,spacesAhead*50,(spacesAhead-1)*50},new int[]{(spacesAhead-1)*75,spacesAhead*75,600-spacesAhead*75,600-(spacesAhead-1)*75},4);
				g.setColor(Color.CYAN);
				g.drawPolygon(new int[]{(spacesAhead-1)*50,spacesAhead*50,spacesAhead*50,(spacesAhead-1)*50},new int[]{(spacesAhead-1)*75,spacesAhead*75,600-spacesAhead*75,600-(spacesAhead-1)*75},4);
				g.setColor(Color.GRAY);
			}else{
				g.setColor(Color.WHITE);
				g.fillPolygon(new int[]{spacesAhead*50-50+1,spacesAhead*50,spacesAhead*50-50+1},new int[]{600-spacesAhead*75,600-spacesAhead*75,675-spacesAhead*75},3);
				g.setColor(Color.GRAY);
				g.fillRect(spacesAhead*50-50,spacesAhead*75,50,600-150*spacesAhead);
				g.setColor(Color.CYAN);
				g.drawRect(spacesAhead*50-50,spacesAhead*75,50,600-150*spacesAhead);
				g.setColor(Color.GRAY);
			}





			spacesAhead++;


		}

		g.setColor(Color.WHITE);
		for(int x=1;x<spacesAhead;x++){
			g.fillPolygon(new int[]{x*50,400-x*50,400-(x-1)*50,(x-1)*50},new int[]{600-x*75,600-x*75,600-(x-1)*75,600-(x-1)*75},4);
		}

		g.setColor(Color.RED);
		for(int x=1;x<spacesAhead;x++){

			//g.drawRect(x*50,x*75,400-x*100,600-x*150);
		}




		/*if(maze[x/20+facingX][y/20+facingY]==1){//if wall 1 space ahead
			g.fillRect(0,0,400,600);

			g.setColor(Color.RED);
			g.drawRect(0,0,400,600);

		}else if(maze[x/20+2*facingX][y/20+2*facingY]==1){//if wall 2 spaces ahead
			g.fillRect(50,75,300,450);

			if(maze[x/20+facingX-facingY][y/20+facingY+facingX]==1)//1 space ahead, right
				g.fillPolygon(new int[]{350,400,400,350},new int[]{75,0,600,525},4);
			if(maze[x/20+facingX+facingY][y/20+facingY-facingX]==1)//1 space ahead, left
				g.fillPolygon(new int[]{0,50,50,0},new int[]{0,75,525,600},4);

			g.setColor(Color.RED);
			g.drawRect(0,0,400,600);
			g.drawRect(50,75,300,450);


			if((x==240)&&(y==260))
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.WHITE);
			g.fillPolygon(new int[]{50,350,400,0},new int[]{525,525,600,600},4);

		}else{
			if(maze[x/20+3*facingX][y/20+3*facingY]==1){//if wall 3 spaces ahead
				g.fillRect(100,150,200,300);
			}

			if(maze[x/20+facingX-facingY][y/20+facingY+facingX]==1)//1 space ahead, right
				g.fillPolygon(new int[]{350,400,400,350},new int[]{75,0,600,525},4);
			if(maze[x/20+facingX+facingY][y/20+facingY-facingX]==1)//1 space ahead, left
				g.fillPolygon(new int[]{0,50,50,0},new int[]{0,75,525,600},4);


			if(maze[x/20+2*facingX-facingY][y/20+2*facingY+facingX]==1)//2 spaces ahead, right
				g.fillPolygon(new int[]{300,350,350,300},new int[]{150,75,525,450},4);
			if(maze[x/20+2*facingX+facingY][y/20+2*facingY-facingX]==1)//2 spaces ahead, left
				g.fillPolygon(new int[]{50,100,100,50},new int[]{75,150,450,525},4);

			g.setColor(Color.WHITE);

			if((x==220)&&(y==260))
				g.setColor(Color.GREEN);
			g.fillPolygon(new int[]{100,300,350,50},new int[]{450,450,525,525},4);
			g.setColor(Color.WHITE);
			g.fillPolygon(new int[]{50,350,400,0},new int[]{525,525,600,600},4);

			g.setColor(Color.RED);
			g.drawRect(0,0,400,600);
			g.drawRect(50,75,300,450);
			g.drawRect(100,150,200,300);
		}*/


		//COMPASS STARTS HERE

		g.setColor(Color.GRAY);
		g.fillOval(20,670,100,50);
		g.fillRect(20,680,100,15);
		g.setColor(Color.BLACK);
		g.fillOval(20,655,100,50);
		g.setColor(Color.WHITE);
		g.drawOval(20,655,100,50);
		g.setColor(Color.RED);

		switch( facing ){
		case 3:
			g.fillRect(68,655,4,25);
			break;
		case 1:
			g.fillRect(68,680,4,25);
			break;
		case 2:
			g.fillRect(20,678,50,4);
			break;
		case 0:
			g.fillRect(70,678,50,4);
			break;

		}

		//COMPASS ENDS HERE

		//FLASHLIGHT STARTS HERE

		if(flashlightOn==1){
			g.setColor(Color.YELLOW);
			g.fillPolygon(new int[]{310,295,325},new int[]{660,605,605},3);
		}
		g.setColor(Color.BLACK);
		g.fillRect(300,650,20,80);
		g.fillPolygon(new int[]{300,295,325,320},new int[]{650,640,640,650},4);


		//FLASHLIGHT ENDS HERE

		//MAP STARTS HERE

		for(int a=0;a<15;a++){
			for(int b=0;b<15;b++){
				if(maze[a][b]==1){
					g.setColor(Color.ORANGE);
					g.fillRect(140+a*8,630+b*8,8,8);
				}
				else{
					g.setColor(Color.YELLOW);
					g.fillRect(140+a*8,630+b*8,8,8);
				}

			}
		}

		//MAP ENDS HERE




		//g.drawRect();

	}
	public void setBoard()
	{
		//choose your maze design

		//pre-fill maze array here

		File name = new File("resources/maze.txt");
		int r=0;
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			int b=0;
			while( (text=input.readLine())!= null)
			{
				System.out.println(text);
				//your code goes in here to chop up the maze design
				//fill maze array with actual maze stored in text file
				for(int a=0;a<15;a++){
					if(text.charAt(a)=='*'){
						System.out.println("YES");
						maze[a][b]=1;
					}else
						maze[a][b]=0;
				}

			b++;

			}


		}
		catch (IOException io)
		{
			System.err.println("File error");
		}
	}
	public void keyPressed(KeyEvent e)
	{
		   int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		           	if(!finished){
		           	switch( facing ){
						case 0:
						if(maze[x/20+1][y/20]!=1){
						x+=20;
						moves++;}
						break;

						case 1:
						if(maze[x/20][y/20+1]!=1){
						y+=20;
						moves++;}
						break;

						case 2:
						if(maze[x/20-1][y/20]!=1){
						x-=20;
						moves++;}
						break;

						case 3:
						if(maze[x/20][y/20-1]!=1){
						y-=20;
						moves++;}
						break;






					}
				}

					if((x==finishX)&&(y==finishY)){
						finished=true;
					 }

		            repaint();
		            break;
		        case KeyEvent.VK_LEFT:
		            facing--;
		            if(facing<0)
		            	facing+=4;
		            facing=facing%4;
		            repaint();
		            break;
		        case KeyEvent.VK_RIGHT :
		            facing++;

		            facing=facing%4;
		            repaint();
            		break;
            	case KeyEvent.VK_ENTER :
            		if(flashlightOn==1)
            			flashlightOn--;
            		else
            			flashlightOn++;
            		repaint();
            		break;
		}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public static void main(String args[])
	{
		MazeProgram3D app=new MazeProgram3D();
	}
}