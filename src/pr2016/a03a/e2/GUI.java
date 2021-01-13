package pr2016.a03a.e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.*;

public class GUI {
    
    private final List<JButton> buttons = new ArrayList<>();
    private final Logic logic;
    
    public GUI(int size) {
        logic = new LogicImpl(size);
        JPanel jp = new JPanel();
        for(int i = 0; i < size; i++) {
            JButton jb = new JButton(" ");
            jb.setEnabled(false);
            jp.add(jb);
            this.buttons.add(jb);
        }
        
        JButton jMoveA = new JButton("MoveA");
        jMoveA.addActionListener(e -> {
            buttons.get(this.logic.getAIndex()).setText(" ");
            if(this.logic.moveA()) {
                System.exit(0);
            }
            buttons.get(this.logic.getAIndex()).setText("A");
        });
        jp.add(jMoveA);
        
        JButton jMoveB = new JButton("MoveB");
        jMoveB.addActionListener(e -> {
            buttons.get(this.logic.getBIndex()).setText(" ");
            if(this.logic.moveB()) {
                System.exit(0);
            }
            buttons.get(this.logic.getBIndex()).setText("B");
        });
        jp.add(jMoveB);
        
        JButton jReset = new JButton("Reset");
        jReset.addActionListener(e -> {
            buttons.get(this.logic.getBIndex()).setText(" ");
            buttons.get(this.logic.getAIndex()).setText(" ");
            this.logic.reset();
            buttons.get(this.logic.getBIndex()).setText("B");
            buttons.get(this.logic.getAIndex()).setText("A");
            
        });
        jp.add(jReset);
        
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
        buttons.get(this.logic.getBIndex()).setText("B");
        buttons.get(this.logic.getAIndex()).setText("A");
    }
    
    public static void main(String[] s){
        new GUI(10);
    }

}
