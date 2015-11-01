import java.util.Scanner;

public class xandoComplete
{
	Scanner scan = new Scanner(System.in);
	
	String[][] displayGrid = new String[3][3];
	int[][] actualGrid = new int[3][3];
	
	boolean isCrosses=true;
	String noughtsOrCrosses="X";
	int userCol=0;
	int userRow=0;
	//This is just for copy paste reference of the loop.
	/*public void loopGrid()
	{
		for(int a=0;a<3;a++)
		{
			for(int b=0;b<3;b++)
			{
				grid[a][b]="";
			}
		}
	}*/
	
	public void populateGrids()
	{
		for(int a=0;a<3;a++)
		{
			for(int b=0;b<3;b++)
			{
				actualGrid[a][b] = 0;
				displayGrid[a][b]=". ";
			}
		}
		
	}
	
	public void printDisplayGrid()
	{
		System.out.println("\r\n");
		for(int a=0;a<3;a++)
		{
			for(int b=0;b<3;b++)
			{
				System.out.print(displayGrid[a][b]);
			}
			System.out.print("\r\n");
		}
	}
	public void printActualGrid() //This is only for trace purposes, shouldn't be needed in final version
	{
		for(int a=0;a<3;a++)
		{
			for(int b=0;b<3;b++)
			{
				System.out.print(actualGrid[a][b]);
			}
			System.out.print("\r\n");
		}
	}
	
	public void getInput()
	{
		System.out.println(noughtsOrCrosses+"'s, it's your turn!");
		System.out.println("Please enter the column(1, 2 or 3) you wish to place your "+noughtsOrCrosses+" in:");
		userCol = scan.nextInt();
		System.out.println("Please enter the row(1, 2 or 3) you wish to place your "+noughtsOrCrosses+" in:");
		userRow = scan.nextInt();
	}
	
	public void updateGrid()
	{
		//check both arrays for consistency, but there should never be a discrepancy
		if(noughtsOrCrosses.equals("X"))
		{
			boolean isValid=false;
			while(isValid==false)
			{   
				this.getInput();
				//Keeps asking until they make a valid choice
				if(userRow > 0 && userRow < 4 && userCol > 0 && userCol < 4)
				{
					//Checks if the the spot in the grid is empty
					if(displayGrid[userRow-1][userCol-1].equals(". ") && actualGrid[userRow-1][userCol-1] == 0)
					{
						displayGrid[userRow-1][userCol-1] = "X ";
						actualGrid[userRow-1][userCol-1] = 1;
						isValid=true;
					}
					//checks if the there is a nought there for a cross to go to
					else if(displayGrid[userRow-1][userCol-1].equals("O ") && actualGrid[userRow-1][userCol-1] == 4)
					{
						System.out.println("You can't put a cross over a nought!\r\nTry again!");
					}
					//makes sure there isn't already a cross there
					else if(displayGrid[userRow-1][userCol-1].equals("X ") && actualGrid[userRow-1][userCol-1] == 1)
					{
						System.out.println("You can't put a cross over a cross!\r\nTry again!");
					}
					//I'm not sure how this could ever happen but just in case
					else
					{
						System.out.println("Not a valid choice.\r\nTry again!");
					}
				}
				else
				{
					System.out.println("You did not enter 1, 2 or 3.\r\nTry again.");
				}
				
			}
			noughtsOrCrosses="O"; //changes the turn
		}
		else if(noughtsOrCrosses.equals("O"))
		{
			boolean isValid=false;
			while(isValid==false)
			{	
				this.getInput();
				//Makes sure there is a valid choice
				if(userRow > 0 && userRow < 4 && userCol > 0 && userCol < 4)
				{
					//Checks if the spot is empty for a nought to go to
					if(displayGrid[userRow-1][userCol-1].equals(". ") && actualGrid[userRow-1][userCol-1] == 0)
					{
						displayGrid[userRow-1][userCol-1] = "O ";
						actualGrid[userRow-1][userCol-1] = 4;
						isValid=true;
					}
					//makes sure there isn't a cross where the nought needs to go
					else if(displayGrid[userRow-1][userCol-1].equals("X ") && actualGrid[userRow-1][userCol-1] == 1)
					{
						System.out.println("You can't put a nought over a cross!\r\nTry again!");
					}
					//makes sure there isn't already a nought there
					else if(displayGrid[userRow-1][userCol-1].equals("O ") && actualGrid[userRow-1][userCol-1] == 4)
					{
						System.out.println("You can't put a nought over a nought!\r\nTry again!");
					}
					//again, hopefully this case will never be fulfilled
					else
					{
						System.out.println("Not a valid choice.\r\nTry again!");
					}
				}
				else
				{
					System.out.println("You did not enter 1, 2 or 3.\r\nTry again.");
				}
			}
			noughtsOrCrosses="X"; //changes the turn
		}
	}

