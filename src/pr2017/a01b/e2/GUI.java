package pr2017.a01b.e2;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class GUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -7639388793003888602L;
    private final List<JButton> buttons = new ArrayList<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.logic = new LogicImpl(size);
        this.setSize(100 * size, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());

        for (int i = 0; i < size; i++) {
            JButton bt = new JButton("?");
            bt.addActionListener(e -> {
                final JButton jbtn = (JButton)e.getSource();
                int index = this.buttons.indexOf(jbtn);
                if(this.logic.showNumber(index)) {
                    jbtn.setText(Integer.toString(logic.get(index)));
                }
            });
            this.buttons.add(bt);
            this.getContentPane().add(bt);

        }
        
        this.setVisible(true);
    }
}
