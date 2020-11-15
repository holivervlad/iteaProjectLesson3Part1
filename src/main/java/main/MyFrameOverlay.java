package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyFrameOverlay {
    private String commonImagePath = "/Users/vladyslavholiver/Documents/itea/src/main/resources";
    BufferedImage image;
    JButton button;

    public JButton addImageToLayOut(String imageName) {
        try {
            String imagePath = commonImagePath + "/" + imageName;
            image = ImageIO.read(new File(imagePath));
            button = new JButton(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(String.format("'%s' image is not found", imageName));
        }
        return button;
    }


}
