package kernel.semantico;

import io.LeitorArquivo;
import kernel.Escopos;
import kernel.FunCallInfo;
import kernel.FunInfo;
import kernel.Info;
import kernel.TabelaSimbolo;
import kernel.VarInfo;
import ast.ActualPar;
import ast.BinaryExpression;
import ast.Boolean;
import ast.Expression;
import ast.Literal;
import ast.NullableExpression;
import ast.Operador;
import ast.UnaryExpression;
import ast.ValorLiteral;
import ast.Variable;

public class Validator {
	
	
	public static boolean verificaBooleanExpression(Expression e){
		
		if(e instanceof BinaryExpression){
			BinaryExpression b = (BinaryExpression)e;
			
			if(isRelationalOperator(b.getOperador())){
				if(verificaIntegerExpression(((BinaryExpression)e).getLeftExp()) && verificaIntegerExpression(((BinaryExpression)e).getRightExp()))
					return true;
			}
			
			else if(isLogicOperator(b.getOperador())){
				
				if(verificaBooleanExpression(((BinaryExpression)e).getLeftExp())&& verificaBooleanExpression(((BinaryExpression)e).getRightExp())){
					return true;
				}
			}
		}
		else if(e instanceof UnaryExpression && ((UnaryExpression)e).getOperador()==Operador.NOT){
			return true;
		}
		else if(e instanceof NullableExpression)
			return true;
		else if(e instanceof Variable){
			return isVarBoolean(e);
			
		}		
		else if(e instanceof ValorLiteral && ((ValorLiteral)e).getValor() instanceof Boolean)
			return true;

		return false;
	}

	
	public static  boolean verificaIntegerExpression(Expression e){
		boolean retorno=false;
		if(e instanceof BinaryExpression){

			BinaryExpression b = (BinaryExpression)e;
			
			if(isMathOperator(b.getOperador() )){
				if(verificaIntegerExpression(b.getLeftExp()) && verificaIntegerExpression(b.getRightExp()))
					retorno= true;
			}
		}
		else if(e instanceof ValorLiteral && !(((ValorLiteral)e).getValor() instanceof Boolean))
			retorno= true;
		else if(e instanceof Variable){
			 retorno=isVarInteger(e);
				
		}
		else if(e instanceof NullableExpression)
			retorno=true;

		return retorno;
		
	}
	
