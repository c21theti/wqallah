import java.util.concurrent.ConcurrentLinkedQueue;

public class Fika {

	ConcurrentLinkedQueue<Person> queue = new ConcurrentLinkedQueue<Person>(); // Creats a new queue

	public Fika() {

	}

	public void init() { //

		// All threads
		// Adds workers and their startenergy, and time for the energy
		Person kulan = new Person(queue);
		kulan.giveName("Kulan");
		kulan.generateTime();
		kulan.startEnergy();
		kulan.start(); // Starts the independent thread

		Person jonsson = new Person(queue);
		jonsson.giveName("Jonsson");
		jonsson.generateTime();
		jonsson.startEnergy();
		jonsson.start(); // starts the thread for jonsson

		Person jonas = new Person(queue);
		jonas.giveName("Jonas");
		jonas.generateTime();
		jonas.startEnergy();
		jonas.start(); /// starts the thread for jonas

		Person pontus = new Person(queue);
		pontus.giveName("Pontus");
		pontus.generateTime(); // for the randomized working time
		pontus.startEnergy(); // for the randomized start energy
		pontus.start(); /// starts the thread for pontus

		// creating coffee object
		Coffemachine coffee = new Coffemachine(queue);
		coffee.start(); // starts the thread for coffee

	}

	// main method
	public static void main(String[] args) {
		// Ingen annan kod ska vara här
		Fika fika = new Fika();
		fika.init();

	}
}