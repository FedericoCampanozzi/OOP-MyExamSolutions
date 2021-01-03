package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    public GUI(int size, int boat) {        
        final Logic logic = new LogicImpl(size, boat);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        final JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        final Map<JButton, Pair<Integer,Integer>> btnsPos = new HashMap<>();
        
        for (int i=0;i<size;i++){
            for(int  j=0;j<size;j++) {
                final JButton jb = new JButton();
                jb.addActionListener(e -> {
                    final JButton bt = (JButton)e.getSource();
                    final var pos = btnsPos.get(bt);
                    
                    if(logic.isBoatPresent(pos.getX(), pos.getY())) {
                        bt.setText("X");   
                    } else {
                        bt.setText("O");
                    }
                    
                    if(logic.isOver()) {
                        System.out.println("SCONFITTA");
                        System.exit(1);
                    } else  if(logic.isWin()) {
                        System.out.println("VITTORIA");
                        System.exit(0);
                    }
                });
                panel.add(jb);
                btnsPos.put(jb, new Pair<>(i,j));
            }
        } 
        this.setVisible(true);
    }
    
}
