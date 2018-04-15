package de.timosittig.remakes.mode7.src;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 320, height = 240;
	public static float scale = 4.0f;
	public static String title = "MODE7 Remake";

	private Thread thread;
	private boolean running = false;
	private JFrame frame;

	private Screen screen;
	private Keyboard keyboard;

	private Level level;
	private Camera camera;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension((int) (width * scale), (int) (height * scale));
		setPreferredSize(size);

		keyboard = new Keyboard();

		screen = new Screen(width, height);
		frame = new JFrame();

		level = new Level(1024, 1024, Sprite.ground_sprite, Sprite.sky_sprite);
		camera = new Camera(512.0f, 512.0f, 0.1f, 0.05f, 0.03f, 3.14159f / 4.0f);

		addKeyListener(keyboard);
	}

	public synchronized void start() {
		running = true;

		thread = new Thread(this, "Seasons");
		thread.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;

		int frames = 0, updates = 0;

		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("Updates: " + updates + ", Frames: " + frames);
				System.out.println("Far: " + camera.getFar() + ", Near: " + camera.getNear());
				System.out.println("Field of view: " + camera.getFieldOfViewHalf());

				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		keyboard.update();
		camera.update();

		int movement = 0;
		if (keyboard.up && !keyboard.down) {
			movement += 1;
		} else if (keyboard.down && !keyboard.up) {
			movement += 4;
		}

		if (keyboard.right && !keyboard.left) {
			movement += 2;
		} else if (keyboard.left && !keyboard.right) {
			movement += 8;
		}

		if (movement != 0) camera.move(movement);

		if (keyboard.q && !keyboard.e) {
			camera.setNear(camera.getNear() + 0.1f);
		} else if (keyboard.e && !keyboard.q) {
			camera.setNear(camera.getNear() - 0.1f);
		}

		if (keyboard.y && !keyboard.c) {
			camera.setFar(camera.getFar() + 0.1f);
		} else if (keyboard.c && !keyboard.y) {
			camera.setFar(camera.getFar() - 0.1f);
		}

		if (keyboard.i && !keyboard.o) {
			camera.setFieldOfViewHalf(camera.getFieldOfViewHalf() + 0.1f);
		} else if (keyboard.o && !keyboard.i) {
			camera.setFieldOfViewHalf(camera.getFieldOfViewHalf() - 0.1f);
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		level.render(camera, screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}