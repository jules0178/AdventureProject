public class Enemy {
    private String name;
    private String description;
    private int health;
    private final Weapon equippedWeapon;
    private final Room currentRoom;
    private final boolean isDragonBoss;

    public Enemy(String name, String description, int health, Weapon equippedWeapon, Room currentRoom, boolean isDragonBoss) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.equippedWeapon = equippedWeapon;
        this.currentRoom = currentRoom;
        this.isDragonBoss = isDragonBoss;
    }

    public int enemyAttack() {
        return equippedWeapon.getDamage();
    }

    public void hit(int damage) {
        this.health -= damage;
        if (!isAlive()) {
            dropWeapon();
            disappear();
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void dropWeapon() {
        if (!isDragonBoss) {
            this.currentRoom.addWeapon(this.equippedWeapon);
        }
    }

    public void disappear() {
        this.currentRoom.addItem(new Item("Corpse of " + this.name, "The lifeless body of " + this.description, 0));
    }
}