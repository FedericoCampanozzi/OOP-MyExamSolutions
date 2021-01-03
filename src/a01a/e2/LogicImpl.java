package a01a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private static final int MAX_FAIL = 5;
    private int fail = 0;
    private final Set<Pair<Integer,Integer>> boatPosition = new HashSet<>();
    
    public LogicImpl(final int size, final int boat) {
        final int randX = (new Random()).nextInt(size);
        final int randY = (new Random()).nextInt(size - boat);
        
        for(int i = 0; i < boat; i++) {
            final var pos = new Pair<>(randX, i + randY);
            boatPosition.add(pos);
            System.out.println((i + randY) + " POSITION : " + pos);
        }
    }
    
    @Override
    public boolean isBoatPresent(final int x, final int y) {
        final var check = new Pair<>(x,y);
        
        if(!this.boatPosition.contains(check)) {
            this.fail ++;
            return false;
        } else {
            this.boatPosition.remove(check);
            return true;
        }
    }

    @Override
    public boolean isOver() {
        return this.fail > this.MAX_FAIL;
    }

    @Override
    public boolean isWin() {
        return this.boatPosition.isEmpty();
    }

}
