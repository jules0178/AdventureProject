import java.util.ArrayList;

public class PlayerNavigation {
    private final ArrayList<Equippable> inventory = new ArrayList<>();
    private Room currentRoom;
    private int health;
    private int carryingWeight; // Actual weight the player is carrying
    private Weapon equippedWeapon;
    private final int maxCarryWeight; // Maximum carrying Weight

    public PlayerNavigation(Room startRoom, int carryingWeight) {
        this.currentRoom = startRoom;
        this.health = 10;
        this.maxCarryWeight = 30;
        this.carryingWeight = carryingWeight;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String goDirection(String direction) {
        String result;
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
        return result;
    }

    public boolean pickUpItem(String itemName) {
        Equippable equippable = currentRoom.findItemByName(itemName);
        if (equippable != null) {
            int totalWeight = carryingWeight + equippable.getWeight();
            if (totalWeight <= maxCarryWeight) {
                currentRoom.removeItem(equippable.getItemName());
                inventory.add(equippable);
                carryingWeight = totalWeight;
                return true; // Return true, when item has been picked up
            } else {
                return false; // Return false, when item is too heavy to carry
            }
        } else {
            return false; // Return false, when item does not exist in room
        }
    }

    public boolean dropItem(String itemName) {
        Equippable equippableToDrop = null;
        for (Equippable equippable : inventory) {
            if (equippable.getItemName().equals(itemName)) {
                equippableToDrop = equippable;
                break;
            }
        }
        if (equippableToDrop != null) {
            inventory.remove(equippableToDrop);
            currentRoom.addItem(equippableToDrop);
            carryingWeight -= equippableToDrop.getWeight();
            return true; // Return true, when item has been removed from inventory
        } else {
            return false; // Return false, when item does not exist in inventory
        }
    }

    public ArrayList<Equippable> getInventory() {
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
        Equippable equippable = findFoodByName(foodName);
        if (equippable instanceof Consumable consumableItem) {
            int healthPoints = consumableItem.getHealthPoints();

            increaseHealth(healthPoints);

            inventory.remove(consumableItem);
            carryingWeight -= consumableItem.getWeight();

            return true;
        } else {
return false;
        }
    }

    private Equippable findFoodByName(String foodName) {
        for (Equippable equippable : inventory) {
            if (equippable.getItemName().equalsIgnoreCase(foodName) && equippable instanceof Consumable) {
                return equippable;
            }
        }
return null;
    }

    public void equip(Weapon newWeapon) {
        this.equippedWeapon = newWeapon;
    }

    public void attack() {
        if (equippedWeapon == null) {
            System.out.println("You have no weapon equipped.");
            return;
        }
        if (!equippedWeapon.canUse()) {
            System.out.println("Your weapon is out of ammunition.");
            return;
        }
        equippedWeapon.use();
        System.out.println("You attacked!");
    }
}
