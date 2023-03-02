package drinks;


public class Latte implements ICoffee {

	@Override
	public String getName() {
		return "latte";
	}

	@Override
	public  int getEnergy() { //  Latte gives 25-35
		int coffeenergy = r.nextInt(35-25) + 25;
		return coffeenergy; 
	}

}
