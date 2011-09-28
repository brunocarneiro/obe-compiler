package gen;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

import kernel.Info;
import kernel.TabelaSimbolo;
import kernel.VarInfo;

public class FinalCodeGenerator {
	
	public static final int SIZE = 8;
	public static final int ALIGN = 3;
	public static boolean fimGlobalPhase=false;
	private HashMap<String, String> registradores = new HashMap<String, String>();
	private HashMap<String, Integer> temporariosReutilizados = new HashMap<String, Integer>();
	private Stack<String> registradoresDiponiveis = inicializaRegistradoresDisponiveis();
	
	public void gen(Quadrupla q) {

		String arg1=q.getArg1();
		String arg2=q.getArg2();

		//contem apenas um argumento e nao contem operador
		if(q.getRes()!=null && (q.getArg1()!=null && !arg1.equals("")) && (q.getArg2()==null || arg2.equals(""))&& ( q.getOp()==null ||q.getOp().equals("") )){
			
			//se o argumento nao esta em nenhum registrador, realizar um mov e coloca-lo no reg.
			if(!registradores.containsKey(q.getArg1())){

				String registrador = getRegistradorDisponivel();
				System.out.println("movq "+q.getArg1()+", "+registrador);
				registradores.put(q.getRes(), registrador);
				
			}
			//vai pegar o registrador e reutilizar
			if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)==0){
				String registradorArg1 =registradores.get(arg1), registrador=getRegistradorDisponivel();
				System.out.println("movq "+registradorArg1+", "+registrador);
				registradores.put(q.getRes(), registrador);
			}
			//como vai ser reutilizado o temporario, criar um novo temporario
			else if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)>0){
				String registrador =registradores.get(arg1);
				String registradorDisponivel = getRegistradorDisponivel();
				System.out.println("movq "+registrador+", "+registradorDisponivel);
				registradores.put(registrador, registradorDisponivel);
				System.out.println(getInstruction(q.getOp(), arg2) + arg2+", "+registradorDisponivel);
			}
		}
		
		//contem apenas um argumento e contem operador
		else if(q.getRes()!=null && q.getArg1()!=null && q.getArg2()==null &&  q.getOp()!=null){
			
			//se o argumento nao esta em nenhum registrador, realizar um mov e coloca-lo no reg.
			if(!registradores.containsKey(q.getArg1())){
				String registrador = getRegistradorDisponivel();
				System.out.println("movq "+q.getArg1()+", "+registrador);
				registradores.put(q.getArg1(), registrador);
				
			}
			
			//vai pegar o registrador e reutilizar
			if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)==0){
				String registrador =registradores.get(arg1);
				System.out.println(getInstruction(q.getOp(), arg2) + arg2+", "+registrador);
				temporariosReutilizados.put(arg1, temporariosReutilizados.get(arg1)-1);
			}
			//como vai ser reutilizado o temporario, criar um novo temporario
			else if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)>0){
				String registrador =registradores.get(arg1);
				String registradorDisponivel = getRegistradorDisponivel();
				System.out.println("movq "+registrador+", "+registradorDisponivel);
				registradores.put(registrador, registradorDisponivel);
				System.out.println(getInstruction(q.getOp(), arg2) + arg2+", "+registradorDisponivel);
			}
			
		}
		else if(q.getRes()!=null && q.getArg1()!=null && q.getArg2()!=null &&  q.getOp()!=null && !q.getOp().equals("")){
			
			 if(q.getRes()!=null && q.getRes().startsWith("L")){
				System.out.println("cmpq "+q.getArg1()+","+q.getArg2()+" "+resolveJump(q.getOp()) +q.getRes());
				//System.out.print(q.getRes()+": ");

			}
			else{
			
				//se o argumento nao esta em nenhum registrador, realizar um mov e coloca-lo no reg.
				if(!registradores.containsKey(q.getArg1())){
					String registrador =getRegistradorDisponivel();
					System.out.println("movq "+q.getArg1()+", "+registrador);
					registradores.put(q.getArg1(), registrador);
					
				}
				
				//vai pegar o registrador e reutilizar
				if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)==0){
					String registrador =registradores.get(arg1);
					System.out.println(getInstruction(q.getOp(), arg2) + arg2+", "+registrador);
					temporariosReutilizados.put(arg1, temporariosReutilizados.get(arg1)-1);
				}
				//como vai ser reutilizado o temporario, criar um novo temporario
				else if(registradores.containsKey(arg1) && temporariosReutilizados.get(arg1)!=null && temporariosReutilizados.get(arg1)>0){
					String registrador =registradores.get(arg1);
					String registradorDisponivel = getRegistradorDisponivel();
					System.out.println("movq "+registrador+", "+registradorDisponivel);
					registradores.put(registrador, registradorDisponivel);
					System.out.println(getInstruction(q.getOp(), arg2) + arg2+", "+registradorDisponivel);
				}
			
			}
			
			
		}
		else if(q.getRes()!=null&&q.getArg1().equals("") && q.getArg2().equals("")){
			if(q.getRes().startsWith("L") || q.getRes().contains("return"))			
				System.out.print(q.getRes()+": ");
			else{
				System.out.println(".globl _"+q.getRes());
				System.out.println("_"+q.getRes()+": ");
			}
		}
		else if(q.getArg2()!=null && q.getArg2().equals("GOTO")){
			System.out.println(" j "+q.getRes());
		}

		else if(q.getRes()!=null && q.getArg1()!=null && q.getArg2()!=null && q.getOp()!=null){
			
			
			
		}
				
		else if (q.getRes()!=null){
			System.out.println(q.getRes()+":");
		}
		

		
	}

		

	private Stack<String> inicializaRegistradoresDisponiveis() {
		
		Stack<String> pilha = new Stack<String>();
		pilha.push("%r15");
		pilha.push("%r14");
		pilha.push("%r13");
		pilha.push("%r12");
		pilha.push("%r11");
		pilha.push("%r10");
		pilha.push("%r9");
		pilha.push("%rdi");
		pilha.push("%rsi");
		pilha.push("%rdx");
		pilha.push("%rcx");
		pilha.push("%rbx");
		pilha.push("%rax");
		return pilha;

	}



	private String resolveJump(String op) {
		
		
		if(op.equals(">")){return "jg";}
		else if(op.equals("<")){return "jl";}
		else if(op.equals(">=")){return "jge";}
		else if(op.equals("=<")){return "jle";}
		else if(op.equals("#")){return "jne";}
		else if(op.equals("=")){return "je";}
		else return "";
		
	}

	
	protected VarInfo findVarInfo(Quadrupla q){
		
			try {
				int pos = Integer.parseInt(q.getRes().substring(2));
				Info info = TabelaSimbolo.getInfo(pos);
				if(info instanceof VarInfo){
					return (VarInfo)info;
				}
			} catch (Exception e) {
				return null;
			}
		
		return null;

	}
	
	protected boolean isGlobalVar(Quadrupla q){
		if(q.getRes().startsWith("id")){
			VarInfo info = findVarInfo(q);
			if(info!=null && info.isGlobal())
				return true;
			else
				return false;
		}
		return false;
	}
	
	protected FinalCodeInstructions getInstruction(String op, String arg2){
		
		if(op.equals("+")&&arg2.equals("1")){
			return FinalCodeInstructions.INCQ;
		}else if(op.equals("+")){
			return FinalCodeInstructions.ADDQ;
		}
		else if(op.equals("*")){
			return FinalCodeInstructions.IMULQ;
		}
		else if(op.equals("/")){
			return FinalCodeInstructions.IDIVQ;
		}
		else if(op.equals("-")&&arg2.equals("1")){
			return FinalCodeInstructions.DECQ;
		}
		else if(op.equals("-")){
			return FinalCodeInstructions.SUBQ;
		}
		else if(op.equals(">")){}
		else if(op.equals("<")){}
		else if(op.equals(">=")){}
		else if(op.equals("=<")){}
		else if(op.equals("|")){
			return FinalCodeInstructions.IMODQ;
		}
		return FinalCodeInstructions.EMPTY;
	}



	public void inserirReusoVariaveis(String id) {
		Integer cont = temporariosReutilizados.get(id);
		if(cont==null){
			temporariosReutilizados.put(id, 0);
		}
		else
			temporariosReutilizados.put(id, ++cont);
		
	}



	public void imprimeVariaveisGlobais(Quadrupla q) {
		//se for variavel global
		if(isGlobalVar(q)){
			System.out.println(".comm "+q.getRes()+ ",	"+SIZE+", "+ALIGN );
			fimGlobalPhase=true;
			return;
		}
		else if (fimGlobalPhase){
			System.out.println(".text");
			fimGlobalPhase=false;
		}

		
	}
	
	
	public String getRegistradorDisponivel(){
		String reg ;
		try{
			reg = registradoresDiponiveis.pop();
		}catch (EmptyStackException e) {
			reg = descobreRegistradorDiponivel();
		}
		return reg;
	}


	/**
	 * Heuristica para obter registrador, quando nao existem mais ociosos.
	 * Procura por algum registrador que possui valor morto, se encontrar, devolve ele.
	 * Senão, lança erro de que não foi possível alocar mais registradores.
	 * @return
	 */
	protected String descobreRegistradorDiponivel() {
		
		for (String chave : temporariosReutilizados.keySet()){
			
			Integer timesLefting=temporariosReutilizados.get(chave);
			if(timesLefting!=null && timesLefting<=0 && registradores.get(chave)!=null){
				String registrador=registradores.get(chave);
				registradores.remove(chave);
				return registrador;
			}
			
		}
		throw new Error("Aiiiiii...Nao existem mais registradores disponiveis! ");
	}
	
	
	

}
