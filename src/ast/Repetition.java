package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

import java.util.List;

import kernel.semantico.Validator;
import static util.WriterHelper.getTabulacao;

public class Repetition extends Node implements Statement  {

	private Expression expression;
	private List<Statement> statements;
	private RepetitionType repetitonType;
	private String address = SourceGenerator.getRotulo();
	private String address2 = SourceGenerator.getRotulo();
	
	public Repetition(Expression expression, List<Statement> statements,
			RepetitionType repetitonType) {
		super();
		Validator.validaBooleanExpression(expression);
		this.expression = expression;
		this.statements = statements;
		this.repetitonType = repetitonType;
		
		
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


	public RepetitionType getRepetitonType() {
		return repetitonType;
	}


	public void setRepetitonType(RepetitionType repetitonType) {
		this.repetitonType = repetitonType;
	}
	
	
	@Override
	public String toString() {

		
		return getTabulacao()+"Repetition: "+repetitonType+expression.toString() + statements.toString();
	}
	

	
	@Override
	public void gen() {
		
		Quadrupla q,q1,q3,q4,q5;
		

		
		if(expression.getOperador()==Operador.AND){
			
			expression.getOperando1().gen();
			expression.getOperando2().gen();
//			q=new Quadrupla(null, "", "", address2);
//			SourceGenerator.getInstance().gen(q);
			q=new Quadrupla(null, expression.getOperando1().getAddress(), null, expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q);
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
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "GOTO", address2));
			q3=new Quadrupla(null, null, "", expression.getOperando2().getT_label());
			SourceGenerator.getInstance().gen(q3);
			//gerando bloco dentro do if
			for(Statement s : statements)
				s.gen();
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "", address2));
						
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
			//fim do comando

			
			
		}
		else{
			//TODO FAZER ISSO RECURSIVO
			expression.getOperando1().gen();
			expression.getOperando2().gen();
			q=new Quadrupla(null, "", "", address2);
			SourceGenerator.getInstance().gen(q);
			if(expression.getOperando1() instanceof ValorLiteral && expression.getOperando2() instanceof ValorLiteral){
				q1=new Quadrupla(expression.getOperador(), expression.getOperando1(), expression.getOperando2(), expression.getOperando1().getT_label());
			}
			else
				q1=new Quadrupla(expression.getOperador(), expression.getOperando1().getAddress(), expression.getOperando2().getAddress(), expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			//sair do if
			
			q1=new Quadrupla(null, null, "GOTO", address);
			SourceGenerator.getInstance().gen(q1);
			//entrar no if
			q1=new Quadrupla(null, null, "", expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			for(Statement s : statements)
				s.gen();
			//rotulo de saida das condicoes
			
			q1=new Quadrupla(null, null, "GOTO", address2);
			SourceGenerator.getInstance().gen(q1);
			
			q1=new Quadrupla(null, null,"", address);
			SourceGenerator.getInstance().gen(q1);
		}
		
		
		
		/*/old
		expression.gen();
		SourceGenerator.getInstance().gen(new Quadrupla(expression.getOperador(),expression.getOperando2().getAddress(), expression.getOperando1().getAddress(),  expression.getT_label()));
		SourceGenerator.getInstance().gen(new Quadrupla(null,"", "",  expression.getF_label()));
		*/
		
	}

}
