package pr2016.a02a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    public LogicImpl(int size) {
        block = new ArrayList<>();
        index = 0;
        for(int i=0;i<size;i++) {
            this.block.add(false);
        }
    }

    private final List<Boolean> block;
    private int index;
    private boolean tryFirstRight = true;
    
    @Override
    public void move() {
        int upper = (index + 1) % block.size();
        int lower = (index - 1 + block.size()) % block.size();

        if (tryFirstRight) {
            if (!block.get(upper)) {
                index = upper;
            } else if(!block.get(lower)) {
                index = lower;
                tryFirstRight = !tryFirstRight;
            }
        } else {
            if (!block.get(lower)) {
                index = lower;
            } else if(!block.get(upper)){
                index = upper;
                tryFirstRight = !tryFirstRight;
            }
        }
    }

    @Override
    public void setBlockAt(int index) {
        block.set(index, true);
    }

    @Override
    public void reset() {
        for(int i=0;i<block.size();i++) {
            this.block.set(i,false);
        }
        this.tryFirstRight = true;
        this.index = 0;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean getBlockAt(int index) {
        return this.block.get(index);
    }

}
