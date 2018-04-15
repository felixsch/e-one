package de.timosittig.remakes.mode7.src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space, ctrl, q, e, i, o, y, c;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		ctrl = keys[KeyEvent.VK_CONTROL];
		q = keys[KeyEvent.VK_Q];
		e = keys[KeyEvent.VK_E];
		i = keys[KeyEvent.VK_I];
		o = keys[KeyEvent.VK_O];
		y = keys[KeyEvent.VK_Y];
		c = keys[KeyEvent.VK_C];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}
}