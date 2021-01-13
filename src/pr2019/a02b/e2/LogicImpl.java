package pr2019.a02b.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private final int size;
    private Map<Pair<Integer,Integer>, Boolean> maps = new HashMap<>();
    private boolean goBot = true;
    
    public LogicImpl(int size) {
        this.size = size;
        for (int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                this.maps.put(new Pair<>(i,j), false);
            }
        }
    }
    
    @Override
    public boolean getPosition (Pair<Integer, Integer> position) {
        return this.maps.get(position);
    }

    @Override
    public void move() {
        
        for(final var el : this.maps.entrySet()) {
            
            final var pos = this.getPosFromDir(el.getKey());
            
            if(el.getValue() && (pos.getX() < 0 || pos.getX() >= size)) {
                this.goBot = !this.goBot;
                break;
            }
        }
        
        final Map<Pair<Integer,Integer>, Boolean> clone = new HashMap<>(this.maps);
        
        for(final var el : this.maps.entrySet()) {
            if(el.getValue()) {
                clone.put(el.getKey(),false);
                clone.put(this.getPosFromDir(el.getKey()), true);
            }
        }
        
        this.maps = new HashMap<>(clone);
    }
    
    private Pair<Integer,Integer> getPosFromDir (Pair<Integer,Integer> position){
        if(this.goBot) {
            return new Pair<>(position.getX() + 1, position.getY());
        } else {
            return new Pair<>(position.getX() - 1, position.getY());
        }
    }
    
    @Override
    public void setX(Pair<Integer, Integer> position) {
        this.maps.put(position, true);
    }

}
