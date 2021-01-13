package pr2018.a03b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic {

    public final List<Integer> values = new ArrayList<>();
    
    public LogicImpl(final int size) {
        while(this.values.size() != size) {
            final int nr = new Random().nextInt(10);
            if(!this.values.contains(nr)) {
                this.values.add(nr);
            }
        }
    }
    
    @Override
    public boolean move(final int index, final boolean goLeft) {
        if(goLeft) {
            if(index - 1 > 0) {
                swap(index, index -1);
            }
        } else {
            if(index + 1 < this.values.size()) {
                swap(index, index + 1);
            }
        }
        
        return this.isOver();
    }

    private void swap(final int oldPos, final int newPos) {
        final int v = this.values.get(newPos);
        this.values.set(newPos, this.values.get(oldPos));
        this.values.set(oldPos, v);
    }
    
    @Override
    public int get(final int index) {
        return this.values.get(index);
    }
    
    private boolean isOver() {
        for(int i=0;i<this.values.size()-1;i++) {
            if(this.values.get(i) > this.values.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
