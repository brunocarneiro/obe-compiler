package ast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Node {
	
	
	/**
	 * Descobre os filhos de um elemento na arvore.
	 * Feito através de reflexao. Pega os atributos de uma classe e depois pega os valoress dele.
	 * @return
	 */
	public List<Object> getChildren(){

		List<Object> nodes =  new ArrayList<Object>();
		Field [] fields = this.getClass().getDeclaredFields();
		Object o;
		for(Field f : fields){			
			try {
				f.setAccessible(true);
				 o = f.get(this);
				nodes.add(o);
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return nodes;		
	}
	
	
	public void gen(){
		
		List<Object> filhos = getChildren();
		
		for(Object o: filhos){
			
			if(o instanceof List){
				List lista = (List)o;
				for(int cont=0; cont< lista.size(); cont++){
					if(lista.get(cont) instanceof Node){
						((Node)lista.get(cont)).gen();
					}
				}
					
			}
			
			else if(o instanceof Node){
				((Node) o).gen();
			}
			
			else{
				//System.out.println("nao gerou nada");
			}
			
			
		}
		
	}


}
