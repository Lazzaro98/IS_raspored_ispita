package projekat_is_package;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class raspored {
	dan[] dani;
	private int brDana;
	private int brSala;
	public raspored(int brDana,int brSala, ListaSala ls) {
		this.setBrDana(brDana);
		this.setBrSala(brSala);
		dani = new dan[brDana];
		for(int i=0;i<brDana;i++) {
			dani[i] = new dan(brSala, ls);
			dani[i].set(0, 0, "Dan " + (i+1));
		}
	}
	public dan getDan(int d) {
		return this.dani[d];
	}
	public String get(int dan, int sala, int termin) {
		return this.dani[dan].get(sala, termin);
	}
	
	public void set(int dan, int sala, int termin, String s) {
		this.dani[dan-1].set(sala, termin, s);
	}
	public int getBrDana() {
		return brDana;
	}
	public void setBrDana(int brDana) {
		this.brDana = brDana;
	}
	public int getBrSala() {
		return brSala;
	}
	public void setBrSala(int brSala) {
		this.brSala = brSala;
	}
	public int getKolonu(String naziv) {
		return dani[0].getKolonuSale(naziv);
	}
	
	public void ispis() {
		for(dan i:dani) {
			i.ispis();
		}
	}
	
	public void sacuvajFajl() throws IOException {
		//FileWriter writer = new FileWriter("JavaOut.csv");
		Writer fstream = new OutputStreamWriter(new FileOutputStream("JavaOut.csv"), StandardCharsets.UTF_8);
		for(dan d:dani) {
			for(int i=0;i<5;i++) {
				for(int j=0;j<=d.brSala;j++) {
					fstream.append(d.get(i, j));
					fstream.append(",");
				}
				fstream.append("\n");
			}
			fstream.append("\n");
			fstream.append("\n");
		}
		fstream.close();
	}
	
}
