package a06a.e2;

import java.util.Optional;

public interface Logic {
    boolean isPressed(int row, int cols);
    Optional<Integer> getElementValue(int row, int cols);
}
