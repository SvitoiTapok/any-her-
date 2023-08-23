import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

public class Grass {
    private final int height = 600;
    private final int width = 800;
    private class Panel extends JPanel {
        private BufferedImage imageGrass;
        Panel() {
            try {
                BufferedImage img = ImageIO.read(new File("src/imgs/grass.jpg"));
                imageGrass = new BufferedImage(50, 50, img.getType());
                AffineTransform tr = AffineTransform.getScaleInstance(50.0/img.getWidth(), 50.0/img.getHeight());
                BufferedImageOp op = new AffineTransformOp(tr, AffineTransformOp.TYPE_BILINEAR);
                op.filter(img, imageGrass);
            }catch (IOException a){
                System.exit(0);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gg = (Graphics2D) g;
            gg.setPaint(new TexturePaint(imageGrass, new Rectangle2D.Double(0,0,50, 50)));
            gg.fillRect(0,0,width,height);
        }
    }
    public Grass() {
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(new Dimension(width, height));
        Panel p = new Panel();
        fr.add(p);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        new Grass();
    }
}
