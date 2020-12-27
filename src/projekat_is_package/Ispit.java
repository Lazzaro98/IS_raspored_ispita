package projekat_is_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class Ispit {
	private String sifra;
	private Long brojStudenata;
	private boolean racunar;
	private ArrayList<String> odseci;
	ListaSala ls = new ListaSala();
	public ArrayList<ListaTermina> hs  = new ArrayList<ListaTermina>(); //domen
	
	public Ispit(String sifra,Long brojStudenata,boolean racunar,ArrayList<String> odseci) {
		this.sifra= sifra;
		this.brojStudenata = brojStudenata;
		this.racunar = racunar;
		this.odseci = odseci;
	}
	
	
	public ListaSala getListaSala() {return this.ls;}
	public void setListaSala(ListaSala ls) {this.ls=ls;}
	
	public String getSifra() {
		return this.sifra;
	}
	
	//f-je za citanje sifre:
	//AAOKKG{P}1/4
	public String getAA() {
		return sifra.substring(0, 2);
	}
	public String getO() {
		return sifra.substring(2,3);
	}
	public String getKK() {
		return sifra.substring(3,5);
	}
	public String getG() {
		return sifra.substring(5,6);
	}
	public String getP() {
		return sifra.substring(6);
	}
	//kraj f-ja za citanje sifre
	
	
	public Long getBrojStudenata() {
		return this.brojStudenata;
	}
	public boolean getRacunar() {
		return this.racunar;
	}
	public ArrayList<String> getOdsek() {
		return this.odseci;
	}
	
	//seteri
	public void setSifra(String sifra1) {
		sifra = sifra1;
	}
	public void setBrojStudenata(Long brojStudenata) {
		this.brojStudenata = brojStudenata;
	}
	public void setRacunar(boolean racunar) {
		this.racunar = racunar;
	}
	public void setOdsek(ArrayList<String> odseci) {
		this.odseci = odseci;
	}
	public void dodajOdsek(String odsek) {
		this.odseci.add(odsek);
		Collections.sort(this.odseci);
	}
	
	public void nadjiSveKombinacije(Ispit i, ListaSala ls) {
		Long brPrijavljenih = i.getBrojStudenata();
		ArrayList<Sala> lista = ls.list;
		
	}
	
	private void nadji_recursive(ArrayList<Sala> numbersR,Long prijavljeni, ArrayList<Sala> partial,raspored r) {
		int s = 0;
		
		
		for (Sala sala:partial) s+=sala.getKapacitet();
		if(s>=prijavljeni) {
			ListaTermina noviTermini = new ListaTermina(ls);
			boolean imaKomp = true;
			for (Sala sala:partial) {
            	if(!sala.getRacunar())imaKomp=false;
            	int k =r.getKolonu(sala.getNaziv());
            	termin novi = new termin(1,1,k);
            	noviTermini.list.add(novi);
            	
            }
            ListaTermina sviTermini = new ListaTermina(ls);
        	for(int i = 1; i <= r.getBrDana();i++) {
        		for(int j = 1; j <= 4;j++) {
        			sviTermini.list.clear();
        			for(termin t:noviTermini.list) {
        			termin novi = new termin(i,j,t.sala);
        			
        			sviTermini.list.add(novi);
        			}
        			ListaTermina td = new ListaTermina(ls);
        			td.list = new ArrayList(sviTermini.list);
        			hs.add(td);
        		}
            }
        	noviTermini.list.clear();
		}
         
		 for(int i=0;i<numbersR.size();i++) {
             ArrayList<Sala> remaining = new ArrayList<Sala>();
             Sala n = numbersR.get(i);
             for (int j=i+1; j<numbersR.size();j++) remaining.add(numbersR.get(j));
             ArrayList<Sala> partial_rec = new ArrayList<Sala>(partial);
             partial_rec.add(n);
             nadji_recursive(remaining,prijavljeni,partial_rec,r);
       }
}
		
		
	
	private void sortirajTermine() {
		Collections.sort(hs,Comparator.comparing(ListaTermina::brDezurnih).thenComparing(ListaTermina::brETF));
	}
	private void nadji(ListaSala numbers,Long prijavljeni,raspored r) {
		nadji_recursive(numbers.list,prijavljeni, new ArrayList<Sala>(),r);
		
	}
	
	public void loadDomain(ListaSala ls,raspored r) {
		this.setListaSala(ls);
		nadji(ls,this.brojStudenata,r);
		sortirajTermine();
	}
	
	public void ispis() {
		System.out.println("Sifra: " + this.sifra);
		System.out.println("Broj prijavljenih: " + this.brojStudenata);
		System.out.println("Racunar: " + racunar);
		System.out.println("Odseci:");
		for(String odsek:odseci) {
			System.out.println(odsek);
		}
		System.out.println();
	}
	
	public void ispisiDomen() {
		Iterator <ListaTermina> itr = hs.iterator();
		while(itr.hasNext()) {
			ListaTermina privremena = itr.next();
			this.ispis();
			System.out.println("Br potrebnih dezurnih:" + privremena.brDezurnih()+", a broj sala u etf-u je:"+privremena.brETF());
			for(termin t:privremena.list) {
				t.ispis();
			}
			System.out.println();
		}
	}
	
	
	public static boolean uporedi(ArrayList<termin> t1, ArrayList<termin> t2) {
		for(termin t1t:t1) {
			for(termin t2t:t2) {
				if(t1t.dan==t2t.dan && t1t.sala==t2t.sala && t1t.sat==t2t.sat) return true;
			}
		}
		return false;
	}
	
	
	public void removeFromDomen(ArrayList<termin> del) {
		for(ListaTermina itr:hs) {
			if(uporedi(itr.list,del))
				hs.remove(itr);
		}
	}
	
	public Ispit copy(){
		ListaIspita li = new ListaIspita();
		Ispit isp = new Ispit(this.sifra,this.brojStudenata,this.racunar,this.odseci);
		isp.hs = new ArrayList(this.hs);
		
		return isp;
	}
	
	
	public void doRestrictionRacunara() {
		if(this.racunar) {
		ArrayList<ListaTermina> rm = new ArrayList<ListaTermina>();
		for(ListaTermina lt:hs) {
			boolean racunar1 = true;
			for(termin t:lt.list) {
				if(ls.list.get(t.sala-1).getRacunar() == false)racunar1=false;
			}
			if(racunar1==false)rm.add(lt);
		}
		hs.removeAll(rm);
		}
	}
}
