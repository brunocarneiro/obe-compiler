package ast;


import static io.EscritorArquivo.getFase;
import static util.WriterHelper.getTabulacao;

import java.util.List;


public class Module extends Node{
	
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

	public Module(Declarations declarations, List<Statement> statements,
			String id) {
		super();
		this.declarations = declarations;
		this.statements = statements;
		this.id = id;
		//SourceGenerator.getInstance().print();
	}
	
	
	@Override
	public String toString() {
		if(getFase()==2)
			return "Reduce Module: MODULE id ; declarations BEGIN statements END id." + declarations.toString() + statements.toString();
		else if(getFase()==3){return "Module";}
		else{	
			return getTabulacao()+"Module: \n" + "Declarations | Statements"+"\n";//declarations.toString() +" | "
			//+statements.toString();
		}
	}

	public Module() {
		super();
	}
	
	
	
	
}
