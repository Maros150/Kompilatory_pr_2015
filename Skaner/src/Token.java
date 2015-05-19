
public class Token {
	char znak;
	Tokens token;
	
	Token(char z,Tokens t){
		znak=z;
		token=t;
	}
	public String toString(){
		return token.toString()+ ": "+znak;
	}
	public boolean equals(Tokens t){
		return token==t?true:false;
	}
}
