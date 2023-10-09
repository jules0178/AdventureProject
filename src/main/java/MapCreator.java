public class MapCreator{
    public Room buildMap() {
        Room room1 = new Room("Room 1");
        room1.addItem(new Item("lantern", "A lantern that emits a soft glow", 10));
        room1.addItem(new RangedWeapon("crossbow", "an old tattered crossbow with a single bolt", 4, 8, 1));
        room1.addItem(new Food("flask", "An odd red liquid boils inside the flask", 1, -20));

        Room room2 = new Room("Room 2");
        room2.addItem(new MeleeWeapon("sword", "Sharp weapon", 3, 10));
        room2.addItem(new Food("apple", "Red fruit", 1, 20));

        Room room3 = new Room("Room 3");
        room3.addItem(new Item("key", "Unlocks a locked door", 10));
        room3.addItem(new Food("stale bread", "Hard and tasteless", 1, -10));

        // Adding a goblin enemy with a dagger
        Weapon goblinWeapon = new Weapon("dagger", "A rusty old dagger", 1, 3);
        Enemy goblin = new Enemy("Goblin", "An ugly small green creature", 10, goblinWeapon, room3, false);
        room3.addEnemy(goblin);

        Room room4 = new Room("Room 4");
        room4.addItem(new MeleeWeapon("axe", "Can be used to break a weak door or fight enemy", 10, 10));
        room4.addItem(new Food("healing potion", "Restores health", 1, 30));

        // Adding a troll enemy with a club
        Weapon trollWeapon = new Weapon("club", "A large wooden club", 15, 15);
        Enemy troll = new Enemy("Troll", "A menacing troll with a big club", 15, trollWeapon, room4, false);
        room4.addEnemy(troll);

        Room room5 = new Room("Room 5");
        room5.setDescription("A glorious room filled with treasure and wonders beyond imagination");
        room5.addItem(new Item("treasure", "You have won, you are rich", 10));
        room5.addItem(new Food("cursed amulet", "A mysterious amulet with a dark aura", 1, -50));

        // Adding a dragon boss guarding the treasure
        Weapon dragonWeapon = new Weapon("fire breath", "The fiery breath of a fearsome dragon", 30, 30);
        Enemy dragonBoss = new Enemy("Dragon", "A colossal dragon guarding the treasure", 100, dragonWeapon, room5, true);
        room5.addEnemy(dragonBoss);

        Room room6 = new Room("Room 6");
        room6.addItem(new Item("book", "A book of knowledge", 10));
        room6.addItem(new Food("fresh berries", "Sweet and delicious", 1, 15));
        room6.addItem(new RangedWeapon("crossbow", "an old tattered crossbow with a single bolt", 4, 8, 1));

        Room room7 = new Room("Room 7");
        room7.addItem(new Item("torch", "You can see better", 10));
        room7.addItem(new Food("spoiled meat", "Rancid and inedible", 1, -15));

        Room room8 = new Room("Room 8");
        room8.addItem(new Item("painting", "A beautiful masterpiece", 10));
        room8.addItem(new Food("honey cake", "Delicious and energizing", 1, 25));

        Room room9 = new Room("Room 9");
        room9.addItem(new Item("skull", "It's a creepy skull", 10));
        room9.addItem(new Food("enchanted elixir", "Magical elixir of power", 1, 50));

        Room room10 = new Room("Room 10");
        room10.addItem(new Item("potion", "A mysterious potion", 10));
        room10.addItem(new Food("moldy cheese", "Moldy and disgusting", 1, -5));

        // Adding a ghost enemy with a haunting presence
        Enemy ghost = new Enemy("Ghost", "A spooky ghost with a haunting presence", 5, null, room10, false);
        room10.addEnemy(ghost);

        Room room11 = new Room("Room 11");
        room11.addItem(new Item("shield", "Protective shield", 10));
        room11.addItem(new Food("crispy bacon", "Savory and delightful", 1, 30));

        Room room12 = new Room("Room 12");
        room12.addItem(new Item("gem", "A precious gemstone", 10));
        room12.addItem(new Food("golden apple", "Gleaming with magic", 1, 40));

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
