package ast;
import static util.WriterHelper.getTabulacao;
import kernel.Escopos;
public class Variable extends Expression{


	private String id;

	private boolean armazenada;
	
	public Variable(String id) {
		this.id = id;
		setAddress("id"+Escopos.getInstance().lookup(id));
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		
		
		return getTabulacao()+"Variable: "+id;
	}

	public boolean isArmazenada() {
		return armazenada;
	}

	public void setArmazenada(boolean armazenada) {
		this.armazenada = armazenada;
	}
	
	

}
