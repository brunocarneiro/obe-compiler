package exception;

public class LexicoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String msg;

	public LexicoException(String m) {
		super (m);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}
