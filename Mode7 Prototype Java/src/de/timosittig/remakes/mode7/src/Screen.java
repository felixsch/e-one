package de.timosittig.remakes.mode7.src;

public class Screen {
	private int width, height;
	private int[] pixels;
	private int xOffset, yOffset;

	public Screen(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.setPixels(new int[width * height]);
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return this.width;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return this.height;
	}

	private void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public void setPixel(int x, int y, int color) {
		this.pixels[x + y * this.getWidth()] = color;
	}
	
	public int[] getPixels() {
		return this.pixels;
	}

	private void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getXOffset() {
		return this.xOffset;
	}

	private void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getYOffset() {
		return this.yOffset;
	}

	public void setOffset(int xOffset, int yOffset) {
		this.setXOffset(xOffset);
		this.setYOffset(yOffset);
	}

	public void clear() {
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				this.setPixel(x, y, 0);
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite) {
		xp -= this.getXOffset();
		yp -= this.getYOffset();

		for (int y = 0; y < sprite.getYSize(); y++) {
			int ya = y + yp;

			for (int x = 0; x < sprite.getXSize(); x++) {
				int xa = x + xp;

				if (xa < -sprite.getXSize() || xa >= this.getWidth() || ya < -sprite.getYSize() || ya >= this.getHeight()) break;

				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;

				int color = sprite.getPixels()[x + y * sprite.getXSize()];
				if (color != 0xffff00ff) this.setPixel(xa, ya, color);
			}
		}
	}
}