package ast;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class FpSection extends Node {

	private List<String> idList;
	private VarType varType;
	
	public FpSection(List<String> idList, VarType varType) {
		super();
		this.idList = idList;
		this.varType = varType;
	}

	@Override
	public String toString() {
		
		
		return getTabulacao()+"FpSection: " +idList.toString()+varType.toString();
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public VarType getVarType() {
		return varType;
	}

	public void setVarType(VarType varType) {
		this.varType = varType;
	}
	
	
	
}
