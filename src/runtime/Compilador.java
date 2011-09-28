package runtime;
import gen.SourceGenerator;
import io.EscritorArquivo;
import io.LeitorArquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kernel.AnalisadorLexico;


public class Compilador {
	
	public static void main(String args[]) throws Exception {

		try {
			
			
			AnalisadorLexico scanner = new AnalisadorLexico(new LeitorArquivo(
					args[0]));
			
			parser sintatico = new parser(scanner, new EscritorArquivo(args[0], 2));
			//sintatico.debug_parse();
			sintatico.parse();
			//imprimindo lexico
			EscritorArquivo escritor = new EscritorArquivo(args[0], 1);
			escritor.escreveTokens(scanner.getAllTokens());
			//imprimindo sintatico
			//escritor = new EscritorArquivo(args[0], 2);
		//	escritor.escreve(sintatico.raiz);
			List<Object> nodos = new ArrayList<Object>();
			nodos.add(sintatico.raiz);
			//escritor.fecharArquivo();
			escritor = new EscritorArquivo(args[0], 3);
			escritor.imprimeAst(nodos);	
			escritor.fecharArquivo();
			escritor = new EscritorArquivo(args[0], 5);
			
			//gerando codigo
			sintatico.raiz.gen();
			if(args[1].equals("-i"))
				SourceGenerator.getInstance().printIntermediate();
			else if(args[1].equals("-f"))
				SourceGenerator.getInstance().printFinalCode();

		} catch (ArrayIndexOutOfBoundsException aiobe) {

			System.out
					.println("NAO HA ARGUMENTOS NA LINHA DE EXECUCAO. \n "
							+ "DIGITE O CAMINHO DO PROGRAMA DE ENTRADA NA LINHA DE COMANDO DE EXECUCAO");

		} catch (FileNotFoundException fnfe) {

			System.out.println("ARQUIVO NAO ENCONTRADO.");

		} catch (IOException e) {
			System.err.println("ERRO DE LEITURA");
		}
	}
}
