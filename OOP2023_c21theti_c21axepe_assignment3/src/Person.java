import java.util.Random;
import java.util.concurrent.*;


public class Person extends Thread{
	
	private int energy;
	private String name;
	private int time;
	
	ConcurrentLinkedQueue<Person> queue; // vi skapar ej en ny k√∂ utan har en variabel f√∂r k√∂n
	
	public Person(ConcurrentLinkedQueue<Person> queue) {  // constructor
		this.queue = queue; 
	}
	
	@Override
	public void run() {
		
		// Det som vi behˆver gˆra h‰r ‰r att skapa en boolean fˆr om arbetaren ‰r pÂ break eller inte
		//Tex n‰r man bˆrjar jobba sÂ ska energy >= 30
		//Men n‰r man drar pÂ break sÂ ska personen inte jobba fˆrns energin ‰r pÂ 100 eller ˆver
		//Det mÂste vi gˆra
		
		// The energy worker starts with, if energy is 0 worker goes home
		while (energy > 0) {
			
			if (energy >= 100 ) { // om dom √§r √∂ver hundra och i listan
				// If energy is at least 100 energy go back to work 
				System.out.println(name + " goes back to work with energy level " + energy);
				queue.remove(this);
			}
			
			else if (energy < 30 && !queue.contains(this)) { // If energy is below 30 worker takes a break
				System.out.println(name + " is taking a break with energy level " + energy);
				queue.add(this); // l√§gger till workern i k√∂n n√§r den har energi under 30
				
			}
			
			else if (energy >= 30) { // ska kˆras tills engerin ‰r 100
				System.out.println(name + " is working with " + energy + " energy.");
				
			}
			
			try {
				loseEnergy();	
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(name + " went home");
	}
	
	// removes 1 from energy
	public void loseEnergy(){
		energy--; // ta bort 1 fr√•n energy	
	}
	
	// Adds random number between 30-90
	public void startEnergy() {
		Random r = new Random();
		int result = r.nextInt(90-30) + 30;
		energy = result;
	}
	
	// man ska kunna best√§mma ett namn p√• en person
	public void giveName(String name) {
		this.name = name;
		setName(name); // setName √§r en funktion som ligger i klassen "Thread", vi anv√§nder oss av den f√∂r att d√∂pa v√•r Thread till namnet vi vill ge
	}
	
	// man ska kunna h√§mta energiniv√•n p√• en person
	public int getEnergy() {
		return energy;
	}
	
	// man ska kunna best√§mma en ny energiniv√• p√• en person ALTERNATIVT ska en person kunna dricka kaffe, beror p√• hur man tolkar det
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void addEnergy(int energy) {
		this.energy += energy;
	}
	
	// generates time for each worker
	public void generateTime() {
		time = (int)(Math.random() * (1500 - 500 + 500));
	}
	

}
		
