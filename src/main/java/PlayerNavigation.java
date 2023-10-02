import java.util.ArrayList;

public class PlayerNavigation {
    private ArrayList<Item> inventory = new ArrayList<>();
    private Room currentRoom;
    private int health;
    private int carryingWeight; // Actual weight the player is carrying
    private int maxCarryWeight; // Maximum carrying Weight

    public PlayerNavigation(Room startRoom, int maxCarryWeight) {
        this.currentRoom = startRoom;
        this.health = 10;
        this.maxCarryWeight = maxCarryWeight;
        this.carryingWeight = 0;
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

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int getCarryingWeight() {
        return carryingWeight;
    }

    public int getMaxCarryWeight() {
        return maxCarryWeight;
    }
}
