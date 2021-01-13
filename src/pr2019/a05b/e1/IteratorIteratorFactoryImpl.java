package pr2019.a05b.e1;

import java.util.*;

public class IteratorIteratorFactoryImpl implements IteratorIteratorFactory {

    private abstract class myIterators<E> implements Iterator<Iterator<E>> {
        @Override
        public boolean hasNext() {
            return true;
        }
        
        @Override
        public abstract Iterator<E> next();
    }
    
    @Override
    public <E> Iterator<Iterator<E>> constantWithSingleton(E e) {
        return new myIterators<E>() {
            @Override
            public Iterator<E> next() {
                return new Iterator<E>() {
                    boolean stop = false;
                    
                    @Override
                    public boolean hasNext() {
                        return !stop;
                    }
                    @Override
                    public E next() {
                        this.stop = true;
                        return e;
                    }
                };
            }
            
        };
    }

    @Override
    public <E> Iterator<Iterator<E>> increasingSizeWithSingleton(E e) {     
        return new myIterators<E>() {
            private int size  = 0;
            @Override
            public Iterator<E> next() {
                size++;
                final List<E> it = new ArrayList<>();
                for(int i=0;i<size;i++) {
                    it.add(e);
                }
                return it.iterator();
            }
            
        };
    }

    @Override
    public Iterator<Iterator<Integer>> squares() {
        
        return new myIterators<Integer>() {
            private int size  = 0;
            @Override
            public Iterator<Integer> next() {
                size++;
                return new Iterator<Integer>() {
                    private int count = 0;
                    @Override
                    public boolean hasNext() {
                        return this.count  < size;
                    }
                    @Override
                    public Integer next() {
                        this.count++;
                        return (this.count - 1) * (this.count - 1);
                    }
                };
            }
            
        };
    }

    @Override
    public Iterator<Iterator<Integer>> evens() {
        return new myIterators<Integer>() {
            private int size  = 0;
            @Override
            public Iterator<Integer> next() {
                size++;
                return new Iterator<Integer>() {
                    private int count = 0;
                    @Override
                    public boolean hasNext() {
                        return this.count  < size;
                    }
                    @Override
                    public Integer next() {
                        this.count++;
                        return 2 * (this.count - 1);
                    }
                };
            }
            
        };
    }

}
