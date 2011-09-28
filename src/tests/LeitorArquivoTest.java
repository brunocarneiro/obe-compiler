package tests;

import io.LeitorArquivo;

import java.io.EOFException;
import java.io.IOException;

public class LeitorArquivoTest {
	
	public static void main(String [] args) throws Throwable{
		
		LeitorArquivo leitor = new LeitorArquivo("teste.txt");
		
		try{
		while(true){
			
			System.out.println(leitor.lerLinha());
			
		}

		}catch (IOException e) {
			if(e instanceof EOFException){
				System.out.println("FIM ARQUIVO");
				leitor.finalize();
			}
			else
			System.err.println("ERRO DE LEITURA");
		}
		leitor = new LeitorArquivo("teste.txt");
		try{
			char a;
			while(true){
				a=(char) leitor.lerCaracter();
				System.out.print(a);
				
			}

			}catch (IOException e) {
				if(e instanceof EOFException){
					System.out.println("FIM ARQUIVO");
					leitor.finalize();
				}
				else
				System.err.println("ERRO DE LEITURA");
			}

		
		
	}

}
