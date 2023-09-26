public class Adventure {
    private Room currentRoom;

    public Adventure() {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room room5 = new Room("Room 5");
        room5.setDescription("A glorious room filled with treasure and wonders beyond imagination");
        Room room6 = new Room("Room 6");
        Room room7 = new Room("Room 7");
        Room room8 = new Room("Room 8");
        Room room9 = new Room("Room 9");

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setNorth(room5);
        room8.setEast(room9);
        room8.setWest(room7);

        room9.setWest(room8);
        room9.setNorth(room6);
        currentRoom = room1;
}
public Room getCurrentRoom(){
        return currentRoom;
}
    public void goDirection(char direction) {
        switch (direction) {
            case 'n' -> {
                if (currentRoom.getNorth() != null) {
                    currentRoom = currentRoom.getNorth();
                    System.out.println("You are now in: " + currentRoom.toString());
                } else {
                    System.out.println("You cannot go north from here.");
                }
            }
            case 's' -> {
                if (currentRoom.getSouth() != null) {
                    currentRoom = currentRoom.getSouth();
                    System.out.println("You are now in: " + currentRoom.toString());
                } else {
                    System.out.println("You cannot go south from here.");
                }
            }
            case 'w' -> {
                if (currentRoom.getWest() != null) {
                    currentRoom = currentRoom.getWest();
                    System.out.println("You are now in: " + currentRoom.toString());
                } else {
                    System.out.println("You cannot go west from here.");
                }
            }
            case 'e' -> {
                if (currentRoom.getEast() != null) {
                    currentRoom = currentRoom.getEast();
                    System.out.println("You are now in: " + currentRoom.toString());
                } else {
                    System.out.println("You cannot go east from here.");
                }
            }
            default -> System.out.println("Invalid Direction. Please try again with another.");
        }
    }
}
