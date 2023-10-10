import java.util.ArrayList;

public class Player {
    private final ArrayList<Item> inventory = new ArrayList<>();
    private Room currentRoom;
    private int health;
    private int carryingWeight; // Actual weight the player is carrying
    private Weapon equippedWeapon;
    private final int maxCarryWeight; // Maximum carrying Weight

    public Player(Room startRoom, int carryingWeight) {
        this.currentRoom = startRoom;
        this.health = 100;
        this.maxCarryWeight = 30;
        this.carryingWeight = carryingWeight;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String goDirection(String direction) {
        String result;
        ArrayList<Enemy> enemiesInRoom = currentRoom.getEnemies();
        if (!enemiesInRoom.isEmpty()) {
            result = "There are enemies in this room. You cannot proceed";
        } else {
            switch (direction.toLowerCase()) {
                case "n", "north" -> {
                    if (currentRoom.getNorth() != null) {
                        currentRoom = currentRoom.getNorth();
                        result = "You are now in: " + currentRoom.toString();
                    } else {
                        result = "You cannot go north from here.";
                    }
                }
                case "s", "south" -> {
                    if (currentRoom.getSouth() != null) {
                        currentRoom = currentRoom.getSouth();
                        result = "You are now in: " + currentRoom.toString();
                    } else {
                        result = "You cannot go south from here.";
                    }
                }
                case "w", "west" -> {
                    if (currentRoom.getWest() != null) {
                        currentRoom = currentRoom.getWest();
                        result = "You are now in: " + currentRoom.toString();
                    } else {
                        result = "You cannot go west from here.";
                    }
                }
                case "e", "east" -> {
                    if (currentRoom.getEast() != null) {
                        currentRoom = currentRoom.getEast();
                        result = "You are now in: " + currentRoom.toString();
                    } else {
                        result = "You cannot go east from here.";
                    }
                }
                default -> result = "Invalid Direction. Please try again with another.";
            }
        }
        currentRoom.checkForEnemies();

        return result;
    }


    public boolean pickUpItem(String itemName) {
        for (Item item : currentRoom.getItems()) {
            // Use equalsIgnoreCase for case-insensitive comparison and contains for partial matching
            if (item.getItemName().equalsIgnoreCase(itemName) || item.getItemName().toLowerCase().contains(itemName.toLowerCase())) {
                int totalWeight = carryingWeight + item.getWeight();
                if (totalWeight <= maxCarryWeight) {
                    currentRoom.removeItem(item.getItemName());
                    inventory.add(item);
                    carryingWeight = totalWeight;
                    return true; // Return true, when item has been picked up
                } else {
                    return false; // Return false, when item is too heavy to carry
                }
            }
        }
        return false; // Return false, when item does not exist in room
    }

    public boolean dropItem(String itemName) {
        Item itemToDrop = null;
        for (Item item : inventory) {
            if (item.getItemName().equals(itemName)) {
                itemToDrop = item;
                break;
            }
        }
        if (itemToDrop != null) {
            inventory.remove(itemToDrop);
            currentRoom.addItem(itemToDrop);
            carryingWeight -= itemToDrop.getWeight();
            return true; // Return true, when item has been removed from inventory
        } else {
            return false; // Return false, when item does not exist in inventory
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public void increaseHealth(int amount) {
        health += amount;
    }

    public int getCarryingWeight() {
        return carryingWeight;
    }

    public int getMaxCarryWeight() {
        return maxCarryWeight;
    }

    public boolean isPlayerAlive(int health) {
        return health > 0;
    }

    public boolean useFood(String foodName) {
        Item item = findFoodByName(foodName);
        if (item instanceof Food foodItem) {
            int healthPoints = foodItem.getHealthPoints();

            increaseHealth(healthPoints);

            inventory.remove(foodItem);
            carryingWeight -= foodItem.getWeight();

            return true;
        } else {
            return false;
        }
    }

    private Item findFoodByName(String foodName) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(foodName) && item instanceof Food) {
                return item;
            }
        }
                return null;
    }
    public void unequip() {
        if (equippedWeapon != null) {
            equippedWeapon = null;
            System.out.println("Weapon has been unequipped");
        }else{
            System.out.println("No weapon is equipped");
        }

    }

    public boolean weaponExistsInInventory(String weaponName) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(weaponName) && item instanceof Weapon) {
                return true;
            }
        }
        return false;
    }

    public String equip(String findWeapon) {
        Weapon weaponToEquip = findWeaponByName(findWeapon);

        if (weaponToEquip != null) {
            if (equippedWeapon != null) {
                unequip();
            }
            equippedWeapon = weaponToEquip;
            return findWeapon + " has been equipped.";
        } else {
            return "Item not found in your inventory or cannot be equipped.";
        }
    }

    private Weapon findWeaponByName(String findWeapon) {
        System.out.println("Searching for weapon: " + findWeapon);
        for (Item item : inventory) {
            System.out.println("Checking item: " + item.getItemName());
            if (item.getItemName().equalsIgnoreCase(findWeapon) && item instanceof Weapon) {
                return (Weapon) item;
            }
        }
        return null;
    }
    public void attack(String enemyName) {
        if (equippedWeapon == null) {
            System.out.println("You have no weapon equipped.");
            return;
        }

        // Check if the equipped weapon is ranged
        boolean isRangedWeapon = equippedWeapon instanceof RangedWeapon;

        // Calculate the damage based on the equipped weapon
        int damage = equippedWeapon.getDamage();

        Enemy targetEnemy = currentRoom.findEnemyByName(enemyName);

        if (targetEnemy != null) {
            boolean isDragonBoss = targetEnemy.isDragonBoss();

            while (isPlayerAlive(health) && targetEnemy.isAlive()) {
                // Player attacks the enemy
                System.out.println("You attack the " + targetEnemy.getName() + " with " + equippedWeapon.getName() + " for " + damage + " damage.");

                // Call the enemy's hit method to apply damage
                targetEnemy.hit(damage);

                // Check if the enemy is defeated
                if (!targetEnemy.isAlive()) {
                    targetEnemy.dropWeapon();
                    targetEnemy.disappear();
                    currentRoom.removeEnemy(targetEnemy);
                    System.out.println("You have defeated the " + targetEnemy.getName() + ".");
                    break; // Exit the loop if the enemy is defeated
                }

                if (isDragonBoss && isRangedWeapon) {
                    // Dragon boss can retaliate with ranged attack
                    int enemyDamage = targetEnemy.enemyAttack();
                    health -= enemyDamage;
                    System.out.println("The " + targetEnemy.getName() + " (Dragon Boss) retaliates with a ranged attack, dealing " + enemyDamage + " damage.");

                    // Check if the player is defeated
                    if (!isPlayerAlive(health)) {
                        System.out.println("You have been defeated by the " + targetEnemy.getName() + " (Dragon Boss).");
                        break; // Exit the loop if the player is defeated
                    }
                } else if(!isRangedWeapon){
                    int enemyDamage = targetEnemy.enemyAttack();
                    health -= enemyDamage;
                    System.out.println("The " + targetEnemy.getName() + " attacks you for " + enemyDamage + " damage.");

                    if (!isPlayerAlive(health)) {
                        System.out.println("You have been defeated by the " + targetEnemy.getName());
                        break;
                    }
                }
            }
        } else {
            System.out.println("There are no enemies in the room named '" + enemyName + "' to attack.");
        }
    }

}
