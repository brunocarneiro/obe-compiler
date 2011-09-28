package kernel;

import java.util.List;

import ast.ActualPar;



public class FunCallInfo extends Info {
	

	private ActualPar fpar;

	public FunCallInfo(String id, ActualPar fpar) {
		super(id);
		this.fpar = fpar;
	}

	public FunCallInfo(String id, ActualPar fpar, int offset, int width) {
		super(id);
		this.fpar = fpar;
		setOffset(offset);
		setWidth(width);
	}
	
	public ActualPar getFpar() {
		return fpar;
	}

	public void setFpar(ActualPar fpar) {
		this.fpar = fpar;
	}

	

	
	

}
