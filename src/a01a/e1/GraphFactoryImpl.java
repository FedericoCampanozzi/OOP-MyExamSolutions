package a01a.e1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class GraphFactoryImpl implements GraphFactory {

    private final class GraphImpl<X> implements Graph<X> {

        private final Set<X> nodes;
        private final Set<Pair<X, X>> edge;

        public GraphImpl(final Set<X> nodes, final Set<Pair<X, X>> edge) {
            this.nodes = nodes;
            this.edge = edge;
        }

        @Override
        public Set<X> getNodes() {
            return this.nodes;
        }

        @Override
        public boolean edgePresent(X start, X end) {
            return edge.contains(new Pair<X, X>(start, end));
        }

        @Override
        public int getEdgesCount() {
            return edge.size();
        }

        @Override
        public Stream<Pair<X, X>> getEdgesStream() {
            return edge.stream();
        }

    }
    
    @Override
    public <X> Graph<X> createDirectedChain(List<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            edge.add(new Pair<X, X>(nodes.get(i), nodes.get(i + 1)));
        }
        return new GraphImpl<X>(new HashSet<X>(nodes), edge);
    }

    @Override
    public <X> Graph<X> createBidirectionalChain(List<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            edge.add(new Pair<X, X>(nodes.get(i), nodes.get(i + 1)));
            edge.add(new Pair<X, X>(nodes.get(i + 1), nodes.get(i)));
        }
        return new GraphImpl<X>(new HashSet<X>(nodes), edge);
    }

    @Override
    public <X> Graph<X> createDirectedCircle(List<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (int i = 0; i < nodes.size(); i++) {
            edge.add(new Pair<X, X>(nodes.get(i), nodes.get((i + 1) % nodes.size())));
        }
        return new GraphImpl<X>(new HashSet<X>(nodes), edge);
    }

    @Override
    public <X> Graph<X> createBidirectionalCircle(List<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (int i = 0; i < nodes.size(); i++) {
            edge.add(new Pair<X, X>(nodes.get(i), nodes.get((i + 1) % nodes.size())));
            edge.add(new Pair<X, X>(nodes.get((i + 1) % nodes.size()), nodes.get(i)));
        }
        return new GraphImpl<X>(new HashSet<X>(nodes), edge);
    }

    @Override
    public <X> Graph<X> createDirectedStar(X center, Set<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (X node : nodes) {
            edge.add(new Pair<X, X>(center, node));

        }
        Set<X> mNodes = new HashSet<X>(nodes);
        mNodes.add(center);
        return new GraphImpl<X>(mNodes, edge);
    }

    @Override
    public <X> Graph<X> createBidirectionalStar(X center, Set<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (X node : nodes) {
            edge.add(new Pair<X, X>(center, node));
            edge.add(new Pair<X, X>(node, center));
        }
        Set<X> mNodes = new HashSet<X>(nodes);
        mNodes.add(center);
        return new GraphImpl<X>(mNodes, edge);
    }

    @Override
    public <X> Graph<X> createFull(Set<X> nodes) {
        Set<Pair<X, X>> edge = new HashSet<>();
        for (X nodeX : nodes) {
            for (X nodeY : nodes) {
                if (!nodeX.equals(nodeY)) {
                    edge.add(new Pair<X, X>(nodeX, nodeY));
                }
            }
        }
        return new GraphImpl<X>(nodes, edge);
    }

    @Override
    public <X> Graph<X> combine(Graph<X> g1, Graph<X> g2) {
        Set<Pair<X, X>> edge = new HashSet<>(g1.getEdgesStream().collect(toSet()));
        Set<X> nodes = new HashSet<>(g1.getNodes());
        
        g2.getNodes().forEach(itm -> {
            if(!nodes.contains(itm)) {
                nodes.add(itm);
            }
        });
        
        g2.getEdgesStream().forEach(itm -> {
            if(!edge.contains(itm)) {
                edge.add(itm);
            }
        });
        
        return new GraphImpl<X>(nodes, edge);
    }

}
