package pr2016.a03a.e1;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;

public class BagFactoryImpl implements BagFactory {

    @Override
    public <X> Bag<X> empty() {
        return new Bag<X>() {

            @Override
            public int numberOfCopies(X x) {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public List<X> toList() {
                return List.of();
            };
        
        };
    }

    @Override
    public <X> Bag<X> fromSet(Set<X> set) {
        return new Bag<X>() {

            @Override
            public int numberOfCopies(X x) {
                return set.contains(x) ? 1 : 0;
            }

            @Override
            public int size() {
                return set.size();
            }

            @Override
            public List<X> toList() {
                List<X> list = new ArrayList<>();
                for(final X el : set) {
                    list.add(el);
                }
                
                return list;
            };
        
        };
    }

    @Override
    public <X> Bag<X> fromList(List<X> list) {
        return new Bag<X>() {

            @Override
            public int numberOfCopies(X x) {
                int copies = 0;
                for(final var el : list) {
                    if(el.equals(x)) {
                        copies++;
                    }
                }
                return copies;
            }

            @Override
            public int size() {
                return list.size();
            }

            @Override
            public List<X> toList() {
                return list;
            };
        
        };
    }

    @Override
    public <X> Bag<X> bySupplier(Supplier<X> supplier, int nElements, ToIntFunction<X> copies) {
        return byListCopies(Stream.generate(supplier).limit(nElements).collect(toList()), copies);
    }

    @Override
    public <X> Bag<X> byIteration(X first, UnaryOperator<X> next, int nElements, ToIntFunction<X> copies) {
        return byListCopies(Stream
                .iterate(first, next)
                .limit(nElements)
                .collect(toList()), copies);
    }
    
    public <X> Bag<X> byListCopies(List<X> list, ToIntFunction<X> copies){
        final List<X> clone = new ArrayList<>(list);        
        for(final X el : list) {
            for(int i=0;i<copies.applyAsInt(el)-1;i++) {
                clone.add(el);
            }
        }
        return fromList(clone);
    }
}
