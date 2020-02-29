package atakanatik_cs310_hw1_q1;

public class Country {
	
	private String domain = "";
	private String region = "";
	private String capitalCity = "";
	private String population = "";
	
	public Country()
	{
		super();
	}
	
	public Country(String domain, String region, String capitalCity, String population)
	{
		super();
		this.domain = domain;
		this.region = region;
		this.capitalCity = capitalCity;
		this.population = population;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
}
