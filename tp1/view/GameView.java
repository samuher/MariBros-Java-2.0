package tp1.view;

import tp1.logic.Game;

public abstract class GameView implements ViewInterface{

	protected Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	
	@Override
	public void showGame() {
		// TODO Auto-generated method stub
	    //System.out.print(this.toString());
		}
	
	@Override
	public void setGame(int nLevel) {
        this.game = new Game(nLevel);
        System.out.println("set echo");
    }
/*
	public void setGame(Game game) {
	    this.game = game;
	}
	*/
	
	
	
	public String[] getPrompt() {
		return null;
	}
	
	public void showWelcome() {
		
	}
	
	public void showEndMessage() {
		
	}
	
	public void showError(String message) {
		
	}
	
	public void showMessage(String message) {
		
	}
	
}
