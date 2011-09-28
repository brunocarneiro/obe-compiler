package ast;

import static util.WriterHelper.getTabulacao;
import gen.Quadrupla;
import gen.SourceGenerator;
import kernel.Escopos;
import kernel.TabelaSimbolo;
import kernel.VarInfo;

public class Assignment extends Node implements Statement {

	private Variable variable;
	private Expression expression;
	private String address;
	private String res;
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public Assignment(Variable variable, Expression expression) {
		super();
		this.variable = variable;
		if(expression instanceof Variable){
			VarInfo info = (VarInfo)TabelaSimbolo.getInfo(Escopos.getInstance().lookup(((Variable)expression).getId()));
			if(info!=null && info.getValorInicial() instanceof Expression)
			this.address = ((Expression)info.getValorInicial()).getAddress();
		}
		else
			address = SourceGenerator.getInstance().getVariavel();
		this.expression = expression;
		
	}
	
	@Override
	public String toString() {

		
		return getTabulacao()+"Assignment: " +expression.toString();
	}
	
	@Override
	public void gen() {
		//expression.setAddress(variable);
		Quadrupla q;
		if(expression instanceof ValorLiteral){
			q = new Quadrupla(null, expression, null, variable.getAddress());
		}
		else{
		expression.gen();
		q = new Quadrupla(null, expression.getAddress(), null, variable.getAddress());
		}
		SourceGenerator.getInstance().gen(q);
		
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public Expression getExpression() {
		return expression;
	}
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}


}
