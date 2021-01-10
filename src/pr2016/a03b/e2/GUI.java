package pr2016.a03b.e2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

public class GUI extends JFrame {
    
     
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Map<JButton, Pair<Integer,Integer>> map = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.logic  = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50 * size, 50 * size);
        int cols = size;
        JPanel panel = new JPanel(new GridLayout(cols,cols));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(e -> {
                    final JButton bt = (JButton)e.getSource();
                    logic.changeElement(this.map.get(bt));
                    this.updateView();
                });
                panel.add(jb);
                map.put(jb, new Pair<>(i,j));
            }
        } 
        this.setVisible(true);
    }
    
    private void updateView() {
        for(var el : this.map.entrySet()) {
            if(logic.getElement(el.getValue())) {
                el.getKey().setText("*");
            } else {
                el.getKey().setText(" ");
            }
        }
    }
}
