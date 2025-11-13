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
	private boolean avanza = false;
	private boolean cayendo = false;
	private boolean downstop = false;
	private boolean alive = true;
	
	
	private ActionList actlist;
	public Mario(GameWorld game, Position position) {
		// TODO Auto-generated constructor stub
		//this.game = game;
		//this.pos = position;
		super(game, position);
		this.actlist = new ActionList();
	}
	
	public Mario() {
		
	}
	
	
	public GameObject parse(String objWords[], GameWorld game) {
		
		// comprobacion de mario 
		if (objWords[2].toLowerCase() == this.NAME || objWords[2].toLowerCase() == this.SHORTCUT) {
			Position p = new Position(Integer.parseInt(objWords[0]), Integer.parseInt(objWords[1]));
			
			if (objWords.length > 3) {
				// direccion
				switch (objWords[3]) {
				case "right", "r" -> {
					lookDirection(Action.RIGHT, false);
				}
				case "left", "l" -> {
					lookDirection(Action.LEFT, false);
				}
				case "stop", "s" -> {
					lookDirection(Action.STOP, false);
				}
				default -> {return null;}
			}
			
			}
			
			if(objWords.length > 4) {
				// small or big
				switch (objWords[3]) {
				case "big", "b" -> {
					this.big = true;
				}
				case "small", "s" -> {
					this.big = false;
				}
				default -> {return null;}
				}
			}
			
		}
		return null;
	}
	
	

	public void update() {
		this.cayendo = false;
			if (actlist.anyActions()) {
				while(actlist.anyActions()) {
					actionMovement(actlist.nextAction());
				}	
				//game.doInteractionsFrom(this);
				game.doInteraction(this);
				this.avanza = false;
				return;
			}
			
			//automatico big
			if(!automaticMovement()) {
				return;
			}
			
			//game.doInteractionsFrom(this);
			game.doInteraction(this);
	
	
		
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
		if(big) {
			return game.isSolid(this.pos.moved(dir).moved(Action.UP)) || game.isSolid(this.pos.moved(dir));
		}
		return game.isSolid(this.pos.moved(dir));
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
		 if (game.isSolid(suelo)) return false;
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
		 //this.pos = this.pos.moved(Action.UP);
		 //game.doInteractionsFrom(this);
		this.cayendo = true;
		return false;
		
	}
	
	public boolean caidaUnitaria(Position suelo) {
		if (game.isSolid(suelo)) return false; 
		if(!game.isSolid(suelo)) {
			if (suelo.isVacio(suelo)) {
				//System.out.println("vaciooooo");
				//game.marioDead();
				game.marioDead();
				return true;
			}
			this.pos = suelo;
			this.cayendo = true;
			//game.doInteractionsFrom(this);
			game.doInteraction(this);
			return true;
		}
		//this.cayendo = true;
		return false;
	}
	
	@Override
	public boolean receiveInteraction(ExitDoor other) {
		wins();
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
				}	
			}
			return true;
		}
		return true;
	}
	
	public void wins() {
		game.marioExited();
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
			item.receiveInteraction(this);
		}
		return canInteract;
	}
	
}
