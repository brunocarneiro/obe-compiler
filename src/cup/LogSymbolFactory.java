package cup;

import static util.WriterHelper.log;
import io.EscritorArquivo;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

public class LogSymbolFactory extends ComplexSymbolFactory {
	
	private static LogSymbolFactory INSTANCE;
	
	/**
	 * Simbolo criado anteriormente
	 */
	private Symbol previousSymbol;
	
	/**
	 * Simbolo atual
	 */
	private Symbol actualSymbol;
	
	private Symbol previousToken;
	private Symbol actualToken;
	
	private String nomeAnteriorSymbol;
	private String nomeAtualSymbol;
	
	
	public static LogSymbolFactory getInstance(){
		
		
		if(INSTANCE ==null){
			return INSTANCE=new LogSymbolFactory();
			
		}
		else
			return INSTANCE;
		
	}
	
	
	@Override
	public Symbol newSymbol(String name, int id, Object value) {
		
		log(value);		
		previousSymbol=actualSymbol; 
		actualSymbol=super.newSymbol(name, id, value);
		return actualSymbol;
	}
	
	@Override
	public Symbol newSymbol(String name, int id, Symbol left, Symbol right,
			Object value) {
		
		EscritorArquivo.setFase(2);
		log(value);
		EscritorArquivo.setFase(5);
		previousSymbol=actualSymbol;
		actualSymbol=super.newSymbol(name, id, left, right, value);
		return actualSymbol;
	}

	
	/**
	 * Cria o Token. Chamado pelo Lexico. Cria apenas terminais.
	 * @param nome
	 * @param idSym
	 * @param lexema
	 * @return
	 */
	public Symbol newCupSymbol(String nome, int idSym, String lexema) {

		
		nomeAnteriorSymbol = nomeAtualSymbol;
		nomeAtualSymbol=nome;
		return super.newSymbol(nome, idSym, lexema);
	}
	
	
	/* GETTERS AND SETTERS */
	
	public ComplexSymbol getPreviousSymbol() {
		return (ComplexSymbol)previousSymbol;
	}

	public void setPreviousSymbol(Symbol previousSymbol) {
		this.previousSymbol = previousSymbol;
	}

	public ComplexSymbol getActualSymbol() {
		
		return (ComplexSymbol)actualSymbol;
	}

	public void setActualSymbol(Symbol actualSymbol) {
		this.actualSymbol = actualSymbol;
	}



	
	

	public String getNomeAnteriorSymbol() {
		return nomeAnteriorSymbol;
	}

	public void setNomeAnteriorSymbol(String nomeAnteriorSymbol) {
		this.nomeAnteriorSymbol = nomeAnteriorSymbol;
	}

	public String getNomeAtualSymbol() {
		return nomeAtualSymbol;
	}

	public void setNomeAtualSymbol(String nomeAtualSymbol) {
		this.nomeAtualSymbol = nomeAtualSymbol;
	}


	

	
	
	
}
