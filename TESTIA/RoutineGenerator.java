import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class RoutineGenerator {
	private ArrayList<Move> myArrayList;
	private File file;

	public RoutineGenerator() {
		file = new File("file.txt");
		myArrayList = new ArrayList<Move>();
		loadFile();
	}

	//loads the data(moves) from the file into the private Arraylist
	public void loadFile() {
		String code1;
		String name1;
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				name1 = scan.nextLine();
				code1 = scan.nextLine();
				myArrayList.add(new Move(name1, code1));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();

		}
	}

	//adds changes and new data made by the client into the file
	public void updateFile()
	{
		file.delete();
		try	
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			PrintWriter write = new PrintWriter(file);
			
			for(Move step: myArrayList)
			{
				write.println(step.getName());
				write.println(step.getCode());
			}
			
			write.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("Error: " + e.getMessage());
		}
	}
	
    // adds a move and its corresponding code into the Arraylist
	public void add(String move, String code) {
		Move step = new Move(move, code);
		myArrayList.add(step);
		updateFile();
	}

	// deletes a move and its corresponding code into the Arraylist
	public void delete(String move, String code) {
		Move step = new Move(move, code);
		for (int x = 0; x < myArrayList.size(); x++) {
			if (myArrayList.get(x).equals(step)) 
			{
				
				myArrayList.remove(x);
			}
		}
		updateFile();
	}

	// finds a move and its corresponding code in the Arraylist and returns the step
	public Move find(String move, String code) {
		Move step = new Move(move, code);
		for (int x = 0; x < myArrayList.size(); x++) 
		{
			if (myArrayList.get(x).equals(step)) 
			{
				return step;
			}
		}		
		return null;
	}

	// If the move is in the arraylist then return true, if not then return false
	public Boolean find2(String move, String code) {
		Move step = new Move(move, code);
		for (int x = 0; x < myArrayList.size(); x++) 
		{
			if (myArrayList.get(x).equals(step)) 
			{
				return true;
			}
		}		
		return false;
	}
	
	// replaces a move and its corresponding code into the Arraylist
	public void replace(int x, String move, String code) 
	{
		Move step = new Move(move, code);
		if (x < myArrayList.size())
		{
			myArrayList.set(x, step);
		}
		else
		{
			
		}
		updateFile();
	}
	
	// prints the Arraylist
	public String printArrayList()
	{
		String result = "";
		for(int i = 0; i < myArrayList.size(); i++)
		{
			System.out.println(myArrayList.get(i).toString());
			result += myArrayList.get(i).toString();
		}
		return result;
	}
	
	// clears the file(file.txt)
	// removes all moves from the arraylist
	public void clearFile()
	{
		PrintWriter clear = null;
		try {
			clear = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		clear.print("");
		clear.close();
		
		for(int x = 0; myArrayList.size() > x; x++)
		{
			myArrayList.remove(x);
			x--;
		}
	}
	
	public ArrayList<Move> getArrayList()
	{
		return myArrayList;
	}
}
