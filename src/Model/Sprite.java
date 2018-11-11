package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {


        private static BufferedImage spriteSheet;
        private static final int TILE_SIZE = 32;

        public static BufferedImage loadSprite(String filename) {

            BufferedImage sprite = null;

            try {
                //sprite = ImageIO.read(new File("resources/Images/" + filename ));
                sprite = ImageIO.read(Sprite.class.getResource("/resources/Images/" + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sprite;
        }

        public static BufferedImage getSprite(int xGrid, int yGrid) {

            if (spriteSheet == null) {
                spriteSheet = loadSprite("bombsprite.png");
            }

            return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

    }

