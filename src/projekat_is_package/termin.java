package projekat_is_package;

public class termin {
	public int sala;
	public int sat;
	public int dan;
	public termin(int dan,int sat,int sala) {
		this.sala = sala;
		this.sat = sat;
		this.dan = dan;
	}
	public void ispis() {
		System.out.println("Dan: " +dan+ ", Sat: "+ sat+", Sala: "+sala);
	}
}
