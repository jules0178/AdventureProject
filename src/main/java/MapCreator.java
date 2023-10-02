public class MapCreator {
    public Room buildMap() {
        Room room1 = new Room("Room 1");
        room1.addItem(new Equippable("lantern", "A lantern that emits a soft glow", 10));
        room1.addItem(new Consumable("flask", "An odd red liquid boils inside the flask", 1, -20));
        Room room2 = new Room("Room 2");
        room2.addItem(new Equippable("sword", "Sharp weapon", 10));
        room2.addItem(new Consumable("apple", "Red fruit", 1, 20));

        Room room3 = new Room("Room 3");
        room3.addItem(new Equippable("key", "Unlocks a locked door", 10));
        room3.addItem(new Consumable("stale bread", "Hard and tasteless", 1, -10));

        Room room4 = new Room("Room 4");
        room4.addItem(new Equippable("axe", "Can be used to break a weak door or fight enemy", 10));
        room4.addItem(new Consumable("healing potion", "Restores health", 1, 30));

        Room room5 = new Room("Room 5");
        room5.setDescription("A glorious room filled with treasure and wonders beyond imagination");
        room5.addItem(new Equippable("treasure", "You have won, you are rich", 10));
        room5.addItem(new Consumable("cursed amulet", "A mysterious amulet with a dark aura", 1, -50));

        Room room6 = new Room("Room 6");
        room6.addItem(new Equippable("book", "A book of knowledge", 10));
        room6.addItem(new Consumable("fresh berries", "Sweet and delicious", 1, 15));

        Room room7 = new Room("Room 7");
        room7.addItem(new Equippable("torch", "You can see better", 10));
        room7.addItem(new Consumable("spoiled meat", "Rancid and inedible", 1, -15));

        Room room8 = new Room("Room 8");
        room8.addItem(new Equippable("painting", "A beautiful masterpiece", 10));
        room8.addItem(new Consumable("honey cake", "Delicious and energizing", 1, 25));

        Room room9 = new Room("Room 9");
        room9.addItem(new Equippable("skull", "It's a creepy skull", 10));
        room9.addItem(new Consumable("enchanted elixir", "Magical elixir of power", 1, 50));

        Room room10 = new Room("Room 10");
        room10.addItem(new Equippable("potion", "A mysterious potion", 10));
        room10.addItem(new Consumable("moldy cheese", "Moldy and disgusting", 1, -5));

        Room room11 = new Room("Room 11");
        room11.addItem(new Equippable("shield", "Protective shield", 10));
        room11.addItem(new Consumable("crispy bacon", "Savory and delightful", 1, 30));

        Room room12 = new Room("Room 12");
        room12.addItem(new Equippable("gem", "A precious gemstone", 10));
        room12.addItem(new Consumable("golden apple", "Gleaming with magic", 1, 40));

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

        room5.setNorth(room12);
        room12.setSouth(room5);

        room12.setWest(room11);
        room11.setEast(room12);

        return room1;
    }
}
