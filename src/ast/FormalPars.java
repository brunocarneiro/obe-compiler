package ast;

import java.util.List;
import static util.WriterHelper.getTabulacao;

public class FormalPars extends Node {

	private List<FpSection> fpSections;

	public List<FpSection> getFpSections() {
		return fpSections;
	}

	public void setFpSections(List<FpSection> fpSections) {
		this.fpSections = fpSections;
	}

	public FormalPars(List<FpSection> fpSections) {
		super();
		this.fpSections = fpSections;
	}
	
	
	@Override
	public String toString() {
		

		return getTabulacao()+"FormalPars: " +fpSections.toString();
		

	}
	
	
	

}
