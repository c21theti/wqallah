import java.util.concurrent.ConcurrentLinkedQueue;

public class Fika{
	
	// for variables 
	
	ConcurrentLinkedQueue<Person> queue = new ConcurrentLinkedQueue<Person>(); // här skapar vi en ny kö
	
	public Fika() { // Ingen aning vad den gör här tbh
		
	}
	

	public void init() { // 
		
		// eftersom varje arbetare har en start(); så har alla en separat thread
		//Adds workers and their startenergy, and time for the energy
		Person kulan = new Person(queue);
		kulan.giveName("Kulan");
		kulan.generateTime();
		kulan.startEnergy();
		kulan.start();
		
		Person jonsson = new Person(queue);
		jonsson.giveName("Jonsson");
		jonsson.generateTime();
		jonsson.startEnergy();
		jonsson.start();
		
		Person jonas = new Person(queue);
		jonas.giveName("Jonas");
		jonas.generateTime();
		jonas.startEnergy();
		jonas.start();
		
		Person pontus = new Person(queue);
		pontus.giveName("Pontus");
		pontus.generateTime();
		pontus.startEnergy();
		pontus.start();
		
		// nu fan bryggs de kaffe 
		Coffemachine coffee = new Coffemachine(queue); 
		coffee.start();
		
		
		
	}
 
	
	public static void main(String[] args) {
		// Ingen annan kod ska vara här 
		Fika fika = new Fika();
		fika.init();
		
	}
}