package pr2019.a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        logic = new LogicImpl(size);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        JPanel panel = new JPanel(new GridLayout(size,size));
        final JButton move = new JButton(">");
        move.addActionListener(e ->{
            this.logic.move();
            this.updateView();
        });
        

        for (int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                final JButton jb = new JButton(" ");
                jb.addActionListener(e ->{
                    this.logic.setX(this.buttons.get((JButton)e.getSource()));
                    this.updateView();
                });
                panel.add(jb);
                this.buttons.put(jb, new Pair<>(i,j));
            }
        }
        
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.getContentPane().add(BorderLayout.SOUTH, move);
        
        this.setVisible(true);
    }
    
    private void updateView() {
        for(final var el : this.buttons.entrySet()) {
            if(this.logic.getPosition(el.getValue())) {
                el.getKey().setText("X");
            } else {
                el.getKey().setText(" ");
            }
        }
    }
}
