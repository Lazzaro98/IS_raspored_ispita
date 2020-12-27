package projekat_is_package;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ListaIspita {
	
	public ListaIspita() {}
	
	ArrayList<Ispit> list;
	Long trajanje_u_danima;
	
	public void load(String filename) throws FileNotFoundException, IOException, ParseException {
		list = loadIspite(filename);
	}
	
	public void ispisListe() {
		System.out.println("Lista Ispita:");
		System.out.println();
		 for(Ispit ispit : list) {
	            ispit.ispis();;
	        }
	}
	private ArrayList<Ispit> loadIspite(String filename)throws FileNotFoundException, IOException, ParseException {
		var listaIspita = new ArrayList<Ispit>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
		
		trajanje_u_danima = (Long)jsonObject.get("trajanje_u_danima");
		JSONArray jsonArray2 = (JSONArray)jsonObject.get("ispiti");

		for(Object o : jsonArray2) {
			ArrayList<String> odseciLista = new ArrayList();
			JSONObject ispit = (JSONObject) o;
			String sifra = (String)ispit.get("sifra");
			Long prijavljeni = (Long)ispit.get("prijavljeni");
			Long racunari = (Long)ispit.get("racunari");
			JSONObject odseci = (JSONObject) ispit;
			JSONArray jsonArray3 =(JSONArray)odseci.get("odseci");
			for(Object o2 : jsonArray3) {
				odseciLista.add((String)o2);
			}
			Ispit noviIspit = new Ispit(sifra,prijavljeni,(racunari==1)?true:false,odseciLista);
			listaIspita.add(noviIspit);
		}
		 
		/*Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream("outfilename.txt"), "UTF-8"));
			try {
			    out.write(listaIspita.get(0).getSifra());
			} finally {
			    out.close();
			}
		*/
		// cirilica ce se videti lepo kada se bude upisivalo u fajlove
		//ne moze se videti u konzoli plaki
		return listaIspita;
	}
	
	public void loadDomain(ListaSala ls,raspored r) {
		for(Ispit i:this.list) {
			i.loadDomain(ls,r);
		}
	}
	
	public void outputDomain() {
		for(Ispit i:this.list) {
			i.ispisiDomen();
		}
	}
	
	public ListaIspita copy(){
		ListaIspita li = new ListaIspita();
		li.trajanje_u_danima = this.trajanje_u_danima;
		li.list = new ArrayList(this.list);
		int br = 0;
		for(Ispit i:li.list) {
			li.list.set(br++, i.copy());
		}
		return li;
	}
	
	public int getSize() {
		return this.list.size();
	}
	
	public void removeFromAllDomains(ArrayList<termin> del) {
		for(Ispit i:this.list) {
			i.removeFromDomen(del);
		}
	}
	
	public void removeDuplicatesFromDomain() {
		for(Ispit i:this.list) {
			i.hs = removeDuplicates(i.hs);
		}
		
	}
	
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
  
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 
  
        // Traverse through the first list 
        for (T element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
	
	public void doRestrictionRacunari() {
		for(Ispit i:this.list) {
			i.doRestrictionRacunara();
		}
	}
}
