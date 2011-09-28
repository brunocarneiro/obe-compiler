package kernel;

import ast.Literal;


/**
 * De acordo com slides
 * classes
 *
 */
public class AtribInfo extends Info {
	
	
	private Integer valorInicial;
	
	
	public AtribInfo(String id, Literal tipo, Integer valorInicial) {
		super(id);
		setTipo(tipo);
		this.valorInicial = valorInicial;
	}
	
	
	public AtribInfo(String id, Literal tipo, Integer valorInicial, int offset, int width) {
		super(id);
		setTipo(tipo);
		this.valorInicial = valorInicial;
		setOffset(offset);
		setWidth(width);
	}
	
	

}
