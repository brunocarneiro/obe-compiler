package ast;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class Declarations extends Node {

	private ConstDecl constDecl;
	private List<VarDecl> varDecl;
	private List<ProcDecl> procDecl;
	
	
	
	
	public Declarations(ConstDecl constDecl, List<VarDecl> varDecl,
			List<ProcDecl> procDecl) {
		super();
		this.constDecl = constDecl;
		this.varDecl = varDecl;
		this.procDecl = procDecl;
	}
	
	
	

	public ConstDecl getConstDecl() {
		return constDecl;
	}




	public void setConstDecl(ConstDecl constDecl) {
		this.constDecl = constDecl;
	}




	public List<VarDecl> getVarDecl() {
		return varDecl;
	}




	public void setVarDecl(List<VarDecl> varDecl) {
		this.varDecl = varDecl;
	}




	public List<ProcDecl> getProcDecl() {
		return procDecl;
	}




	public void setProcDecl(List<ProcDecl> procDecl) {
		this.procDecl = procDecl;
	}




	@Override
	public String toString() {
		
		
		
		return "Declarations";
	}

	protected String criaStringRetorno(){
		
		String retorno="";
		if(constDecl!=null)
			retorno="ConstDecl |";
		if(!varDecl.isEmpty())
			retorno=retorno+"VarDecl |";
		if(!procDecl.isEmpty())
			retorno=retorno+"ProcDecl |";
		return retorno;
	}
	
	@Override
	public void gen() {
		
		constDecl.gen();
		for(VarDecl v: varDecl)
			v.gen();
		for(ProcDecl pd : procDecl)
			pd.gen();
		
	}
	
}
