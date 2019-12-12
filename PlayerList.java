// CS 0401 Fall 2019
// Shell of the PlayerList class.
// This class represents a collection of players -- a very simple database.  The
// collection can be represented in various ways.  However, for this assignment
// you are required to use an array of GamePlayer.

// Note the imports below.  java.util.* is necessary for ArrayList and java.io.* is
// necessary for the file reading and writing.
import java.util.*;
import java.io.*;

public class PlayerList
{
	private GamePlayer [] players;  // array of GamePlayer
	private int size;				// logical size
	private String file;			// name of file associated with this PlayerList
	
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new GamePlayer object for each line
	// and putting them into the array.  Your initial size for the array MUST be 3 and
	// if you fill it should resize by doubling the array size.  
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that 
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your
	// main program (Assig3.java).  Note that your saveList() method will also need
	// throws IOException in its header, since it is also accessing a file.
	
	//constructor
	public PlayerList(String fName) throws IOException
	{
		Scanner S = new Scanner(new FileInputStream(fName));
		file=fName;
		players = new GamePlayer[3];
		size=0;
		while(S.hasNextLine()) {
			String player=S.nextLine();
			if (size < players.length)
			{
				String[] parts = player.split(",");
				int round = Integer.parseInt(parts[2]);
				int succe = Integer.parseInt(parts[3]);
				int opti = Integer.parseInt(parts[4]);
				int trie = Integer.parseInt(parts[5]);
				players[size] = new GamePlayer(parts[0], parts[1], round, succe, opti, trie);
				size++;
			}
			else {
				//No room to add player to resize the array to make room 
				GamePlayer[] temp = new GamePlayer[players.length*2];
				for(int i=0; i< players.length; i++)
					temp[i]=players[i];
				players=temp;
				String[] parts = player.split(",");
				int round = Integer.parseInt(parts[2]);
				int succe = Integer.parseInt(parts[3]);
				int opti = Integer.parseInt(parts[4]);
				int trie = Integer.parseInt(parts[5]);
				players[size] = new GamePlayer(parts[0], parts[1], round, succe, opti, trie);
				size++;	
			}
		}
		S.close();
		  
	}

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the modified
	// GamePlayer class before the PlayerList class will work, since your array is an
	// array of GamePlayer objects.
	
	
	//addPlayer()
	public boolean addPlayer(GamePlayer m)
	{
		if(containsName(m.getName())) {
			return false;
		}
		else {
			if (size < players.length)
			{
				players[size] = m;
				size++;
				return true;
			}
			else {
				//No room to add player to resize the array to make room 
				GamePlayer[] temp = new GamePlayer[players.length*2];
				for(int i=0; i< players.length; i++)
					temp[i]=players[i];
				players=temp;
				players[size]=m;
				size++;
				return true;
			}
		}
		
	}
	//size()
	public int size()
	{
		return size;
	}
	//capacity()
	public int capacity()
	{
		return players.length;
	}
	//containsName()
	public boolean containsName(String s)
	{
		for (int i = 0; i < size; i++)
		{
			if (players[i].getName().equals(s))
				return true;
		}
		return false;
	}
	//authenticate()
	public GamePlayer authenticate(GamePlayer g)
	{
		for(int i = 0; i < size; i++) {
			if(players[i].equals(g))
				return players[i];
		}
		return null;
	}
	//toString()
	public String toString()
	{
		StringBuffer B = new StringBuffer();
		B.append("Total Players: "+ size+ "\n");
		for (int i = 0; i < size; i++)
			B.append(players[i].toString() + "\n \n");
		return B.toString();
	}
	//toStringFile()
	public String toStringFile()
	{
		StringBuffer B = new StringBuffer();
		for (int i = 0; i < size; i++)
			B.append(players[i].toStringFile());
		return B.toString();
	}
		
	//saveList()
	public void saveList()throws IOException
	{
		PrintWriter w = new PrintWriter(file);
		w.print(toStringFile());
		w.close();
	}
	// You may also want to add some methods that are not tested in PlayerListTest.java.
	// Think about what you need to do to a PlayerList in your Assig3 program and write 
	// the methods to achieve those tasks.  However, make sure you are always thinking
	// about encapsulation and data abstraction.
}