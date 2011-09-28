package ast;

import static util.WriterHelper.getTabulacao;

public class ArrayVarType extends VarType {
	
	private Expression expression;
	
	public ArrayVarType(Literal l, Expression expression) {
		super(l);
		this.expression = expression;
		
	}

	@Override
	public String toString() {
		

		return getTabulacao()+"ArrayVarType: " +expression.toString() ;
	}

	@Override
	public void gen() {
		
		expression.gen();
	}
	
	

}
