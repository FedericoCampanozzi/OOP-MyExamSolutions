package a02b.e2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> map = new HashMap<>();
    private final int SIZE = 10;
    private final Logic logic = new LogicImpl();
    
    public GUI() {
        this.setSize(50 * SIZE, 50 * SIZE);
        
        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final JButton jb = new JButton(" ");
                jb.addActionListener(e -> {
                    if(logic.draw(this.map.get((JButton) e.getSource()))) {
                        System.exit(1);
                    }
                    this.updateView();
                });
                this.map.put(jb, new Pair<>(i,j));
                panel.add(jb);
            }
        }
        
        this.setVisible(true);
    }
    
    private  void updateView() {
        for(final var el : this.map.entrySet()) {
            if(this.logic.getElement(el.getValue())) {
                el.getKey().setText("*");
            }
        }
    }
}
