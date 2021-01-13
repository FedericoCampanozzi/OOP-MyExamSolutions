package pr2019.a04a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3820670928688849866L;
    private final Map<Pair<Integer,Integer>, JButton> jMap = new HashMap<>();
    private final Logic logic;
    private int counter = 0;
    
    public GUI(int size) {
        logic = new LogicImpl(size);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        final JPanel panel = new JPanel(new GridLayout(size, size));
        final JButton move = new JButton(">");
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.getContentPane().add(BorderLayout.SOUTH, move);
        
        move.addActionListener(e -> {
            if(this.logic.move()) {
                System.exit(0);
            }
            this.jMap.get(this.logic.getPosition()).setText(Integer.toString(this.counter % 2));
            counter++;
        });

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                jMap.put(new Pair<>(i,j), jb);
                panel.add(jb);

            }
        }
        this.jMap.get(this.logic.getPosition()).setText("1");
        this.setVisible(true);
    }

}
