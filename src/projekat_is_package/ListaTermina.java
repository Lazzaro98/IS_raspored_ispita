package projekat_is_package;

import java.util.ArrayList;

public class ListaTermina {
	public ArrayList<termin> list = new ArrayList<termin>();
	ListaSala ls;
	public ListaTermina(ListaSala ls) {this.ls=ls;}
	
	public int brDezurnih() {
		int ret=0;
		for(termin t:list) {
			ret+=ls.list.get(t.sala-1).getBrojNastavnika();
		}
		return ret;
	}
	
	public int brETF() {
		int ret=0;
		for(termin t:list) {
			ret+=(ls.list.get(t.sala-1).getETF()?1:0);
		}
		return ret;
	}
}
