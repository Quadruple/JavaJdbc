package atakanatik_cs310_hw1_q1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtakanJdbcManager manager = new AtakanJdbcManager();
		manager.readFromFile("world.txt");
		//manager.writeIntoTable(manager.getCountryList());
		//System.out.println(manager.getCountryById(10).getDomain());
		manager.deleteCountryById(10);
		//manager.updateCountryPopulationById(20, 20);
	}

}
