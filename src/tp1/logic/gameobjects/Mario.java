package tp1.logic.gameobjects;


import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameWorld;
import tp1.view.Messages;
import tp1.logic.Position;

public class Mario extends MovingObject{

	//TODO fill your code
	
	/**
	 *  Implements the automatic update	
	 */
	
	//private Position pos;
	//private Game game;
	private boolean big = true;
	private boolean right = true;
	private boolean left = false;
	private boolean stop = false;
	//private boolean avanza = false;
	private boolean cayendo = false;
	private boolean downstop = false;
	private boolean alive = true;
	private Action lastAction;
	private ActionList actlist;	
	
	public Mario(GameWorld game, Position position) {
		// TODO Auto-generated constructor stub
		//this.game = game;
		//this.pos = position;
		super(game, position);
		this.actlist = new ActionList();
		this.avanza = false;
	}
	
	public Mario() {
		this.actlist = new ActionList();
		this.NAME = Messages.MARIO_NAME;
		this.SHORTCUT = Messages.MARIO_SHORTCUT;
		this.avanza = false;
	}
	
	
	
	public GameObject parse(String objWords[], GameWorld game) {
		// comprobacion de mario 
		if (objWords[1].toLowerCase().equals(this.NAME)|| objWords[1].toLowerCase().equals(this.SHORTCUT)) {
			// pase Position and check not null
			Position p = Position.parsePosition(objWords[0]);
			if (p == null) return null;
			
			Mario m = new Mario(game, p);
			if (objWords.length >2) {
				Action act = Action.parseAction(objWords[2]);
				if(act != null) {
					m.lookDirection(act, false);
				}else {
					return null;
				}
			}
			
			// parse tamaño
			if(objWords.length > 3) {
				// small or big si existe
				switch (objWords[3].toLowerCase()) {
				case "big", "b" -> {
					m.big = true;
				}
				case "small", "s" -> {
					m.big = false;
				}
				default -> {
					return null;}
				}
			}
			return m;
		}
		return null;
	}
	
	// null / no se usa
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public void update() {
		this.cayendo = false;
			if (actlist.anyActions()) {
				while(actlist.anyActions()) {
					actionMovement(actlist.nextAction());
					game.doInteraction(this);
				}	
				this.avanza = false;
				return;
			}
			//automatico big
			if(!automaticMovement()) {
				game.doInteraction(this);
				return;
			}		
	}
	
	

	private void actionMovement(Action dir) {
		if(isNextToLateral(dir) || isNextToSolid(dir)) {
			if(dir == Action.DOWN) {
				this.stop = true;
				this.left = false;
				this.right = false;
				this.downstop = true;
				return;
			}
			lookDirection(dir, true);
		}else {
			if (lookDirection(dir, false)) return;
			marioMove(dir);
		}
		
	}
	
	
	public void marioMove(Action dir) {
		this.pos = this.pos.moved(dir);
	}
	
	public boolean lookDirection(Action dir, boolean choque) {
		
		if(choque) {
			if(dir == Action.LEFT) dir = Action.RIGHT;
			if(dir == Action.RIGHT) dir = Action.LEFT;
		}
		
		if (dir == Action.LEFT) {
			this.left = true;
			this.right = false;
			this.stop = false;
			this.downstop = false;
		} else if (dir == Action.RIGHT){
			this.right = true;
			this.left = false;
			this.stop = false;
			this.downstop = false;
		} else if (dir == Action.DOWN) {
			Position suelo = this.pos.moved(Action.DOWN);
			caida(suelo);
			return true;
		} else if (dir == Action.STOP) {
			this.stop = true;
			this.downstop = true;
		}
		return choque;
	}
	
	public boolean automaticMovement() {
		
		if (caidaUnitaria(this.pos.moved(Action.DOWN))) {
			return false;
		}
		Action dir = avanza ? Action.LEFT : Action.RIGHT;
		if(isNextToLateral(dir) || isNextToSolid(dir)) {
			avanza = !avanza;
			leftToRight(avanza);
		} else {
			movimientoUnitario(avanza, downstop, dir);
		}
		return true;
		
	}
	
	//redirect movimientoUnitario
	protected void movimientoUnitario(boolean avanza, Action dir) {
		// TODO Auto-generated method stub
		movimientoUnitario(avanza, downstop, dir);
	}
	
	public void movimientoUnitario(boolean avanza, boolean downstop, Action dir) {
		if(!this.downstop) {
			this.pos = this.pos.moved(dir);
			leftToRight(avanza);
			this.stop = false;
		}
	}
	
	
	public void leftToRight(boolean avanza) {
		this.left = avanza;
		this.right = !avanza;
	}
	
	
	public boolean isNextToLateral(Action dir) {
		Position lateral = this.pos.moved(dir);
		if(big) {
			Position lateral_arriba = this.pos.moved(Action.UP).moved(dir);
			return (lateral.isLateral(lateral) || lateral.isLateral(lateral_arriba));
		}
		
		return lateral.isLateral(lateral);
	}
	
