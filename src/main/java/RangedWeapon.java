public class RangedWeapon extends Weapon {
    private int remainingShots;

    public RangedWeapon(String name, String description, int weight, int damage, int remainingShots) {
        super(name, description, weight, damage);
        this.remainingShots = remainingShots;
    }

    @Override
    public boolean canUse() {
        return remainingShots > 0;
    }

    @Override
    public void use() {
        if (canUse()) {
            remainingShots--;

        }
    }
}
