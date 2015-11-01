//TODO's @ 87
//Also add a reset button and a text field displaying who's turn it is (could also say winner/draw)

import javax.swing.*;
import java.awt.event.*;

public class GUIXandoOld extends JFrame implements ActionListener
{
	boolean isCrosses=true;

	JTabbedPane tp = new JTabbedPane();
	
	/////////
	JPanel pnlInfo = new JPanel(null); //Uses a null layout
	JButton buttons[] = new JButton[9];
	///////
	
	public void runGUI()
	{
		this.setTitle("Noughts and Crosses");
		this.setSize(800, 800);
		this.setLocation(10, 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createInfoPanel();
		
		tp.addTab("Noughts and Crosses", pnlInfo);
		
		this.add(tp);
		this.setVisible(true);
	}
	
	public void createInfoPanel()
	{
		int r=100;
		int i=0;
		int c=0;
		while(r<203)
		{
			c=100;
			while(c<203)
			{
				buttons[i] = new JButton(String.valueOf(i));
				buttons[i].setSize(50, 50);
				buttons[i].setLocation(c, r);
				buttons[i].setText("");
				buttons[i].addActionListener(this);
				pnlInfo.add(buttons[i]);
				i++;
				c=c+51;
			}
			r=r+51;
		}
	}
	
	public boolean isDraw()
	{
		int iCountSquares=0;
		
		for(int i=0;i<9;i++)
		{
			if(buttons[i].getText().equals("")==false)
			{
				iCountSquares++;
			}
		}
		if(iCountSquares==9)
		{
			return true;
		}
		return false;
	}
	
	final int[][] lines = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
							{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
							{0, 4, 8}, {2, 4, 6}} // diagonals
							
	
	public String checkWin(int a, int b, int c)
	{
		if(buttons[a].getText()==buttons[b].getText() && buttons[b].getText()==buttons[c].getText() && buttons[a].getText().equals("")==false)
		{
			return buttons[a].getText();
		}
		else
		{
			return null;
		}
	}
	
	public String hasGameFinished() //TODO- update this to work with new button/int grid layout
	{
		for (int i = 0; i < 8; i++ )
		{
			String result=checkWin(lines[i][0], lines[i][1], lines[i][2]);
			if (result != null) 
			{
				return result;
			}
		}
		
		//row win conditions
		String result;
		result=checkWin(0, 1, 2);
		if (result != null) return result;
		result=checkWin(3, 4, 5);
		if (result != null) return result;
		
		if(buttons[0].getText()==buttons[1].getText() && buttons[1].getText()==buttons[2].getText() && buttons[2].getText().equals("")==false)
		{
			return buttons[2].getText();
		}
		else if(buttons[3].getText()==buttons[4].getText() && buttons[4].getText()==buttons[5].getText() && buttons[5].getText().equals("")==false)
		{
			return buttons[5].getText();
		}
		else if(buttons[6].getText()==buttons[7].getText() && buttons[7].getText()==buttons[8].getText() && buttons[8].getText().equals("")==false)
		{
			return buttons[8].getText();
		}
		//column win conditions
		else if(buttons[0].getText()==buttons[3].getText() && buttons[3].getText()==buttons[6].getText() && buttons[6].getText().equals("")==false)
		{
			return buttons[6].getText();
		}
		else if(buttons[1].getText()==buttons[4].getText() && buttons[4].getText()==buttons[7].getText() && buttons[7].getText().equals("")==false)
		{
			return buttons[7].getText();
		}
		else if(buttons[2].getText()==buttons[5].getText() && buttons[5].getText()==buttons[8].getText() && buttons[8].getText().equals("")==false)
		{
			return buttons[8].getText();
		}
		//Diagonal win conditions
		else if(buttons[0].getText()==buttons[4].getText() && buttons[4].getText()==buttons[8].getText() && buttons[8].getText().equals("")==false)
		{
			return buttons[8].getText();
		}
		else if(buttons[6].getText()==buttons[4].getText() && buttons[4].getText()==buttons[2].getText() && buttons[2].getText().equals("")==false)
		{
			return buttons[2].getText();
		}
		//Draw
		else if(this.isDraw()==true)
		{
			return "d";
		}
		else
		{
			return "no"; //This shouldn't really need to be returned but w/e
		}
	}
	
	public void disableButtons()
	{
		for(int i=0;i<9;i++)
		{
			buttons[i].setEnabled(false);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//puts getSource into an object, makes sure it is the right type
		//means that the buttons all do the same thing
		Object source = e.getSource();
		if(source instanceof JButton)
		{
			JButton gridButton = (JButton)source;
			if(isCrosses==true)
			{
				gridButton.setText("X");
				isCrosses=false;
			}
			else if(isCrosses==false)
			{
				gridButton.setText("O");
				isCrosses=true;
			}
			else
			{
				System.out.println("What?");
			}
			gridButton.setEnabled(false);
				
		}
		if(hasGameFinished().equals("d"))
		{
			System.out.println("It's a draw!");
			disableButtons();
		}
		else if(hasGameFinished().equals("X")||hasGameFinished().equals("O"))
		{
			System.out.println(hasGameFinished()+"'s, has won!");
			disableButtons();
		}
	}

	public static void main(String[] args)
	{
		GUIXandoOld gtemp = new GUIXandoOld();
		gtemp.runGUI();
	}
}