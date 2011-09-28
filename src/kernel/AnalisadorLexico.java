package kernel;

import io.LeitorArquivo;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Symbol;
import util.CupHelper;
import cup.LogSymbolFactory;
import exception.InvalidCharacterException;
import exception.LexicoException;


public class AnalisadorLexico{
	
	private List<Token> allTokens = new ArrayList<Token>();
	
	/*Estados intermediarios*/
	private static final int ESTADO_0  = 0;
	private static final int ESTADO_1  = 1;
	private static final int ESTADO_2  = 2;
	private static final int ESTADO_3  = 3;
	private static final int ESTADO_4  = 4;
	private static final int ESTADO_5  = 5;
	private static final int ESTADO_6  = 6;
	private static final int ESTADO_7  = 7;
	private static final int ESTADO_8  = 8;
	private static final int ESTADO_11  = 11;
	
	

	private static final int LIMITE_FINAL = 1000;

	/*Estados Finais*/
	private static final int FINAL_9  = 1001;
	private static final int FINAL_10  = 1002;
	private static final int FINAL_12  = 1004;
	private static final int FINAL_13  = 1013;
	private static final int FINAL_14  = 1014;
	private static final int FINAL_15  = 1015;
	private static final int FINAL_16  = 1005;
	private static final int FINAL_17  = 1006;
	private static final int FINAL_18 = 1007;
	private static final int FINAL_19  = 1008;
	private static final int FINAL_20  = 1009;
	private static final int FINAL_21  = 1010;
	private static final int FINAL_22  = 1011;
	private static final int FINAL_26  = 1012;
	private static final int FINAL_27  = 1027;
	private static final int FINAL_28  = 1028;
	private static final int FINAL_29  = 1029;
	private static final int FINAL_30  = 1030;
	private static final int FINAL_31  = 1031;
	private static final int FINAL_23  = 1023;
	private static final int FINAL_24  = 1024;
	private static final int FINAL_25 = 1025;
	private static final int FINAL_32 = 1032;

	
	//tokens finais a partir desse numero
	private static final int TOKEN_NUM   = 5001;
	private static final int TOKEN_DIV   = 5002;
	private static final int TOKEN_MAIS  = 5003;
	private static final int TOKEN_MENOS = 5004;
	private static final int TOKEN_MULT  = 5005;
	private static final int TOKEN_EOF   = 5006;
	private static final int TOKEN_ID    = 5007;
	private static final int TOKEN_SQR   = 5008;
	private static final int TOKEN_SQRT  = 5009;
	private static final int TOKEN_OPAR  = 5010;
	private static final int TOKEN_CPAR  = 5011;
	private static final int TOKEN_IGUAL  = 5011;
	private static final int TOKEN_DIF  = 5012;
	private static final int TOKEN_PTV  = 5013;
	private static final int TOKEN_2PTS  = 5014;
	private static final int TOKEN_PT  = 5015;
	private static final int TOKEN_VIRG  = 5016;
	private static final int TOKEN_MAIOR_IGUAL  = 5016;
	private static final int TOKEN_MENOR_IGUAL  = 5017;
	private static final int TOKEN_MAIOR  = 5018;
	private static final int TOKEN_MENOR  = 5019;
	private static final int TOKEN_OCOL  = 5020;
	private static final int TOKEN_CCOL  = 5021;
	private static final int TOKEN_ATRIB  = 5022;
	
	

	private LogSymbolFactory complexSymbolFactory = LogSymbolFactory.getInstance();
	
	private int linha = 1;
	private int devolve =- 1 ;
	private TabelaSimbolo tabelaSimbolo;
	private LeitorArquivo leitor;
	private int estadoAtual = 0;
	private RegistradorLexico registrador = new RegistradorLexico();
	
	
	
	
	public AnalisadorLexico(LeitorArquivo leitor) {
		
		this.leitor=leitor;
		TabelaSimbolo.inicializarTabelaSimbolos();		
	}




	public List<Token> getAllTokens() throws IOException, LexicoException {
		
		if(!allTokens.isEmpty()){
			return allTokens;
		}
		
		List<Token>  allTokens = new ArrayList<Token>();
		Token token = nextToken();
		
		while(token==null || token.getCategoriaToken()!= TOKEN_EOF){
			if(token!=null)
			allTokens.add(token);
			token=nextToken();	
			
		}
		
		
		return allTokens;
		
	}
	
