package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class Elsif extends Node {
	
	private Expression expression;
	private List<Statement> statements;
	private String address = SourceGenerator.getRotulo();
	private String address2 = SourceGenerator.getRotulo();
	private String addressSaida;
	
	
	public Elsif(Expression expression, List<Statement> statements) {
		super();
		this.expression = expression;
		this.statements = statements;
	}
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public List<Statement> getStatements() {
		return statements;
	}
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	
	
	@Override
	public String toString() {
		

		return getTabulacao()+"Elsif: " +expression.toString() +statements.toString();
	}


	@Override
	public void gen() {
		
		Quadrupla q,q1,q3,q4,q5;
		

		
		if(expression.getOperador()==Operador.AND){
			
			String rotulo1 = SourceGenerator.getRotulo();
			expression.getOperando1().gen();
			expression.getOperando2().gen();
			q=new Quadrupla(null, "", "", address2);
			SourceGenerator.getInstance().gen(q);
			q=new Quadrupla(null, expression.getOperando1().getAddress(), null, expression.getOperando1().getT_label());
			//saida do if, gerando somente rotulo
			q1=new Quadrupla(null, "", null, address);
			SourceGenerator.getInstance().gen(q1);
			//agora sair da condicao

			
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "GOTO", address2));
			//bloco dentro do if, gerando rotulo
			q3=new Quadrupla(null, "", "", expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q3);
			q3=new Quadrupla(null, expression.getOperando2().getAddress(), "", expression.getOperando2().getT_label());
			SourceGenerator.getInstance().gen(q3);
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "GOTO", address));
			q3=new Quadrupla(null, null, "", expression.getOperando2().getT_label());
			SourceGenerator.getInstance().gen(q3);
			//gerando bloco dentro do if
			for(Statement s : statements)
				s.gen();
						
		}
		else if(expression.getOperador()==Operador.OR){
			expression.getOperando1().gen();
			expression.getOperando2().gen();		
			q=new Quadrupla(null, "", "", address2);
			SourceGenerator.getInstance().gen(q);
			q1=new Quadrupla(null, expression.getOperando1().getAddress(), null, expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			q1=new Quadrupla(null, "", null, expression.getOperando1().getF_label());
			SourceGenerator.getInstance().gen(q1);
			q1=new Quadrupla(null, expression.getOperando2().getAddress(), null, expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			q1=new Quadrupla(null, null, "GOTO", address);
			SourceGenerator.getInstance().gen(q1);
			//inseriu agora o rotulo do final do if
			//agora o meio do if
			q1=new Quadrupla(null, null, "", expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			for(Statement s : statements)
				s.gen();
			q1=new Quadrupla(null, null, "", address);
			SourceGenerator.getInstance().gen(q1);
			
			
		}
		else{
			expression.getOperando1().gen();
			expression.getOperando2().gen();
			q=new Quadrupla(null, "", "", address);
			SourceGenerator.getInstance().gen(q);
			q1=new Quadrupla(expression.getOperador(), expression.getOperando1().getAddress(), expression.getOperando2().getAddress(), expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			//sair do if
			q1=new Quadrupla(null, null, "GOTO", address2);
			SourceGenerator.getInstance().gen(q1);
			//entrar no if
			q1=new Quadrupla(null, null, "", expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			for(Statement s : statements)
				s.gen();
			//rotulo de saida das condicoes
			q1=new Quadrupla(null, null, "GOTO", addressSaida);
			SourceGenerator.getInstance().gen(q1);
			
//			q1=new Quadrupla(null, null, "", address);
//			SourceGenerator.getInstance().gen(q1);
			
			
//			q=new Quadrupla(null, "", "s", address2);
//			SourceGenerator.getInstance().gen(q);
			
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddressSaida() {
		return addressSaida;
	}
	public void setAddressSaida(String addressSaida) {
		this.addressSaida = addressSaida;
	}
	
	

}
