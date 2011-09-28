package ast;
import static util.WriterHelper.getTabulacao;

public class VarType extends Expression {
	
	private Literal liteal;
	private boolean armazenada;

	public Literal getLiteral() {
		return liteal;
	}

	public void setLiteal(Literal liteal) {
		this.liteal = liteal;
	}

	public VarType(Literal liteal) {
		super();
		this.liteal = liteal;
	}

	public VarType() {
		super();
	}
	
	
	@Override
	public String toString() {
		
			
		return getTabulacao()+"VarType: "+liteal;
	}

	public boolean isArmazenada() {
		return armazenada;
	}

	public void setArmazenada(boolean armazenada) {
		this.armazenada = armazenada;
	}

	
	
}
