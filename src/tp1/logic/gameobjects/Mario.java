package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.view.Messages;
import tp1.logic.Position;

public class Mario {

	//TODO fill your code
	
	/**
	 *  Implements the automatic update	
	 */
	
	private Position pos;
	private boolean big = true;
	private boolean right = true;
	private boolean left = false;
	private boolean stop = false;
	private boolean avanza = false;
	private boolean cayendo = false;
	private boolean downstop = false;
	private Game game;
	
	private ActionList actlist;
	public Mario(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
		this.actlist = new ActionList();
		
	}

	public void update() {
		this.cayendo = false;
		if (!big) {
			Action dir;
			//movmiento no automatico
			if (actlist.anyActions()) {
				while(actlist.anyActions()) {
					dir = actlist.nextAction();
					Position lateral = this.pos.moved(dir);
					
					if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
						Position suelo = this.pos.moved(Action.DOWN);
						if (dir == Action.DOWN) {
							this.stop = true;
							this.left = false;
							this.right = false;
							//System.out.println("se agacha");
							//this.pos = suelo;
							this.downstop = true;
							continue;
						}
						if (dir == Action.LEFT) {
							this.right = true;
							this.stop = false;
							this.left = false;
							this.avanza = false;
							continue;
						}
						if (dir == Action.RIGHT) {
							this.right = false;
							this.stop = false;
							this.left = true;
							this.avanza = false;
							continue;
						}
						
						continue;
					}
					//this.pos = this.pos.moved(dir);
					if (dir == Action.LEFT) {
						this.left = true;
						this.stop = false;
						this.downstop = true;
					} else if (dir == Action.RIGHT){
						this.right = true;
						this.stop = false;
						this.downstop = true;
					} else 
						if (dir == Action.DOWN) {
							Position suelo = this.pos.moved(Action.DOWN);
							caida(suelo);
							//this.downstop = true;
							//this.stop = true;
							continue;
						//}
						} else if (dir == Action.STOP) {
							this.stop = true;
							this.downstop = true;
						}
						
					
					this.pos = this.pos.moved(dir);
				}
				game.doInteractionsFrom(this);
				this.avanza = false;
				return;
			}
			
			Position suelo = this.pos.moved(Action.DOWN);
			if (caidaUnitaria(suelo)) return;
			dir = avanza ? Action.LEFT : Action.RIGHT;
			Position lateral = this.pos.moved(dir);
			if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
				avanza = !avanza;
				//dir = avanza ? Action.LEFT : Action.RIGHT;
				//this.pos = this.pos.moved(dir);
			} else {
				if (!this.downstop) {
					this.pos = this.pos.moved(dir);
				}
			}
			if (avanza && !this.downstop) {
				this.left = true;
				this.right = false;
				this.stop = false;
			} else if(!this.downstop) {
				this.left = false;
				this.right = true;
				this.stop = false;
			}
			game.doInteractionsFrom(this);
		
			
		} else { //big
			Action dir;
			if (actlist.anyActions()) {
				while(actlist.anyActions()) {
					dir = actlist.nextAction();
					// lateral position (abajo)
					Position lateral = this.pos.moved(dir);
					Position lateral_arriba = this.pos.moved(Action.UP).moved(dir);
					if (lateral_arriba.isLateral(lateral_arriba) || game.isSolid(lateral_arriba)) { continue; }
					if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
						Position suelo = this.pos.moved(Action.DOWN);
						if (dir == Action.DOWN) {
							this.stop = true;
							this.left = false;
							this.right = false;
							//System.out.println("se agacha");
							//this.pos = suelo;
							this.downstop = true;
							continue;
						}
						if (dir == Action.LEFT) {
							this.right = true;
							this.stop = false;
							this.left = false;
							this.avanza = false;
							continue;
						}
						if (dir == Action.RIGHT) {
							this.right = false;
							this.stop = false;
							this.left = true;
							this.avanza = false;
							continue;
						}
						
						continue;
					}
					//this.pos = this.pos.moved(dir);
					if (dir == Action.LEFT) {
						this.left = true;
						this.stop = false;
						this.downstop = false;
					} else if (dir == Action.RIGHT){
						this.right = true;
						this.stop = false;
						this.downstop = false;
					} else if (dir == Action.DOWN) {
						//this.stop = true; solo es true cuando ya tiene suelo debajo
						Position suelo = this.pos.moved(Action.DOWN);
							caida(suelo);
							
							continue;
						//}
					} else if (dir == Action.STOP) {
						this.stop = true;
						this.downstop = true;
					}
					
					this.pos = this.pos.moved(dir);
				}
				game.doInteractionsFrom(this);
				this.avanza = false;
				return;
			}
			
			//automatico big
			Position suelo = this.pos.moved(Action.DOWN);
			dir = avanza ? Action.LEFT : Action.RIGHT;
			Position lateral = this.pos.moved(dir);
			if (caidaUnitaria(suelo)) return;
			if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
				avanza = !avanza;
				//dir = avanza ? Action.LEFT : Action.RIGHT;
				//this.pos = this.pos.moved(dir);
			} else {
				if (!this.downstop) {
					this.pos = this.pos.moved(dir);
				}
				
			}
			if (avanza && !this.downstop) {
				this.left = true;
				this.right = false;
				this.stop = false;
			} else if(!this.downstop){
				this.left = false;
				this.right = true;
				this.stop = false;
			}
			//if (caidaUnitaria(suelo)) return;
			/*
			suelo = this.pos.moved(Action.DOWN);
			//caida(suelo);
			if(!game.isSolid(suelo)) {
				this.pos = suelo;
			}
			*/
			game.doInteractionsFrom(this);
		}
	
		
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
				game.marioDead();
				return true;
			}
			this.pos = suelo;
			this.cayendo = true;
			game.doInteractionsFrom(this);
			return true;
		}
		//this.cayendo = true;
		return false;
	}
	
	public boolean interactWith(ExitDoor other) {
		return other.isInPosition(this);
	}
	
	public boolean interactWith(Goomba goomba) {
		
		if(isInPosition(goomba)) {
			game.addPoints(100);
			goomba.receiveInteraction(this);
			if (!cayendo) {
				if (this.big) {
					this.big = false;
				} else {
					game.marioDead();
				}	
			}
			return true;
		}
		
		return false;
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
	
}
