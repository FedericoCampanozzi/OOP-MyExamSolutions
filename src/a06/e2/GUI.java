package a06.e2;

import java.awt.*;
import java.util.stream.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame{
	
	public GUI(int size){
	    this.setSize(50 * size + 100, 100);
            
	    final List<JButton> buttons = new ArrayList<>();
            final Logic logic = new LogicImpl(size);
            
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.getContentPane().setLayout(new FlowLayout());
            
            for(int i=0;i<size;i++) {
                JButton jb = new JButton("1");
                jb.addActionListener(e->{
                    final JButton btn = (JButton)e.getSource();
                    int index = buttons.indexOf(btn);
                    buttons.get(index).setText(Integer.toString(logic.sumAt(index)));
                });
                this.getContentPane().add(jb);
                buttons.add(jb);
            }
            
            JButton jbReset = new JButton("Reset");
            
            jbReset.addActionListener(e->{
                logic.reset();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setText(Integer.toString(logic.get(i)));
                }

            });
            
            this.getContentPane().add(jbReset);
            
            this.setVisible(true);
	}

}
