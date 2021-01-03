package a06a.e2;

import java.util.*;

public class LogicImpl implements Logic {
    private final Map<Pair<Integer,Integer>,Integer> values = new HashMap<>();
    
    @Override
    public Optional<Integer> getElementValue(int row, int cols) {
        return Optional.ofNullable(this.values.get(new Pair<>(row,cols)));
    }
    
    private void updateValues(Pair<Integer,Integer> p) {
        for(final var x : this.values.keySet()) {
            if(x.getX() != p.getX() && x.getY() != p.getY()) {
                int nv = this.values.get(x);
                nv++;
                this.values.put(x, nv);
            }
        }
    }

    @Override
    public boolean isPressed(int row, int cols) {
        final Pair<Integer,Integer> p = new Pair<>(row,cols);
        if(this.values.containsKey(p)) {
           return true;
        }
        this.updateValues(p);
        this.values.put(p, 0);
        return false;
    }
}
