package gen;

import ast.BinaryExpression;
import ast.Expression;
import ast.Operador;
import ast.UnaryExpression;
import ast.ValorLiteral;



public class Quadrupla {
	
	private String op;
	private String arg1;
	private String arg2;
	private String res;
	


	public Quadrupla(Operador operador, Expression arg1, Expression arg2, String res) {
		super();
		this.op = trataOperador(operador);
		
		this.arg1 = procuraExpressaoNoMapa(arg1);
		this.arg2 = procuraExpressaoNoMapa(arg2);
		this.res = res;
	}
	
	public Quadrupla(Operador operador, String arg1, String arg2, String res) {
		super();
		this.op = trataOperador(operador);
		
		this.arg1 = procuraTemporarioNoMapa(arg1);
		this.arg2 = procuraTemporarioNoMapa(arg2);
		this.res = res;
	}

	private String procuraTemporarioNoMapa(String arg12) {
		String temp =SourceGenerator.getInstance().getRealocacaoVar().get(arg12); 
		if(temp!=null)
			return temp;
		return arg12;
	}

	private String trataOperador(Operador operador) {
		
		if(operador==null)
			return null;
		else
			return operador.toString();
	}

	public String procuraExpressaoNoMapa(Expression e){
		Quadrupla q=null;
		
		if(e instanceof BinaryExpression){
			BinaryExpression b = (BinaryExpression)e;
			q=new Quadrupla(b.getOperador(),b.getOperando1(), b.getOperando2(), null);
		}
		else if (e instanceof UnaryExpression){
			UnaryExpression b = (UnaryExpression)e;
			q=new Quadrupla(b.getOperador(),b.getOperando1(), null, null);
		}
		else if(e instanceof ValorLiteral){
			
			
		}
		
		int pos = SourceGenerator.getInstance().lookup(q);
		if(pos>-1){
			return SourceGenerator.getInstance().getQuadruplaFromCache(pos).getRes();
		}
		else if(e ==null)
			return "";
		else
			return e.toString();
	}



	@Override
	public boolean equals(Object obj) {
		boolean retorno=false,retorno2=false, retorno3=false;
		if(obj instanceof Quadrupla){
			
			Quadrupla q = (Quadrupla)obj;
			if(((this.op==null||op.equals("")) && (q.getOp()==null||q.getOp().equals("")))||(this.op!=null && q.getOp()!=null && op.equals(q.getOp()) || (this.op==null && q.getOp()==null))){
				retorno=true;
			}
			else
				retorno=false;
			if(arg1!=null && q.getArg1()!=null && arg1.equals(q.getArg1()) && !(arg1.equals("")&&q.getArg1().equals("") ) || ((this.arg1==null||arg1.equals("")) && (q.getArg1()==null||q.getArg1().equals("")))){
				retorno2=true;
			}
			else
				retorno2=false;
			
			if(arg2!=null && q.getArg2()!=null && arg2.equals(q.getArg2())&& !(arg2.equals("")&&q.getArg2().equals("") ) || ((this.arg2==null||arg2.equals("")) && (q.getArg2()==null||q.getArg2().equals("")))){
				retorno3=true;
			}
			else
				retorno3=false;
			
		}
		return retorno && retorno2&&retorno3;
	}


	public String getOp() {
		if(op==null)
			return "";
		return op;
	}


	public void setOp(String op) {
		this.op = op;
	}

	

	public String getRes() {
		if(res==null)
			return "";
		return res;
	}


	public void setRes(String res) {
		this.res = res;
	}


	@Override
	public String toString() {
		if(res!=null)
			return res+" = "+arg1+op+arg2;
		return ""+arg1+op+arg2;
	}

	public String getArg1() {
		if(arg1==null)
			return "";
		return arg1;
	}

	public void setArg1(String arg1) {
		
		this.arg1 = arg1;
	}

	public String getArg2() {
		if(arg2==null)
			return "";
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	
	
}
