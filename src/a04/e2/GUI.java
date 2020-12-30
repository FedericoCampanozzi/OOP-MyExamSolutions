package a04.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final static int SIZE = 4;
    private final Map<JButton,Pair<Integer,Integer>> jbs = new HashMap<>();
    private final Logic logic = new LogicImpl();
    
    public GUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*SIZE, 100*SIZE);
        
        JPanel panel = new JPanel(new GridLayout(SIZE,SIZE));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            var position = jbs.get(bt);
            System.out.println(position);
            
            if(logic.isAdjacent(position)) {
                bt.setEnabled(false);
            }
            
            if(logic.isOver()) {
                System.exit(0);
            }
        };
                
        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(al);
                jbs.put(jb, new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
}