	public Token nextToken() throws IOException, LexicoException{
		
		char caracter;
		
		try{
	    while (estadoAtual < LIMITE_FINAL)
	    {
	        if (devolve==-1){
	        	caracter = (char) leitor.lerCaracter();
	        	verificarCaractere(caracter);
	        }
	        else {
	        	caracter=(char)devolve;
	        	estadoAtual=0;
	        	devolve = -1;
	        }
	        switch (estadoAtual)
	        {
	            case ESTADO_0: estadoAtual = estado0(); break;
	            case ESTADO_1: estadoAtual = estado1(); break;
	            case ESTADO_2: estadoAtual = estado2(); break;
	            case ESTADO_3: estadoAtual = estado3(); break;
	            case ESTADO_4: estadoAtual = estado4(); break;
	            case ESTADO_5: estadoAtual = estado5(); break;
	            case ESTADO_6: estadoAtual = estado6(); break;
	            case ESTADO_7: estadoAtual = estado7(); break;
	            case ESTADO_8: estadoAtual = estado8(); break;
	            case ESTADO_11: estadoAtual= estado11(); break;
	               
	        }  
	    }
		}catch (EOFException e) {
			return new Token(TOKEN_EOF, "EOF", leitor.getContLinha(), leitor.getContCol());
		}
        switch (estadoAtual)
        {
            case FINAL_9:  estadoAtual=0; 
            Token t= new Token(TOKEN_NUM, registrador.getLexema(), leitor.getContLinha(), leitor.getContCol());
            //resetando registrador
            registrador.resetar();
            return t;
            case FINAL_10: estadoAtual=0; registrador.resetar(); break; 
            case FINAL_12: estadoAtual=0; return new Token(TOKEN_DIV, "/", leitor.getContLinha(), leitor.getContCol());
            case FINAL_13: estadoAtual=0; return new Token(TOKEN_MAIS, "+", leitor.getContLinha(), leitor.getContCol());
            case FINAL_14: estadoAtual=0; return new Token(TOKEN_MENOS, "-", leitor.getContLinha(), leitor.getContCol());
            case FINAL_15: estadoAtual=0; return new Token(TOKEN_MULT, "*", leitor.getContLinha(), leitor.getContCol());
		case FINAL_16:
			estadoAtual = 0;
			t = new Token(TOKEN_ID, registrador.getLexema(), leitor.getContLinha(), leitor.getContCol());
			registrador.resetar();
			return t;
            case FINAL_17: estadoAtual=0; return new Token(TOKEN_EOF, "EOF", leitor.getContLinha(), leitor.getContCol());
            case FINAL_18: estadoAtual=0; return new Token(TOKEN_OPAR, "(", leitor.getContLinha(), leitor.getContCol());
            case FINAL_19: estadoAtual=0; return new Token(TOKEN_CPAR, ")", leitor.getContLinha(), leitor.getContCol());
            case FINAL_20: estadoAtual=0; return new Token(TOKEN_PTV, ";", leitor.getContLinha(), leitor.getContCol());
            case FINAL_21: estadoAtual=0; registrador.resetar();return new Token(TOKEN_ATRIB, ":=", leitor.getContLinha(), leitor.getContCol());
            case FINAL_22: estadoAtual=0; return new Token(TOKEN_2PTS, ":", leitor.getContLinha(), leitor.getContCol());
            case FINAL_23: estadoAtual=0; return new Token(TOKEN_OCOL, "[", leitor.getContLinha(), leitor.getContCol());
            case FINAL_24: estadoAtual=0; return new Token(TOKEN_CCOL, "]", leitor.getContLinha(), leitor.getContCol());
            case FINAL_25: estadoAtual=0; return new Token(TOKEN_PT, ".", leitor.getContLinha(), leitor.getContCol());
            case FINAL_26: estadoAtual=0; return new Token(TOKEN_MAIOR_IGUAL, ">=", leitor.getContLinha(), leitor.getContCol());
            case FINAL_27: estadoAtual=0; return new Token(TOKEN_MAIOR, ">", leitor.getContLinha(), leitor.getContCol());
            case FINAL_28: estadoAtual=0; return new Token(TOKEN_DIF, "#", leitor.getContLinha(), leitor.getContCol());
            case FINAL_29: estadoAtual=0; return new Token(TOKEN_IGUAL, "=", leitor.getContLinha(), leitor.getContCol());
            case FINAL_30: estadoAtual=0; return new Token(TOKEN_MENOR_IGUAL, "<=", leitor.getContLinha(), leitor.getContCol());
            case FINAL_31: estadoAtual=0; return new Token(TOKEN_MENOR, "<", leitor.getContLinha(), leitor.getContCol());
            case FINAL_32: estadoAtual=0; return new Token(TOKEN_VIRG, ",", leitor.getContLinha(), leitor.getContCol());
        }


		
		
		return null;
		
	}
	
	
	
