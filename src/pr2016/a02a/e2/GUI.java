package pr2016.a02a.e2;

import javax.swing.*;
import java.util.*;

public class GUI {
    
    private final List<JButton> listBtn = new ArrayList<>();
    private Logic logic;
    
    public GUI(int size){
        logic = new LogicImpl(size);
        JPanel jp = new JPanel();
        for(int i=0;i<size;i++) {
            JButton jb = new JButton("  ");
            jb.addActionListener(e -> {
                JButton btn = (JButton)e.getSource();
                btn.setEnabled(false);
                logic.setBlockAt(this.listBtn.indexOf(btn));
            });
            jp.add(jb);
            listBtn.add(jb);
        }
        
        JButton jbReset = new JButton("Reset");
        jbReset.addActionListener(e -> {
            logic.reset();
            for(int i=0;i<size;i++) {
                if(logic.getBlockAt(i)) {
                    listBtn.get(i).setText("*");
                    listBtn.get(i).setEnabled(false);
                } else {
                    listBtn.get(i).setText(" ");
                    listBtn.get(i).setEnabled(true);
                }
            }
            listBtn.get(logic.getIndex()).setText("*");
        });
        jp.add(jbReset);
        JButton jbMove = new JButton("Move");
        jbMove.addActionListener(e -> {
            listBtn.get(logic.getIndex()).setText(" ");
            logic.move();
            listBtn.get(logic.getIndex()).setText("*");
        });
        jp.add(jbMove);
        
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
        
        listBtn.get(logic.getIndex()).setText("*");
    }
    
    public static void main(String[] s){
        new GUI(10);
    }

}
