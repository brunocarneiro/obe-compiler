package kernel;


public class RegistradorLexico {
    
	
	private String lexema;
    
	public RegistradorLexico(){
	
		lexema = "";
	}

	public void concatenar(char caractere) {
		lexema = lexema + caractere;
	}
	
	public void resetar(){
		
		lexema="";
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	
}