	public Symbol nextCupToken() throws IOException, LexicoException{
		
		char caracter;
		
		try{
	    while (estadoAtual < LIMITE_FINAL)
	    {
	        if (devolve==-1){
	        	caracter = (char) leitor.lerCaracter();
	        	verificarCaractere(caracter);
	        }
	        else {
	        	caracter=(char)devolve;
	        	estadoAtual=0;
	        	devolve = -1;
	        }
	        switch (estadoAtual)
	        {
	            case ESTADO_0: estadoAtual = estado0(); break;
	            case ESTADO_1: estadoAtual = estado1(); break;
	            case ESTADO_2: estadoAtual = estado2(); break;
	            case ESTADO_3: estadoAtual = estado3(); break;
	            case ESTADO_4: estadoAtual = estado4(); break;
	            case ESTADO_5: estadoAtual = estado5(); break;
	            case ESTADO_6: estadoAtual = estado6(); break;
	            case ESTADO_7: estadoAtual = estado7(); break;
	            case ESTADO_8: estadoAtual = estado8(); break;
	            case ESTADO_11: estadoAtual= estado11(); break;
	               
	        }  
	    }
		}catch (EOFException e) {
			return criaSimboloCUP("EOF","EOF", 0, leitor.getContLinha(), leitor.getContCol());
		}
        switch (estadoAtual)
        {
            case FINAL_9:  estadoAtual=0; 
            Symbol t= criaSimboloCUP("NUM",registrador.getLexema(), TOKEN_NUM, leitor.getContLinha(), leitor.getContCol());
            //resetando registrador
            registrador.resetar();
            return t;
            case FINAL_10: estadoAtual=0; registrador.resetar(); break; 
            case FINAL_12: estadoAtual=0; registrador.resetar();  return criaSimboloCUP("DIV","/",TOKEN_DIV, leitor.getContLinha(), leitor.getContCol());
            case FINAL_13: estadoAtual=0; registrador.resetar(); return criaSimboloCUP("MAIS","+",TOKEN_MAIS, leitor.getContLinha(), leitor.getContCol());
            case FINAL_14: estadoAtual=0; registrador.resetar(); return criaSimboloCUP("MENOS","-",TOKEN_MENOS, leitor.getContLinha(), leitor.getContCol());
            case FINAL_15: estadoAtual=0; registrador.resetar(); return criaSimboloCUP("MULT","*",TOKEN_MULT, leitor.getContLinha(), leitor.getContCol());
		case FINAL_16:
			estadoAtual = 0;
			String nome = verificaNomeID(registrador.getLexema());
			t =  criaSimboloCUP(nome, registrador.getLexema().toUpperCase(),39, leitor.getContLinha(), leitor.getContCol());
			registrador.resetar();
			return t;
            case FINAL_17: estadoAtual=0; return criaSimboloCUP( "EOF","EOF", TOKEN_EOF, leitor.getContLinha(), leitor.getContCol());
            case FINAL_18: estadoAtual=0; return criaSimboloCUP("OPAR","(",TOKEN_OPAR, leitor.getContLinha(), leitor.getContCol());
            case FINAL_19: estadoAtual=0; return criaSimboloCUP("CPAR",")", TOKEN_CPAR, leitor.getContLinha(), leitor.getContCol());
            case FINAL_20: estadoAtual=0; return criaSimboloCUP("PTVIRGULA",";", TOKEN_PTV, leitor.getContLinha(), leitor.getContCol());
            case FINAL_21: estadoAtual=0; registrador.resetar();return criaSimboloCUP("ATRIB",":=",TOKEN_ATRIB, leitor.getContLinha(), leitor.getContCol());
            case FINAL_22: estadoAtual=0; return criaSimboloCUP("DOISPONTOS", ":", TOKEN_2PTS, leitor.getContLinha(), leitor.getContCol());
            case FINAL_23: estadoAtual=0; return criaSimboloCUP("OCOL","[", TOKEN_OCOL, leitor.getContLinha(), leitor.getContCol());
            case FINAL_24: estadoAtual=0; return criaSimboloCUP("CCOL","]", TOKEN_CCOL, leitor.getContLinha(), leitor.getContCol());
            case FINAL_25: estadoAtual=0; return criaSimboloCUP("PONTO",".",TOKEN_PT, leitor.getContLinha(), leitor.getContCol());
            case FINAL_26: estadoAtual=0; return criaSimboloCUP("MAIORIGUAL",">=", TOKEN_MAIOR_IGUAL, leitor.getContLinha(), leitor.getContCol());
            case FINAL_27: estadoAtual=0; return criaSimboloCUP("MAIOR", ">", TOKEN_MAIOR, leitor.getContLinha(), leitor.getContCol());
            case FINAL_28: estadoAtual=0; return criaSimboloCUP("DIFERENTE","#",TOKEN_DIF, leitor.getContLinha(), leitor.getContCol());
            case FINAL_29: estadoAtual=0; return criaSimboloCUP("IGUAL", "=",TOKEN_IGUAL, leitor.getContLinha(), leitor.getContCol());
            case FINAL_30: estadoAtual=0; return criaSimboloCUP("MENORIGUAL", "<=",TOKEN_MENOR_IGUAL, leitor.getContLinha(), leitor.getContCol());
            case FINAL_31: estadoAtual=0; return criaSimboloCUP("MENOR", "<",TOKEN_MENOR, leitor.getContLinha(), leitor.getContCol());
            case FINAL_32: estadoAtual=0; return criaSimboloCUP("VIRGULA", ",",TOKEN_VIRG, leitor.getContLinha(), leitor.getContCol());
        }


		
		/*a diferenca aqui, porque o cup nao trata token nulo, entao qdo for comentario,
		 * vamos ignora-lo e ler o proximo token.
		 */
        
		return nextCupToken();
		
	}
	
