package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class Else extends Node{
	
	private List<Statement> statements;
	private String addr=SourceGenerator.getRotulo();
	private String addr2=SourceGenerator.getRotulo();

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public Else(List<Statement> statements) {
		super();
		this.statements = statements;
	}

	public Else() {
		super();
	}
	
	@Override
	public String toString() {
		
		if(statements!=null)
			return getTabulacao()+"Else: "  +statements.toString();
		
		
		return "";
	}
	
	@Override
	public void gen() {
		if(statements!=null){
			Quadrupla q = new Quadrupla(null, null, "", addr);
			SourceGenerator.getInstance().gen(q);
			if(statements!=null){
				for(Statement s : statements)
					s.gen();
			}
		}
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	

}
