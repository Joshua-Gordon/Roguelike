package Entity.Stats;

public class Resource {

    int max;
    int level;

    public Resource(int max) {
        this.max = max;
        this.level = max;
    }

    public void change(int diff) {
        level += diff;
        if(level > max) level = max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
