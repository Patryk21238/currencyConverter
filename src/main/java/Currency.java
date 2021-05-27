package src.main.java;

import java.util.ArrayList;
import java.util.HashMap;

public class Currency {
	private String name;
	private String shortName;
	private HashMap<String, Double> exchangeValues = new HashMap<String, Double>();
	
	public Currency(String nameValue, String shortNameValue) {
		this.name = nameValue;
		this.shortName = shortNameValue;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	public HashMap<String, Double> getExchangeValues() {
		return this.exchangeValues;
	}
	
	public void defaultValues() {
		String currency = this.name;
		
		switch (currency) {	
			case "Złoty Polski":
				this.exchangeValues.put("PLN", 1.00);
				this.exchangeValues.put("EUR", 0.22);
				this.exchangeValues.put("USD", 3.66);
				this.exchangeValues.put("GBP", 5.17);
				this.exchangeValues.put("CNY", 0.57);
				this.exchangeValues.put("BTC", 139004.44);
				break;
			case "Euro":
				this.exchangeValues.put("PLN", 4.48);
				this.exchangeValues.put("EUR", 1.00);
				this.exchangeValues.put("USD", 0.82);
				this.exchangeValues.put("GBP", 1.15);
				this.exchangeValues.put("CNY", 0.13);
				this.exchangeValues.put("BTC", 31027.06);
				break;
			case "Dolar Amerykański":
				this.exchangeValues.put("PLN", 0.27);
				this.exchangeValues.put("EUR", 1.23);
				this.exchangeValues.put("USD", 1.00);
				this.exchangeValues.put("GBP", 1.41);
				this.exchangeValues.put("CNY", 0.16);
				this.exchangeValues.put("BTC", 37872.70);
				break;
			case "Funt Brytyjski":
				this.exchangeValues.put("PLN", 0.19);
				this.exchangeValues.put("EUR", 0.87);
				this.exchangeValues.put("USD", 0.71);
				this.exchangeValues.put("GBP", 1.00);
				this.exchangeValues.put("CNY", 0.11);
				this.exchangeValues.put("BTC", 26675.39);
				break;
			case "Chińskie Juany":
				this.exchangeValues.put("PLN", 1.75);
				this.exchangeValues.put("EUR", 7.86);
				this.exchangeValues.put("USD", 6.41);
				this.exchangeValues.put("GBP", 9.07);
				this.exchangeValues.put("CNY", 1.00);
				this.exchangeValues.put("BTC", 241940.88);
				break;
			case "Bitcoin":
				this.exchangeValues.put("PLN", 0.0000072);
				this.exchangeValues.put("EUR", 0.000032);
				this.exchangeValues.put("USD", 0.000026);
				this.exchangeValues.put("GBP", 0.000037);
				this.exchangeValues.put("CNY", 0.0000041);
				this.exchangeValues.put("BTC", 1.000);
				break;
		}
	}
	
	public static ArrayList<Currency> init() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
				
		currencies.add( new Currency("Złoty Polski", "PLN") );
		currencies.add( new Currency("Euro", "EUR") );
		currencies.add( new Currency("Dolar Amerykański", "USD") );
		currencies.add( new Currency("Funt Brytyjski", "GBP") );
		currencies.add( new Currency("Chińskie Juany", "CNY") );
		currencies.add( new Currency("Bitcoin", "BTC") );
		
		for (Integer i =0; i < currencies.size(); i++) {
			currencies.get(i).defaultValues();
		}		
		
		return currencies;
	}
	
	public static Double convert(Double amount, Double exchangeValue) {
		Double price;
		price = amount * exchangeValue;
		price = Math.round(price * 100d) / 100d;
		
		return price;
	}
}