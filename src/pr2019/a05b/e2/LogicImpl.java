package pr2019.a05b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private final Map<Pair<Integer,Integer>,Boolean> map = new HashMap<>();
    private final Pair<Integer,Integer> start;
    private int k = 1;
    
    public LogicImpl(int size) {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                map.put(new Pair<>(i,j), false);
            }
        }
        
        Random rnd = new Random();
        start = new Pair<>(rnd.nextInt(size-3) + 1, rnd.nextInt(size-3) + 1);
        map.put(start, true);
    }
    
    @Override
    public boolean update() {
        boolean stop = false;
        for(Directions d : Directions.values()) {
            Pair<Integer,Integer> nPos = new Pair<>(
                    this.start.getX() + k * d.getXOffset(),
                    this.start.getY() + k * d.getYOffset()
                    );
            if(this.map.containsKey(nPos)) {
                map.put(nPos, true);
            } else {
                stop = true; 
            }
        }
        
        k++;
        return stop;
    }

    @Override
    public boolean getElement(Pair<Integer, Integer> position) {
        return this.map.get(position);
    }
}
