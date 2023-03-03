import java.util.Random;
import java.util.concurrent.*;


public class Person extends Thread{
	
	private int energy;
	private String name;
	private int time;
	
	ConcurrentLinkedQueue<Person> queue; // vi skapar ej en ny kö utan har en variabel för kön
	
	public Person(ConcurrentLinkedQueue<Person> queue) {  // constructor
		this.queue = queue; 
	}
	
	@Override
	public void run() {
		
		// Det som vi beh�ver g�ra h�r �r att skapa en boolean f�r om arbetaren �r p� break eller inte
		//Tex n�r man b�rjar jobba s� ska energy >= 30
		//Men n�r man drar p� break s� ska personen inte jobba f�rns energin �r p� 100 eller �ver
		//Det m�ste vi g�ra
		
		// The energy worker starts with, if energy is 0 worker goes home
		while (energy > 0) {
			
			if (energy >= 100 ) {// If energy is at least 100 energy go back to work 
				System.out.println(name + " goes back to work with energy level " + energy);
				queue.remove(this);
			}
			
			else if (energy < 30 && !queue.contains(this)) { // If energy is below 30 worker takes a break
				System.out.println(name + " is taking a break with energy level " + energy);
				queue.add(this); // Adds the person to the queue in CoffeMachine.java
				
			}
			
			else if (energy >= 30) { // Runs when energy is over or 30
				System.out.println(name + " is working with " + energy + " energy.");
				
			}
			
			try {
				loseEnergy();
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(name + " went home"); // Runs if energy goes out
	}
	
	// removes 1 from energy
	public void loseEnergy(){
		energy--;
	}
	
	// Adds random number between 30-90
	public void startEnergy() {
		Random r = new Random();
		int result = r.nextInt(90-30) + 30;
		energy = result;
	}
	
	// man ska kunna bestämma ett namn på en person
	public void giveName(String name) {
		this.name = name;
		setName(name); // setName är en funktion som ligger i klassen "Thread", vi använder oss av den för att döpa vår Thread till namnet vi vill ge
	}
	
	// man ska kunna hämta energinivån på en person
	public int getEnergy() {
		return energy;
	}
	
	// man ska kunna bestämma en ny energinivå på en person ALTERNATIVT ska en person kunna dricka kaffe, beror på hur man tolkar det
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
		
