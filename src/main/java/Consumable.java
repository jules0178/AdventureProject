public class Consumable extends Equippable {
    private int healthPoints;
    public Consumable(String name, String description, int weight, int healthPoints) {
        super(name, description, weight);
        this.healthPoints = healthPoints;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
}
