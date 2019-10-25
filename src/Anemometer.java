import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 27-Jul-18.
 */
public class Anemometer {
    public static void main(String[] args) {
        Assembler assembler = new Assembler();
        assembler.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        assembler.setSize(1000,800);
        assembler.setTitle("Anemometer");
        assembler.setVisible(true);
        assembler.setBackground(Color.WHITE);
    }
}
