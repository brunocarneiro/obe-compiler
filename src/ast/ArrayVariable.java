package ast;

public class ArrayVariable extends Variable {

	private Expression expression;

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public ArrayVariable(String id, Expression expression) {
		super(id);
		this.expression = expression;
	}
	
	
	
	

}
