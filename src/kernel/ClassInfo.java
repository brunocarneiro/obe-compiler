package kernel;

public class ClassInfo extends Info{
	
	private int[] atribs;
	private int[] metods;
	
	
	public ClassInfo(String id, int[] atribs, int[] metods) {
		super(id);
		this.atribs = atribs;
		this.metods = metods;
	}

	
	
}
