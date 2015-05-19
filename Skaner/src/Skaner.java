
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;


public class Skaner {
	private String plik; 
	
	public Skaner(String plik) {
		this.plik=plik;
	}

	public static void main(String[] args) throws Exception {
		String plik = "plik.txt";
		Skaner skaner = new Skaner(plik);
		Parser parser= new Parser(skaner.start());
		System.out.println("Parser:\n"+parser.parse());
	}
	
	
	public LinkedList<Token> start() throws FileNotFoundException{
		String wsp_X= "abcdefgh";
		String wsp_Y= "12345678";
		Scanner in = new Scanner(new FileReader(plik));
		LinkedList<Token> tokeny = new LinkedList<Token>();
		char znak;
		
		while(in.hasNext()){
		//for(int j=0;j<1;j++){
			String s = in.nextLine();
			
			for(int i=0;i<s.length();i++){
				
				znak =s.charAt(i);
				switch(znak){
					case 'r':	tokeny.add(new Token(znak,Tokens.Krol));	break;
					case 's':	tokeny.add(new Token(znak,Tokens.Skoczek));	break;
					case 'x':	tokeny.add(new Token(znak,Tokens.Pion));	break;
					case 'w':	tokeny.add(new Token(znak,Tokens.Wieza));	break;
					case 't':	tokeny.add(new Token(znak,Tokens.Goniec));	break;
					case 'u':	tokeny.add(new Token(znak,Tokens.Hetman));	break;
					default :	if(wsp_X.contains(Character.toString(znak)))
									tokeny.add(new Token(znak,Tokens.WspX));
								else if(wsp_Y.contains(Character.toString(znak)))
									tokeny.add(new Token(znak, Tokens.WspY));
								else if(znak == ' ')
									System.out.println("spacja");
								else	
									System.out.println("zly znak");
								break;
				}
				if(znak!=tokeny.getLast().znak)
					System.out.println(tokeny.getLast().toString());	
			}
		}
		System.out.println("Wczytane tokeny\n"+tokeny.toString());
		in.close();
		return tokeny;
		}
		
	}
