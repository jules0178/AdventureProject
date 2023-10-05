import java.util.ArrayList;
import java.util.Random;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private ArrayList<Item> equip;

    public Room(String n) {
        this.name = n;
        this.description = generateRandomDescription();
        this.equip = new ArrayList<>();
    }

    private String generateRandomDescription() {
        String[] adjectives = {"dark", "spooky", "bright", "airy", "moist", "mysterious", "silent", "echoing", "ornate", "simple"};
        String[] furnishings = {"with an old wooden chair", "with a broken table", "with a flickering lantern", "with a tattered rug", "with a dusty bookshelf", "with a hidden compartment", "with a grand fireplace", "with a mysterious painting"};

        Random random = new Random();

        String adjective = adjectives[random.nextInt(adjectives.length)];
        String furnishing = furnishings[random.nextInt(furnishings.length)];

        return "A " + adjective + " room " + furnishing;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        if (this.north != north) {
            this.north = north;
            north.setSouth(this);  // To make sure the connection is two-way
        }
    }


    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        if (this.south != south) {
            this.south = south;
            south.setNorth(this);  // To make sure the connection is two-way
        }
    }


    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        if (this.east != east) {
            this.east = east;
            east.setWest(this);
        }
    }

    public void setWest(Room west) {
        if (this.west != west) {
            this.west = west;
            west.setEast(this);
        }
    }


    public Room getWest() {
        return west;
    }

    @Override
    public String toString() {
        return "Room name: " + this.name + ", Description: " + this.description;
    }

    public String availableDirections() {
        StringBuilder sb = new StringBuilder();
        if (this.getEast() != null) sb.append("East ");
        if (this.getWest() != null) sb.append("West ");
        if (this.getNorth() != null) sb.append("North ");
        if (this.getSouth() != null) sb.append("South ");
        return sb.toString();
    }

    public Item findItemByName(String itemName) {
        for (Item item : equip) {  // Assuming 'items' is your ArrayList of Item objects in the Room class
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        equip.add(item);
    }

    public void removeItem(String itemName) {
        for (Item item : equip) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                equip.remove(item);
                return;  // Return the removed item
            }
        }
    }

    public ArrayList<Item> getItems() {
        return equip;
    }
}