import java.util.Random;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String n) {
        this.name = n;
        this.description = generateRandomDescription();
    }

    private String generateRandomDescription() {
        String[] adjectives = {"dark", "spooky", "bright", "airy", "moist", "mysterious"};
        String[] furnishings = {"with an old wooden chair", "with a broken table", "with a flickering lantern", "with a tattered rug"};

        Random random = new Random();

        String adjective = adjectives[random.nextInt(adjectives.length)];
        String furnishing = furnishings[random.nextInt(furnishings.length)];

        return "A " + adjective + " room " + furnishing;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        if(this.north != north) {
            this.north = north;
            north.setSouth(this);  // To make sure the connection is two-way
        }
    }


    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        if(this.south != south) {
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
}
