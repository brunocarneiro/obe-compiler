package ast;

import gen.Quadrupla;
import gen.SourceGenerator;

import java.util.List;

import kernel.Escopos;
import kernel.FunInfo;
import kernel.TabelaSimbolo;
import static util.WriterHelper.getTabulacao;

public class ProcBody extends Node {


	private Declarations declarations;
	private List<Statement> statements;
	private String id;
	public Declarations getDeclarations() {
		return declarations;
	}
	public void setDeclarations(Declarations declarations) {
		this.declarations = declarations;
	}
	public List<Statement> getStatements() {
		return statements;
	}
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProcBody(Declarations declarations, List<Statement> statements,
			String id) {
		super();
		this.declarations = declarations;
		this.statements = statements;
		this.id = id;
	}
	public ProcBody() {
		super();
	}
	
	
	@Override
	public String toString() {

		return getTabulacao()+"ProcBody: "+id +declarations.toString() +statements.toString();
	}

	@Override
	public void gen() {
		String retorno="";
		declarations.gen();
		for(Statement stat : statements){
			
			stat.gen();
			if(stat instanceof Assignment && ((Assignment) stat).getVariable().getId().toUpperCase().equals("RESULT")){
				retorno = ((Assignment) stat).getAddress();
			}
		}
		Quadrupla q = new Quadrupla(null,"",null, "return "+retorno);
		SourceGenerator.getInstance().gen(q);
		
	}
	
}
