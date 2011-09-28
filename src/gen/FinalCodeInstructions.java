package gen;


public enum FinalCodeInstructions {
	
	INCQ ,
	DECQ,
	NEGQ,
	ADDQ,
	SUBQ,
	IMULQ,
	IDIVQ,
	IMODQ,
	MOVQ,
	LEAQ,
	PUSHQ,
	POPQ,
	EMPTY;
	
	public String toString() {
		
		if(this.equals(INCQ)){
			return "INCQ ";
		}else if(this.equals(DECQ)){
			return "DECQ ";
			
		}else if(this.equals(NEGQ)){
			return "NEGQ ";
			
		}
		else if(this.equals(ADDQ)){
			return "ADDQ ";
			
		}
		else if(this.equals(SUBQ)){
			return "SUBQ ";
			
		}
		else if(this.equals(IMULQ)){
			return "IMULQ ";
			
		}
		else if(this.equals(IDIVQ)){
			return "IDIVQ ";
			
		}
		
		else if(this.equals(IMODQ)){
			return "IMODQ ";
			
		}
		else if(this.equals(MOVQ)){
			return "MOVQ ";
			
		}
		else if(this.equals(LEAQ)){
			return "LEAQ ";
			
		}
		else if(this.equals(PUSHQ)){
			return "PUSHQ ";
			
		}
		else if(this.equals(POPQ)){
			return "POPQ ";
			
		}
		else if(this.equals(EMPTY)){
			return " ";
			
		}
		else 
			return "";
		
		
	};

}
