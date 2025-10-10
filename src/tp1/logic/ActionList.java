package tp1.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActionList {
	
	private List<Action> list;
	
	public ActionList(){
		this.list = new ArrayList<>();
		
	}
	
	public void add(Action act) {
		//Aplicar condiciones
		this.list.add(act);
		restringir();
	}
	
	public void restringir() {
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		List<Action> list_aux = new ArrayList<>();
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i) == Action.LEFT) {
				if (!right) {
					left = true;
					if (Collections.frequency(list_aux, Action.LEFT) <4 ) {
						list_aux.add(Action.LEFT);
						continue;
					}
				} 
				continue;
			} 
			if (this.list.get(i) == Action.RIGHT) {
				if (!left) {
					right = true;
					if (Collections.frequency(list_aux, Action.RIGHT) <4 ) {
						list_aux.add(Action.RIGHT);
						continue;
					}
				} 
				
			}
			if (this.list.get(i) == Action.UP) {
				if (!down) {
					up = true;
					if (Collections.frequency(list_aux, Action.UP) <4 ) {
						list_aux.add(Action.UP);
						continue;
					}
				} 
				
			}
			
			if (this.list.get(i) == Action.DOWN) {
				if (!up) {
					down = true;
					if (Collections.frequency(list_aux, Action.DOWN) <4 ) {
						list_aux.add(Action.DOWN);
						continue;
					}
				} 
				
			}
			

		}
		this.list = list_aux;
	}
	
	public Action nextAction() {
		return this.list.remove(0);
	}
	
	public boolean anyActions() {
		return (this.list.size() > 0);
	}
	
}
