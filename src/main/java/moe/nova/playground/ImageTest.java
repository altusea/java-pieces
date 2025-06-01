package moe.nova.playground;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    static void main(String[] args) throws IOException {
        File imageFile = new File("40b3a1.jpeg");
        BufferedImage image = ImageIO.read(imageFile);

        int width = image.getWidth();
        int height = image.getHeight();

        IO.println("Image Width: " + width);
        IO.println("Image Height: " + height);
    }
}
