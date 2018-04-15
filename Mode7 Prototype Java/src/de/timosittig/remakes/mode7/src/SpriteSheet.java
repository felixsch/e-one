package de.timosittig.remakes.mode7.src;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int XSIZE, YSIZE;
	public int[] pixels;

	public static SpriteSheet ground_spritesheet = new SpriteSheet("/spritesheet_mk_ground.png", 1024, 1024);
	public static SpriteSheet sky_spritesheet = new SpriteSheet("/spritesheet_mk_ground.png", 1024, 1024);

	public SpriteSheet(String path, int xSize, int ySize) {
		this.path = path;
		XSIZE = xSize;
		YSIZE = ySize;
		pixels = new int[XSIZE * YSIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));

			int w = image.getWidth();
			int h = image.getHeight();

			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
