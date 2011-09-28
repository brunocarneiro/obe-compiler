package ast;

import gen.Quadrupla;
import gen.SourceGenerator;
import io.EscritorArquivo;

public class ValorLiteral extends Expression {
	
	private Object valor;
	private boolean armazenada;

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public ValorLiteral(Object valor) {
		super();
		this.valor = valor;
		//SourceGenerator.getInstance().gen(new Quadrupla(null, this,null, null));
	}
	
	@Override
	public String toString() {
		if(EscritorArquivo.getFase()==5){
			return "" +valor;
		}	
		
		return "Valor Literal : "+valor;
	}

	public boolean isArmazenada() {
		return armazenada;
	}

	public void setArmazenada(boolean armazenada) {
		this.armazenada = armazenada;
	}
		
	@Override
	public Expression getOperando1() {
		
		if(valor instanceof Boolean)
		return this;
		else
			return super.getOperando1();
	}
	
	@Override
	public void gen() {
		
		if(valor!=null){
			Quadrupla q = new Quadrupla(null, valor.toString(), "", getAddress());
			SourceGenerator.getInstance().gen(q);
		}
	}
	

}
