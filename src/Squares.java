import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Squares extends JPanel {

    public static final float RATIO = 0.75f;
    public static final int SQUARES = 30;
    public static final float LAMDA = 0.05f;


    public Squares(){
        setPreferredSize(new Dimension(800,800));
        setBackground(Color.BLACK);
        setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        int frameHeight = getHeight();
        int frameWidth = getWidth();

        float side = RATIO * Math.min(frameHeight,frameWidth);


        g2.setPaint(Color.DARK_GRAY);
            for (int i = 0; i < frameWidth; i+=50)
                g2.drawLine(i,0,i,frameHeight);
            for (int j = 0; j < frameHeight; j+=50)
                g2.drawLine(0,j, frameWidth,j );


        g2.translate(frameWidth/2,frameHeight/2);
        g2.scale(side,-side);
        g2.setStroke(new BasicStroke(1f/side));
        g2.translate(-1/2f,-1/2f);


        g2.setPaint(Color.ORANGE);
        Shape rect = new Rectangle2D.Float(0, 0 , 1, 1);
        g2.draw(rect);

        AffineTransform at = new AffineTransform(
                1-LAMDA, LAMDA,
                -LAMDA, 1-LAMDA,
                LAMDA, 0
        );

        for (int i = 0; i < SQUARES -1; i++) {
            rect =  at.createTransformedShape(rect);
            g2.draw(rect);
        }
    }

    public static void createAndShowGUI(){
        JFrame frame = new JFrame("Квадраты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Squares());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){ SwingUtilities.invokeLater(Squares::createAndShowGUI); }
}
