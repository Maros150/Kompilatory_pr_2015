
import java.util.LinkedList;
import java.util.ListIterator;


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
			else if(aktualny.equals(Tokens.Goniec)){
				parseGoniec();
			}
			else if(aktualny.equals(Tokens.Hetman)){
				parseHetman();
			}
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
        String str = "abcdefgh";
        char[] X = str.toCharArray();
        String str2 = "12345678";
        char[] Y = str2.toCharArray();
		 aktualny = getToken(Tokens.WspX);  //1 wspolrzedna X
		 
         switch(aktualny.znak){
                 case 'a':AHr(); break;
             case 'b':Or(X); break;
             case 'c':Or(X); break;
             case 'd':Or(X); break;
             case 'e':Or(X); break;
             case 'f':Or(X); break;
             case 'g':Or(X); break;
             case 'h':AHr(); break;
             default :
                     throw new Exception();
         }
         aktualny = getToken(Tokens.WspY);
         switch(aktualny.znak){
                 case '1':N18r(); break;
             case '2':Or(Y); break;
             case '3':Or(Y); break;
             case '4':Or(Y); break;
             case '5':Or(Y); break;
             case '6':Or(Y); break;
             case '7':Or(Y); break;
             case '8':N18r(); break;
             default :
                     throw new Exception();
                
         }	
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

	private void parseGoniec() throws Exception{
		Token lookhead=findahead(1,Tokens.WspX);
		Token lookhead2=findahead(2,Tokens.WspY);
		Token lookhead3=findahead(3,Tokens.WspX);
		Token lookhead4=findahead(4,Tokens.WspY);
		if((lookhead.znak + lookhead2.znak)==('a'+'8') && (lookhead3.znak + lookhead4.znak)==('a'+'8')){
			Ya8();Ya8();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'7') && (lookhead3.znak + lookhead4.znak)==('a'+'7')){
			Ya7();Ya7();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'6') && (lookhead3.znak + lookhead4.znak)==('a'+'6')){
			Ya6();Ya6();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'5') && (lookhead3.znak + lookhead4.znak)==('a'+'5')){
			Ya5();Ya5();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'4') && (lookhead3.znak + lookhead4.znak)==('a'+'4')){
			Ya4();Ya4();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'3') && (lookhead3.znak + lookhead4.znak)==('a'+'3')){
			Ya3();Ya3();
		}
		else if((lookhead.znak + lookhead2.znak)==('a'+'2') && (lookhead3.znak + lookhead4.znak)==('a'+'2')){
			Ya2();Ya2();
		}
		else if((lookhead.znak + lookhead2.znak)==('b'+'8') && (lookhead3.znak + lookhead4.znak)==('b'+'8')){
			Yb8();Yb8();
		}
		else if((lookhead.znak + lookhead2.znak)==('c'+'8') && (lookhead3.znak + lookhead4.znak)==('c'+'8')){
			Yc8();Yc8();
		}
		else if((lookhead.znak + lookhead2.znak)==('d'+'8') && (lookhead3.znak + lookhead4.znak)==('d'+'8')){
			Yd8();Yd8();
		}
		else if((lookhead.znak + lookhead2.znak)==('e'+'8') && (lookhead3.znak + lookhead4.znak)==('e'+'8')){
			Ye8();Ye8();
		}
		else if((lookhead.znak + lookhead2.znak)==('f'+'8') && (lookhead3.znak + lookhead4.znak)==('f'+'8')){
			Yf8();Yf8();
		}
		else if((lookhead.znak + lookhead2.znak)==('g'+'8') && (lookhead3.znak + lookhead4.znak)==('g'+'8')){
			Yg8();Yg8();
		}
		else if((lookhead.znak + lookhead2.znak)==('b'+'8') && (lookhead3.znak + lookhead4.znak)==('b'+'8')){
			Yb8();Yb8();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'1') && (lookhead3.znak - lookhead4.znak)==('a'-'1')){
			Xa1();Xa1();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'2') && (lookhead3.znak - lookhead4.znak)==('a'-'2')){
			Xa2();Xa2();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'3') && (lookhead3.znak - lookhead4.znak)==('a'-'3')){
			Xa3();Xa3();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'4') && (lookhead3.znak - lookhead4.znak)==('a'-'4')){
			Xa4();Xa4();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'5') && (lookhead3.znak - lookhead4.znak)==('a'-'5')){
			Xa5();Xa5();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'6') && (lookhead3.znak - lookhead4.znak)==('a'-'6')){
			Xa6();Xa6();
		}
		else if((lookhead.znak - lookhead2.znak)==('a'-'7') && (lookhead3.znak - lookhead4.znak)==('a'-'7')){
			Xa7();Xa7();
		}
		else if((lookhead.znak - lookhead2.znak)==('b'-'1') && (lookhead3.znak - lookhead4.znak)==('b'-'1')){
			Xb1();Xb1();
		}
		else if((lookhead.znak - lookhead2.znak)==('c'-'1') && (lookhead3.znak - lookhead4.znak)==('c'-'1')){
			Xc1();Xc1();
		}
		else if((lookhead.znak - lookhead2.znak)==('d'-'1') && (lookhead3.znak - lookhead4.znak)==('d'-'1')){
			Xd1();Xd1();
		}
		else if((lookhead.znak - lookhead2.znak)==('e'-'1') && (lookhead3.znak - lookhead4.znak)==('e'-'1')){
			Xe1();Xe1();
		}
		else if((lookhead.znak - lookhead2.znak)==('f'-'1') && (lookhead3.znak - lookhead4.znak)==('f'-'1')){
			Xf1();Xf1();
		}
		else if((lookhead.znak - lookhead2.znak)==('g'-'1') && (lookhead3.znak - lookhead4.znak)==('g'-'1')){
			Xg1();Xg1();
		} 
		else
			throw new Exception("Goniec: wrong tokens: "+lookhead.toString()+lookhead2.toString());
	}
	private void parseHetman() throws Exception{
		Token lookhead=findahead(1,Tokens.WspX);
		Token lookhead2=findahead(2,Tokens.WspY);
		Token lookhead3=findahead(3,Tokens.WspX);
		Token lookhead4=findahead(4,Tokens.WspY);
		if(lookhead.znak==lookhead3.znak || lookhead2.znak==lookhead4.znak)
			parseWieza();
		else
			parseGoniec();
	}

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
		if(!(aktualny.znak==lookahead.znak-1 || (aktualny.znak=='2' && lookahead.znak=='4')))
			
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
		if(!(aktualny.znak==lookahead.znak+1 || (aktualny.znak=='7' && lookahead.znak=='5')))
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
	
	private void Ya8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Ya8:Wrong tokens expected 'a8'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Ya8:Wrong tokens expected 'b7'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Ya8:Wrong tokens expected 'c6'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Ya8:Wrong tokens expected 'd5'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Ya8:Wrong tokens expected 'e4'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'f3'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'g2'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'h1'");
					break;
			default	:
				throw new Exception("Ya8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Ya7() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Ya8:Wrong tokens expected 'a7'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Ya8:Wrong tokens expected 'b6'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Ya8:Wrong tokens expected 'c5'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Ya8:Wrong tokens expected 'd4'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'e3'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'f2'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'g1'");
					break;
			default	:
				throw new Exception("Ya7:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Ya6() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Ya8:Wrong tokens expected 'a6'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Ya8:Wrong tokens expected 'b5'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Ya8:Wrong tokens expected 'c4'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'd3'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'e2'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'f1'");
					break;
			default	:
				throw new Exception("Ya6:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Ya5() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Ya8:Wrong tokens expected 'a5'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Ya8:Wrong tokens expected 'b4'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'c3'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'd2'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'e1'");
					break;
			default	:
				throw new Exception("Ya5:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Ya4() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Ya8:Wrong tokens expected 'a4'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'b3'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'c2'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'd1'");
					break;
			default	:
				throw new Exception("Ya4:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Ya3() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Ya8:Wrong tokens expected 'a3'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya8:Wrong tokens expected 'b2'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya8:Wrong tokens expected 'c1'");
					break;
			default	:
				throw new Exception("Ya3:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Ya2() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Ya2:Wrong tokens expected 'a2'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Ya2:Wrong tokens expected 'b1'");
					break;
			default	:
				throw new Exception("Ya2:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}		
	private void Yb8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'b':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Yb8:Wrong tokens expected 'b8'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Yb8:Wrong tokens expected 'c7'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Yb8:Wrong tokens expected 'd6'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Yb8:Wrong tokens expected 'e5'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Yb8:Wrong tokens expected 'f4'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Yb8:Wrong tokens expected 'g3'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Yb8:Wrong tokens expected 'h2'");
					break;
			default	:
				throw new Exception("Yb8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Yc8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'c':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Yc8:Wrong tokens expected 'c8'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Yc8:Wrong tokens expected 'd7'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Yc8:Wrong tokens expected 'e6'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Yc8:Wrong tokens expected 'f5'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Yc8:Wrong tokens expected 'g4'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Yc8:Wrong tokens expected 'h3'");
					break;
			default	:
				throw new Exception("Yc8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Yd8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'd':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Yd8:Wrong tokens expected 'd8'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Yd8:Wrong tokens expected 'e7'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Yd8:Wrong tokens expected 'f6'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Yd8:Wrong tokens expected 'g5'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Yd8:Wrong tokens expected 'h4'");
					break;
			default	:
				throw new Exception("Yd8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Ye8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'e':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Ye8:Wrong tokens expected 'e8'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Ye8:Wrong tokens expected 'f7'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Ye8:Wrong tokens expected 'g6'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Ye8:Wrong tokens expected 'h5'");
					break;
			default	:
				throw new Exception("Ye8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Yf8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'f':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Yf8:Wrong tokens expected 'f8'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Yf8:Wrong tokens expected 'g7'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Yf8:Wrong tokens expected 'h6'");
					break;
			default	:
				throw new Exception("Yf8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Yg8() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'g':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Yg8:Wrong tokens expected 'g8'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Yg8:Wrong tokens expected 'h7'");
					break;
			default	:
				throw new Exception("Yg8:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}

	private void Xa1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xa1:Wrong tokens expected 'a1'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xa1:Wrong tokens expected 'b2'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xa1:Wrong tokens expected 'c3'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xa1:Wrong tokens expected 'd4'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xa1:Wrong tokens expected 'e5'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa1:Wrong tokens expected 'f6'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa1:Wrong tokens expected 'g7'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa1:Wrong tokens expected 'h8'");
					break;
			default	:
				throw new Exception("Xa1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Xa2() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xa2:Wrong tokens expected 'a2'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xa2:Wrong tokens expected 'b3'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xa2:Wrong tokens expected 'c4'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xa2:Wrong tokens expected 'd5'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa2:Wrong tokens expected 'e6'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa2:Wrong tokens expected 'f7'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa2:Wrong tokens expected 'g8'");
					break;
			default	:
				throw new Exception("Xa2:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Xa3() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xa3:Wrong tokens expected 'a3'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xa3:Wrong tokens expected 'b4'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xa3:Wrong tokens expected 'c5'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa3:Wrong tokens expected 'd6'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa3:Wrong tokens expected 'e7'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa3:Wrong tokens expected 'f8'");
					break;
			default	:
				throw new Exception("Xa3:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xa4() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xa4:Wrong tokens expected 'a4'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xa4:Wrong tokens expected 'b5'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa4:Wrong tokens expected 'c6'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa4:Wrong tokens expected 'd7'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa4:Wrong tokens expected 'e8'");
					break;
			default	:
				throw new Exception("Xa4:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xa5() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xa5:Wrong tokens expected 'a5'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa5:Wrong tokens expected 'b6'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa5:Wrong tokens expected 'c7'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa5:Wrong tokens expected 'd8'");
					break;
			default	:
				throw new Exception("Xa5:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Xa6() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xa6:Wrong tokens expected 'a6'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa6:Wrong tokens expected 'b7'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa6:Wrong tokens expected 'c8'");
					break;
			default	:
				throw new Exception("Xa6:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xa7() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'a':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xa7:Wrong tokens expected 'a7'");
					break;
			case 'b':if(getToken(Tokens.WspY).znak!='8')
						throw new Exception("Xa7:Wrong tokens expected 'b8'");
					break;
			default	:
				throw new Exception("Xa7:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}		
	private void Xb1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'b':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xb1:Wrong tokens expected 'b1'");
					break;
			case 'c':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xb1:Wrong tokens expected 'c2'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xb1:Wrong tokens expected 'd3'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xb1:Wrong tokens expected 'e4'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xb1:Wrong tokens expected 'f5'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xb1:Wrong tokens expected 'g6'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='7')
						throw new Exception("Xb1:Wrong tokens expected 'h7'");
					break;
			default	:
				throw new Exception("Xb1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Xc1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'c':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xc1:Wrong tokens expected 'c1'");
					break;
			case 'd':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xc1:Wrong tokens expected 'd2'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xc1:Wrong tokens expected 'e3'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xc1:Wrong tokens expected 'f4'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xc1:Wrong tokens expected 'g5'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='6')
						throw new Exception("Xc1:Wrong tokens expected 'h6'");
					break;
			default	:
				throw new Exception("Xc1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xd1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'd':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xd1:Wrong tokens expected 'd1'");
					break;
			case 'e':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xd1:Wrong tokens expected 'e2'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xd1:Wrong tokens expected 'f3'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xd1:Wrong tokens expected 'g4'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='5')
						throw new Exception("Xd1:Wrong tokens expected 'h5'");
					break;
			default	:
				throw new Exception("Xd1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xe1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'e':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xe1:Wrong tokens expected 'e1'");
					break;
			case 'f':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xe1:Wrong tokens expected 'f2'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xe1:Wrong tokens expected 'g3'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='4')
						throw new Exception("Xe1:Wrong tokens expected 'h4'");
					break;
			default	:
				throw new Exception("Xe1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}	
	private void Xf1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'f':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xf1:Wrong tokens expected 'f1'");
					break;
			case 'g':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xf1:Wrong tokens expected 'g2'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='3')
						throw new Exception("Xf1:Wrong tokens expected 'h3'");
					break;
			default	:
				throw new Exception("Xf1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}
	private void Xg1() throws Exception{
		aktualny=getToken(Tokens.WspX);	
		switch(aktualny.znak){
			case 'g':if(getToken(Tokens.WspY).znak!='1')
						throw new Exception("Xg1:Wrong tokens expected 'g1'");
					break;
			case 'h':if(getToken(Tokens.WspY).znak!='2')
						throw new Exception("Xg1:Wrong tokens expected 'h2'");
					break;
			default	:
				throw new Exception("Xg1:Wrong tokens expected WspY, was: "+aktualny.token);
		}	
	}

	private void AHr() throws Exception {
        char akt = aktualny.znak;               //1 wsp X
        aktualny = tokenIter.next();    //2 wsp Y
       
        if(akt=='a'){  
                if(aktualny.znak != 'b' && aktualny.znak != 'a' )
                        throw new Exception("wrong token: expected a or b, was: "+aktualny.znak);
        }      
        else{
                if(aktualny.znak != 'g' && aktualny.znak != 'h')
                        throw new Exception("wrong token: expected g or h, was: "+aktualny.znak);      
        }      
}

	private void N18r() throws Exception  {
        char akt = aktualny.znak;               //1 wsp Y
        aktualny = tokenIter.next();    //2 wsp Y
        if(akt=='1'){    
                if(aktualny.znak != '1'  && aktualny.znak!= '2')
                        throw new Exception("wrong token: expected 1 or 2, was: "+aktualny.znak);
        }      
        else{
                if(aktualny.znak != '7' && aktualny.znak != '8')
                        throw new Exception("wrong token: expected 7 or 8, was: "+aktualny.znak);      
        }      
	}

	private void Or(char[] X) throws Exception {
        char akt = aktualny.znak;        //1 wsp X lub Y
        aktualny = tokenIter.next(); //2 wsp X lub Y
        int pos=0;
        for(char c : X){
                if(c==akt)
                        break;
                pos=pos+1;     
                }
                if(!(X[pos-1] == aktualny.znak || X[pos] == aktualny.znak || X[pos+1] == aktualny.znak))
                        throw new Exception("wrong token: expected " + String.valueOf(X[pos-1]) + " or "+ X[pos] + " or "+X[pos+1] +", was: "+aktualny.znak);
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
