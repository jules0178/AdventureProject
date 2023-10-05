public class Weapon extends Item {
    private int damage;

    public Weapon(String name, String description, int weight, int damage) {
        super(name, description, weight);
        this.damage = damage;
    }

    public boolean canUse() {
        return true;
    }

    public void use() {

    }
}



