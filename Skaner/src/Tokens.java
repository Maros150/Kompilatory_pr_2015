
public enum Tokens {
	//terminalne symbole
	Krol,
	Skoczek,
	Pion,
	Wieza,
	Goniec,
	Hetman,
	WspX, //ABCDEFGH
	WspY,  //12345678
	
	//symbole nie terminalne
	
	NTS_Start,			//Start
	NTS_C,				//C
	NTS_J,				//J
	
	NTS_W,				//W (wieza)
	
	NTS_X,					//X (pion)
		NTS_Z1,				//Z1
		NTS_Z2,				//Z2
			NTS_B1,			//B1
			NTS_B2,			//B2
			NTS_Q3,			//Q3
			NTS_Q4,			//Q4
			NTS_Q5,			//Q5
				NTS_Ax,		//Ax
				NTS_Bx,		//Bx
				NTS_Cx,		//Cx
				NTS_Dx,		//Dx
				NTS_Ex,		//Ex
				NTS_Fx,		//Fx
				NTS_Gx,		//Gx
				NTS_Hx,		//Hx
				
	NTS_R,					//R (krol)
		NTS_Q1,				//Q1
			NTS_AR,			//AR
			NTS_BR,			//BR
			NTS_CR,			//CR
			NTS_DR,			//DR
			NTS_ER,			//ER
			NTS_FR,			//FR
			NTS_GR,			//GR
			NTS_HR,			//HR
		NTS_Q2,				//Q2
			NTS_Lx1,		//Lx1
			NTS_Lx2,		//Lx2
			NTS_Lx3,		//Lx3
			NTS_Lx4,		//Lx4
			NTS_Lx5,		//Lx5
			NTS_Lx6,		//Lx6
			NTS_Lx7,		//Lx7
			NTS_Lx8,		//Lx8
	
	NTS_S,					//S (skoczek)
		NTS_As,				//As
			NTS_As1,		//As1
		NTS_Bs,				//Bs
			NTS_Bs1,		//Bs1
		NTS_Cs,				//Cs
			NTS_Cs1,		//Cs1
		NTS_Ds,				//Ds
			NTS_Ds1,		//Ds1
		NTS_Es,				//Es
			NTS_Es1,		//Es1
		NTS_Fs,				//Fs
			NTS_Fs1,		//Fs1
		NTS_Gs,				//Gs
			NTS_Gs1,		//Gs1
		NTS_Hs,				//Hs
			NTS_Hs1,		//Hs1
		NTS_Q7,				//Q7
			NTS_Ls1,		//Ls1
			NTS_Ls2,		//Ls2
			NTS_Ls3,		//Ls3
			NTS_Ls4,		//Ls4
			NTS_Ls5,		//Ls5
			NTS_Ls6,		//Ls6
			NTS_Ls7,		//Ls7
			NTS_Ls8,		//Ls8
		NTS_Q8,				//Q8
			NTS_L1,			//L1
			NTS_L2,			//L2
			NTS_L3,			//L3
			NTS_L4,			//L4
			NTS_L5,			//L5
			NTS_L6,			//L6
			NTS_L7,			//L7
			NTS_L8,			//L8
			
	NTS_T,					//T (goniec)
		NTS_YA8,			//YA8
		NTS_YA7,			//YA7
		NTS_YA6,			//YA6
		NTS_YA5,			//YA5
		NTS_YA4,			//YA4
		NTS_YA3,			//YA3
		NTS_YA2,			//YA2
		NTS_YB8,			//YB8
		NTS_YC8,			//YC8
		NTS_YD8,			//YD8
		NTS_YE8,			//YE8
		NTS_YF8,			//YF8
		NTS_YG8,			//YG8
		
		NTS_XA1,			//XA1
		NTS_XA2,			//XA2
		NTS_XA3,			//XA3
		NTS_XA4,			//XA4
		NTS_XA5,			//XA5
		NTS_XA6,			//XA6
		NTS_XA7,			//XA7
		NTS_XB1,			//XB1
		NTS_XC1,			//XC1
		NTS_XD1,			//XD1
		NTS_XE1,			//XE1
		NTS_XF1,			//XF1
		NTS_XG1,			//XG1
			
	NTS_U,					//U(hetman)
}
