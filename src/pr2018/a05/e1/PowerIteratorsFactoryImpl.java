package pr2018.a05.e1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class PowerIteratorsFactoryImpl implements PowerIteratorsFactory {

    @Override
    public PowerIterator<Integer> incremental(int start, UnaryOperator<Integer> successive) {
        return new PowerIterator<Integer>() {
            private final List<Integer> values = new ArrayList<>();
            
            @Override
            public Optional<Integer> next() {
                
                if(this.values.size() == 0) {
                    this.values.add(start);
                } else {
                    this.values.add(successive.apply(this.values.get(this.values.size() - 1)));
                }
                
                return Optional.of(this.values.get(this.values.size() - 1));
            }

            @Override
            public List<Integer> allSoFar() {
                return this.values;
            }

            @Override
            public PowerIterator<Integer> reversed() {
                Collections.reverse(this.values);
                return fromList(this.values);
            }
        };
    }

    @Override
    public <X> PowerIterator<X> fromList(List<X> list) {
        return new PowerIterator<X>() {
            private int index = 0;
            
            @Override
            public Optional<X> next() {
                if(this.index < list.size()) {
                    return Optional.of(list.get(index++));
                } else {
                    return Optional.empty();
                }
            }

            @Override
            public List<X> allSoFar() {
                return list.stream().limit(index).collect(toList());
            }

            @Override
            public PowerIterator<X> reversed() {
                Collections.reverse(list);
                return fromList(list);
            }
            
        };
    }

    @Override
    public PowerIterator<Boolean> randomBooleans(int size) {
        return new PowerIterator<Boolean>() {
            private final List<Boolean> values = new ArrayList<>();
            private final double PERC_DIV = 0.5d;
            
            @Override
            public Optional<Boolean> next() {
                if(this.values.size() < size) {
                    boolean b = this.getRandomElement();
                    this.values.add(b);
                    return Optional.of(b);
                } else {
                    return Optional.empty();
                }
            }

            @Override
            public List<Boolean> allSoFar() {
                return this.values;
            }

            @Override
            public PowerIterator<Boolean> reversed() {
                Collections.reverse(this.values);
                return fromList(this.values);
            }
            
            private boolean getRandomElement() {
                return new Random().nextDouble() < PERC_DIV;
            }
        };
    }

}
