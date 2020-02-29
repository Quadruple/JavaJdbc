package atakanatik_cs310_hw1_q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AtakanJdbcManager {
	
	private ArrayList<Country> countryDataList;
	private static String parameterizedUrl = "jdbc:mysql://127.0.0.1/cs310_hw1?useTimezone=true&serverTimezone=GMT";
	private static String parameterizedDbUser = "root";
	private static String parameterizedDbPassword = "1q2w3e4r";
	private static String parameterizedInsertStatement = "insert into countries (domain, region, capital, population) values (?,?,?,?)";
	private static String selectionTemplate = "select domain, region, capital, population from countries where countryid = ?";
	private static String deletionTemplate = "delete from countries where countryid = ?";
	private static String updateTemplate = "update countries set population = <poptag> where countryid = <idtag>";
	
	public AtakanJdbcManager()
	{
		super();
		countryDataList = new ArrayList<Country>();
	}
	
	public void readFromFile(String filename)
	{
		try
		{
			File worldData = new File(filename);
			Scanner dataReader = new Scanner(worldData);
			
			while(dataReader.hasNextLine())
			{
				String data = dataReader.nextLine();
				String[] extractedData = data.split(",");
				Country country = new Country(extractedData[0], extractedData[1], extractedData[2], extractedData[3]);
				countryDataList.add(country);
			}
			dataReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Specified file not found.");
			e.printStackTrace();
		}
	}
	
	public void writeIntoTable(ArrayList<Country> countries)
	{
		if(!countries.isEmpty()) {
			try {
				Connection connection = DriverManager.getConnection(parameterizedUrl, parameterizedDbUser, parameterizedDbPassword);
				for(Country country: countries)
				{
					PreparedStatement statement = connection.prepareStatement(parameterizedInsertStatement);
					statement.setString(1, country.getDomain());
					statement.setString(2, country.getRegion());
					statement.setString(3, country.getCapitalCity());
					statement.setString(4, country.getPopulation());
					statement.execute();
				}
				
				System.out.println("Data insertion is succesful.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			System.out.println("Nothing to send yet. Read some data first.");
		}
	}
	
	public Country getCountryById(int countryId)
	{
		String id = Integer.toString(countryId);
		String parameterizedSelectStatement = selectionTemplate.replace("?", id);
		Country resultCountry = new Country();
		try
		{
			Connection connection = DriverManager.getConnection(parameterizedUrl, parameterizedDbUser, parameterizedDbPassword);
			PreparedStatement statement = connection.prepareStatement(parameterizedSelectStatement);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next())
			{
				resultCountry.setDomain(result.getString("domain"));
				resultCountry.setRegion(result.getString("region"));
				resultCountry.setCapitalCity(result.getString("capital"));
				resultCountry.setPopulation(result.getString("population"));
			}
			
			return resultCountry;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteCountryById(int countryId)
	{
		String id = Integer.toString(countryId);
		String parameterizedDeleteStatement = deletionTemplate.replace("?", id);
		
		try
		{
			Connection connection = DriverManager.getConnection(parameterizedUrl, parameterizedDbUser, parameterizedDbPassword);
			PreparedStatement statement = connection.prepareStatement(parameterizedDeleteStatement);
			statement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateCountryPopulationById(int CountryId, int population)
	{
		String id = Integer.toString(CountryId);
		String pop = Integer.toString(population);
		String temp = updateTemplate.replace("<poptag>", pop);
		String parameterizedUpdateStatement = temp.replace("<idtag>", id);
		
		try 
		{
			Connection connection = DriverManager.getConnection(parameterizedUrl, parameterizedDbUser, parameterizedDbPassword);
			PreparedStatement statement = connection.prepareStatement(parameterizedUpdateStatement);
			statement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Country> getCountryList()
	{
		return countryDataList;
	}
}






















