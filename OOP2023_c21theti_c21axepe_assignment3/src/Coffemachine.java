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
			int option = r.nextInt(3-0) + 0; // randomizar vilken kaffe det blir
			makeCoffee(option); // 
			
			System.out.println("We have created the coffee");
			
			// eftersom att man inte ska kunna dricka kaffe om kön eller kaffereservoiren är tom
	if (queue.peek() != null && coffee.peek() != null) { // så länge kön inte är tom och det finns kaffe i reservouiren körs denna, peek kollar om det finns någon tillagd
		Person coffeeDrinker = queue.poll(); // poll tar bort senaste ur kön
		drink(coffeeDrinker); // vi kör denna funktion och tilldelar energin som sagt nedan till den som ska ha kaffe
		System.out.println("We have added the energy");
	}			
			// queue.peek();  använder vi för att kolla om det finns något i kön. // The machine can deliver one drink to one worker each second, as long as there is at least one drink in the reserve
		}
			System.out.println("Drink created. Coffee Machine has" + "" + "in reserve");
			System.out.println("" + "enjoyed a" + "" + " with" + "" + "energy");
			System.out.println("Coffee Machine has" + "" + "drinks in reserve.");
	}
	
	public void makeCoffee(int option) {
		
		switch(option) { // Vi använder en switch istället för att skriva många if..else statements
		case 1:
			BlackCoffee blackcoffee = new BlackCoffee();
			coffee.add(blackcoffee); // lägger till kaffet i vector listan
			break;
		case 2:
			Capuccino cappuccino = new Capuccino();
			coffee.add(cappuccino); // lägger till kaffet i vector listan
			break;
		default:
			System.out.println("case3");
			Latte latte = new Latte();
			coffee.add(latte); // lägger till kaffet i vector listan
			break;
		}
	}
	
	public void drink(Person p) { // för att lägga till energin till personen
		
		ICoffee c = coffee.element(); // hämtar den först tillagda kaffen
		
		p.addEnergy(c.getEnergy()); // add energy funktionen finns i person.java, getenergy är energin man får från kaffet
		
		
	}
	
}
