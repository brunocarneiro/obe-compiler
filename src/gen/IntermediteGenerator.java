package gen;

import java.util.Stack;

import kernel.Info;
import kernel.TabelaSimbolo;
import kernel.VarInfo;

public class IntermediteGenerator {
	
	private Stack<Quadrupla> variaveisGlobais = new Stack<Quadrupla>();
	boolean globalVarsPhase=true;
	
	public void gen(Quadrupla q){
		if(variaveisGlobais.contains(q)&&!globalVarsPhase){
			return;
		}
		if(q.getRes()!=null && q.getRes().startsWith("t")){
			
			System.out.println(""+q.getRes()+" = "+q.getArg1()+q.getOp()+q.getArg2());
		}
		else if(q.getRes()!=null && q.getRes().startsWith("L")&&q.getArg1().equals("") && q.getArg2().equals("")){
			System.out.print(q.getRes()+": ");
			//System.out.print(q.getRes()+": ");
			
		}
		else if(q.getArg2()!=null && q.getArg2().equals("GOTO")){
			System.out.println(" GOTO "+q.getRes());
		}
		else if(q.getRes()!=null && q.getRes().startsWith("L")){
			System.out.println("IF "+q.getArg1()+q.getOp()+q.getArg2()+" GOTO "+q.getRes());
			//System.out.print(q.getRes()+": ");
			
		}
		else if(q.getRes()!=null && q.getArg1()!=null&&!q.getArg1().equals("")){
			System.out.println(q.getRes()+":"+q.getArg1());
		}
		else if(q.getRes()!=null && q.getArg2()!=null &&q.getArg2().equals("call")){
			System.out.println(q.getArg2() + " "+ q.getRes());
		}
		
		
		
		else if (q.getRes()!=null){
			System.out.println(q.getRes()+":");
		}
		
	}

	public boolean empilhaVariaveisGlobais(Quadrupla q) {
		
		if(isGlobalVar(q)){
			variaveisGlobais.push(q);
			return false;
		}

		return true;
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

	public void imprimeVariaveisGlobais() {
		
		for(int i=variaveisGlobais.size()-1; i>=0;i--){
			Quadrupla q = variaveisGlobais.get(i);
			gen(q);
		}
		globalVarsPhase=false;
	}
}
