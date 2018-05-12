package Entity.Stats;

public class Stats {

    Resource hp, power;
    int attack, defense, speed, processing, range;

    public Stats(Resource hp, Resource power, int attack, int defense, int speed, int processing, int range) {
        this.hp = hp;
        this.power = power;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.processing = processing;
        this.range = range;
    }


    public static Stats defaultStats() {
        Resource hp = new Resource(10);
        Resource power = new Resource(10);
        return new Stats(hp,power,10,10,10,10,64);
    }



    public Resource getHp() {
        return hp;
    }

    public void setHp(Resource hp) {
        this.hp = hp;
    }

    public Resource getPower() {
        return power;
    }

    public void setPower(Resource power) {
        this.power = power;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getProcessing() {
        return processing;
    }

    public void setProcessing(int processing) {
        this.processing = processing;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