	public boolean isNextToSolid(Action dir) {
		boolean solido =game.isSolid(this.pos.moved(dir));
		if(big) {
			solido = game.isSolid(this.pos.moved(dir).moved(Action.UP)) || solido;
		}
		if(solido && dir == Action.UP) {
			checkBox(dir);
		}
		return solido;
		
	}
	
	public void checkBox(Action dir) {
		marioMove(dir);
		if(big) {
			move(dir);
		}
		//System.out.println("Mario en: " + this.pos.toString() + " para interactuar.");
		game.doInteraction(this);
		move(oposite(dir));
		if (big) {
			move(oposite(dir));
		}
	}
	
	private Action oposite(Action dir) {
		if(dir == Action.UP) return Action.DOWN;
		if(dir == Action.DOWN) return Action.UP;
		if(dir == Action.RIGHT) return Action.LEFT;
		if(dir == Action.LEFT) return Action.RIGHT;
		else return dir;
	}
	
	
	public String getIcon() {
		// devuelve el icono, segun su direccion
		
		if (this.stop) {
			return Messages.MARIO_STOP; 
		} else if (this.right) {
			return Messages.MARIO_RIGHT;
		} else if (this.left) {
			return Messages.MARIO_LEFT;
		}
		//System.out.println("Eror direccion Mario");
		
		return Messages.MARIO_STOP;
	}
	
	public String toString() {
		// devuelve una representación de Mario, ej: Mario grande situado en la posicion (1,2), parado
		return null;
		
	}
	
	public boolean isInPosition (Position p) {
		if (this.big) {
			//System.out.println("esta big");
			Position pb = this.pos;
			pb = pb.moved(Action.UP);
			return (this.pos.equals(p)|| pb.equals(p));
		}
		return (this.pos.equals(p));
	}
	
	
	public boolean isInPosition (Goomba g) {
		if(this.big) {
			Position pb = this.pos.moved(Action.UP);
			return (g.isInPosition(pb)||g.isInPosition(this.pos));
		}
		return g.isInPosition(this.pos);
	}
	
	public void addAction(Action act) {
		this.actlist.add(act);
	}
	
	public void restringirLista() {
		//System.out.println("restringiendo lista");
		this.actlist.restringirLista();
	}
	
	public boolean caida(Position suelo) {
		//caida infinita
		/*
		 if (game.isSolid(suelo)) {
			 return false;
		 }
		 while (!game.isSolid(suelo)) {
		        if (this.pos.isVacio(suelo)) { 
		        	game.marioDead();                      
			        suelo = suelo.moved(Action.DOWN);
			        this.pos =suelo;
			        return true; 
			        		}
		        this.pos = suelo;                        // baja 1
		        suelo = suelo.moved(Action.DOWN);     // recalcula
		    }
		 */
		 while (caidaUnitaria(suelo)) {
			 if (this.pos.isVacio(this.pos)) {
		            return true;
		        }
			 suelo = this.pos.moved(Action.DOWN);
		 }
		 
		 
		 //this.pos = this.pos.moved(Action.UP);
		//this.cayendo = true;
		return false;
		
	}
	
	public boolean caidaUnitaria(Position suelo) {
		if (game.isSolid(suelo)) return false; 
		if(!game.isSolid(suelo)) {
			if (suelo.isVacio(suelo)) {
				game.marioDead();
				return true;
			}
			this.pos = suelo;
			this.cayendo = true;
			game.doInteraction(this);
			return true;
		}
		//this.cayendo = true;
		return false;
	}
	
	@Override
	public boolean receiveInteraction(ExitDoor other) {
		game.marioExited();
		return true;
		//return other.isInPosition(this);
	}
	
	@Override
	public boolean receiveInteraction(Goomba goomba) {
		//System.out.println("bbsita");
		
		if (goomba.isAlive()) {
			goomba.receiveInteraction(this);	
		}else {
			if (!cayendo) {
				if (this.big) {
					this.big = false;
				} else {
					game.marioDead();
					dead();
				}	
			}
			return true;
		}
		return true;
	}
	
	public boolean isWin() {
		return game.isMarioWins();
	}
	
	public boolean isBig() {
		return this.big;
	}
	
	
	private void goingToDead() {
		dead();
		game.marioDead();
	}
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			return item.receiveInteraction(this);
		}
		return canInteract;
	}
	
	@Override
	public boolean receiveInteraction(MushRoom mushRoom) {
		// TODO Auto-generated method stub
		if (mushRoom.isAlive()) {
			mushRoom.receiveInteraction(this);
		}else {
			if (!big) {
				this.big = true;
			}
		}
		return true;
	}
	
	@Override
	public boolean receiveInteraction(Box box) {
		if(box.isFull()) {
			box.receiveInteraction(this);
		}
		return false;
	}
	
}
