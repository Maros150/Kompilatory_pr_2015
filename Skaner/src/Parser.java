import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import com.sun.org.apache.bcel.internal.generic.LLOAD;


public class Parser {
	LinkedList<Token> toParse;
	ListIterator<Token> tokenIter;
	Token aktualny;
	
	Parser(LinkedList<Token> toParse){
		this.toParse=toParse;
		tokenIter = toParse.listIterator();
		
	}
	

	
	public boolean parse() throws Exception{
		if (!tokenIter.hasNext()) {
			throw new Exception("Premature end of expression");
		}
		while(tokenIter.hasNext()){
			aktualny = tokenIter.next();
			
			if(aktualny.equals(Tokens.Krol)){
				parseKrol();
			}
			else if(aktualny.equals(Tokens.Wieza)){
				parseWieza();
			}
			/*else if(aktualny.equals(Tokens.Goniec)){
				parseGoniec();
			}
			else if(aktualny.equals(Tokens.Hetman)){
				parseHetman();
			}*/
			else if(aktualny.token==Tokens.Pion){
				parsePion();
			}
			else if(aktualny.equals(Tokens.Skoczek)){
				parseSkoczek();
			}
			else
				throw new Exception("Wrong token: oczekiwana figura-jest: "+ aktualny.znak+" "+aktualny.token+" ");
			
		}
		return true;
		
	}
	private void parseKrol() throws Exception{
		
		
	/*	//jezeli wspy_z = wspy_do +1 lub wspy_do lub wspy_do -1(w szerz)i dodatkowo do góry o jeden lub w do³
		if((ruch.wspy_z!=ruch.wspy_do && ruch.wspy_z!=ruch.wspy_do+1 && ruch.wspy_z!=ruch.wspy_do-1) || 
		   (ruch.wspx_z!=ruch.wspx_do-1 &&  ruch.wspx_z!=ruch.wspx_do+1  &&  ruch.wspx_z!=ruch.wspx_do))
				throw new Exception("Krol - zle przejscie");
		*/
			
	}
	private void parseWieza() throws Exception{
		aktualny = getToken(Tokens.WspX);	//1 wspolrzedna X
		Token lookahead =findahead(2,Tokens.WspX);	//sprawdzenie co jest o 2 z przodu
		if(aktualny.znak == lookahead.znak){
			C();							//2 wspolrzedna Y
			getToken(Tokens.WspX);			//3 X
			C();							//4 Y
		}
		else {
			aktualny = getToken(Tokens.WspY);	//2wsp Y
			lookahead =findahead(2,Tokens.WspY);
			if(aktualny.znak == lookahead.znak){
				L();						//3 wsp X
				getToken(Tokens.WspY);		//4 wspY
			}
			else
				throw new Exception("Wieza: wrong tokens"+aktualny.toString()+lookahead.toString());
		}
	}	
	private void parsePion() throws Exception{
		aktualny = getToken(Tokens.WspY);	//kolor
		if(aktualny.znak=='1')
			Z1();
		else if(aktualny.znak=='2')
			Z2();
		else
			throw new Exception("Pion: wrong color token"+aktualny.znak);
	}	

	/*private void parseGoniec() throws Exception{
			
	}*/
	/*private void parseHetman() throws Exception{
		ruch.wspx_z=getToken(ruch,Tokens.WspX);
		ruch.wspx_do=getToken(ruch,Tokens.WspX);
		ruch.wspy_z=getToken(ruch,Tokens.WspY);
		ruch.wspy_do=getToken(ruch,Tokens.WspY);
	}
*/
	private void parseSkoczek() throws Exception{
		aktualny = getToken(Tokens.WspX);	//X
		switch(aktualny.znak){
			case 'a':As();break;
			case 'b':Bs();break;
			case 'c':Cs();break;
			case 'd':Ds();break;
			case 'e':Es();break;
			case 'f':Fs();break;
			case 'g':Gs();break;
			case 'h':Hs();break;
			default	:
				throw new Exception("Skoczek: wrong first token"+aktualny.toString());
		}
	}

	
	
