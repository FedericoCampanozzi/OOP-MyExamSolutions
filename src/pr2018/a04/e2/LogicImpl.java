package pr2018.a04.e2;

import java.util.*;
import java.util.Optional;

public class LogicImpl implements Logic {
    
    private final static int SIZE = 4;
    private List<Pair<Integer, Integer>> positions = new ArrayList<>();
    
    @Override
    public boolean isOver() {
        return this.positions.size() == this.SIZE * this.SIZE || !thereIsAdjacent();
    }

    @Override
    public boolean isAdjacent(Pair<Integer, Integer> position) {
        if (!this.getLastPosition().isPresent() || checkAdjacent(position)) {
            this.positions.add(position);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkAdjacent(Pair<Integer, Integer> pos) {
        int absDiffX = Math.abs(pos.getX() - this.getLastPosition().get().getX());
        int absDiffY = Math.abs(pos.getY() - this.getLastPosition().get().getY());
        
        return absDiffX <= 1 && absDiffY <= 1;
    }
    
    private boolean thereIsAdjacent() {
        for(int i = 0;i  < this.SIZE; i++) {
            for(int j = 0;j  < this.SIZE; j++) {
                Pair<Integer, Integer> p = new Pair<>(i,j);
                if(!this.positions.contains(p) && checkAdjacent(p)) {
                    return true;
                }
            }    
        }
        return false;
    }
    
    private Optional<Pair<Integer, Integer>> getLastPosition(){
        if(this.positions.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(this.positions.get(this.positions.size() - 1));
        }
    }
}
