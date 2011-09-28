package ast;
import static util.WriterHelper.getTabulacao;
import gen.Quadrupla;
import gen.SourceGenerator;

public class ProcCall extends Expression implements Statement  {


	private String id;
	private ActualPar actualPar;
	
	private boolean armazenada;
	
	public ProcCall(String id, ActualPar actualPar) {
		super();
		this.id = id;
		this.actualPar = actualPar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ActualPar getActualPar() {
		return actualPar;
	}
	public void setActualPar(ActualPar actualPar) {
		this.actualPar = actualPar;
	}

	@Override
	public String toString() {
		
		return getTabulacao()+ "ProcCall: "+id;
	}


	@Override
	public boolean isArmazenada() {
		
		return false;
	}


	@Override
	public void gen() {
		
		SourceGenerator.getInstance().gen(new Quadrupla(null, null, "call", id));
	}
	
}