	private void C() throws Exception{
		Token token =getToken(Tokens.WspY); 
		
		switch(token.znak){
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':break;
			default : 
				throw new Exception("wrong token: expected "+ Tokens.WspY +", was: "+token.token);
		}
	}	
	private void L() throws Exception{	
		Token token =getToken(Tokens.WspX); 
		switch(token.znak){
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':break;
			default : 
				throw new Exception("Wrong token: expected "+ Tokens.WspX +", was: "+token.token);
		}
	}
	
	private void J() throws Exception{// aa bb cc ...
		aktualny=getToken(Tokens.WspX);			//1
		Token lookahead =getToken(Tokens.WspX);	//2
		
		if(aktualny.znak != lookahead.znak)
			throw new Exception("Wrong tokens: expected 2* "+ Tokens.WspX +", was: "+lookahead.token + " and "+aktualny.token);
	}
	private void Z1() throws Exception{
		if(findahead(1, Tokens.WspX).znak==findahead(2, Tokens.WspX).znak){ //sprawdzenie bez przesuwania wskaznika
			B1();
		}
		else {
			Q3();					//XX
			Q4();					//YY
		}
	}
	private void B1() throws Exception{
		J();									//XX
		aktualny=getToken(Tokens.WspY);			//Y
		Token lookahead =getToken(Tokens.WspY);	//Y
		//23,24,32,34
		if(aktualny.znak!=lookahead.znak-1 && (aktualny.znak=='2' && lookahead.znak!='4'))
			throw new Exception("B1():Wrong token: expected "+ Tokens.WspY +", was: "+lookahead.token);
	}
	private void Q3() throws Exception{
		aktualny=getToken(Tokens.WspX);			//X
		switch(aktualny.znak){					
			case 'a':Ax();break;				//b
			case 'b':Bx();break;				//a,c
			case 'c':Cx();break;				//b,d
			case 'd':Dx();break;				//c,e
			case 'e':Ex();break;				//d,f
			case 'f':Fx();break;				//e,g
			case 'g':Gx();break;				//f,h
			case 'h':Hx();break;				//g
			default	:
				throw new Exception("Q3():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
				
		}
	}
	private void Ax() throws Exception{
		aktualny=getToken(Tokens.WspX);		//b
		if(aktualny.znak!='b')
			throw new Exception("Ax():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Bx() throws Exception{
		aktualny=getToken(Tokens.WspX);		// a,c
		if(aktualny.znak!='a' && aktualny.znak!='c')
			throw new Exception("Bx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Cx() throws Exception{
		aktualny=getToken(Tokens.WspX);		//b,d
		if(aktualny.znak!='b' && aktualny.znak!='d')
			throw new Exception("Cx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Dx() throws Exception{
		aktualny=getToken(Tokens.WspX);		//c,e
		if(aktualny.znak!='c' && aktualny.znak!='e')
			throw new Exception("Dx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Ex() throws Exception{
		aktualny=getToken(Tokens.WspX);		//d,f
		if(aktualny.znak!='d' && aktualny.znak!='f')
			throw new Exception("Ex():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Fx() throws Exception{
		aktualny=getToken(Tokens.WspX);		//e,g
		if(aktualny.znak!='e' && aktualny.znak!='g')
			throw new Exception("Fx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Gx() throws Exception{
		aktualny=getToken(Tokens.WspX);		//f,h
		if(aktualny.znak!='f' && aktualny.znak!='h')
			throw new Exception("Gx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Hx() throws Exception{
		aktualny=getToken(Tokens.WspX);		//g
		if(aktualny.znak!='g')
			throw new Exception("Hx():Wrong token: expected "+ Tokens.WspX +", was: "+aktualny.token);
	}
	private void Q4() throws Exception{
		aktualny=getToken(Tokens.WspY);			//Y
		Token lookahead =getToken(Tokens.WspY);	//Y
		
		if(aktualny.znak!=lookahead.znak-1)
			throw new Exception("Q4:Wrong tokens expected "+ Tokens.WspY +", was: "+aktualny.token+ " "+lookahead.token);
	}

	private void Z2() throws Exception{
		if(findahead(1, Tokens.WspX).znak==findahead(2, Tokens.WspX).znak){ //sprawdzenie bez przesuwania wskaznika
			B2();
		}
		else {
			Q3();					//XX
			Q5();					//YY
		}	
	}
	private void B2() throws Exception{
		J();									//XX
		aktualny=getToken(Tokens.WspY);			//Y
		Token lookahead =getToken(Tokens.WspY);	//Y
		//76,75,65,54,43,32,21
		if(aktualny.znak!=lookahead.znak+1 && (aktualny.znak=='7' && lookahead.znak!='5'))
			throw new Exception("B1():Wrong token: expected "+ Tokens.WspY +", was: "+lookahead.znak+"-"+aktualny.znak);	
	}
	private void Q5() throws Exception{
		aktualny=getToken(Tokens.WspY);			//Y
		Token lookahead =getToken(Tokens.WspY);	//Y
		
		if(aktualny.znak!=lookahead.znak+1)
			throw new Exception("Q4:Wrong tokens expected "+ Tokens.WspY +", was: "+aktualny.token+ " "+lookahead.token);
	}
	
	private void As() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='c'){
			As1();		//c
			Q7();
		}
		else{
			Ax();
			Q8();
		}
	}
	private void Bs() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='d'){
			Bs1();		//d
			Q7();
		}
		else{
			Bx();
			Q8();
		}	
	}
	private void Cs() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='e' || findahead(1, Tokens.WspX).znak=='e'){
			Cs1();		//e lub f
			Q7();
		}
		else{
			Cx();
			Q8();
		}	
	}
	private void Ds() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='b' || findahead(1, Tokens.WspX).znak=='f'){
			Ds1();		//b lub f
			Q7();
		}
		else{
			Dx();
			Q8();
		}	
	}
	private void Es() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='c' || findahead(1, Tokens.WspX).znak=='g'){
			Es1();		//c lub g
			Q7();
		}
		else{
			Ex();
			Q8();
		}	
	}
	private void Fs() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='d' || findahead(1, Tokens.WspX).znak=='h'){
			Fs1();		//d lub h
			Q7();
		}
		else{
			Fx();
			Q8();
		}	
	}
	private void Gs() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='e'){
			Gs1();		//e
			Q7();
		}
		else{
			Gx();
			Q8();
		}		
	}
	private void Hs() throws Exception{
		if(findahead(1, Tokens.WspX).znak=='f'){
			Hs1();		//f
			Q7();
		}
		else{
			Hx();
			Q8();
		}		
	}
	
	private void As1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='c')
			throw new Exception("As1:Wrong tokens expected 'c', was: "+aktualny.znak);
	}	
	private void Bs1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='d')
			throw new Exception("Bs1:Wrong tokens expected 'd', was: "+aktualny.znak);
	}
	private void Cs1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='a' && aktualny.znak!='e')
			throw new Exception("Cs1:Wrong tokens expected 'a lub e', was: "+aktualny.znak);
	}	
	private void Ds1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='b' && aktualny.znak!='f')
			throw new Exception("Ds1:Wrong tokens expected 'b lub f', was: "+aktualny.znak);
	}	
	private void Es1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='c' && aktualny.znak!='g')
			throw new Exception("Es1:Wrong tokens expected 'c lub g', was: "+aktualny.znak);
	}	
	private void Fs1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='d' && aktualny.znak!='h')
			throw new Exception("Fs1:Wrong tokens expected 'd lub h', was: "+aktualny.znak);
	}	
	private void Gs1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='e')
			throw new Exception("Gs1:Wrong tokens expected 'e', was: "+aktualny.znak);
	}	
	private void Hs1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		if(aktualny.znak!='f')
			throw new Exception("Hs1:Wrong tokens expected 'f', was: "+aktualny.znak);
	}
	
	private void Q7() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		switch(aktualny.znak){
			case '1':Ls1();break;
			case '2':Ls2();break;
			case '3':Ls3();break;
			case '4':Ls4();break;
			case '5':Ls5();break;
			case '6':Ls6();break;
			case '7':Ls7();break;
			case '8':Ls8();break;
			default	:
				throw new Exception("Q7:Wrong tokens expected WspY, was: "+aktualny.token);
		}
	}
	private void Ls1() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='2')
			throw new Exception("Ls1:Wrong tokens expected '2', was: "+aktualny.znak);
	}
	private void Ls2() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='1' && aktualny.znak!='3')
			throw new Exception("Ls2:Wrong tokens expected '1 lub 3', was: "+aktualny.znak);
	}
	private void Ls3() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='2' && aktualny.znak!='4')
			throw new Exception("Ls3:Wrong tokens expected '2 lub 4', was: "+aktualny.znak);
	}
	private void Ls4() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='3' && aktualny.znak!='5')
			throw new Exception("Ls4:Wrong tokens expected '3 lub 5', was: "+aktualny.znak);
	}
	private void Ls5() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='4' && aktualny.znak!='6')
			throw new Exception("Ls5:Wrong tokens expected '4 lub 6', was: "+aktualny.znak);
	}
	private void Ls6() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='5' && aktualny.znak!='7')
			throw new Exception("Ls6:Wrong tokens expected '5 lub 7', was: "+aktualny.znak);
	}
	private void Ls7() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='6' && aktualny.znak!='8')
			throw new Exception("Ls7:Wrong tokens expected '6 lub 8', was: "+aktualny.znak);
	}
	private void Ls8() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='7')
			throw new Exception("Ls8:Wrong tokens expected '7', was: "+aktualny.znak);
	}
	
	private void Q8() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		switch(aktualny.znak){
			case '1':£s1();break;
			case '2':£s2();break;
			case '3':£s3();break;
			case '4':£s4();break;
			case '5':£s5();break;
			case '6':£s6();break;
			case '7':£s7();break;
			case '8':£s8();break;
			default	:
				throw new Exception("Q8:Wrong tokens expected WspY, was: "+aktualny.token);
		}
	}

	private void £s1() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='3')
			throw new Exception("£s1:Wrong tokens expected '3', was: "+aktualny.znak);
	}
	private void £s2() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='4')
			throw new Exception("£s2:Wrong tokens expected '4', was: "+aktualny.znak);
	}
	private void £s3() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='1' && aktualny.znak!='5')
			throw new Exception("£s3:Wrong tokens expected '1 lub 5', was: "+aktualny.znak);
	}
	private void £s4() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='2' && aktualny.znak!='6')
			throw new Exception("£s4:Wrong tokens expected '2 lub 6', was: "+aktualny.znak);
	}
	private void £s5() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='3' && aktualny.znak!='7')
			throw new Exception("£s5:Wrong tokens expected '3 lub 7', was: "+aktualny.znak);
	}
	private void £s6() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='4' && aktualny.znak!='8')
			throw new Exception("£s6:Wrong tokens expected '4 lub 8', was: "+aktualny.znak);
	}
	private void £s7() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='5')
			throw new Exception("£s7:Wrong tokens expected '5', was: "+aktualny.znak);
	}
	private void £s8() throws Exception{
		aktualny=getToken(Tokens.WspY);	
		if(aktualny.znak!='6')
			throw new Exception("£s8:Wrong tokens expected '6', was: "+aktualny.znak);
	}
	
	
	
	
	
	//pobranie kolejneo tokena i przesuniecie wskaznika
	private Token getToken(Tokens tok) throws Exception{
		if (!tokenIter.hasNext()) {
			throw new Exception("Lack of tokens ("+aktualny.token +")");
		}
		Token temp = tokenIter.next();
		
		if(!temp.equals(tok))
			throw new Exception("Wrong token: expected "+ tok +", was: "+temp.token+"("+temp.znak+")");
		return temp;
	}
	
	//wyciagniecie tokena bez przesuwania wsakaznika
	private Token findahead(int ahead,Tokens tok) throws Exception{
		//pchniecie wskaznika  do przodu
		for(int i=0;i<ahead-1;i++){ 
			if(!tokenIter.hasNext())
				throw new Exception("Lack of tokens ("+tokenIter.next().token +")");
			tokenIter.next();
		}
		Token temp = getToken(tok);
		
		//cofniecie wskaznika o 1 raz wiecej
		for(int i=0;i<ahead;i++){
			tokenIter.previous();
		}
		return temp;
		
	}

}
