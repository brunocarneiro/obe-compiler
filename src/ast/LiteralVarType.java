package ast;
import static util.WriterHelper.getTabulacao;

public class LiteralVarType extends Node {

	private Literal literal;
	
	
	

	public LiteralVarType(Literal literal) {
		super();
		this.literal = literal;
	}

	public Literal getLiteral() {
		return literal;
	}

	public void setLiteral(Literal literal) {
		this.literal = literal;
	}
	
	@Override
	public String toString() {
		
			
		return getTabulacao()+"LiteralVarType: "+literal;
	}
	
	
}
