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
            System.out.println("You used a ranged weapon");
            System.out.println("Ammo remaining: " + remainingShots);
            remainingShots--;
        }else {
            System.out.println("No remaining shots.");
        }
    }
}

