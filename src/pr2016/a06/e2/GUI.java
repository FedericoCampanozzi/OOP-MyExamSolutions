package pr2016.a06.e2;

import javax.swing.*;
import java.util.*;

public class GUI {
    
    private Optional<Integer> start = Optional.empty();
    private final JPanel jp = new JPanel();
    private final List<JButton> btns = new ArrayList<>();
        
    public GUI(int size){    
        for(int i=0;i<size;i++) {
            JButton jb = new JButton("-");
            jb.addActionListener(e -> {
                if (start.isEmpty()) {
                    start = Optional.of(btns.indexOf((JButton) e.getSource()));
                    for (int j = 0; j <= start.get(); j++) {
                        btns.get(j).setEnabled(false);
                    }
                } else {
                    int end = btns.indexOf((JButton) e.getSource());
                    for (int j = start.get() + 1; j < btns.size(); j++) {
                        if(j >= end) {
                            btns.get(j).setEnabled(false);
                        } else {
                            btns.get(j).setText(Integer.toString(j - start.get()));
                        }
                    }
                }
            });  
            jp.add(jb);
            btns.add(jb);
        }
        
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
}
