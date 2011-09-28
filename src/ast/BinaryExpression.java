package ast;

import static util.WriterHelper.getTabulacao;
import gen.Quadrupla;
import gen.SourceGenerator;
import io.EscritorArquivo;

public class BinaryExpression extends Expression{

	private Expression operando1;
	private Expression operando2;
	

	
	private boolean armazenada;
	
	private Operador operador;

	public Expression getOperando1() {
		return operando1;
	}

	public void setOperando1(Expression operando1) {
		this.operando1 = operando1;
	}

	public Expression getOperando2() {
		return operando2;
	}

	public void setOperando2(Expression operando2) {
		this.operando2 = operando2;
	}

	public Operador getOperador() {
		
		
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public BinaryExpression(Expression operando1, Expression operando2,
			Operador operador) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
		this.operador = operador;
		setAddress(SourceGenerator.getVariavel());
		
	}
	
	@Override
	public String toString() {
		if(EscritorArquivo.getFase()==5){
			return ""+operando1.toString()+operador.toString()+operando2.toString();
		}
		return getTabulacao()+"BinaryExpression: " +operando1.toString()+operador.toString()+operando2.toString();
	}


	public Expression getLeftExp() {
		
		return operando1;
	}


	public Expression getRightExp() {
		
		return operando2;
	}

	public boolean isArmazenada() {
		return armazenada;
	}

	public void setArmazenada(boolean armazenada) {
		this.armazenada = armazenada;
	}

	
	@Override
	public void gen() {
		if(!(operando1 instanceof ValorLiteral) && !(operando2 instanceof ValorLiteral)){
			operando1.gen();
			operando2.gen();
			SourceGenerator.getInstance().gen(new Quadrupla(operador, operando1.getAddress(), operando2.getAddress(), getAddress()));
		}
		
		else if(!(operando1 instanceof ValorLiteral)&&(operando2 instanceof ValorLiteral)){
			operando1.gen();
			operando2.gen();
			SourceGenerator.getInstance().gen(new Quadrupla(operador, operando1.getAddress(), operando2.toString(), getAddress()));
		}
		else if((operando1 instanceof ValorLiteral)&&!(operando2 instanceof ValorLiteral)){
			operando1.gen();
			operando2.gen();
			SourceGenerator.getInstance().gen(new Quadrupla(operador, operando1.toString(), operando2.getAddress(), getAddress()));
		}
		else
			SourceGenerator.getInstance().gen(new Quadrupla(operador, operando1, operando2, getAddress()));
	}

	
}
