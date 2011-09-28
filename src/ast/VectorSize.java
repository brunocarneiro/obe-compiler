package ast;

import util.WriterHelper;

public class VectorSize extends Node {
	
	private String id;

	public VectorSize(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		

		return WriterHelper.getTabulacao()+"Vector Size: "+id;
	}
	

}
