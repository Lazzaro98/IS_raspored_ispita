package projekat_is_package;

public class dan {
	private String[][] tabela;
	int brSala;
	String [] terminiString = new String[] {"08:00","11:30","15:00","18:30"};
	public dan(int brSala, ListaSala ls) {
		this.brSala=brSala;
		tabela = new String[6][brSala+1];
		for(int i=1;i<=4;i++) {
			tabela[i][0] = terminiString[i-1];
		}
		for(int i=0;i<brSala;i++)
		{
			tabela[0][i+1] = ls.list.get(i).getNaziv();
		}
		for(int i=1;i<5;i++) {
			for(int j=1;j<=brSala;j++) {
				tabela[i][j] = "x"; // prazan string za sad oznacava slobodno mesto
			}
		}
	}
	
	public String get(int x, int y) {
		return this.tabela[x][y];
	}
	public void set(int x, int y,String s) {
		this.tabela[x][y] = s;
	}
	public int getKolonuSale(String naziv) {
		for(int i=1;i<=brSala;i++)
			if(tabela[0][i]==naziv)return i;
		return -1;
	}
	
	public void ispis() {
		for (int i = 0; i <5; i++) {
		    for (int j = 0; j <= brSala; j++) {
		        System.out.print(tabela[i][j] + " ");
		    }
		    System.out.println();
		}
	}
}
