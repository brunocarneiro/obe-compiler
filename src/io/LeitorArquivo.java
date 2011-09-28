package io;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
	
	
	private BufferedReader reader;
	private String linha;
	private int caracter;
	private static int contCol = 0;
	private static int contLinha = 1;
	
	
	
	public LeitorArquivo(String arquivo){
		
		try {
			reader = new BufferedReader(new FileReader(arquivo));
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Arquivo não encontrado, tente novamente");
			e.printStackTrace();
		}
		
		
	}
	
	
	public int lerCaracter() throws IOException{
		caracter=reader.read();
		
		if(caracter==-1)
			throw new EOFException();
		if(caracter==10){
			contLinha++;
			contCol=0;
			return caracter;
		}
		
		contCol++;
		return caracter;
	}
	
	public String lerLinha() throws IOException{
		linha=reader.readLine();
		if(linha==null)
			throw new EOFException();
		
		return linha;
	}
	
	@Override
	public void finalize() throws Throwable {
		
		super.finalize();
		reader.close();
	}


	public BufferedReader getReader() {
		return reader;
	}


	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}


	public String getLinha() {
		return linha;
	}


	public void setLinha(String linha) {
		this.linha = linha;
	}


	public char getCaracter() {
		
		return (char)caracter;
	}
	
	public int getCaracterInt() {
		
		return caracter;
	}


	public void setCaracter(int caracter) {
		this.caracter = caracter;
	}


	public static int getContCol() {
		return contCol-1;
	}


	public void setContCol(int contCol) {
		this.contCol = contCol;
	}


	public static int getContLinha() {
		return contLinha;
	}


	public void setContLinha(int contLinha) {
		this.contLinha = contLinha;
	}
	
	
	
	

}
