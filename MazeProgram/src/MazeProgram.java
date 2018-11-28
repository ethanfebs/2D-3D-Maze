import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class MazeProgram extends JPanel implements KeyListener,MouseListener
{
	JFrame frame;
	//declare an array to store the maze
	int[][] maze = new int[15][15];
	int x=20,y=20;
	int finishX=260,finishY=260;
	int facing = 0;
	int moves = 0;
	boolean finished=false;
	//facing right=0,down=1,left=2,up=3

	public MazeProgram()
	{
		setBoard();
		frame=new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
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



		for(int a=0;a<15;a++){
			for(int b=0;b<15;b++){
				if(maze[a][b]==1){
					g.setColor(Color.ORANGE);
					g.fillRect(a*20,b*20,20,20);
				}

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

		}


		//x & y would be used to located your
									//playable character
									//values would be set below

		//other commands that might come in handy
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(Color.GREEN);
		if(finished)
			g.drawString("YOU FINISHED!",20,325);
		if(!finished)
			g.drawString("Moves: "+moves,20,340);
		else
			g.drawString("Moves to Finish: "+moves,20,340);
				//you can also use Font.BOLD, Font.ITALIC, Font.BOLD|Font.Italic
		//g.drawOval(x,y,10,10);
		//g.fillRect(x,y,100,100);
		//g.fillOval(x,y,10,10);
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
		MazeProgram app=new MazeProgram();
	}
}