	/**
	 * 
	 * @param nome 
	 * @param id colocamos o numero do token declarado no inicio desta classe
	 * @param linha TODO nao faz nada por enquanto!
	 * @param coluna TODO nao faz nada por enquanto!
	 * @return
	 */
	protected Symbol criaSimboloCUP(String nome, String lexema, int id, int linha, int coluna){
		

		if(nome.equals("ID"))
			nome = verificaNomeID(nome);

		
		try {
			allTokens.add(new Token(0, lexema, linha, coluna));
			return complexSymbolFactory.newCupSymbol(nome, CupHelper.getIdSym(nome) , lexema);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private String verificaNomeID(String nome) {
		
		nome=tabelaSimbolo.getNome(nome);
				
		if(nome==null) return "ID"; else return nome;
	}




	private int estado11() {
		if(leitor.getCaracter()=='='){
			registrador.concatenar(leitor.getCaracter());
			return FINAL_30;
		}
		
		devolve=leitor.getCaracter();
		return FINAL_31;
	}




	private int estado6() {
		
		if(leitor.getCaracter()=='_' || Character.isDigit(leitor.getCaracter()) || Character.isLetter(leitor.getCaracter())){
			registrador.concatenar(leitor.getCaracter());			
			return ESTADO_6;
		}
		
		devolve=leitor.getCaracter();
		return FINAL_16;
	}


	private int estado25() {
		// TODO Auto-generated method stub
		return 0;
	}




	private int estado24() {
		// TODO Auto-generated method stub
		return 0;
	}




	private int estado23() {
		// TODO Auto-generated method stub
		return 0;
	}




	private int estado8() {
		
		if(leitor.getCaracter()=='='){
			registrador.concatenar(leitor.getCaracter());
			return FINAL_21;			
		}
		devolve=leitor.getCaracter();
		return FINAL_22;
	}




	private int estado7() {

		if(leitor.getCaracter()=='='){
			registrador.concatenar(leitor.getCaracter());
			return FINAL_26;
		}
		
		devolve=leitor.getCaracter();
		return FINAL_27;
	}




	private int estado5() {
		
		if(leitor.getCaracter()=='/')
			return ESTADO_0;
		
		return ESTADO_4;
	}
	
	private int estado4() {
		
		if(leitor.getCaracter()=='*')
			return ESTADO_5;
		
		return ESTADO_4;
	}

	private int estado3() {
		
		if(leitor.getCaracterInt()==13){
			//devolve = leitor.getCaracter();
			
			return FINAL_10;			
		}		
		return ESTADO_3;
	}

	private int estado2() {
		
		if(leitor.getCaracter()=='/'){
			return ESTADO_3;
		}
		
		else if(leitor.getCaracter()=='*')
			return ESTADO_4;
		else{
			devolve=leitor.getCaracter();
			return FINAL_12;
			
		}
	}




	private int estado0() throws LexicoException{
		
		char caracter = leitor.getCaracter();
		int caracterInt = leitor.getCaracterInt();
		if(caracterInt == 13 || caracterInt == 10 || caracter == ' ' || caracterInt == 9 ){			
			return ESTADO_0;
			
		}
		else if(Character.isDigit(caracter)){
			registrador.concatenar(caracter);
			return ESTADO_1;		
		}
		
		else if(caracter=='/'){
			registrador.concatenar(caracter);
			return ESTADO_2;
		}
		else if(caracter=='+'){
			registrador.resetar();
			registrador.concatenar(caracter);
			return FINAL_13;
		}
		else if(caracter=='-')
			return FINAL_14;
		else if(caracter=='*')
			return FINAL_15;
		else if(caracter=='_'|| Character.isLetter(caracter)){
			registrador.concatenar(caracter);
			return ESTADO_6;
		}
		else if(caracter=='(')
			return FINAL_18;
		else if(caracter==')')
			return FINAL_19;
		else if(caracter==';')
			return FINAL_20;
		else if(caracter==':')
			return ESTADO_8;
		else if(caracter=='[')
			return FINAL_23;
		else if(caracter==']')
			return FINAL_24;
		else if(caracter=='.')
			return FINAL_25;
		else if(caracter=='<')
			return ESTADO_11;
		else if(caracter=='>')
			return ESTADO_7;
		else if(caracter=='#')
			return FINAL_28;
		else if(caracter=='=')
			return FINAL_29;
		else if(caracter==',')
			return FINAL_32;

		else
			throw new InvalidCharacterException("O caracter "+caracter+" é inválido.");
		
	}




	private void verificarCaractere(char caractere) throws InvalidCharacterException {
        if (!(Character.isLetterOrDigit(caractere) ||
                caractere == '.' || caractere == ',' || caractere == ';' || caractere == ':' || caractere == '(' ||
                caractere == ')' || caractere == '[' || caractere == ']' || caractere == '#' ||
                caractere == '+' || caractere == '-' || caractere == '/' || caractere == '_' ||
                caractere == '>' ||caractere == '+' ||caractere == '-' ||
                caractere == '<' || caractere == '*' || caractere == ' ' || caractere == '='|| caractere == '\t' || caractere == '\n' || caractere == '\r')) {
        	
            System.err.println("Linha: "+linha + ": Caractere invalido.");
            System.exit(0);
        }
    }


	public void imprimeErro(String mensagem, Symbol objeto){
		
		
		System.err.println(mensagem + "- Linha: "+leitor.getContLinha() +", Coluna: "+leitor.getContCol());
		System.err.println(objeto +" "+ objeto.value);
		System.exit(0);
		
	}


	/**
	 * Verifica numeros.
	 * @return 
	 */
	public int estado1(){		
		
		if(!Character.isDigit(leitor.getCaracter())){
			devolve=leitor.getCaracter();
			return FINAL_9;
		}
		
		registrador.concatenar(leitor.getCaracter());
		return ESTADO_1;
		
		
	}




	public LeitorArquivo getLeitor() {
		return leitor;
	}




	public void setLeitor(LeitorArquivo leitor) {
		this.leitor = leitor;
	}
	

	
	
	
	
	

}
