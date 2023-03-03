import java.util.Random;
import java.util.concurrent.*;

public class Person extends Thread {

	private int energy;
	private String name;
	private int time;

	ConcurrentLinkedQueue<Person> queue; // We use the same queue as in fika, we create a new variable for the queue

	public Person(ConcurrentLinkedQueue<Person> queue) { // this is a constructor
		this.queue = queue;
	}

	@Override
	public void run() { // Contains the executable code of the thread

		// Det som vi beh�ver g�ra h�r �r att skapa en boolean f�r om arbetaren �r p�
		// break eller inte
		// Tex n�r man b�rjar jobba s� ska energy >= 30
		// Men n�r man drar p� break s� ska personen inte jobba f�rns energin �r p� 100
		// eller �ver
		// Det m�ste vi g�ra
		// dom ska stanna i kön i 100 i energi
		
		while (energy > 0) { // as long as workers energy is more than 0 this loop is true

			if (energy >= 30 && !queue.contains(this)) { // Runs when energy is over or 30
				System.out.println(name + " is working with " + energy + " energy.");

			} else if (energy < 30 && !queue.contains(this)) { // If energy is below 30 worker takes a break
				System.out.println(name + " is taking a break with energy level " + energy);
				
				System.out.println(name + "-------WORKER IS ADDED TO THE QUEUE-----------");
				queue.add(this); // Adds the person to the queue in CoffeMachine.java
				
			} else if (energy >= 100) {// If energy is at least 100 energy go back to work
				System.out.println(name + " goes back to work with energy level " + energy);
			}
			try {
				loseEnergy();
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// if workers energy is 0 worker goes home
		System.out.println(name + " went home"); // Runs if energy goes out
	}

	// this method removes 1 from energy
	public void loseEnergy() {
		energy--;
	}

	// Adds random number between 30-90 for the startenergy
	public void startEnergy() {
		Random r = new Random();
		int result = r.nextInt(90 - 30) + 30;
		energy = result;
	}

	public void giveName(String name) {
		this.name = name;
		setName(name); // setName is a method in Thread which sets the name
	}

	// Returns the energy
	public int getEnergy() {
		return energy;
	}

	// Sets the energy
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	// Adds the energy
	public void addEnergy(int energy) {
		this.energy += energy;
	}

	// Generates working time for each worker
	public void generateTime() {
		time = (int) (Math.random() * (1500 - 500 + 500));
	}

}
