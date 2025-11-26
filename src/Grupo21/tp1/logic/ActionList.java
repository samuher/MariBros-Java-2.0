package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
	
	private List<Action> list;
	
	public ActionList(){
		this.list = new ArrayList<>();
		
	}
	
	public void add(Action act) {
		//Aplicar condiciones
		if (act != null) this.list.add(act);
		restringir();
	}
	
//	public void restringirLista() {
//		//System.out.println("restringiendo lista");
//		this.list = restringir();
//	}
	
	public List<Action> restringir() {
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		int lr = 0;
		int ud = 0;
		
		List<Action> list_aux = new ArrayList<>();
		for (int i = 0; i < this.list.size(); i++) {
            Action a = this.list.get(i);

            // 1) CONSERVA STOP (no cuenta para límites)
            if (a == Action.STOP) {
                list_aux.add(Action.STOP);
                continue;
            }

            // 2) HORIZONTAL: primera dirección manda, máx 4
            if (a == Action.LEFT) {
                if (!right) {                 // si apareció RIGHT antes, ignora LEFT
                    left = true;              
                    if (lr < 4) {
                        list_aux.add(Action.LEFT);
                        lr++;
                    }
                }
                continue; // ← evita seguir comprobando UP/DOWN inútilmente
            }

            if (a == Action.RIGHT) {
                if (!left) {                  // si apareció LEFT antes, ignora RIGHT
                    right = true;             
                    if (lr < 4) {
                        list_aux.add(Action.RIGHT);
                        lr++;
                    }
                }
                continue;
            }

            // 3) VERTICAL: primera dirección manda, máx 4
            if (a == Action.UP) {
                if (!down) {                  // si apareció DOWN antes, ignora UP
                    up = true;
                    if (ud < 4) {
                        list_aux.add(Action.UP);
                        ud++;
                    }
                }
                continue;
            }

            if (a == Action.DOWN) {
                if (!up) {                    // si apareció UP antes, ignora DOWN
                    down = true;
                    if (ud < 4) {
                        list_aux.add(Action.DOWN);
                        ud++;
                    }
                }
                continue;
            }
        }

        return list_aux;
	}
	
	public Action nextAction() {
		return this.list.isEmpty() ? null : this.list.remove(0);
	}
	
	public boolean anyActions() {
		return !this.list.isEmpty();
	}
	
	
}
