/* The class will represent each player of the game 
 * and should have data to represent the player's name, 
 * number of rounds players, and other information.
 * It have methods to update the values as needed
 * and to represent the data that can be saved into a file.
 */

public class GamePlayer {

	// Declare instance variables
	private String name;
	private String pass;
	private int round;
	private int succe;
	private int opti;
	private int tries;
	private int fail;
	private double ratio;
	
	// constructor1
    public GamePlayer(String n) {
		name = n;
    	ratio = 1.0;
	}
	// constructor2
	public GamePlayer(String n, String p, int r, int su, int op, int tr) {
		name = n;
		pass = p;
		round = r;
		succe = su;
		opti = op;
		tries = tr;
		fail = r-su;
		ratio = (double)tries/(double)opti;
	}
	
	//toString()
	public String toString() {
		StringBuilder S = new StringBuilder();
		S.append("   Name: " + name+ "\n");
		S.append("   Rounds: " + round+ "\n");
		S.append("   Successes : " + succe+ "\n");
		S.append("   Failures: " + fail+ "\n");
		S.append("   Ratio (successes only): "+ ratio);
		return S.toString();
	}
	
	//success()
	public void success(int t, int o) {
		// Update statistics
		tries += t;
		opti += o;
		round++;
		succe++;
		ratio = (double)tries/(double)opti;
	}
	
	//failure()
	public void failure() {
		// Update statistics
		round++;
		fail++;
	}
		
	//getName()
	public String getName() {
		return name;
	}
	//getPass()
		public String getPass() {
			return pass;
		}
	
	//toStringFile()
	public String toStringFile() {
		StringBuilder S = new StringBuilder();
		S.append(name+",");
		S.append(pass+",");
		S.append(round+",");
		S.append(succe+",");
		S.append(opti+",");
		S.append(tries+"\n");
		return S.toString();
	}
	
	//setPass()
	public void setPass(String p) {
		pass = p;
	}
	
	//equals()
	public boolean equals(GamePlayer g) {
		if(name.equals(g.getName())&&pass.equals(g.getPass()))
			return true;
		else
			return false;
	}
}
