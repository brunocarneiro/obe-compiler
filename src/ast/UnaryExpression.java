package ast;

import static util.WriterHelper.getTabulacao;
import gen.Quadrupla;
import gen.SourceGenerator;
import io.EscritorArquivo;

public class UnaryExpression extends Expression {

	private Expression operando1;
	private Operador operador;
	private boolean armazenada;
	
	public UnaryExpression(Expression operando1, Operador operador) {
		super();
		this.operando1 = operando1;
		this.operador = operador;
		setAddress(SourceGenerator.getVariavel());
		
			
	}

	public Expression getOperando1() {
		return operando1;
	}

	public void setOperando1(Expression operando1) {
		this.operando1 = operando1;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	} 
	
	
	@Override
	public String toString() {
		
		if(EscritorArquivo.getFase()==5){
			return "" +operando1.toString() + operador.toString();
		}	
		return getTabulacao()+"UnaryExpression: " +operando1.toString() + operador.toString();
	}

	public boolean isArmazenada() {
		return armazenada;
	}

	public void setArmazenada(boolean armazenada) {
		this.armazenada = armazenada;
	}
	
	
	
	@Override
	public void gen() {
		
		SourceGenerator.getInstance().gen(new Quadrupla(operador, operando1,null, getAddress()));
	}
	
}
