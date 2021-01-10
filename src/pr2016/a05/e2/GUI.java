package pr2016.a05.e2;

import javax.swing.*;
import java.util.*;

public class GUI {
        
    public GUI(int size){
        
        final List<JButton> jbList = new ArrayList<>();
        final Logic logic = new LogicImpl(size - 1);
            
        JPanel jp = new JPanel();
        JButton jbMove = new JButton("Move");
        JCheckBox jCheck = new JCheckBox("GoRight", true);
        
        jbMove.addActionListener(e -> {
            jbList.get(logic.getCurrentIndex()).setText(" ");
            logic.move(jCheck.isSelected());
            jbList.get(logic.getCurrentIndex()).setText("*");
        });
        
        for(int  i=0;i<size;i++) {
            JButton jb  =  new JButton(" ");
            jp.add(jb);
            jbList.add(jb);
        }
        
        jp.add(jbMove);
        jp.add(jCheck);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
        jbList.get(0).setText("*");
    }
}