	/**
	 * Verifica expressao e imprime o erro se houver.
	 * @param e expressao
	 */
	public static void validaIntegerExpression(Expression e){
		
		if(!verificaIntegerExpression(e)){
			System.err.println("Atribuicao não e válida. Tentar atribuir a um Integer, um valor não inteiro. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
			System.exit(0);
		}
		
	}
	
	
	/**
	 * Verifica expressao e imprime o erro se houver.
	 * @param e expressao
	 */
	public static void validaBooleanExpression(Expression e){
		
		if(!verificaBooleanExpression(e)){
			System.err.println("Estrutura com erro Semântico. Expressao não resulta em um booleano. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
			System.exit(0);
		}
	}
	
	
	
	

	protected static  boolean isLogicOperator(Operador operador) {
		
		boolean retorno = false;
		if(operador==Operador.AND){retorno = true;}
		if(operador==Operador.OR){retorno = true;}
		
		return retorno;
	
	}


	protected static  boolean isRelationalOperator(Operador operador) {
		
		boolean retorno = false;
		if(operador==Operador.MAIOR){retorno = true;}
		if(operador==Operador.MENOR){retorno = true;}
		if(operador==Operador.MAIORIGUAL){retorno = true;}
		if(operador==Operador.MENORIGUAL){retorno = true;}
		if(operador==Operador.IGUAL){retorno = true;}
		if(operador==Operador.DIFERENTE){retorno = true;}
		
		return retorno;
	}

	protected static boolean isMathOperator(Operador operador) {
		
		boolean retorno = false;
		if(operador==Operador.MAIS){retorno = true;}
		if(operador==Operador.MENOS){retorno = true;}
		if(operador==Operador.MULT){retorno = true;}
		if(operador==Operador.DIV){retorno = true;}
		if(operador==Operador.MOD){retorno = true;}
		
		return retorno;
	}
	
	public static boolean isVarBoolean(Expression e){
		
		Variable var  =  (Variable)e;
		int pos = Escopos.getInstance().lookup(var.getId());
		if(pos>0){
			Info info = TabelaSimbolo.getInfo(pos);
			if(info !=null && (info.getTipo()==Literal.BOOLEAN || info.getTipo()==Literal.ARRAY_TYPE_OF_BOOLEAN || info.getTipo()==Literal.CONST_BOOLEAN))
				return true;
		}
		return false;
		
	}
		
	public static boolean isVarInteger(Expression e){
			
			Variable var  =  (Variable)e;
			int pos = Escopos.getInstance().lookup(var.getId());
			if(pos>0){
				Info info = TabelaSimbolo.getInfo(pos);
				if(info !=null && (info.getTipo()==Literal.INTEGER || info.getTipo()==Literal.ARRAY_TYPE_OF_INTEGER || info.getTipo()==Literal.CONST_INTEGER))
					return true;
			}
			return false;
			
	}
	
	public static boolean verificaVariavelConst(Expression e){
		Variable v=null;
		if(e instanceof Variable){
			v=(Variable)e;
		}
		else return true;
		
		Variable var  =  (Variable)e;
		int pos = Escopos.getInstance().lookup(var.getId());
		if(pos>0){
			Info info = TabelaSimbolo.getInfo(pos);
			if(info.getTipo()==Literal.CONST||info.getTipo()==Literal.CONST_ARRAY||info.getTipo()==Literal.CONST_BOOLEAN||info.getTipo()==Literal.CONST_INTEGER){
				return true;
			}
		}
		
		if(Validator.isVarBoolean(v)){
			return false;
		}
		else if(Validator.isVarInteger(v)){
			return false;
		}
		else{
			//se nao for as acima, com certeza é uma constante
			return true;
		}
		
	}
	
	
	public static boolean verificaVetor(String id){
		int pos=Escopos.getInstance().lookup(id);
		if(pos!=-1){
			Info i = (Info)TabelaSimbolo.getInfo(pos);
			if((i.getTipo()==Literal.ARRAY_TYPE_OF_BOOLEAN || i.getTipo()==Literal.ARRAY_TYPE_OF_INTEGER)){
				return true;
			}
			
			
		}
		return false;
		
	}
	
	public static void validaVetor(String id){
		
		if(!verificaVetor(id)){
			System.err.println("O simbolo não é um vetor, ou nao foi declarado. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
			System.exit(0);
		}
		
	}
	
	public static boolean verificaChamadaFuncao(Info funcallinfo){
		
	    String id=funcallinfo.getId(); ActualPar actual = ((FunCallInfo)funcallinfo).getFpar();
		int pos = Escopos.getInstance().lookup(id);
		Info i = (Info) TabelaSimbolo.getInfo(pos);
		
		if(i instanceof FunInfo){
			FunInfo f =	((FunInfo) i);
			if(f.getFpar().size()==actual.getExpressions().size()){
				
				for(int cont=0; cont<f.getFpar().size(); cont++){
					
					if(!verificaTipo(f.getFpar().get(cont), (Variable) actual.getExpressions().get(cont))){
						System.err.println("O parametro de posicao " +cont+" nao corresponde ao tipo esperado.  Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
						System.exit(0);
					}
					
				}
				
			}
			else{
				System.err.println("Numero de parametros invalido Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
				System.exit(0);
			}
		}
		
		return false;
		
	}

	protected static boolean verificaTipo(int var1, Variable var2) {
		
		Info i = TabelaSimbolo.getInfo(var1);
		Info i2 = TabelaSimbolo.getInfo(Escopos.getInstance().getEscopoAtual().lookup(var2.getId()));

		if(i!=null && i2!=null&&(i.getTipo()==i2.getTipo())){
			return true;
		}
		
		return false;
	}
	
	
	public static void verificaTipo(VarInfo info, Object valor) {
		
		Literal tipo= info.getTipo();

		if((tipo==Literal.INTEGER)){
			Validator.validaIntegerExpression((Expression)valor);
		}			
		else if(tipo==Literal.BOOLEAN){
			Validator.validaBooleanExpression((Expression)valor);
			
		}
		else if(isConst(info)){
			System.err.println("Já foi atribuido valor inicial para constante, não é possível novamente. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
			System.exit(0);
		}
	}
	
	public static boolean isConst(VarInfo info) {
		
		if(info instanceof VarInfo && (info.getTipo()==Literal.CONST_INTEGER||info.getTipo()==Literal.CONST_ARRAY||info.getTipo()==Literal.CONST_BOOLEAN)){
			return true;
		}
		
		return false;
	}


	public static void verificaTipoConst(Info info) {
		VarInfo v = (VarInfo)info;
	
		if(!Validator.verificaVariavelConst((Expression)v.getValorInicial())){
			System.err.println("Constante esta recebendo uma variavel que não é constante. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
			System.exit(0);
			
		}
		else if(Validator.verificaBooleanExpression((Expression)v.getValorInicial())){
			v.setTipo(Literal.CONST_BOOLEAN);
			
		}
		else if(Validator.verificaIntegerExpression((Expression)v.getValorInicial())){
			v.setTipo(Literal.CONST_INTEGER);
		}		
	}
	

}
