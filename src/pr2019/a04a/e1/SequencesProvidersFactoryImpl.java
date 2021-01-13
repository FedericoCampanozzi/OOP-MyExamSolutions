package pr2019.a04a.e1;

import java.util.*;

public class SequencesProvidersFactoryImpl implements SequencesProvidersFactory {

    @Override
    public <E> SequencesProvider<E> iterative(E e) {
        return new SequencesProvider<E>() {

            private int size = 0;
            @Override
            public List<E> nextSequence() {
                final List<E> seq = new ArrayList<>();
                for(int i=0;i<this.size;i++) {
                    seq.add(e);
                }
                this.size++;
                return seq;
            }

            @Override
            public boolean hasOtherSequences() {
                return true;
            }

            @Override
            public void reset() {
                this.size = 0;
            }
            
        };
    }

    @Override
    public <E> SequencesProvider<E> alternating(E e1, E e2) {
        return new SequencesProvider<E>() {

            private int size = 0;
            @Override
            public List<E> nextSequence() {
                final List<E> seq = new ArrayList<>();
                for(int i=0;i<this.size/2;i++) {
                    if(size % 2 == 0) {
                        seq.add(e1);
                    } else {
                        seq.add(e2);
                    }
                }
                this.size++;
                return seq;
            }

            @Override
            public boolean hasOtherSequences() {
                return true;
            }

            @Override
            public void reset() {
                this.size = 0;
            }
            
        };
    }

    @Override
    public <E> SequencesProvider<E> iterativeBounded(E e, int bound) {
        return new SequencesProvider<E>() {

            private int size = 0;
            @Override
            public List<E> nextSequence() {
                final List<E> seq = new ArrayList<>();
                for(int i=0;i<this.size;i++) {
                    seq.add(e);
                }
                this.size++;
                return seq;
            }

            @Override
            public boolean hasOtherSequences() {
                return this.size < bound;
            }

            @Override
            public void reset() {
                this.size = 0;
            }
            
        };
    }

    @Override
    public <E> SequencesProvider<E> alternatingBounded(E e1, E e2, int bound) {
        return new SequencesProvider<E>() {

            private int size = 0;
            @Override
            public List<E> nextSequence() {
                final List<E> seq = new ArrayList<>();
                for(int i=0;i<this.size/2;i++) {
                    if(size % 2 == 0) {
                        seq.add(e1);
                    } else {
                        seq.add(e2);
                    }
                }
                this.size++;
                return seq;
            }

            @Override
            public boolean hasOtherSequences() {
                return this.size < bound;
            }

            @Override
            public void reset() {
                this.size = 0;
            }
            
        };
    }

}
