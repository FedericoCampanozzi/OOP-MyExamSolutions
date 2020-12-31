package a06a.e1;

import java.util.*;

public class SRServiceFactoryImpl implements SRServiceFactory {

    @Override
    public SRService createMaximumAccess() {
        return new SRService() {

            private final Set<Integer> clientsOperazions = new HashSet<>();
            private final Set<Integer> clients = new HashSet<>();
            private int count = 0;
            
            @Override
            public int enter() {
                this.clients.add(this.count);
                return this.count++;
            }

            @Override
            public void goReceive(int id) {
                existClient(id);
                if(madeOperations(id)) {
                    throw new IllegalStateException();
                }
                this.clientsOperazions.add(id);
            }

            @Override
            public void goSend(int id) {
                existClient(id);
                if(madeOperations(id)) {
                    throw new IllegalStateException();
                }
                this.clientsOperazions.add(id);
            }

            @Override
            public void exit(int id) {
                existClient(id);
                if(!madeOperations(id)) {
                    throw new IllegalStateException();
                }
                this.clients.remove(id);
            }
            
            private void existClient(int id) {
                if(!this.clients.contains(id)) {
                    throw new IllegalStateException();
                }
            }
            
            private boolean madeOperations(int id) {
                return this.clientsOperazions.contains(id);
            }            
        };
    }

    @Override
    public SRService createWithNoConcurrentAccess() {
        return new SRService() {

            private final Set<Integer> clients = new HashSet<>();
            private int count = 0;
            private Optional<Integer> idNow = Optional.empty();
            
            @Override
            public int enter() {
                this.clients.add(this.count);
                return this.count++;
            }

            @Override
            public void goReceive(int id) {
                existClient(id);
                idNow = Optional.of(id);
            }

            @Override
            public void goSend(int id) {
                existClient(id);
                idNow = Optional.of(id);
            }

            @Override
            public void exit(int id) {
                existClient(id);
                this.clients.remove(id);
                idNow = Optional.empty();
            }
            
            private void existClient(int id) {
                if(!this.clients.contains(id) || (this.idNow.isPresent() && this.idNow.get() != id)) {
                    throw new IllegalStateException();
                }
            }
            
        };
    }

    @Override
    public SRService createManyReceiveAndMaxTwoSend() {
        return new SRService() {

            private final Set<Integer> sendOperations = new HashSet<>();
            private final Set<Integer> clients = new HashSet<>();
            private int count = 0;
            
            @Override
            public int enter() {
                this.clients.add(this.count);
                return this.count++;
            }

            @Override
            public void goReceive(int id) {
                existClient(id);
            }

            @Override
            public void goSend(int id) {
                existClient(id);
                if(this.sendOperations.size() + 1 > 2) {
                    throw new IllegalStateException();
                }
                this.sendOperations.add(id);
            }

            @Override
            public void exit(int id) {
                existClient(id);
                this.clients.remove(id);
            }
            
            private void existClient(int id) {
                if(!this.clients.contains(id)) {
                    throw new IllegalStateException();
                }
            }            
        };
    }

}
