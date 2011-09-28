package kernel.semantico;

public abstract class Type {
	
	
	
	private boolean correct = true;
	
	private boolean array ;

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}
	
	

}
