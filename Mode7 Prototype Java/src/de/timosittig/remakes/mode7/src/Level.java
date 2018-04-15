package de.timosittig.remakes.mode7.src;

import java.awt.image.SampleModel;

public class Level {
	private Sprite level, background;
	private int width, height;

	public Level(int width, int height, Sprite level, Sprite background) {
		this.setWidth(1024);
		this.setHeight(1024);
		this.setLevel(Sprite.ground_sprite);
		this.setBackground(Sprite.sky_sprite);
	}

	public Sprite getLevel() {
		return level;
	}

	private void setLevel(Sprite level) {
		this.level = level;
	}

	public Sprite getBackground() {
		return background;
	}

	private void setBackground(Sprite background) {
		this.background = background;
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}
	
	public void render(Camera camera, Screen screen) {
		float farX1 = camera.getX() + (float) Math.cos(camera.getA() - camera.getFieldOfViewHalf()) * camera.getFar();
		float farY1 = camera.getY() + (float) Math.cos(camera.getA() - camera.getFieldOfViewHalf()) * camera.getFar();
		
		float nearX1 = camera.getX() + (float) Math.cos(camera.getA() - camera.getFieldOfViewHalf()) * camera.getNear();
		float nearY1 = camera.getY() + (float) Math.cos(camera.getA() - camera.getFieldOfViewHalf()) * camera.getNear();

		float farX2 = camera.getX() + (float) Math.cos(camera.getA() + camera.getFieldOfViewHalf()) * camera.getFar();
		float farY2 = camera.getY() + (float) Math.cos(camera.getA() + camera.getFieldOfViewHalf()) * camera.getFar();
		
		float nearX2 = camera.getX() + (float) Math.cos(camera.getA() + camera.getFieldOfViewHalf()) * camera.getNear();
		float nearY2 = camera.getY() + (float) Math.cos(camera.getA() + camera.getFieldOfViewHalf()) * camera.getNear();
		
		for (int y = 0; y < screen.getHeight() / 2; y++) {
			float sampleDepth = (float) y / ((float) screen.getHeight() / 2.0f);
			
			float startX = (farX1 - nearX1) / (sampleDepth) + nearX1;
			float startY = (farY1 - nearY1) / (sampleDepth) + nearY1;
			float endX = (farX2 - nearX2) / (sampleDepth) + nearX2;
			float endY = (farY2 - nearY2) / (sampleDepth) + nearY2;
			
			for (int x = 0; x < screen.getWidth(); x++) {
				float sampleWidth = (float) x / (float) screen.getWidth();
				
				float sampleX = (endX - startX) * sampleWidth + startX;
				float sampleY = (endY - startY) * sampleWidth + startY;
				
				sampleX = sampleX - (sampleX % 1.0f);//sampleX % 1.0f;
				sampleY = sampleY - (sampleY % 1.0f);
				
				screen.setPixel(x, (y + (screen.getHeight() / 2)), this.getLevel().getPixels()[(int) sampleX + (int) sampleY * (int) this.getWidth()]);
			}
		}
	}
}