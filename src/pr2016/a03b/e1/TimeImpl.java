//Finito in 35 minuti obb. + opzionale
package pr2016.a03b.e1;

public class TimeImpl implements Time {

    private final int hours;
    private final int minutes;
    private final int seconds;
    
    public TimeImpl(int hours, int minutes, int seconds) {
        
        if(hours > 24 || minutes > 59 || seconds > 59 ||
                hours < 0 || minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException();
        }
        
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public int getHours() {
        return this.hours;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public int getSeconds() {
        return this.seconds;
    }

    @Override
    public String getLabel24() {
        return this.getString(this.getHours()) + ":" + this.getString(this.getMinutes()) + ":"
                + this.getString(this.getSeconds());
    }

    private String getString(int val) {
        return val < 10 ? "0" + val : "" + val;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hours;
        result = prime * result + minutes;
        result = prime * result + seconds;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeImpl other = (TimeImpl) obj;
        if (hours != other.hours)
            return false;
        if (minutes != other.minutes)
            return false;
        if (seconds != other.seconds)
            return false;
        return true;
    }

    @Override
    public int getSecondsFromMidnight() {
        return this.getHours() * 60 * 60 + this.getMinutes() * 60 + this.getSeconds();
    }

}
