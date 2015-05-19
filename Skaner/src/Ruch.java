
public class Ruch {
	char wspx_z;
	char wspx_do;
	char wspy_z;
	char wspy_do;
	Tokens figura;
	boolean white;
	
/*	Ruch(char wspx_z,char wspx_do,short wspy_z,short wspy_do, Tokens fig){
		this.wspx_do=wspx_do;
		this.wspx_z=wspx_z;
		this.wspy_do=wspy_do;
		this.wspy_z=wspy_z;
		this.figura=fig;
	}*/
	public String toString(){
		if(white)
			return "Ruch: "+ figura +" biala"+" z "+wspx_z+wspy_z+" do "+wspx_do+wspy_do;
		else
			return "Ruch: "+ figura +" czarna"+" z "+wspx_z+wspy_z+" do "+wspx_do+wspy_do;
		
	}
}
