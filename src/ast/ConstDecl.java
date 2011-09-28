package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

public class ConstDecl extends Node {

	private String id;
	private Expression expression;
	private String addr = id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public ConstDecl(String id, Expression expression) {
		super();
		this.id = id;
		this.expression = expression;
	}

	
	public ConstDecl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		
		if(id==null && expression ==null)
			return "";
		return "ConstDecl: "+id+expression.toString();
	}
	
	@Override
	public void gen() {
		if(expression!=null &&expression.getOperador()!=null && expression.getOperando1()!=null &&expression.getOperando2()!=null ){
			Quadrupla q = new Quadrupla(expression.getOperador(), expression.getOperando1(), expression.getOperando2(), addr);
			SourceGenerator.getInstance().gen(q);
		}
	}
	
}
