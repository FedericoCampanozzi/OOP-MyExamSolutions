package pr2016.a06.e1;

import java.util.Iterator;
import java.util.List;

public class IntIteratorsFactoryImpl implements IntIteratorsFactory {

    @Override
    public Iterator<Integer> empty() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                return null;
            };
        };
    }

    @Override
    public Iterator<Integer> zeros() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return 0;
            };
        };
    }

    @Override
    public Iterator<Integer> alternateOneAndZeroIndefinitely() {
        return new Iterator<Integer>() {
            private int value = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return (this.value++) % 2;
            };
        };
    }

    @Override
    public Iterator<Integer> fromTo(int start, int end) {
        return new Iterator<Integer>() {
            private int value = start;

            @Override
            public boolean hasNext() {
                return value <= end;
            }

            @Override
            public Integer next() {
                return value++;
            };
        };
    }

    @Override
    public Iterator<Integer> fromToIndefinitely(int start, int stop) {
        return new Iterator<Integer>() {
            private int value = start;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                if (this.value > stop) {
                    this.value = start;
                }
                return this.value++;
            };
        };
    }

    @Override
    public Iterator<Integer> fromList(List<Integer> list) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < list.size();
            }

            @Override
            public Integer next() {
                return list.get(this.index++);
            };
        };
    }

}
