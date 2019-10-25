import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.*;

/**
 * Created by acer on 27-Jul-18.
 */
public class Display extends JPanel {
    static double angle;

    public void Update(double x){
        angle = x;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.translate(this.getWidth()/2,this.getHeight()/2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setBackground(Color.black);

        angle = getAngle(angle);

        double delta_angle = 5;         //delta angle is used for width of direction arrow
        int radius = 200,               //radius of outer circle
        center_x = 250,                 //x coordinate of center of circle
        center_y = 300;                 //y coordinate of center of circle

        int origRadius = radius;        //copy radius value for future calculations
        Point center = new Point(center_x,center_y);
                                        //center of all circles
        Ellipse2D circle1 = new Ellipse2D.Float(0,0,200,200);
        circle1.setFrameFromCenter(center,new Point((center_x-radius),(center_y-radius)));



        //making of outer circle
        radius -= 0.05*radius;          //reduce radius to 95% of its original value for make middle circle
        Ellipse2D circle2 = new Ellipse2D.Float(0,0,200,200);
        circle2.setFrameFromCenter(center,new Point((center_x-radius),(center_y-radius)));
                                        //making of meddle circle
        radius -= 1*radius/3;           //reduce radius to 63% of its original value for make inner circle
        Ellipse2D circle3 = new Ellipse2D.Float(0,0,200,200);
        circle3.setFrameFromCenter(center,new Point((center_x-radius),(center_y-radius)));
                                        //making of inner circle

        g2.setColor(Color.gray);
        g2.fill(circle1);               //print outer circle

        g2.setColor(Color.BLACK);       //print marks on outer circle
        Arc2D.Float arcMark = new Arc2D.Float(Arc2D.PIE);

        g2.setColor(Color.lightGray);
        arcMark.setFrameFromCenter(center,new Point(circle1.getBounds().x,circle1.getBounds().y) );
        int partitionsAngle = 5;
        for (int a=0;a<=360/partitionsAngle;a++){
            arcMark.setAngleStart(-partitionsAngle*a);
            arcMark.setAngleExtent(1);
            g2.draw(arcMark);
            g2.fill(arcMark);
        }
        g2.setColor(Color.black);
        arcMark.setFrameFromCenter(center,new Point((center_x-origRadius),(center_y-origRadius)));
        for (int a=0;a<=7;a++){
            arcMark.setAngleStart(-45*a);
            arcMark.setAngleExtent(1);
            g2.draw(arcMark);
            g2.fill(arcMark);
        }

        g2.setColor(new Color(50,50,150));
        g2.fill(circle2);               //print middle circle

        g2.setColor(new Color(100,100,200));
        arcMark.setFrameFromCenter(center,new Point(circle2.getBounds().x,circle2.getBounds().y) );
        partitionsAngle = 30;
        for (int a=0;a<=360/partitionsAngle;a++){
            arcMark.setAngleStart(-partitionsAngle*a);
            arcMark.setAngleExtent(0);
            g2.draw(arcMark);
            g2.fill(arcMark);
        }
        g2.setColor(Color.white);
        g2.fill(circle3);               //print inner circle

        int[] arrowX = new int[4];      //x coordinate set of arrow
        int[] arrowY = new int[4];      //y coordinate set of arrow

        arrowX[0] = (int) (center_x +  (0.68*origRadius*sin(toRadians(angle))));
        arrowY[0] = (int) (center_y+  (0.68*origRadius*cos(toRadians(angle))));

        arrowX[2] = (int) (center_x +  (0.88*origRadius*sin(toRadians(angle))));
        arrowY[2] = (int) (center_y+  (0.88*origRadius*cos(toRadians(angle))));

        arrowX[1] = (int) (center_x + (0.93*origRadius*sin((toRadians(angle+delta_angle)))));
        arrowY[1] = (int) (center_y+  (0.93*origRadius*cos((toRadians(angle+delta_angle)))));

        arrowX[3] = (int) (center_x + (0.93*origRadius*sin((toRadians(angle-delta_angle)))));
        arrowY[3] = (int) (center_y+  (0.93*origRadius*cos((toRadians(angle-delta_angle)))));

        g2.setColor(Color.WHITE);
        g2.fillPolygon(arrowX,arrowY,4);
                                        //print arrow

        g2.setColor(new Color(100,100,200));
        g.setFont(new Font(null,Font.BOLD,origRadius/8));
                                        //font of degrees
        FontMetrics fm=g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(abs(-angle+180)+"deg",g);
        g2.drawString(abs(-angle+180)+"deg",center_x-(int)r.getWidth()/2,center_y + fm.getAscent());
                                        //draw degree value
        g2.setColor(Color.BLACK );      //NEWS font color
        r = fm.getStringBounds("W",g);  //print NEWS letters
        g2.drawString("N",center_x,center_y-origRadius);
        g2.drawString("E",center_x+origRadius,center_y);
        g2.drawString("S",center_x, (float) (center_y + origRadius + r.getWidth()));
        g2.drawString("W", (float) (center_x - origRadius - r.getWidth()),center_y);

        g2.setColor(Color.darkGray);
        g.setFont(new Font(null,Font.PLAIN,origRadius/10));
        r = fm.getStringBounds("WW",g);
        g2.drawString("NE", (float) (center_x+origRadius*cos(45) + r.getWidth() ), (float) (center_y-origRadius*cos(45) - r.getWidth()));
        g2.drawString("ES", (float) (center_x+origRadius*cos(45) + r.getWidth() ), (float) (center_y+origRadius*cos(45)  + r.getWidth() + r.getHeight()));
        g2.drawString("SW", (float) (center_x-origRadius*cos(45) - 2*r.getWidth() ), (float) (center_y+origRadius*cos(45)  + r.getWidth() + r.getHeight()));
        g2.drawString("NW", (float) (center_x-origRadius*cos(45) - 2*r.getWidth() ), (float) (center_y-origRadius*cos(45) - r.getWidth()));
    }

    private double getAngle(double angle) {
        return 180-angle;
    }


}
