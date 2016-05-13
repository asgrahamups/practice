import java.util.Scanner;
public class Solve
{

	public static void main(String[] args)
	{
		Solve solve = new Solve();
		int[][] board = new int[10][10];

		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
				board[i][j] = 0;

		System.out.println("Now solving NQueens for N=" + board.length);
		solve.printBoard(solve.solve(board));

		System.out.println("Want another solution?");
		Scanner s = new Scanner(System.in);
		solve.lastPlacedColumn = 0;
		int next = solve.lastPlacedColumn;
		while(s.nextLine().equals("yes"))
		{
			for(int i=0;i<board.length;i++)
				for(int j=0;j<board.length;j++)
					board[i][j] = 0;

			solve.currentRow = 0;
			next++;
			solve.lastPlacedColumn = next;
			if(next == board.length)
			{
				System.out.println("All possible solutions have been displayed.");
				break;
			}
			solve.printBoard(solve.solve(board));
			System.out.println("How about another?");
		}
	}
	//Code related to checking for a valid placement of the queen
	public boolean checkRow(int[][] board, int row)
	{
		for(int i=0;i<board.length;i++)
		{
			
			if(board[row][i]==1)
				return false;
			//board[row][i] = 5;
		}
		return true;
	}
	public boolean checkColumn(int[][] board, int column)
	{
		for(int i=0;i<board.length;i++)
		{
			if(board[i][column]==1)
				return false;
			//board[i][column] = 5;	
		}
		return true;	
	}
	public boolean checkDiagonal(int[][] board, int row, int column)
	{
		//upper left diagonals
		int ro = row;
		int col = column;

		while(ro>0 && col>0)
		{
			ro--;
			col--;
			if(board[ro][col]==1)
				return false;
			//board[ro][col]=5;
		}

		ro = row;
		col = column;

		//upper right diagonals
		while(ro>0 && col<board.length-1)
		{
			ro--;
			col++;
			if(board[ro][col]==1)
				return false;
			//board[ro][col]=5;			
		}

		ro = row;
		col = column;

		//lower left diagonals
		while(ro<board.length-1 && col>0)
		{
			ro++;
			col--;
			if(board[ro][col]==1)
				return false;
			//board[ro][col]=5;
		}

		ro = row;
		col = column;

		//lower right diagonals
		while(ro<board.length-1 && col<board.length-1)
		{

			ro++;
			col++;
			if(board[ro][col]==1)
				return false;
			//board[ro][col]=5;			
		}

		return true;
	}
	public boolean validPlacement(int[][] board, int row, int column)
	{
		//board[row][column] = 1;
		return checkDiagonal(board,row,column) && checkColumn(board, column) && checkRow(board, row);
	}

	public void printBoard(int[][] board)
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
		System.out.println("------------------");
	}


	/*
	Recursive backtracking solve algorithm. Uses two instance variable instead of
	passing data through parameters to reduce memory stress of brute force algorithm.
	*/
	
	int lastPlacedColumn = 0;
	int currentRow = 0;

	public int[][] solve(int[][] board)
	{
		if(solve(board,currentRow,lastPlacedColumn))
		{
			return board;
		}
		//backtracking code goes here
		else
		{

			board[currentRow][lastPlacedColumn] = 0;
			lastPlacedColumn++;
			if(lastPlacedColumn == board.length)
			{
				currentRow--;
				lastPlacedColumn = 0;
			}
			return solve(board);
		}
	}
	

	public boolean solve(int[][] board, int row, int col)
	{
		if(validPlacement(board,row,col))
		{
				
			board[row][col] = 1;

			//assign instance variables for passing data to recursive backtracking algorithm
			lastPlacedColumn = col;
			currentRow = row;

			row++;			

			//see if the problem is finished.
			if(row==board.length)
			{
				System.out.println("placed all queens");
				return true;
			}

			//start at the beginning of the next row
			col = 0;
			return solve(board,row,col);
		}
		else
		{
			//try to find another spot for it in the row.
			col++;
			//if we couldn't find a place in the row, we backtrack
			if(col==board.length)
				return false;

			return solve(board,row,col);
		}
	}

	public void sleep(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			System.out.println("Sleep function failed for some reason.");
		}
	}
}