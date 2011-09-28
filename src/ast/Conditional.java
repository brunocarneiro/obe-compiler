package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

import java.util.List;

import kernel.semantico.Validator;
import static util.WriterHelper.getTabulacao;

public class Conditional extends Node implements Statement  {


	private Expression expression;
	private List<Statement> statements;
	private List<Elsif> elsifs;
	private Else elseStatement;
	private String address = SourceGenerator.getRotulo();
	private String address2 = SourceGenerator.getRotulo();
	
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
	public List<Elsif> getElsifs() {
		return elsifs;
	}
	public void setElsifs(List<Elsif> elsifs) {
		this.elsifs = elsifs;
	}
	public Else getElseStatement() {
		
		return elseStatement;
	}
	public void setElseStatement(Else elseStatement) {
		this.elseStatement = elseStatement;
	}
	public Conditional(Expression expression, List<Statement> statements,
			List<Elsif> elsifs, Else elseStatement) {
		super();
		Validator.validaBooleanExpression(expression);
		this.expression = expression;
		this.statements = statements;
		this.elsifs = elsifs;
		this.elseStatement = elseStatement;
		
	
	}
	
	@Override
	public String toString() {
		
		return getTabulacao()+"Conditional: "  +expression.toString()+statements.toString()+elsifs.toString()+elseStatement.toString();
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
			for(Elsif elsif : elsifs)
				elsif.gen();
			elseStatement.gen();
			
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
			for(Elsif elsif : elsifs)
				elsif.gen();
			elseStatement.gen();
			
			
		}
		else{
			expression.getOperando1().gen();
			expression.getOperando2().gen();
			q=new Quadrupla(null, "", "", address2);
			SourceGenerator.getInstance().gen(q);
			q1=new Quadrupla(expression.getOperador(), expression.getOperando1().getAddress(), expression.getOperando2().getAddress(), expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			//sair do if

			if(elseStatement!=null){
				address=elseStatement.getAddr();
			}
			if(elsifs!=null){
				
				address=elsifs.get(0).getAddress();
				
			}
			
			q1=new Quadrupla(null, null, "GOTO", address);
			SourceGenerator.getInstance().gen(q1);
			//entrar no if
			q1=new Quadrupla(null, null, "", expression.getOperando1().getT_label());
			SourceGenerator.getInstance().gen(q1);
			for(Statement s : statements)
				s.gen();
			//rotulo de saida das condicoes
			
			if(elseStatement ==null&& elsifs!=null){
				
				address2=elsifs.get(elsifs.size()-1).getAddress();
			}
			else if(elseStatement!=null){
				address2=elseStatement.getAddr2();
			}
			
			q1=new Quadrupla(null, null, "GOTO", address2);
			SourceGenerator.getInstance().gen(q1);
			
//			q1=new Quadrupla(null, null, "", address);
//			SourceGenerator.getInstance().gen(q1);
			
			//marcar rotulo de saida da condicao
			for(int cont=0; cont<elsifs.size();cont++){
				Elsif elsif = elsifs.get(cont);
				String rotuloProximo;
				if(cont==elsifs.size()-1){
					rotuloProximo=elseStatement.getAddr();
				}
				else rotuloProximo = elsifs.get(cont+1).getAddress();
				
				elsif.setAddress2(rotuloProximo);
				elsif.setAddressSaida(address2);
				elsif.gen();
			}
			//elsifs.get(elsifs.size()-1).setAddress(address)
			elseStatement.gen();
			
			q=new Quadrupla(null, "", "", address2);
			SourceGenerator.getInstance().gen(q);
			
		}
		
		
		
		/*/old
		expression.gen();
		SourceGenerator.getInstance().gen(new Quadrupla(expression.getOperador(),expression.getOperando2().getAddress(), expression.getOperando1().getAddress(),  expression.getT_label()));
		SourceGenerator.getInstance().gen(new Quadrupla(null,"", "",  expression.getF_label()));
		*/
		
	}
	
}
