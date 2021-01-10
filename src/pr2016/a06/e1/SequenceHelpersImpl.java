package pr2016.a06.e1;

import java.util.List;
import java.util.function.BinaryOperator;

public class SequenceHelpersImpl implements SequenceHelpers {

    @Override
    public <X> Sequence<X> of(X x) {
        return new Sequence<X>() {
            @Override
            public X nextElement() {
                return x;
            }
        };
    }

    @Override
    public <X> Sequence<X> cyclic(List<X> l) {
        return new Sequence<X>() {
           private int index = 0;
            @Override
            public X nextElement() {
                if(index > l.size() - 1) {
                    index = 0;
                }
                return l.get(index++);
            }
        };
    }

    @Override
    public Sequence<Integer> incrementing(int start, int increment) {
        return new Sequence<Integer>() {
            private int number = start - increment;
            @Override
            public Integer nextElement() {
                number += increment;
                return number;
            }
        };
    }

    @Override
    public <X> Sequence<X> accumulating(Sequence<X> input, BinaryOperator<X> op) {
        return new Sequence<X>() {
            private int n = 0;
            private X lastElCreated;
            
            @Override
            public X nextElement() {
                X newEl;
                if(n ==  0) {
                    newEl = input.nextElement();
                } else {
                    newEl = op.apply(this.lastElCreated, input.nextElement());
                }
                n++;
                this.lastElCreated = newEl;
                return newEl;
            }
            
        };
    }

    @Override
    public <X> Sequence<Pair<X, Integer>> zip(Sequence<X> input) {
        return new Sequence<Pair<X, Integer>>() {
            private int index = 0;
            @Override
            public Pair<X, Integer> nextElement() {
                return new Pair<>(input.nextElement(), index++);
            }
            
        };
    }

}
