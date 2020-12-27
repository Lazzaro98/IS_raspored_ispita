package projekat_is_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sala {
	private String naziv;
	private Long kapacitet;
	private boolean racunar;
	private Long brojNastavnika;
	private boolean ETF;
	public Sala(String naziv, Long kapacitet, boolean racunar, Long brojNastavnika, boolean ETF) {
		this.naziv = naziv;
		this.kapacitet = kapacitet;
		this.racunar = racunar;
		this.brojNastavnika = brojNastavnika;
		this.ETF = ETF;
	}
	public boolean getETF() {
		return ETF;
	}
	public void setETF(boolean eTF) {
		ETF = eTF;
	}
	public Long getBrojNastavnika() {
		return brojNastavnika;
	}
	public void setBrojNastavnika(Long brojNastavnika) {
		this.brojNastavnika = brojNastavnika;
	}
	public boolean getRacunar() {
		return racunar;
	}
	public void setRacunar(boolean racunar) {
		this.racunar = racunar;
	}
	public Long getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(Long kapacitet) {
		this.kapacitet = kapacitet;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public void ispis() {
		System.out.println("Naziv: " + this.naziv);
		System.out.println("Broj Nastavnika: " + this.brojNastavnika);
		System.out.println("Racunar: " + racunar);
		System.out.println("Kapacitet: " + kapacitet);
		System.out.println("ETF: " + this.ETF);
		System.out.println();
	}
}
