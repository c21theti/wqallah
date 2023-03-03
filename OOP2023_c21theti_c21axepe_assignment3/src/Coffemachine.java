import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import drinks.BlackCoffee;
import drinks.Capuccino;
import drinks.ICoffee;
import drinks.Latte;

public class Coffemachine extends Thread{
	ConcurrentLinkedQueue<ICoffee> coffee = new ConcurrentLinkedQueue<ICoffee>(); // I MAKECOFFE NEDAN SKA VI LÄGGA IN KAFFET I DENNA LISTA
	
	ConcurrentLinkedQueue<Person> queue; // For the queue. We use Queue interface to have elements in FIFO order
	
	public Coffemachine(ConcurrentLinkedQueue<Person> queue) {  // constructor
		this.queue = queue; 
	}
		
 		
	@Override
	public void run() {

			// blackcoffee.getEnergy();
			// vi kan skriva detta för att få ut energin på kaffen: System.out.println("Hello" + blackcoffee.getEnergy());
			// blackcoffee.getName();
			// vi kan skriva detta för att få ut namnet på kaffen: System.out.println("Hello" + blackcoffee.getName()); 
		
		Random r = new Random();
		
		while (coffee.size() < 20) { // The reserve can hold at most 20 drinks at a time
			try {
				Thread.sleep(2000); // It takes two seconds for the coffee machine to produce a hot drink, which is added to its drink reserve
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int option = r.nextInt(3-0) + 0; // Randomizes which coffee to make
			makeCoffee(option); // Makes the coffee
			
			System.out.println("Drink created. Coffee Machine has " + coffee.size() + " in reserve"); 
			
			// Makes so that you cant drink a coffee if the queue or coffee reserve is empty
			if (queue.peek() != null && coffee.peek() != null) { // As long as the queue is not empty and there is coffee this will run, Peek checks the first object in the queue
				System.out.println(queue.element().getName() +  " enjoyed a " + coffee.element().getName() + " with " + coffee.element().getEnergy() + " energy");
				Person coffeeDrinker = queue.poll(); // removes the head of the queue
				drink(coffeeDrinker); // Gives energy depending on the coffee distributed
				coffee.remove(); // Removes the coffee
				System.out.println("Coffee Machine has " + coffee.size() + " drinks in reserve.");
			}			
			// queue.peek();  använder vi för att kolla om det finns något i kön. // The machine can deliver one drink to one worker each second, as long as there is at least one drink in the reserve
		}
			
	}

	public void makeCoffee(int option) { // Method for which coffee to make
		
		switch(option) { // We use switch instead of an If statement. Takes the random int generated and uses that int to decide which case to be used
		case 1:
			BlackCoffee blackcoffee = new BlackCoffee();
			coffee.add(blackcoffee); // Adds coffee to the coffee queue
			break;
		case 2:
			Capuccino cappuccino = new Capuccino();
			coffee.add(cappuccino); // Adds coffee to the coffee queue
			break;
		default:
			Latte latte = new Latte();
			coffee.add(latte); // Adds coffee to the coffee queue
			break;
		}
	}
	
	public void drink(Person p) { // To give energy to a person depending on which coffee

		ICoffee c = coffee.element(); // Gets the first object from the coffee queue
		
		p.addEnergy(c.getEnergy()); // Adds energy (addEnergy and getEnergy is in person.java)
		
	}
}
