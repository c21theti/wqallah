package drinks;

public class BlackCoffee implements ICoffee {
	

	@Override
	public String getName() { // Denna funktion kallar vi på när vi skriver ut vilken kaffesort det är personen har druckit
		// TODO Auto-generated method stub
		return "Blackcoffee";
	}
	
//A BlackCoffee gives 15-20
	@Override
	public int getEnergy() { // Denna funktion kallar vi på när vi vill lägga till energin från kaffen till personen
	// Eftersom att kaffesorten ska ha en random energi mellan 15-20
		int coffeenergy = r.nextInt(20-15) + 15;
		return coffeenergy; 
	}

}
