package ast;

import gen.SourceGenerator;



public abstract class Expression extends Node {

	private String t_label;
	private String f_label;
	private String address=SourceGenerator.getVariavel();
	

	abstract boolean isArmazenada();
	
	public Expression() {
		setF_label(SourceGenerator.getRotulo());
		setT_label(SourceGenerator.getRotulo());
	}
	
	public Operador getOperador(){
		
		return null;
	}

	public Expression getOperando2() {
		return null;
	}

	public Expression getOperando1() {
		return null;
	}

	public String getT_label() {
		return t_label;
	}

	public void setT_label(String t_label) {
		this.t_label = t_label;
	}

	public String getF_label() {
		return f_label;
	}

	public void setF_label(String f_label) {
		this.f_label = f_label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
