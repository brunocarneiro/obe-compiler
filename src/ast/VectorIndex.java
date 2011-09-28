package ast;

import static util.WriterHelper.getTabulacao;

public class VectorIndex extends Node {
	
	private String id;
	private Expression expression;
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
	public VectorIndex(String id, Expression expression) {
		super();
		this.id = id;
		this.expression = expression;
	}
	
	@Override
	public String toString() {

		return getTabulacao()+"VectorIndex: "+id;
	}
		

}
