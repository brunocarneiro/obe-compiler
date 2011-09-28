package kernel;

import java.util.Hashtable;

import kernel.semantico.Validator;
import ast.Literal;

public class TabelaSimbolo {
	

	private static Hashtable<String, Object> hash;
	private static boolean globalAttributePhase = true;
	
	public static void inicializarTabelaSimbolos(){
		

		hash = new Hashtable<String, Object>();
		hash.put("TRUE", "TRUE");
		hash.put("FALSE", "FALSE");
		hash.put("MODULE", "MODULE");
		hash.put("BEGIN", "BEGIN");
		hash.put("END", "END");
		hash.put("CONST", "CONST");
		hash.put("VAR", "VAR");
		hash.put("BOOLEAN", "BOOLEAN");
		hash.put("INTEGER", "INTEGER");
		hash.put("REAL", "REAL");
		hash.put("ARRAY", "ARRAY");
		hash.put("OF", "OF");
		hash.put("PROCEDURE", "PROCEDURE");
		hash.put("OR", "OR");
		hash.put("AND", "AND");
		hash.put("NOT", "NOT");
		hash.put("MOD", "MOD");
		hash.put("size", "SIZE");
		hash.put("SIZE", "SIZE");
		hash.put("IF", "IF");
		hash.put("THEN", "THEN");
		hash.put("ELSE", "ELSE");
		hash.put("ELSIF", "ELSIF");
		hash.put("WHILE", "WHILE");
		hash.put("DO", "DO");
		hash.put("REPEAT", "REPEAT");
		hash.put("UNTIL", "UNTIL");
		hash.put("WRITE", "WRITE");
		hash.put("WRITELN", "WRITELN");
		hash.put("READ", "READ");
		hash.put(":=", "ATRIB");
		hash.put("=", "IGUAL");		
		hash.put("(", "OPAR");
		hash.put(")", "CPAR");
		hash.put("[", "OCOL");
		hash.put("]", "CCOL");
		hash.put("<", "MENOR");
		hash.put(">", "MAIOR");
		hash.put(">=", "MAIORIGUAL");
		hash.put("<=", "MENORIGUAL");
		hash.put("+", "MAIS");
		hash.put("-", "MENOS");
		hash.put("*", "MULT");
		hash.put("/", "DIV");
		hash.put(",", "VIRGULA");
		hash.put(".", "PONTO");
		hash.put(";", "PTVIRGULA");
		hash.put("#", "DIFERENTE");
		hash.put(":", "DOISPONTOS");
		hash.put("SQRT", "SQRT");
		hash.put("SQR", "SQR");
		
		
	}
	

	public static String getNome(String chaveValor){
		
		chaveValor = chaveValor.toUpperCase();
		return (String)hash.get(chaveValor);
			
	}
	
	public static void insere(String chave, String valor){
		
		hash.put(chave, valor);
			
	}
	
	public static int insereInfo(Info info){
		if(info instanceof FunInfo){
			
			globalAttributePhase=false;
			
		}
		
		if(info instanceof VarInfo){
			((VarInfo)info).setGlobal(globalAttributePhase);
		}
		
		if(info.getTipo() == Literal.CONST ){
			Validator.verificaTipoConst(info);
		}
		//verifica se exise a funcao
		if(info instanceof FunCallInfo && Escopos.getInstance().lookup(info.getId())==-1){
			
			System.err.println("Simbolo não declarado! "+ info.getId());
			System.exit(0);
		}
		else if (info instanceof FunCallInfo){
			Validator.verificaChamadaFuncao(info);
		}
		
		//verifica se a variavel ja foi declarada nesse escopo.
		else if(Escopos.getInstance().getEscopoAtual().lookup(info.getId())==null){
			int num = Escopos.getNumeroVariavel();
			Escopos.getInstance().getEscopoAtual().insere(info.id,num);
			hash.put(""+num, info);
			return num;
		}
		
		else{
			System.err.println("Simbolo ja declarado! "+ info.getId());
			System.exit(0);
		}
		return -1;

	}
	

	public static void modificaInfo(String id, Object valor){
		
		
		int posicao = Escopos.getInstance().lookup(id);
		
		Object i = hash.get(""+posicao);
		if(i instanceof VarInfo){
			
			VarInfo info = (VarInfo)i;
			Validator.verificaTipo(info, valor);
			info.setValorInicial(valor);
			
		}
		
		if(i==null){
			System.err.println("Simbolo não declarado "+id);
			System.exit(0);
		}		
	}
	



	public static Info getInfo(Integer index){
		if(index==null)
			return null;
		return (Info)hash.get(""+index);
		
		
	}
	

}
