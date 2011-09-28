package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ast.Node;

import kernel.Token;

public class EscritorArquivo{
	
	private PrintWriter writer;
	
	private static int fase;
	

	public EscritorArquivo(String nome, int fase) {
		EscritorArquivo.fase=fase;
		try {
			
			if(fase==1)
				writer = new PrintWriter(new FileWriter(trataNomeArquivo(nome)+".LEXICO"));
			else if(fase==2)
				writer = new PrintWriter(new FileWriter(trataNomeArquivo(nome)+".SINTATICO"));
			else if(fase==3)
				writer = new PrintWriter(new FileWriter(trataNomeArquivo(nome)+".AST"));
			else if(fase==4)
				writer = new PrintWriter(new FileWriter(trataNomeArquivo(nome)+".TS"));
			else if(fase==5)
				writer = new PrintWriter(new FileWriter(trataNomeArquivo(nome)+".CODINT"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void escreveTokens (List<Token> allTokens) throws IOException{
		
		for(Token token: allTokens){
			
			writer.println(token);			
		}
		writer.flush();
		writer.close();		
	}
	
	
	protected String trataNomeArquivo(String nomeArq){
		
		String [] nomeEExt= nomeArq.split("\\.");
		return nomeEExt[0];
		
	}


	public static int getFase() {

		return fase;
	}
	
	


	public void escreve(Object obj) {
		
		writer.println(obj);
		
	}


	@Override
	protected void finalize() throws Throwable {
	
		writer.close();
		super.finalize();
	}
	
	
	/**
	 * Algoritmo de impressao da arvore. Imprimi a arvore de forma recusiva.
	 * @param nodes
	 */
	public void imprimeAst(List<Object> nodes){
		
		List<Object> novaLista=new ArrayList<Object>();
		for(Object n: nodes){
			if(n ==null)
				continue;
			
			if(n instanceof List){
				
				List lista = (List)n;
				if(lista!=null && !lista.isEmpty() && lista.size()>0){
					for(int i=0; i<lista.size();i++){
						if(i==0 && !lista.get(i).getClass().getSuperclass().getSimpleName().equals("Node")&&!lista.get(i).getClass().getSimpleName().equals("String")&& !lista.get(i).getClass().getSuperclass().getSimpleName().equals("Object")){
							novaLista.add("[");
							writer.print(lista.get(i).getClass().getSuperclass().getSimpleName()+"s");
							writer.print(" | ");
						}
						else if(i==0 && lista.get(i).getClass().getSimpleName().equals("String") ){
							novaLista.add("[");
							writer.print("ids");
							writer.print(" | ");
						}
						
						else if(i==0 && lista.get(i).getClass().getSimpleName().equals("Enum") ){
							writer.print("enum");
							writer.print(" | ");
						}
						
						else if(i==0 && lista.get(i).getClass().getSuperclass().getSimpleName().equals("Node") ){
							writer.print(lista.get(i).getClass().getSimpleName()+"s");
							writer.print(" | ");
						}
						
						novaLista.add(lista.get(i));
					}
					novaLista.add(" ]");
				}
								
			}
			else if(n instanceof Node){
				Node node = (Node)n;
				writer.print(n.toString());
				writer.print(" | ");
				for(Object filho: node.getChildren()){
					novaLista.add(filho);
				}
			}
			else{
				writer.print(n.toString());
				writer.print(" | ");
			}
				
		}
		writer.println();
		
		//momento de parada da recursividade
		if(!novaLista.isEmpty())
			imprimeAst(novaLista);
				
	}


	public void fecharArquivo() {
		writer.flush();		
		writer.close();
	}




	public static void setFase(int fase) {
		EscritorArquivo.fase = fase;
	}

	
	
}
