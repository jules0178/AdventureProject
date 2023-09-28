public class MapCreator {
    public Room buildMap() {
        Room room1 = new Room("Room 1");
        room1.addItem(new Item("lantern", "A lantern that emits a soft glow"));

        Room room2 = new Room("Room 2");
        room2.addItem(new Item("sword", "Sharp weapon"));

        Room room3 = new Room("Room 3");
        room3.addItem(new Item("key", "Unlocks a locked door"));

        Room room4 = new Room("Room 4");
        room4.addItem(new Item("axe", "Can be used to break a weak door or fight enemy"));

        Room room5 = new Room("Room 5");
        room5.setDescription("A glorious room filled with treasure and wonders beyond imagination");
        room5.addItem(new Item("treasure", "You have won you are rich"));

        Room room6 = new Room("Room 6");
        room6.addItem(new Item("book", "smort"));

        Room room7 = new Room("Room 7");
        room7.addItem(new Item("torch", "You can see better"));

        Room room8 = new Room("Room 8");
        room8.addItem(new Item("painting", "nice"));

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

        return room1;
    }
}