	public boolean isDraw()
	{
		int countSquares=0;
		
		for(int a=0;a<3;a++)
		{
			for(int b=0;b<3;b++)
			{
				if(actualGrid[a][b] != 0)
				{
					countSquares++;
				}
			}
		}
		if(countSquares==9)
		{
			return true;
		}
		return false;
	}
	
	
	public String hasGameFinished()
	{
		//X's = 1 and 0's = 4 for a reason
		//when the totals are added, a 1 can't make 12 and 4 can't make a 3 so there is never a false positive
		//a very blocky way of doing this, maybe be better someday but hardcode for now
		//have a for loop that increments by 1
		//use nested fors
		
		//row win conditions
		if((actualGrid[0][0]+actualGrid[0][1]+actualGrid[0][2])==3)
		{
			return "X";
		}
		else if((actualGrid[0][0]+actualGrid[0][1]+actualGrid[0][2])==12)
		{
			return "O";
		}
		else if((actualGrid[1][0]+actualGrid[1][1]+actualGrid[1][2])==3)
		{
			return "X";
		}
		else if((actualGrid[1][0]+actualGrid[1][1]+actualGrid[1][2])==12)
		{
			return "O";
		}
		else if((actualGrid[2][0]+actualGrid[2][1]+actualGrid[2][2])==3)
		{
			return "X";
		}
		else if((actualGrid[2][0]+actualGrid[2][1]+actualGrid[2][2])==12)
		{
			return "O";
		}
		//column win conditions
		else if((actualGrid[0][0]+actualGrid[1][0]+actualGrid[2][0])==3)
		{
			return "X";
		}
		else if((actualGrid[0][0]+actualGrid[1][0]+actualGrid[2][0])==12)
		{
			return "O";
		}
		else if((actualGrid[0][1]+actualGrid[1][1]+actualGrid[2][1])==3)
		{
			return "X";
		}
		else if((actualGrid[0][1]+actualGrid[1][1]+actualGrid[2][1])==12)
		{
			return "O";
		}
		else if((actualGrid[0][2]+actualGrid[1][2]+actualGrid[2][2])==3)
		{
			return "X";
		}
		else if((actualGrid[0][2]+actualGrid[1][2]+actualGrid[2][2])==12)
		{
			return "O";
		}
		//Diagonal win conditions
		else if((actualGrid[0][0]+actualGrid[1][1]+actualGrid[2][2])==3)
		{
			return "X";
		}
		else if((actualGrid[0][0]+actualGrid[1][1]+actualGrid[2][2])==12)
		{
			return "O";
		}
		else if((actualGrid[2][0]+actualGrid[1][1]+actualGrid[0][2])==3)
		{
			return "X";
		}
		else if((actualGrid[2][0]+actualGrid[1][1]+actualGrid[0][2])==12)
		{
			return "O";
		}
		//Draw
		else if(this.isDraw()==true)
		{
			return "Draw!";
		}
		else
		{
			return "no";
		}
	}
	

	public void runXando()
	{
		populateGrids();
		printDisplayGrid();
		while(hasGameFinished().equals("no")) 
		{
			updateGrid();
			printDisplayGrid();
		}
		if(hasGameFinished()=="Draw!")
		{
			System.out.println("It's a draw!");
		}
		else
		{
			System.out.println(hasGameFinished()+"'s, has won!");
		}
		
		//printActualGrid();
	}
	
	public static void main(String[] args)
	{
		xandoComplete myObject = new xandoComplete();
		myObject.runXando();
	}
}