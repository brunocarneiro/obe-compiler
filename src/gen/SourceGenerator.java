package gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class SourceGenerator {
	private HashMap<String, String> realocacaoVar = new HashMap<String, String>(); 
	private static SourceGenerator instance = new SourceGenerator();
	private HashMap<Integer, Quadrupla> expMap = new HashMap<Integer, Quadrupla>();
	private HashMap<Integer, Quadrupla> cache = new HashMap<Integer, Quadrupla>();
	private IntermediteGenerator intermediteGenerator = new IntermediteGenerator();
	private FinalCodeGenerator finalCodeGenerator = new FinalCodeGenerator();
	private List<Integer> lista = new ArrayList<Integer>();
	private static int cont =1;
	private static int numRotulo=0;
	private static int numVariavel=0;

	
	public void gen(Quadrupla quad){
		int pos;
		cache.put(cont, quad);
		pos=cont;
		expMap.put(cont, quad);
		cont++;			
		lista.add(pos);
		
	}
	
	
	
	public HashMap<String, String> getRealocacaoVar() {
		return realocacaoVar;
	}



	public void setRealocacaoVar(HashMap<String, String> realocacaoVar) {
		this.realocacaoVar = realocacaoVar;
	}



	public int lookup(Quadrupla q){

		Set<Entry<Integer, Quadrupla>> tab = cache.entrySet();
        for(Entry<Integer, Quadrupla> entry: tab){
        	
        	if(entry.getValue().equals(q)){
        		return entry.getKey();
        	}
        }
		return -1;
	}

	public static SourceGenerator getInstance() {
		return instance;
	}

	public void printIntermediate() {
		imprimirAoContrario();
		for(Integer i : lista){
			Quadrupla q = expMap.get(i);
			intermediteGenerator.gen(q);
		}
	}
	
	public void printFinalCode() {
		verificarReusoVariaveis();
		for(Integer i : lista){
			Quadrupla q = expMap.get(i);
			finalCodeGenerator.gen(q);
		}
	}
	

	public void verificarReusoVariaveis(){
		for(int i = lista.size()-1; i>=0; i--){
			Quadrupla q = expMap.get(lista.get(i));
			finalCodeGenerator.imprimeVariaveisGlobais(q);
			finalCodeGenerator.inserirReusoVariaveis(q.getArg1());
			finalCodeGenerator.inserirReusoVariaveis(q.getArg2());
		}				
	}
	
	public void imprimirAoContrario(){

		for(int i = lista.size()-1; i>=0; i--){
			Quadrupla q = expMap.get(lista.get(i));
			intermediteGenerator.empilhaVariaveisGlobais(q);
		}
		
		intermediteGenerator.imprimeVariaveisGlobais();
	
	}
	
	
	public void resetarCache(){
		cache = new HashMap<Integer, Quadrupla>();
	}

	public static String getRotulo(){
		
		return "L"+ ++numRotulo;
		
	}
	
	public static String getVariavel(){
		
		return "t"+ ++numVariavel;
		
	}
	
	public Quadrupla getQuadruplaFromCache(int pos){
		return cache.get(pos);
	}
	

}
