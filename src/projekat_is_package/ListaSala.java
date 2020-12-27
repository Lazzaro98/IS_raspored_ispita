package projekat_is_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ListaSala {
	public ArrayList<Sala> list;
	
	public ListaSala(){
		list = new ArrayList<Sala>();
	}
	public void load(String filename)throws FileNotFoundException, IOException, ParseException 
	{
		list = loadSale(filename);
	}

	public int getSize() {
		return list.size();
	}
	public void ispisListe() {
		System.out.println("Lista Sala:");
		System.out.println();
		 for(Sala sala : list) {
	            sala.ispis();
	        }
	}
	private ArrayList<Sala> loadSale(String filename)throws FileNotFoundException, IOException, ParseException {
		var listaSala = new ArrayList<Sala>();
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));

		for (Object o : jsonArray) {
			JSONObject sala = (JSONObject) o;

			String naziv = (String)sala.get("naziv");
			Long kapacitet = (Long)sala.get("kapacitet");		
			Long racunari =  (Long)sala.get("racunari");
			Long dezurni = (Long) sala.get("dezurni");
			Long etf = (Long) sala.get("etf");

			Sala novaSala = new Sala(naziv,kapacitet,(racunari==1)?true:false,dezurni,(etf==1)?true:false);
			listaSala.add(novaSala);
			System.out.println();
		}
		Collections.sort(listaSala, Comparator.comparing(Sala::getBrojNastavnika).thenComparing(Sala::getETF));
		return listaSala;
	}
}
