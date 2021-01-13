package pr2019.a04a.e2;

import java.util.Random;

public class LogicImpl implements Logic {

    private Pair<Integer, Integer> currentPosition;
    private final int size;
    
    public LogicImpl(int size) {
        this.size = size;
        this.currentPosition = new Pair<>((size - 1)/2,(size-1)/2);
    }
    
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.currentPosition;
    }

    @Override
    public boolean move() {
        int dRand = (new Random()).nextInt(Directions.values().length);
        var  np = Directions.values()[dRand].applyTo(currentPosition);
        if(np.getX() < 0 || np.getX() >= size || np.getY() < 0 || np.getY() >= size) {
            this.move();
        }  else {
            this.currentPosition = np;
        }
        return isOver();
    }
    
    private boolean isOver() {
        return this.currentPosition.equals(new Pair<>(0,0)) ||
                this.currentPosition.equals(new Pair<>(0,size-1)) ||
                this.currentPosition.equals(new Pair<>(size-1,0)) ||
                this.currentPosition.equals(new Pair<>(size-1,size-1));
    }
}
