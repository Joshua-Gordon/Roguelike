package Environment;

import Entity.Entity;

import java.util.function.Consumer;

public interface Collidable {

    Consumer<Entity> onCollide();

}
