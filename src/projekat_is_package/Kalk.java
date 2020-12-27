package projekat_is_package;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class Kalk {
	ListaSala ls;
	ListaIspita li;
	raspored r;
	String saleFileName = "C:\\Users\\board\\Desktop\\b\\javni_testovi\\sale5.json";
	String rokFileName="C:\\Users\\board\\Desktop\\b\\javni_testovi\\rok5.json";
	Kalk() throws FileNotFoundException, IOException, ParseException{
		ls= new ListaSala();
		ls.load(saleFileName);
		li = new ListaIspita();
		li.load(rokFileName);
		r = new raspored(li.trajanje_u_danima.intValue(),(int)ls.getSize(),ls);
		li.loadDomain(ls, r);
		li.doRestrictionRacunari();
	}
	
	public ArrayList<ArrayList<termin>> resi(){
		
		ListaIspita li2 = new ListaIspita();
		li2 = li.copy();
		int i = 1;
		boolean f = saleFileName.contains("sale4");
		ArrayList<ArrayList<termin>> resenje = new ArrayList<ArrayList<termin>>();
		while(i>=1 && i<=li2.getSize()) {
			ListaIspita li3 = new ListaIspita();
			li3 = li.copy();
			ArrayList<termin> x = SelectValue(i-1);
			
			if(x==null) {

				for(int j=i+1;j<=li.getSize();j++)
					li.list.set(j-1, li3.list.get(j-1).copy());
				i--;
				//int index = resenje.size() - 1; 
		        // Delete last element by passing index 
				//if(index>=0)resenje.remove(index); 
			}
			else {
				resenje.add(x);
				i++;
			}
			//if(f && i==li2.getSize())return resenje;
			
		}
		if(i==0) 
			return null;
		else 
			return resenje;
	}
	
	
	private ArrayList<termin> SelectValue(int i) {
		ListaTermina a = new ListaTermina(ls);
		ListaIspita li2 = new ListaIspita();
		while(!li.list.get(i).hs.isEmpty()) {
			
			a = li.list.get(i).hs.get(0);
			li.list.get(i).hs.remove(a);
			boolean prazan = false;
			li2 = li.copy();
			for(int k = i+2; k <= li2.getSize();k++) {
				ArrayList<ListaTermina> removeTermins = new ArrayList<ListaTermina>();
				for(ListaTermina b : li.list.get(k-1).hs) {
					boolean preklop = li.list.get(i).getG().equals(li.list.get(k-1).getG()) && preklapanjeOdseka(li.list.get(i).getOdsek(), li.list.get(k-1).getOdsek());
					if(is_consistent(b.list, a.list) || (preklop && (a.list.get(0).dan == b.list.get(0).dan)))
						removeTermins.add(b);
				}
				li.list.get(k-1).hs.removeAll(removeTermins);
				if(li.list.get(k-1).hs.isEmpty()) 
					{
						prazan=true;
					}
			}
			if(prazan) 
				for(int j=i+1;j<=li.getSize();j++) 
					li.list.set(j-1, li2.list.get(j-1).copy());
			
			
			else return a.list;
			
		}
		return null;
	}
	
	public void ispis(ArrayList<ArrayList<termin>> res) throws IOException {
		 if(res!=null) {
			  for (int i = 0; i < res.size(); i++) { 
				  System.out.println(li.list.get(i).getSifra());
		            for (int j = 0; j < res.get(i).size(); j++) {
		            	r.set(res.get(i).get(j).dan, res.get(i).get(j).sat, res.get(i).get(j).sala,li.list.get(i).getSifra());
		            	res.get(i).get(j).ispis();
		            }
		            System.out.println();
			   }
			   } else {
				   
			   }
				
			   r.ispis();
			
			   r.sacuvajFajl();
	}
	  public static boolean is_consistent(ArrayList<termin> a, ArrayList<termin> t) {
	    	return uporedi(a,t);
	    }
	  public static boolean uporedi(ArrayList<termin> t1, ArrayList<termin> t2) {
			for(termin t1t:t1) {
				for(termin t2t:t2) {
					if(t1t.dan==t2t.dan && t1t.sala==t2t.sala && t1t.sat==t2t.sat) return true;
				}
			}
			return false;
		}
	  public boolean preklapanjeOdseka(ArrayList<String> s1, ArrayList<String> s2) {
		  for(String a:s1) {
			  for(String b:s2) {
				  if(a.equals(b))return true;
			  }
		  }
		  return false;
		  
	  }
}
