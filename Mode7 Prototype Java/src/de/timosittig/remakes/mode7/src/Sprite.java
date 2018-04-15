package de.timosittig.remakes.mode7.src;

public class Sprite {

	public final int XSIZE, YSIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite ground_sprite = new Sprite(1024, 1024, 0, 0, false, SpriteSheet.ground_spritesheet);
	public static Sprite sky_sprite = new Sprite(1024, 1024, 0, 0, false, SpriteSheet.sky_spritesheet);
	
	public Sprite(int xSize, int ySize, int x, int y, boolean pixelOffset, SpriteSheet sheet) {
		XSIZE = xSize;
		YSIZE = ySize;
		pixels = new int[XSIZE * YSIZE];

		if (!pixelOffset) {
			this.x = x * xSize;
			this.y = y * ySize;
		} else {
			this.x = x;
			this.y = y;
		}

		this.sheet = sheet;
		load();
	}

	private void load() {
		for (int y = 0; y < YSIZE; y++) {
			for (int x = 0; x < XSIZE; x++) {
				pixels[x + y * XSIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.XSIZE];
			}
		}
	}
	
	public int getXSize() {
		return this.XSIZE;
	}
	
	public int getYSize() {
		return this.YSIZE;
	}
	
	public int[] getPixels() {
		return this.pixels;
	}
}