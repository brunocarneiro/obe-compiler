package util;

import io.EscritorArquivo;

public class WriterHelper {
	
	private static String tabulacao="";
	private static EscritorArquivo escritor;


	public static String getTabulacao() {
		return tabulacao;
	}

	public static String incrementaTabulacao() {
		return tabulacao="	"+tabulacao;
	}
	
	public static void setTabulacao(String tabulacao) {
		WriterHelper.tabulacao = tabulacao;
	}

	public static void log(final Object RESULT){
		
		if(RESULT != null)
			escritor.escreve(RESULT);		
	}

	public static EscritorArquivo getEscritor() {
		return escritor;
	}

	public static void setEscritor(EscritorArquivo escritor) {
		WriterHelper.escritor = escritor;
	}

	
	
}
