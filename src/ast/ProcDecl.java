package ast;
import static util.WriterHelper.getTabulacao;
import gen.FinalCodeGenerator;
import gen.Quadrupla;
import gen.SourceGenerator;
import io.LeitorArquivo;

public class ProcDecl extends Node {

	private ProcHeader procHeader;
	private ProcBody procBody;
	private String rotulo;
	
	public ProcDecl(ProcHeader procHeader, ProcBody procBody) {
		super();
		this.procHeader = procHeader;
		this.procBody = procBody;
		
		if(!procHeader.getId().equals(procBody.getId())){
			System.err.println("Erro sintático, o nome da função deve ser o mesmo nome na finalizacao dela. END. Linha: "+LeitorArquivo.getContLinha()+", Coluna: "+LeitorArquivo.getContCol());
		}
		rotulo= procHeader.getId();
	}

	public ProcHeader getProcHeader() {
		return procHeader;
	}

	public void setProcHeader(ProcHeader procHeader) {
		this.procHeader = procHeader;
	}

	public ProcBody getProcBody() {
		return procBody;
	}

	public void setProcBody(ProcBody procBody) {
		this.procBody = procBody;
	}
	
	@Override
	public String toString() {
		
			
		return getTabulacao()+ "ProcDecl "+procHeader.getId();
	}
	

	

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@Override
	public void gen() {
		
		Quadrupla q = new Quadrupla(null, null, "", rotulo);
		SourceGenerator.getInstance().gen(q);
		procHeader.gen();
		procBody.gen();
		
		
	}	
}
