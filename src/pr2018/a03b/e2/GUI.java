package pr2018.a03b.e2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> buttons = new ArrayList<>();
    private final JCheckBox cb;
    private final int SIZE = 5;
    private final Logic logic = new LogicImpl(SIZE);

    public GUI() {
        this.cb = new JCheckBox("Left");
        JPanel panel = new JPanel(new FlowLayout());

        final Random rnd = new Random();

        for (int i = 0; i < SIZE; i++) {
            JButton jb = new JButton(Integer.toString(rnd.nextInt(10)));
            jb.addActionListener(e -> {
                final JButton bt = (JButton) e.getSource();
                if(logic.move(this.buttons.indexOf((bt)), this.cb.isSelected())) {
                    System.exit(1);
                }
                this.updateView();
            });
            buttons.add(jb);
            panel.add(jb);
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.add(this.cb);
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.pack();
        this.setVisible(true);
        this.updateView();
    }
    
    private void updateView() {
        for(int i=0;i<SIZE;i++) {
            this.buttons.get(i).setText(Integer.toString(logic.get(i)));
        }
    }
}
