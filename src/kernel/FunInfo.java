package kernel;

import gen.SourceGenerator;

import java.util.List;

import ast.FormalPars;

import ast.Literal;

/**
 * 
 * 
 * 
 * 1 Nao tera type, pq as funcoes oberon nao possuem retorno.
 * 
 * 2 Nao colocamos B no inicio de body, apenas o numero.
 * 
 * 
 */

public class FunInfo extends Info {

	private List<Integer> fpar;

	private int body;

	private Literal tipo;
	
	

	public FunInfo(String id, List<Integer> fpar, int body, Literal tipo) {

		super(id);

		this.fpar = fpar;

		this.body = body;

		this.tipo = tipo;
		
	}

	public FunInfo(String id, List<Integer> fpar, Literal tipo) {

		super(id);

		this.fpar = fpar;

		this.body = body;

		this.tipo = tipo;

	}
	
	public FunInfo(String id, List<Integer> fpar, Literal tipo, int offset, int width) {

		super(id);

		this.fpar = fpar;

		this.body = body;

		this.tipo = tipo;
		setWidth(width);
		setOffset(offset);

	}

	public List<Integer> getFpar() {
		return fpar;
	}

	public void setFpar(List<Integer> fpar) {
		this.fpar = fpar;
	}

	public int getBody() {
		return body;
	}

	public void setBody(int body) {
		this.body = body;
	}

	public Literal getTipo() {
		return tipo;
	}

	public void setTipo(Literal tipo) {
		this.tipo = tipo;
	}

	

}
