package kernel;

import gen.FinalCodeGenerator;
import ast.Literal;

/**
 * De acordo com slides
 * metodos
 *
 */
public class VarInfo extends Info {
	
	
	private Object valorInicial;
	private boolean global;
	private static int offsetAtual;
	
	public Object getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(Object valorInicial) {
		this.valorInicial = valorInicial;
	}
	public VarInfo(String id, Literal tipo, Object valorInicial) {
		super(id);
		setTipo(tipo);
		this.valorInicial = valorInicial;
		if(id!=null && !id.equals("RESULT"))
			setOffset(getOffSetAtual());
	}

	
	public VarInfo(String id, Literal tipo, Object valorInicial, int offset, int width) {
		super(id);
		setTipo(tipo);
		this.valorInicial = valorInicial;

		setWidth(width);
	}
	
	public boolean isGlobal() {
		return global;
	}
	public void setGlobal(boolean global) {
		this.global = global;
	}
	
	public int getOffSetAtual(){
		return (-1*FinalCodeGenerator.SIZE)*(++offsetAtual);
	}
	
	
	
}
