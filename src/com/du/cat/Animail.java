package com.du.cat;

public class Animail{
	private int afraid;
	private int controller;
	
	public int getAfraid() {
		return afraid;
	}

	public void setAfraid(int afraid) {
		this.afraid = afraid;
	}

	public Animail() {
		super();
	}

	public int getController() {
		return controller;
	}

	public void setController(int controller) {
		this.controller = controller;
	}

	public boolean check(Animail animal){
		return animal.afraid==this.controller||animal.controller==this.afraid;
	}
}
