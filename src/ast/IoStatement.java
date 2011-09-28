package ast;

import static util.WriterHelper.getTabulacao;
import gen.Quadrupla;
import gen.SourceGenerator;

public class IoStatement extends Node implements Statement  {

	private Expression expressao;
	private IoType ioType;
	
	
	
	
	public IoStatement(Expression expressao, IoType ioType) {
		super();
		this.expressao = expressao;
		this.ioType = ioType;
	}
	public Expression getExpressao() {
		return expressao;
	}
	public void setExpressao(Expression expressao) {
		this.expressao = expressao;
	}
	public IoType getIoType() {
		return ioType;
	}
	public void setIoType(IoType ioType) {
		this.ioType = ioType;
	}
	
	
	@Override
	public String toString() {
		if(expressao!=null)
			return getTabulacao()+"IoStatement: "+ioType;
		else
			return getTabulacao()+"IoStatement: "+ioType ;
		 
	}
	
	@Override
	public void gen() {
		
		if(ioType==IoType.WRITE){
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "call", "write"));
		}
		
		if(ioType==ioType.WRITELN){
			SourceGenerator.getInstance().gen(new Quadrupla(null, null, "call", "writeln"));
		}
	}
	

}
