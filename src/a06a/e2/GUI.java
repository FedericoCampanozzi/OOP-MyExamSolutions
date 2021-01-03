package a06a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -7207649407673104381L;
    final Map<JButton, Pair<Integer, Integer>> map = new HashMap<>();
    final Logic logic = new LogicImpl();

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(size * 100, size * 100);
        final JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                jb.addActionListener(e -> {
                    final JButton bt = (JButton) e.getSource();
                    final Pair<Integer, Integer> pos = map.get(bt);
                    if(logic.isPressed(pos.getX(), pos.getY())) {
                        System.exit(1);
                    }
                    this.updateValues();
                });
                map.put(jb, new Pair<>(i, j));
                panel.add(jb);

            }
        }
        this.setVisible(true);
    }
    
    private void updateValues() {
        for(var x : this.map.entrySet()) {
            Optional<Integer> v = logic.getElementValue(x.getValue().getX(), x.getValue().getY());
            if(v.isPresent()) {
                x.getKey().setText(v.get().toString());
            }
        }
    }
}
