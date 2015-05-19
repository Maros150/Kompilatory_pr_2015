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
			/*else if(aktualny.equals(Tokens.Skoczek)){
				parseSkoczek();
			}*/
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
			
	/*
	private void parseGoniec() throws Exception{
		ruch.wspx_z=getToken(ruch,Tokens.WspX);
		ruch.wspy_z=getToken(ruch,Tokens.WspY);
		ruch.wspx_do=getToken(ruch,Tokens.WspX);
		ruch.wspy_do=getToken(ruch,Tokens.WspY);
		
		if(ruch.wspy_z==ruch.wspy_do-2 && (ruch.wspx_z!=ruch.wspx_do+1 && ruch.wspx_z!=ruch.wspx_do-1))
			throw new Exception("Goniec: zly ruch");
			
	}
	private void parseHetman() throws Exception{
		ruch.wspx_z=getToken(ruch,Tokens.WspX);
		ruch.wspx_do=getToken(ruch,Tokens.WspX);
		ruch.wspy_z=getToken(ruch,Tokens.WspY);
		ruch.wspy_do=getToken(ruch,Tokens.WspY);
	}

	private void parseSkoczek() throws Exception{
		ruch.wspx_z=getToken(ruch,Tokens.WspX);
		ruch.wspx_do=getToken(ruch,Tokens.WspX);
		ruch.wspy_z=getToken(ruch,Tokens.WspY);
		ruch.wspy_do=getToken(ruch,Tokens.WspY);
	}
	
	*/

	
	
	
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
