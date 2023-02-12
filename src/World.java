import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class World
{
  public static void main(String[] args)
  {
      run();
  }

  private static final int RANDOM_SEED = 12345;
  private static final int GRADIENT_SIZE = 256;
  private static final int GRADIENT_MASK = GRADIENT_SIZE - 1;
  private static final int GRADIENT_OCTAVES = 4;
  private static final double PERSISTENCE = 0.5;

  private static final double[] GRADIENT = new double[GRADIENT_SIZE * 2];
  private static final Random RANDOM = new Random(RANDOM_SEED);

  static {
    for (int i = 0; i < GRADIENT_SIZE; i++) {
      double angle = RANDOM.nextDouble() * Math.PI * 2;
      GRADIENT[i * 2] = Math.cos(angle);
      GRADIENT[i * 2 + 1] = Math.sin(angle);
    }
  }
  private int width = 500;
  private int height = 500;

  private double[][] map;

  public static void run()
  {
    Display display = new Display(500, 500);
    display.run();
  }



  public World(int w, int h)
  {
    map = new double[w][h];
    for (int i = 0; i < map.length; i++)
      for (int j = 0; j < map[i].length; j++)
      {
        map[i][j] = noise(i,j);
      }


    for (double[] doubles : map) {
      System.out.println();
      for (double aDouble : doubles) {
        System.out.print(aDouble + ", ");
      }
    }

  }

  public double noise(int x, int y) {
    int x0 = x & GRADIENT_MASK; //GRADIENT_MASK = 255
    int y0 = y & GRADIENT_MASK;
    int x1 = (x0 + 1) & GRADIENT_MASK;
    int y1 = (y0 + 1) & GRADIENT_MASK;
    double xf = x - Math.floor(x);
    double yf = y - Math.floor(y);
    double u = fade(xf);
    double v = fade(yf);
    double n0, n1, n2;

    n0 = dot(GRADIENT[x0 * 2], GRADIENT[y0 * 2], xf, yf);
    n1 = dot(GRADIENT[x1 * 2], GRADIENT[y0 * 2], xf - 1, yf);
    n0 = lerp(n0, n1, u);

    n1 = dot(GRADIENT[x0 * 2], GRADIENT[y1 * 2], xf, yf - 1);
    n2 = dot(GRADIENT[x1 * 2], GRADIENT[y1 * 2], xf - 1, yf - 1);
    n1 = lerp(n1, n2, u);

    return lerp(n0, n1, v) * 0.5 + 0.5;
  }

  private double dot(double x, double y, double xf, double yf) {
    return x * xf + y * yf;
  }

  private double fade(double t) {
    return t * t * t * (t * (t * 6 - 15) + 10);
  }

  private double lerp(double a, double b, double t) {
    return a + t * (b - a);
  }
  
  public void stepAll()
  {








  }

 






  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }







  public String getTitle()
  {
    return "World";
  }





  public void paintComponent(Graphics g) {


    g.setColor(new Color(0, 60, 250));
    g.fillRect(0, 0, width, height);



    for(int i = 0; i < width-1; i++)
      for(int j = 0; j < height-1; j++)
      {
        g.setColor(new Color(0, 210, 13));
        if(map[i][j]>.6)
          g.fillRect(i,j,i+1,j+1);
      }


  }

  public void keyPressed(int keyCode) {
  }

  public void keyReleased(int keyCode) {
  }

  public void mouseClicked(int mouseX, int mouseY) {
  }

  public void mouseMoved(int mouseMoveX, int mouseMoveY) {
  }
}