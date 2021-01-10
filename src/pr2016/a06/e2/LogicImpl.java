package pr2016.a06.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private final List<Integer> values;

    public LogicImpl(int size) {
        values = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            values.add(1);
        }
    }

    @Override
    public int sumAt(int index) {
        int v = 0;
        if (index - 1 >= 0 && index + 1 < this.values.size()) {
           v = this.values.get(index - 1) + this.values.get(index) + this.values.get(index + 1);
        } else if (index - 1 >= 0) {
            v = this.values.get(index - 1) + this.values.get(index);
        } else {
            v = this.values.get(index) + this.values.get(index + 1);
        }
        this.values.set(index, v);
        return v;
    }

    @Override
    public void reset() {
        for (int i = 0; i < this.values.size(); i++) {
            this.values.set(i, 1);
        }
    }

    @Override
    public int get(int index) {
        return this.values.get(index);
    }

}
