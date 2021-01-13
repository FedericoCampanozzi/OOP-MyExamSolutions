package pr2017.a01b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.*;

import pr2016.a02b.e1.Pair;

public class LogicImpl implements Logic {

    private final List<Pair<Integer, Boolean>> numbers = new ArrayList<>();
    private static final int MAX_RANDOM = 100;

    public LogicImpl(int size) {
        for (int i = 0; i < size; i++) {
            this.numbers.add(new Pair<>(new Random().nextInt(MAX_RANDOM), false));
        }
    }

    @Override
    public boolean showNumber(int index) {
        if (this.getMin() == this.get(index)) {
            this.numbers.set(index, new Pair<>(this.get(index), true));
            return true;
        }
        return false;
    }

    @Override
    public boolean isOver() {
        return this.numbers.stream()
                .filter(itm -> itm.getY())
                .collect(toList()).size() == this.numbers.size();
    }

    private int getMin() {
        return this.numbers.stream()
                .filter(itm -> !itm.getY())
                .map(itm -> itm.getX())
                .reduce((a, b) -> Math.min(a, b)).get();
    }

    @Override
    public int get(int index) {
        return this.numbers.get(index).getX();
    }
}
