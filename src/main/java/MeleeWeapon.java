public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String name, String description, int weight, int damage) {
        super(name, description, weight, damage);
    }
    @Override
        public void use() {
        System.out.println("Used melee weapon");
    }
    }

