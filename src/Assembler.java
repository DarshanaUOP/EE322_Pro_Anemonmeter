import javax.swing.*;
import java.awt.*;
import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acer on 28-Jul-18.
 */
public class Assembler extends JFrame {
    private JPanel backPannel,header,footer,middle;
    private Display display;
    private JLabel labelDateAndTime;
    private JTextArea tetArErrorLogger;
    GridBagConstraints g=new GridBagConstraints();
    public Assembler(){
        backPannel = new JPanel(new GridBagLayout());
        add(backPannel);

        header = new JPanel(new GridBagLayout());
        header.setBackground(Color.black);

        g.gridx = 0;
        g.gridy=0;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.BOTH;
        backPannel.add(header,g);

        middle = new JPanel(new GridBagLayout());
        middle.setBackground(Color.black);
        g.gridx = 0;
        g.gridy=1;
        g.weightx=4;
        g.weighty=4;
        g.gridheight=4;
        g.gridwidth=4;
        g.fill=GridBagConstraints.BOTH;
        backPannel.add(middle,g);

        footer = new JPanel(new GridBagLayout());
        footer.setBackground(Color.BLACK);
        g.gridx = 0;
        g.gridy=5;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.BOTH;
        backPannel.add(footer,g);

        labelDateAndTime = new JLabel();
        labelDateAndTime.setForeground(Color.cyan);
        labelDateAndTime.setFont(new Font(null,Font.PLAIN,25));
        labelDateAndTime.setHorizontalTextPosition(SwingConstants.CENTER);
        g.gridx =1;
        g.gridy=0;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.BOTH;
        labelDateAndTime.setBackground(Color.DARK_GRAY);
        header.add(labelDateAndTime,g);

        display = new Display();
        display.setVisible(true);
        g.gridx =0;
        g.gridy=0;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.BOTH;
        middle.add(display,g);

        tetArErrorLogger = new JTextArea("Errors");
        tetArErrorLogger.setFont(new Font(null,Font.PLAIN,20));
        tetArErrorLogger.setVisible(true);
        tetArErrorLogger.setBackground(new Color(0,0,0));
        tetArErrorLogger.setCaretColor(Color.GREEN);
        tetArErrorLogger.setSelectionColor(Color.GREEN);
        tetArErrorLogger.setDisabledTextColor(Color.WHITE);
        tetArErrorLogger.setForeground(Color.CYAN);
        tetArErrorLogger.setText("EE 3.01 Anemometer");
        tetArErrorLogger.setRows(3);
        tetArErrorLogger.setAutoscrolls(true);
        tetArErrorLogger.setEnabled(true);
        tetArErrorLogger.setLineWrap(false);
        g.gridx =0;
        g.gridy=0;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.BOTH;
        footer.add(tetArErrorLogger,g);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String year, month, date, hour, min, sec;
                while (true) {
                    year = new SimpleDateFormat("YYYY").format(new Date());
                    month = new SimpleDateFormat("MM").format(new Date());
                    date = new SimpleDateFormat("dd").format(new Date());

                    hour = new SimpleDateFormat("H").format(new Date());
                    min = new SimpleDateFormat("mm").format(new Date());
                    sec = new SimpleDateFormat("ss").format(new Date());
                    labelDateAndTime.setText(year+"."+month+"."+date+" " +hour + ":" + min + ":" + sec);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                double a=0;
                while (true){
                    display.Update(a);
                    display.repaint();
                    a++;
                    if (a==360) {a=0;}
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    //tetArErrorLogger.setText(tetArErrorLogger.getText()+"\n"+a);
                }
            }
        }).start();
    }
}
