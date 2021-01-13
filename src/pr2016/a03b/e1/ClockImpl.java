package pr2016.a03b.e1;

import java.util.ArrayList;
import java.util.List;

import pr2016.a02b.e2.Pair;

public class ClockImpl implements Clock {

    private Time t;
    private List<Pair<Integer, Runnable>> observer = new ArrayList<>();

    public ClockImpl(Time t) {
        this.t = t;
    }

    @Override
    public Time getTime() {
        return t;
    }

    @Override
    public void tick() {
        int h = t.getHours();
        int m = t.getMinutes();
        int s = t.getSeconds() + 1;
        if (s >= 60) {
            s -= 60;
            m++;
            if (m >= 60) {
                m -= 60;
                h++;
                if (h >= 24) {
                    h -= 24;
                }
            }
        }

        t = new TimeImpl(h, m, s);

        for (int i = 0; i < this.observer.size(); i++) {
            final var elem = this.observer.get(i);

            if (elem.getX() <= 1) {
                elem.getY().run();
            } else {
                this.observer.set(i, new Pair<>(elem.getX() - 1, elem.getY()));
            }
        }
    }

    @Override
    public void registerAlarmObserver(Time time, Runnable observer) {
        this.observer.add(new Pair<>(time.getSecondsFromMidnight() - t.getSecondsFromMidnight(), observer));
    }

    @Override
    public void registerHoursDeadlineObserver(int hours, Runnable observer) {
        this.observer.add(new Pair<>(60 * 60 * hours, observer));
    }

    @Override
    public void registerMinutesDeadlineObserver(int minutes, Runnable observer) {
        this.observer.add(new Pair<>(60 * minutes, observer));
    }

    @Override
    public void registerSecondsDeadlineObserver(int seconds, Runnable observer) {
        this.observer.add(new Pair<>(seconds, observer));
    }

}
