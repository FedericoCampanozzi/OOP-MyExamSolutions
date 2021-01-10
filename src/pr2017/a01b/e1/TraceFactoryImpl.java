package pr2017.a01b.e1;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TraceFactoryImpl implements TraceFactory {

    @Override
    public <X> Trace<X> fromSuppliers(Supplier<Integer> sdeltaTime, Supplier<X> svalue, int size) {
        return new TraceImpl<X>(sdeltaTime, svalue, size);
    }

    @Override
    public <X> Trace<X> constant(Supplier<Integer> sdeltaTime, X value, int size) {
        return fromSuppliers(sdeltaTime, () -> value, size);
    }

    @Override
    public <X> Trace<X> discrete(Supplier<X> svalue, int size) {
        return fromSuppliers(() -> 1, svalue, size);
    }
}
