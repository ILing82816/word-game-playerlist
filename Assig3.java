/*1. create PlayerList object
 *2. to ask the dictionary name and make the Dictionary object
 *3. have first while loop to start the program
 *4. to login the program and handle the new player to sign up (ask the name and password)
 *5. Second while loop to ask they want to try
 *6. have two words randomly from Dictionary class
 *7. have repeWord() method to check the word had been used.
 *8. use the Dictionary.distance to find the optimal time to transfer the word
 *9. start game in one min using the MyTimer class
 *10. use StringBuilder to transfer the words
 *11. use the GamePlayer to count the number of success or failure
 *12. use PlayerList method to save file
 * 
 *
 * */
import java.util.*;
import java.io.*;
import java.lang.*;
public class Assig3 {

	public static void main(String[] args) throws IOException { 
		
		//Create PlayerList object
		PlayerList allPlayers = new PlayerList("players.txt");
		
		// Ask the dictionary name and make the Dictionary object
		Scanner inScan = new Scanner(System.in);
		System.out.println("Dictionary file name?");
		String DicNa = inScan.nextLine();
        Dictionary D = new Dictionary(DicNa);
        
        //start the program-first loop
        boolean quit = false;
        while(!quit) {
        	//welcome
        	System.out.println("Welcome to the Word Changer Program");
            System.out.println();
            //ask to quit or not
            System.out.println("Please sign in with your name and password");
            System.out.println("If you are a new player, leave your name field blank");
            System.out.println("and we will set you up with a new account");
            System.out.println("If you would like to end the game, enter \'Quit\'");
            System.out.println();
        	System.out.println("\t Name: ");
        	String player = inScan.nextLine();
        	//quit
        	if(player.contentEquals("Quit")) {
        		System.out.println("No more players to play!");
        		quit = true;
        	}//quit
        	//not quit
        	else {
        		//sign in
        		GamePlayer onePlayer;
        		//name field blank
        		if (player.contentEquals(" ")) {
        			System.out.println();
        			System.out.println("Welcome new player!!");
        			System.out.println("Before playing we must get some information from you");
        			// Create a new Gameplayer with just a String for the name.
        			System.out.println("\t Please enter a name for your account (no spaces): ");
        			String name= inScan.nextLine();
        			boolean found = allPlayers.containsName(name);
        			while(found){
        				System.out.println("\t Sorry but name "+ name+" is taken.  Please try a different name.");
        				System.out.println("\t Name: ");
        				name= inScan.nextLine();
        	    		found = allPlayers.containsName(name);
        			}
        			onePlayer = new GamePlayer(name);
            		register(inScan, onePlayer);//method register set password
            		// Add the player to the PlayerList. 
            		allPlayers.addPlayer(onePlayer);
            	}//name field blank
        		//have name
            	else {
            		boolean found = allPlayers.containsName(player);
            		//new player
            		if(!found) {
            			System.out.println("Your name does not exist, so your sign-in has been"); 
            			System.out.println("canceled.  Please register as a new player.");
            			System.out.println();
            			System.out.println("Welcome new player!!");
            			System.out.println("Before playing we must get some information from you");
            			onePlayer = new GamePlayer(player);
            			register(inScan, onePlayer);//method register
                		// Add the player to the PlayerList. 
                		allPlayers.addPlayer(onePlayer);
            		}//new player
            		//old player
            		else {
            			GamePlayer temp = new GamePlayer(player);
                		System.out.println("\t Please enter your password:");
                		String password = inScan.nextLine();
                		temp.setPass(password);
                		onePlayer = allPlayers.authenticate(temp);
                		if(onePlayer==null)
                		{
                			System.out.println("\t Sorry, your password does not match. Please try again.");
                			System.out.println("\t Please enter your password:");
                    		password = inScan.nextLine();
                    		temp.setPass(password);
                    		onePlayer = allPlayers.authenticate(temp);
                    		if(onePlayer==null)
                    		{
                    			System.out.println("Your password still does not match, so your sign-in has been");
                    			System.out.println("canceled. Please register as a new player.");
                    			System.out.println();
                    			System.out.println("Welcome new player!!");
                    			System.out.println("Before playing we must get some information from you");
                    			// Create a new Gameplayer with just a String for the name.
                    			while(found){
                    				System.out.println("\t Sorry but name "+ player+" is taken.  Please try a different name.");
                    				System.out.println("\t Name: ");
                    				player= inScan.nextLine();
                    	    		found = allPlayers.containsName(player);
                    			}
                    			onePlayer = new GamePlayer(player);
                    			register(inScan, onePlayer);//method register
                        		// Add the player to the PlayerList. 
                        		allPlayers.addPlayer(onePlayer);
                    		}//second time wrong password
                		}//first time wrong password 
            		}//old player
            	}//have name
        		
            	//intro the game rule
                intro();//method intro()
                
            	//ask they want to try? - Second loop
            	boolean again = true;
            	String ans;
            	Dictionary DD = new Dictionary();
            	while(again) {
            		System.out.println(onePlayer.getName()+", Would you like to try? (y/n)");
            		ans = inScan.nextLine();
            		//not again
            		if (ans.toLowerCase().equals("n")) {
            			again = false;
            			System.out.println();
            			System.out.println("Thanks for playing "+onePlayer.getName());
            			System.out.println();
            		}//not again
            		//again
            		else {
            			//have two random words and check the word had been used.
                		boolean repe = true;
                		boolean repe1 = true;
                		String Curword = D.randWord(5,9);
                		String word2 = D.randWord(5,9);
                		//check Curword
                		while(repe) {
                			if(repeWord(DD, Curword)) { //method repeWord
                				Curword = D.randWord(5,9);
                			}
                			else {
                				DD.addWord(Curword);
                				repe = false;
                			}
                		}//check Curword
                		//check word2
                		while(repe1) {
                			if(repeWord(DD, word2)) { //method repeWord
                				word2 = D.randWord(5,9);
                			}
                			else {
                				DD.addWord(word2);
                				repe1 = false;
                			}
                		}//check word2
                		
                		//the optimal distance
                		int dist = Dictionary.distance(Curword, word2);  
                		System.out.println("Your goal is to convert \'"+Curword+"\' to \'"+word2+"\' in "+dist+" edits.");
                		System.out.println();
                		
                		//star game in one min.
                		CharSequence cSeq = Curword;
                		StringBuilder str = new StringBuilder(cSeq);
                		MyTimer T = new MyTimer(60000);
                		char v;
                		int k, count=0;;
                		T.start();
                		boolean valid = false;
                		while (T.check())
                    	{
                			System.out.printf("%-20s %s\n", "Index:", "0123456789");
                    		System.out.printf("%-20s %s\n", "------", "----------");
                    		System.out.printf("%-20s %s\n", "Currend Word:",str.toString());
                    		System.out.printf("%-20s %s\n", "Word 2:",word2);
                    		System.out.println("Here are your options:");
                    		System.out.println("\t C k v -- Change char at location k to value v");
                    		System.out.println("\t I k v -- Insert char at location k with value v");
                    		System.out.println("\t D k   -- Delete char at location k");
                    		System.out.println("Option choice");
                    		CharSequence task = inScan.nextLine();
                    		StringBuilder str1 = new StringBuilder(task);
                    		char act = str1.charAt(0);
                    		char kc = str1.charAt(2);
                    		k = Character.getNumericValue(kc);  
                    		if (T.check())
                    		{
                    			if(act == 'C' || act == 'c') {
                    				v = str1.charAt(4);
                        			str.deleteCharAt(k);
                        			str.insert(k, v);
                        		}
                        		else if (act == 'I' || act == 'i') {
                        			v = str1.charAt(4);
                        			str.insert(k, v);
                        		}
                        		else {
                        			str.deleteCharAt(k);
                        		}
                    			count++;
                    			if (str.toString().contentEquals(word2)) {
                    				T.stop();
                        		}//time stop
                    		}
                    		else {
                    			System.out.println("Sorry but time has expired!");
                    		}
                    	}// T.check true
                		
                		//count the number of success or failure
                		if (str.toString().contentEquals(word2)) {
                			System.out.println("Congratulations!");
                			System.out.printf("%-15s %s\n", "You have converted:",str.toString());
                    		System.out.printf("%-15s %s\n", "into:",word2);
                    		System.out.println("in "+ count+ " edits");
                    		if (count==dist) {
                    			System.out.println("You used the minimal number of changes -- great job!");
                    		}
                    		else {
                    			System.out.println("You used "+(count-dist)+" extra changes.");
                    			System.out.println("See if you can do the optimal number next time!");
                    			
                    		}
                    		onePlayer.success(count, dist);
                    		System.out.println("Here are your current stats:");
                    	    System.out.println(onePlayer.toString()); 
                    	    System.out.println();
                    		
                		}
                		else {
                			System.out.println("After "+count+"  changes your final guess was "+ str.toString());
                			onePlayer.failure();
                			System.out.println("Here are your current stats:");
                			System.out.println(onePlayer.toString()); 
                			System.out.println();
                		}// count success or failure
            		}// again	
            	}// Second loop	
        	}//not quit
    	}//end the program-first loop
        System.out.println("Here are the player stats: ");
		System.out.println(allPlayers.toString());
    	// save to file
		System.out.println("Saving players back to the file...");
    	allPlayers.saveList();
	}// main
	
	
	// method repeWord()
	public static boolean repeWord(Dictionary DD, String ww)
	{
		if (DD.contains(ww)) {
			return true;
		}	
		else return false;
	}
	//method register() Set the password for the player and confirm password
	public static boolean register(Scanner S, GamePlayer Player)
	{
		System.out.println("\t Please enter your password:");
		String password = S.nextLine();
		Player.setPass(password);
		System.out.println("\t Confirm password:");
		String conpass = S.nextLine();
		boolean comfir = conpass.equals(Player.getPass());
		while(!comfir){
			System.out.println("\t Sorry but your passwords don't match!");
			System.out.println("\t Please enter your password:");
			password = S.nextLine();
			Player.setPass(password);
			System.out.println("\t Confirm password:");
			conpass = S.nextLine();
			comfir = conpass.equals(Player.getPass());
		}
		return true;
	}
	// intro the game rule
	public static void intro()
	{
		System.out.println("Here is how to play:");
        System.out.println("\t For each round you will see two randomly selected words.");
        System.out.println("\t You will have 1 minute to convert the first word to the second");
        System.out.println("\t using only the following changes:");	 
        System.out.println("\t\t Insert a character at position k (with k starting at 0)");	
        System.out.println("\t\t Delete a character at position k (with k starting at 0)");
        System.out.println("\t\t Change a character at position k (with k starting at 0)");
        System.out.println("\t For example, to change the word 'WEASEL' to 'SEASHELL' you could");
        System.out.println("\t do the following:");
        System.out.println("\t\t 1) Change 'W' at position 0 to 'S': SEASEL");	
        System.out.println("\t\t 2) Insert 'H' at position 4: SEASHEL");
        System.out.println("\t\t 3) Insert 'L' at position 7: SEASHELL");
        System.out.println("\t Your goal is to make this conversion with the fewest changes.");
        System.out.println("\t Note that there may be more than one way to achieve this goal.");
	}

}// class
