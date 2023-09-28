import java.util.ArrayList;

public class PlayerNavigation {
    private ArrayList<Item> inventory = new ArrayList<>();
    private Room currentRoom;

    public PlayerNavigation(){
        inventory = new ArrayList<>();
    }
    public PlayerNavigation(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public String goDirection(String direction) {
        String result = "";
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
            currentRoom.removeItem(item.getItemName());
            inventory.add(item);
            System.out.println(itemName + " has been picked up."); // Add this line for debugging
            return true;  // Return true when item is successfully picked up
        } else {
            System.out.println("Item not found in the room."); // Add this line for debugging
            return false; // Return false when item is not found
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
            return true;
        } else {
            System.out.println("Item not found in the inventory.");
            return false;
        }
    }

    private Item findItemInInventoryByName(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public boolean hasItem() {
        return !inventory.isEmpty();
    }
}
