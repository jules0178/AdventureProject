import java.util.Scanner;
import java.util.ArrayList;

public class GameUI {
    Scanner keyboard = new Scanner(System.in);
    GameInitializer gameInitializer = new GameInitializer();
    PlayerNavigation playerNavigation = new PlayerNavigation(new MapCreator().buildMap(), 10);

    public void start() {
        System.out.println("""
                Welcome to Adventure game. Please type:
                Start
                Help
                Look
                Go + Direction
                Exit
                """);

        while (true) {
            try {
                String choice = keyboard.nextLine().toLowerCase().trim();

                if (choice.startsWith("go ")) {
                    String[] parts = choice.split(" ");
                    if (parts.length > 1) {
                        char direction = parts[1].charAt(0);
                        String moveResult = playerNavigation.goDirection(String.valueOf(direction));
                        System.out.println(moveResult);
                    }
                } else {
                    switch (choice) {
                        case "start", "begin" -> {
                            System.out.println("Program starting.....");
                            System.out.println(gameInitializer.getCurrentRoom().toString());
                        }
                        case "help", "info" -> displayHelp();
                        case "look", "observe" -> lookAround(playerNavigation.getCurrentRoom());
                        case "inventory" -> showInventory();
                        case "pick up" -> pickupItems();
                        case "drop" -> dropItems();
                        case "exit", "bye", "quit" -> {
                            System.out.println("Exiting Program -------- ");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void displayHelp() {
        System.out.println("Here are some helpful instructions:");
        System.out.println("'Start' - Starts the game.");
        System.out.println("'Help' - Displays this help menu.");
        System.out.println("'Look' - Shows details about the current room.");
        System.out.println("'Inventory' - Show inventory");
        System.out.println("'Pick up' - Pick up items");
        System.out.println("'Drop' - Drop items");
        System.out.println("'Go + [Direction]' - Moves you in the specified direction. Valid directions are N, S, E, W.");
        System.out.println("'Exit' - Exits the game.");
    }

    private void lookAround(Room currentRoom) {
        System.out.println("You are in: " + currentRoom.toString());
        System.out.println("You can go: " + currentRoom.availableDirections());

        ArrayList<Item> itemsInRoom = currentRoom.getItems();

        if (!itemsInRoom.isEmpty()) {
            System.out.println("Items in this room:");
            for (Item item : itemsInRoom) {
                System.out.println("- " + item.getItemName() + ": " + item.getDescription() + ", Weight: " + item.getWeight());
            }
        } else {
            System.out.println("There are no items in this room.");
        }
    }


    private void dropItems() {
        System.out.print("Enter the name of the item you want to drop: ");
        String itemName = keyboard.nextLine().trim();
        boolean success = playerNavigation.dropItem(itemName);
        if (success) {
            System.out.println(itemName + " has been dropped.");
        } else {
            System.out.println("Could not drop " + itemName);
        }
    }

    private void pickupItems() {
        System.out.print("Enter the name of the item you want to pick up: ");
        String itemName = keyboard.nextLine().trim();

        if (playerNavigation.getCarryingWeight() >= playerNavigation.getMaxCarryWeight()) {
            System.out.println("You cannot carry more items. Your inventory is full.");
        } else {
            boolean success = playerNavigation.pickUpItem(itemName);
            if (success) {
                System.out.println(itemName + " has been picked up.");
            } else {
                System.out.println("Item not found in the room.");
            }
        }
    }

    private void showInventory() {
        ArrayList<Item> inventory = playerNavigation.getInventory();
        if (!inventory.isEmpty()) {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getItemName() + ", Wegiht: " + item.getWeight());
            }
        } else {
            System.out.println("Inventory is empty.");
        }
    }
}