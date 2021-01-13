package pr2019.a05b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Map<JButton,Pair<Integer,Integer>> grid = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        JPanel panel = new JPanel(new GridLayout(size,size));
        
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                final JButton jb = new JButton(" ");               
                grid.put(jb, new Pair<>(i,j));
                panel.add(jb);
            }
        }
       
        JButton updBtn = new JButton(">");
        updBtn.addActionListener(e ->{
            if(logic.update()) {
                System.exit(1);
            }
            
            this.updateView();
        });
        
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.getContentPane().add(BorderLayout.SOUTH,updBtn);
        
        this.setVisible(true);
        this.updateView();
        
        }
    
   private void updateView() {
       for(final var pos : this.grid.entrySet()) {
           if(logic.getElement(pos.getValue())) {
               pos.getKey().setText("*");
           } else {
               pos.getKey().setText(" ");
           }
       }
   }
}
