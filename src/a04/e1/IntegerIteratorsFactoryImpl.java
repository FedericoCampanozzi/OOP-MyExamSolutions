package a04.e1;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class IntegerIteratorsFactoryImpl implements IntegerIteratorsFactory {

    @Override
    public SimpleIterator<Integer> empty() {
        return new SimpleIterator<Integer>() {
            @Override
            public Optional<Integer> next() {
                return Optional.empty();
            }
        };
    }

    @Override
    public SimpleIterator<Integer> fromList(List<Integer> list) {
        return new SimpleIterator<Integer>() {
            private int index = 0;
            @Override
            public Optional<Integer> next() {
                if(this.index < list.size()) {
                    return Optional.of(list.get(index++));
                } else {
                    return Optional.empty();
                }
            }
        };
    }

    @Override
    public SimpleIterator<Integer> random(int size) {
        return new SimpleIterator<Integer>() {
            private int index = 0;
            private final Random random = new Random();
            
            @Override
            public Optional<Integer> next() {
                if(this.index < size) {
                    index++;
                    return Optional.of(random.nextInt(size));
                } else {
                    return Optional.empty();
                }
            }
        };
    }

    @Override
    public SimpleIterator<Integer> quadratic() {
        return new SimpleIterator<Integer>() {
            private int number = 1;
            private int count = 0;
            @Override
            public Optional<Integer> next() {
                if(this.count == number) {
                    this.number++;
                    this.count = 0;
                }
                this.count ++;
                return Optional.of(number);
            }
        };
    }

    @Override
    public SimpleIterator<Integer> recurring() {
        return new SimpleIterator<Integer>() {
            private int number = 0;
            private int count = 0;
            private int nextStop = 1; 
            @Override
            public Optional<Integer> next() {
                if(this.count == nextStop) {
                    this.number = 0;
                    this.count = 0;
                    this.nextStop ++;
                }
                this.count ++;
                return Optional.of(number++);
            }
        };
    }

}
