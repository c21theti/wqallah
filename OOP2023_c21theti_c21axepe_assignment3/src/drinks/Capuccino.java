package drinks;

// för vårt interface
public class Capuccino implements ICoffee {

	@Override
	public String getName() {
		return "Cappuccino";
	}

	@Override
	public  int getEnergy() { //  Capuccino gives 20-30
		int coffeenergy = r.nextInt(30-20) + 20;
		return coffeenergy; 
	}

}
