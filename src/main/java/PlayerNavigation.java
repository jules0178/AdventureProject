public class PlayerNavigation {
    private Room currentRoom;

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
}
