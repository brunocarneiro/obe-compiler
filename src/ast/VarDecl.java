package ast;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class VarDecl extends Node {

	private List<String> ids;
	private VarType varType;
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public VarType getVarType() {
		return varType;
	}
	public void setVarType(VarType varType) {
		this.varType = varType;
	}
	public VarDecl(List<String> ids, VarType varType) {
		super();
		this.ids = ids;
		this.varType = varType;
		
	}
	
	@Override
	public String toString() {
		
				
		return getTabulacao()+"VarDecl: " +ids.toString() + varType.toString();
	}
	
	@Override
	public void gen() {
		
		varType.gen();
	}
	

	
	
}
