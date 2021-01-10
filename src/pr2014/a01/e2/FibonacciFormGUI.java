package pr2014.a01.e2;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import pr2014.a01.e1.FibonacciAcceptor;
import pr2014.a01.e1.FibonacciAcceptorImpl;

import java.util.stream.*;

public class FibonacciFormGUI extends JFrame {

    public FibonacciFormGUI(int size) {
        List<JTextField> texts = new ArrayList<>();
        
        // Inizializzazione base
        this.setSize(500, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        // Pannello sud, ossia in basso
        JPanel south = new JPanel(new FlowLayout());
        JButton ok = new JButton("OK");
        south.add(ok);
        
        ok.addActionListener(e -> {
            
            FibonacciAcceptor fib = new FibonacciAcceptorImpl();
            fib.reset("Standard");
            fib.consumeNext(1L);
            fib.consumeNext(1L);
            fib.consumeNext(2L);
            fib.consumeNext(3L);
            fib.consumeNext(5L);
            
            List<Long> v = fib.getCurrentSequence();
            
            for (int i = 0; i < texts.size(); i++) {
                if (Integer.valueOf(texts.get(i).getText()) != v.get(i).intValue()) {
                    return;
                }
            }
            
            System.exit(0);
        });
        
        this.getContentPane().add(BorderLayout.SOUTH, south);

        JPanel center = new JPanel(new GridLayout(size, 3));

        for (int i = 1; i <= size; i++) {
            var text = new JTextField(20);
            texts.add(text);
            center.add(subPanel(new JLabel(i +  " : "), FlowLayout.LEFT));
            center.add(subPanel(text, FlowLayout.CENTER));
        }

        this.getContentPane().add(BorderLayout.CENTER, center);
        this.setVisible(true);
    }

    /*
     * Helper function statica per wrappare un componente in un pannellino con
     * FlowLayout Serve a garantire che il componente sia piazzato secondo le sue
     * dimentioni preferite
     */
    private static JPanel subPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }
}
