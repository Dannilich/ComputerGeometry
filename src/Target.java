import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
 
public class Target extends JPanel {
  private static class Figure extends Path2D.Double {
    public Figure() {
      append(new Ellipse2D.Double(-1, -1, 2, 2), false);
      append(new Arc2D.Double(-.5, -.5, 1, 1, 0, -180,  Arc2D.OPEN), false);
      append(new Line2D.Double(-2, 0, 2, 0), false);
      moveTo(2, 0);
      lineTo(1.5, -.5);
      moveTo(2, 0);
      lineTo(1.5, .5);
      append(new Line2D.Double(0, -2, 0, 2), false);
      moveTo(0, 2);
      lineTo(-.5, 1.5);
      moveTo(0, 2);
      lineTo(.5, 1.5);
    }
  }

  private Figure target;

  public Target() {
    setBackground(Color.WHITE);
    setOpaque(true);
    setPreferredSize(new Dimension(1000, 800));
    target = new Figure();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setPaint(Color.LIGHT_GRAY);
    // Рисование координатной сетки
    for (int x = 100; x < getWidth(); x += 100)
      g2.drawLine(x, 0, x, getHeight());
    for (int y = 100; y < getHeight(); y += 100)
      g2.drawLine(0, y, getWidth(), y);
    g2.setPaint(Color.BLUE);

    AffineTransform at = new AffineTransform();

    /**************************************************************/
    /* Код для построения аффинного отображения записывается сюда */
    /**************************************************************/
    at.translate(800, 400);
    at.rotate(Math.PI/6);
    at.scale(150, 100);
    at.translate(-2, 0);
    /**************************************************************/

    g2.draw(at.createTransformedShape(target));
  }

  private static void createAndShowGUI() {
    JFrame frame = new JFrame("");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new Target());
    frame.pack();

  }
 
  public static void main(String[] args) {
    SwingUtilities.invokeLater(Target::createAndShowGUI);
  }
}
