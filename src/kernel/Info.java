package kernel;

import ast.Literal;

public class Info {
	
	protected String id;	
	protected String linha;
	protected String coluna;
	
	private Literal tipo;
	
	private int offset;
	private int width;
	
	public CategoriaInfo getCategoria(){
		
		
		if(this instanceof VarInfo)
			return CategoriaInfo.VAR;
		else if(this instanceof AtribInfo)
			return CategoriaInfo.ATRIB;
		else if(this instanceof FunInfo)
			return CategoriaInfo.METODO;
		else if(this instanceof ClassInfo)
			return CategoriaInfo.CLASSE;
		else 
			return CategoriaInfo.PARAM;
		
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
	
		//aqui significa q veio um id.
		if(obj instanceof String){
			obj.equals(this.id);
				
		}	
		
		return super.equals(obj);
	}

	public Info(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Literal getTipo() {
		return tipo;
	}

	public void setTipo(Literal tipo) {
		this.tipo = tipo;
	}

	public double getOffset() {
		return offset;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	

	
	
	
	
}
