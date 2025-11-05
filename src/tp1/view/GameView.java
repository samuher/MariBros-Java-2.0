package tp1.view;

import tp1.logic.GameStatus;

public abstract class GameView implements ViewInterface{

	protected GameStatus game;
	
	public GameView(GameStatus game) {
		this.game = game;
	}
	
	@Override
	public void showGame() {
		// TODO Auto-generated method stub
	    //System.out.print(this.toString());
		}

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
