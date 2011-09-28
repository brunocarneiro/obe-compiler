package kernel;

import java.util.Stack;

public class Escopos {
	
	private Stack<Escopo> escopos;
	private static int numeroVariavel=0;
	private static Escopos INSTANCE;
	
	private Escopo escopoAtual;
	private Escopo escopoRemovido;
	
	
	
	public void createEscopo(String nivel){
		
		escopoAtual= new Escopo(nivel);
		
		escopos.push(escopoAtual);
		
		
		
	}
	
	public void removeEscopo(){
		
		escopoRemovido=escopos.pop();
		if(!escopos.isEmpty())
			escopoAtual = escopos.peek();
		
		
	}
	
	
	public int lookup(String id){
		Integer valor=-1;
		
		for(int posEscopoProcurar = escopos.size(); posEscopoProcurar>0;posEscopoProcurar--){
			Escopo escopo = escopos.get(posEscopoProcurar-1);
			valor = escopo.lookup(id);
			if(valor!=null)
				break;
			else valor=-1;
			
			
		}
		
		return valor;
		
		
	}
	
	public static int getNumeroVariavel(){
		
		return ++numeroVariavel;
		
	}
	
	public static int getNumeroVariavelAtual(){
		
		return numeroVariavel;
		
	}
	
	
	public static Escopos getInstance(){
		
		if(INSTANCE==null)
			return INSTANCE=new Escopos();
		else
			return INSTANCE;
		
	}

	private Escopos() {
		escopos = new Stack<Escopo>();
		
	}

	public Escopo getEscopoAtual() {
		return escopoAtual;
	}

	public void setEscopoAtual(Escopo escopoAtual) {
		this.escopoAtual = escopoAtual;
	}
	
	
	

}
