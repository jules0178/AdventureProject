public class Food extends Item {
    private int healthPoints;
    public Food(String name, String description, int weight, int healthPoints) {
        super(name, description, weight);
        this.healthPoints = healthPoints;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
}
