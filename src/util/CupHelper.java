package util;

import runtime.sym;
/**
 * Classe para ajuda na integração do Analisador Léxico desenvolvido pela dupla e o Java Cup.
 * 
 *
 */
public class CupHelper {
	
	
	private CupHelper(){}
	
	
	public static int getIdSym(String valor) throws Exception{
		
		if(valor.equals("ID")){
			return sym.ID;
		}
		else if(valor.equals("SQRT")){
			return sym.SQRT;
		}		
		else if(valor.equals("MULT")){
			return sym.MULT;
		}
		else if(valor.equals("SQR")){
			return sym.SQR;
		}
		else if(valor.equals("CONST")){
			return sym.CONST;
		}
		else if(valor.equals("OPAR")){
			return sym.OPAR;
		}
		else if(valor.equals("OCOL")){
			return sym.OCOL;
		}
		else if(valor.equals("ELSIF")){
			return sym.ELSIF;
		}
		else if(valor.equals("DIFERENTE")){
			return sym.DIFERENTE;
		}
		else if(valor.equals("CPAR")){
			return sym.CPAR;
		}
		else if(valor.equals("CCOL")){
			return sym.CCOL;
		}
		else if(valor.equals("INTEGER")){
			return sym.INTEGER;
		}
		else if(valor.equals("REPEAT")){
			return sym.REPEAT;
		}
		else if(valor.equals("ARRAY")){
			return sym.ARRAY;
		}
		else if(valor.equals("MAIORIGUAL")){
			return sym.MAIORIGUAL;
		}
		else if(valor.equals("WRITE")){
			return sym.WRITE;
		}
		else if(valor.equals("VIRGULA")){
			return sym.VIRGULA;
		}
		else if(valor.equals("PONTO")){
			return sym.PONTO;
		}
		else if(valor.equals("AND")){
			return sym.AND;
		}
		else if(valor.equals("NOT")){
			return sym.NOT;
		}
		else if(valor.equals("MENORIGUAL")){
			return sym.MENORIGUAL;
		}
		else if(valor.equals("IGUAL")){
			return sym.IGUAL;
		}
		else if(valor.equals("OR")){
			return sym.OR;			
		}
		else if(valor.equals("MAIS")){
			return sym.MAIS;
		}
		else if(valor.equals("BEGIN")){
			return sym.BEGIN;
		}
		else if(valor.equals("DIV")){
			return sym.DIV;
		}
		else if(valor.equals("UNTIL")){
			return sym.UNTIL;
		}
		else if(valor.equals("NUM")){
			return sym.NUM;
		}
		else if(valor.equals("WRITELN")){
			return sym.WRITELN;
		}
		else if(valor.equals("IF")){
			return sym.IF;
		}
		else if(valor.equals("OF")){
			return sym.OF;
		}
		else if(valor.equals("EOF")){
			return sym.EOF;
		}
		else if(valor.equals("BOOLEAN")){
			return sym.BOOLEAN;
		}
		else if(valor.equals("MAIOR")){
			return sym.MAIOR;
		}
		else if(valor.equals("SIZE")){
			return sym.SIZE;
		}
		else if(valor.equals("TRUE")){
			return sym.TRUE;
		}
		else if(valor.equals("MENOS")){
			return sym.MENOS;
		}
		else if(valor.equals("MENOR")){
			return sym.MENOR;
		}
		else if(valor.equals("MOD")){
			return sym.MOD;
		}
		else if(valor.equals("READLN")){
			return sym.READLN;	
		}
		else if(valor.equals("MODULE")){
			return sym.MODULE;
		}
		else if(valor.equals("ATRIB")){
			return sym.ATRIB;
		}
		else if(valor.equals("ELSE")){
			return sym.ELSE;
		}
		else if(valor.equals("READ")){
			return sym.READ;
		}
		else if(valor.equals("WHILE")){
			return sym.WHILE;
		}
		else if(valor.equals("THEN")){
			return sym.THEN;
		}
		else if(valor.equals("END")){
			return sym.END;
		}
		else if(valor.equals("FALSE")){
			return sym.FALSE;
		}
		else if(valor.equals("PTVIRGULA")){
			return sym.PTVIRGULA;
		}
		else if(valor.equals("PROCEDURE")){
			return sym.PROCEDURE;
		}
		else if(valor.equals("VAR")){
			return sym.VAR;
		}
		else if(valor.equals("DO")){
			return sym.DO;
		}
		else if (valor.equals("DOISPONTOS")){
			return sym.DOISPONTOS;
		}

		//nunca pode entrar aqui
		throw new Exception("ERRO TOKEN NAO ESPERADO EM getIdSym. Token: "+valor);
		
		
		
	}
	

}
