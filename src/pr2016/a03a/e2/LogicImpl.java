package pr2016.a03a.e2;

public class LogicImpl implements Logic {

    private int aIdx = 0;
    private int bIdx = 1;
    private final int size;
    
    public LogicImpl(final int size) {
        this.size = size;
    }
    
    @Override
    public boolean moveA() {
        if(this.aIdx + 1 != this.bIdx) {
            this.aIdx++;
        }
        return this.isOver();
    }

    @Override
    public boolean moveB() {
        if(this.bIdx + 1 < this.size) {
            this.bIdx++;
        }
        return this.isOver();
    }

    @Override
    public void reset() {
        this.aIdx = 0;
        this.bIdx = 1;
    }

    @Override
    public int getAIndex() {
        return this.aIdx;
    }

    @Override
    public int getBIndex() {
        return this.bIdx;
    }
    
    private boolean isOver() {
        return this.aIdx == size - 2 && this.bIdx == size -1;
    }
}
