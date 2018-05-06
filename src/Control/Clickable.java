package Control;

import java.awt.*;
import java.util.function.Consumer;

public interface Clickable {

    Consumer<Integer> onClick();
    Rectangle clickBox();

}
