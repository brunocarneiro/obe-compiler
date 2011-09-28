package ast;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class ActualPar extends Node {

	private List<Expression> expressions;

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public ActualPar(List<Expression> expressions) {
		super();
		this.expressions = expressions;
	}
	
	@Override
	public String toString() {

		return getTabulacao()+"ActualPar: " +expressions.toString();
	}

	
	
}
