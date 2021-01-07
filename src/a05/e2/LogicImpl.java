package a05.e2;

public class LogicImpl implements Logic {

    private int index = 0;
    private final int limit;
    
    public LogicImpl(int limit) {
        this.limit = limit;
    }
    
    @Override
    public int getCurrentIndex() {
        return index;
    }
    
    @Override
    public int move(boolean isRight) {
        if(isRight) {
            index ++;
        } else {
            index --;
        }
        
        if(index < 0) {
            index = 0;
        } else if(index > limit) {
            index = limit;
        }
        
        return index;
    }

}
