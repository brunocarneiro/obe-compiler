package kernel;

import ast.Literal;

public class ParamInfo extends Info {
	
	
	

	public ParamInfo(String id, Literal tipo) {
		super(id);
		setTipo(tipo);
	}
	
	public ParamInfo(String id, Literal tipo, int offset, int width) {
		super(id);
		setTipo(tipo);
	
		setOffset(offset);
		setWidth(width);
	}
	

}
