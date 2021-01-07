package a03b.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private final Map<Pair<Integer, Integer>, Boolean> map = new HashMap<>();
    private final int size;
    
    public LogicImpl(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map.put(new Pair<>(i, j), false);
            }
        }
    }

    @Override
    public void changeElement(Pair<Integer, Integer> position) {
        int k = 1;
        
        while(k<size) {
            var up = new Pair<>(position.getX() + k, position.getY() - k);
            var down = new Pair<>(position.getX() - k, position.getY() + k);
            if(map.containsKey(up)) {
                map.put(up, !map.get(up));
            }
            if(map.containsKey(down)) {
                map.put(down, !map.get(down));
            }
            k++;
        }
        map.put(position, !map.get(position));
    }

    @Override
    public boolean getElement(Pair<Integer, Integer> position) {
        return map.get(position);
    }

}
