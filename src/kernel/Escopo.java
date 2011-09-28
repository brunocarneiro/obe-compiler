package kernel;

import java.util.HashMap;

public class Escopo {
	
	private NivelEscopo nivelEscopo;
	
	private HashMap<String, Integer> variaveis;
	
	
	public void insere(String nome, Integer valor){
		
		variaveis.put(nome, valor);
		
	}
	
	
	public void remove(String nome){
		
		variaveis.remove(nome);
		
	}
	
	public Integer lookup(String nome){
		
		return variaveis.get(nome);
		
		
	}


	public Escopo(String nivel) {
		super();
		variaveis = new HashMap<String, Integer>();
		nivelEscopo = resolveNivel(nivel);
	}


	private NivelEscopo resolveNivel(String nivel) {
		
		if(nivel.equals("module".toUpperCase())){
			return NivelEscopo.CLASSE;
			
		}else if(nivel.equals("if".toUpperCase())){
			return NivelEscopo.CONDICIONAL;
			
		}else if (nivel.equals("else".toUpperCase())){
			return NivelEscopo.CONDICIONAL;
			
		}else if(nivel.equals("repeat".toUpperCase())){
			return NivelEscopo.REPETICAO;
			
		}else if(nivel.equals("procedure".toUpperCase())){
			return NivelEscopo.METODO;
			
		}
		else
			return null;
		
		
		
		
	}
	
	
	
	

}
