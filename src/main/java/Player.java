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
        Item item = currentRoom.findItemByName(itemName);
        if (item != null) {
            int totalWeight = carryingWeight + item.getWeight();
            if (totalWeight <= maxCarryWeight) {
                currentRoom.removeItem(item.getItemName());
                inventory.add(item);
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

    public boolean equip(String findWeapon) {
        Weapon weaponToEquip = findWeaponByName(findWeapon);

        if (weaponToEquip != null) {
            equippedWeapon = weaponToEquip;
            return true;
        } else {
            return false; // Return false, if weapon is not in the players inventory
        }
    }

    private Weapon findWeaponByName(String findWeapon) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(findWeapon) && item instanceof Weapon) {
                return (Weapon) item;
            }
        }
        return null;

    }
    public boolean attack() {
        if (equippedWeapon == null) {
            System.out.println("You have no weapon equipped.");
            return false;
        }
        if (!equippedWeapon.canUse()) {
            System.out.println("Your weapon is out of ammunition.");
            return false;
        }
        equippedWeapon.use();
        System.out.println("You attacked!");
        return true;
    }
}
