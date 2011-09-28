package kernel;

import static io.EscritorArquivo.getFase;

public class Token {

	private String lexema;
	private int linha;
	private int colInicio;
	private int colFim;
	private int categoriaToken;
	
	
	public Token(int categoriaToken, String lexema, int linha, int contCol) {
		
		
		this.categoriaToken = categoriaToken;
		this.lexema = lexema;
		this.linha = linha;
		this.colFim = contCol;
		this.colInicio = colFim - lexema.length()+1;
		
		
	}
	
	

	public Token(String lexema, int linha, int colInicio, int colFim,
			int categoriaToken) {
		super();
		this.lexema = lexema;
		this.linha = linha;
		this.colInicio = colInicio;
		this.colFim = colFim;
		this.categoriaToken = categoriaToken;
	}


	public String getLexema() {
		return lexema;
	}




	public void setLexema(String lexema) {
		this.lexema = lexema;
	}




	public int getLinha() {
		return linha;
	}




	public void setLinha(int linha) {
		this.linha = linha;
	}




	public int getColInicio() {
		return colInicio;
	}




	public void setColInicio(int colInicio) {
		this.colInicio = colInicio;
	}




	public int getColFim() {
		return colFim;
	}




	public void setColFim(int colFim) {
		this.colFim = colFim;
	}

	
	
	public int getCategoriaToken() {
		return categoriaToken;
	}






	public void setCategoriaToken(int categoriaToken) {
		this.categoriaToken = categoriaToken;
	}






	@Override
	public String toString() {
		
		if(getFase()==1){
			
			return getLexema()+"  linha: "+linha+ "   coluna inicio: "+colInicio+"  coluna fim: "+colFim;
			
		}
		
		return ""+getNome()+totalEspacos(getNome())+this.getLexema()+"  linha: "+linha+ "   coluna inicio: "+colInicio+"  coluna fim: "+colFim;
	}
	
	
	/**
	 * Retorna o total espacos para identar todos os tokens.
	 * @param string
	 * @return
	 */
	protected String totalEspacos(String nome) {
		
		int vezes = 30 - nome.length();
		String retorno="";
		for(int cont=0; cont<vezes; cont++){
			
			retorno = retorno+" ";
			
		}
		return retorno;
	}



	protected String getNome(){
		
		String nome = TabelaSimbolo.getNome(this.getLexema());
		
		//e um id.
		if(nome==null && this.categoriaToken==5007){
			TabelaSimbolo.insere(this.getLexema(), "ID");
			nome="ID";
		}
		
		//e um num
		if(nome==null && this.categoriaToken==5001){
			nome="NUM";
		}
		
		return nome;
		
	}
	
	
	
	

}
