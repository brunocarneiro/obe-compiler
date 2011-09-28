package ast;

import static util.WriterHelper.getTabulacao;
import kernel.Escopos;
import kernel.FunInfo;
import kernel.TabelaSimbolo;
import gen.Quadrupla;
import gen.SourceGenerator;

public class ProcHeader extends Node {

	private String id;
	private FormalPars formalPars;
	private VarType varType;
	
	
	
	public ProcHeader(String id, FormalPars formalPars, VarType varType) {
		super();
		this.id=id;
		this.formalPars = formalPars;
		this.varType = varType;
	}

	public FormalPars getFormalPars() {
		return formalPars;
	}

	public void setFormalPars(FormalPars formalPars) {
		this.formalPars = formalPars;
	}

	public VarType getVarType() {
		return varType;
	}

	public void setVarType(VarType varType) {
		this.varType = varType;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	


	@Override
	public String toString() {
	
		
		return getTabulacao()+"ProcHeader: "+id +formalPars.toString() + varType.toString();
	}
	
	


}
