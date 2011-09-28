package ast;

public enum Operador {
	
	MAIS,
	MENOS,
	MULT,
	DIV,
	MOD,
	AND,
	OR,
	NOT,
	DIFERENTE,
	IGUAL,
	MENORIGUAL,
	MAIORIGUAL,
	MENOR,
	MAIOR;
	
	
	public String toString() {
		
		if(this==MAIS)
			return "+";
		else if(this==MENOS)
			return "-";
		else if(this==MULT)
			return "*";
		else if(this==DIV)
			return "/";
		else if(this==MOD)
			return "|";
		else if(this==AND)
			return "AND";
		else if(this==OR)
			return "OR";
		else if(this==NOT)
			return "NOT";
		else if(this==DIFERENTE)
			return "#";
		else if(this==IGUAL)
			return "=";
		else if(this==MENORIGUAL)
			return ">=";
		else if(this==MENOR)
			return "<";
		else if(this==MAIORIGUAL)
			return ">=";
		else if(this==MAIOR)
			return ">";
		else
			return "OPERADOR IRRECONHECIDO, VERIFIQUE EM Operador.java";
		
	};

}
