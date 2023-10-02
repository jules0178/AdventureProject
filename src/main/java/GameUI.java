import java.util.Scanner;
import java.util.ArrayList;

public class GameUI {
    Scanner keyboard = new Scanner(System.in);
    PlayerNavigation playerNavigation = new PlayerNavigation(new MapCreator().buildMap(), 10);

    public void start() {
        System.out.println("Welcome to Adventure game. Please type 'help' for instructions.");

        while (true) {
            try {
                String choice = keyboard.nextLine().toLowerCase().trim();

                if (!playerNavigation.isPlayerAlive(playerNavigation.getHealth())) {
                    System.out.println("Game Over. You have died!");
                    break;
                }
                if (choice.startsWith("go ")) {
                    String[] parts = choice.split(" ");
                    if (parts.length > 1) {
                        char direction = parts[1].charAt(0);
                        String moveResult = playerNavigation.goDirection(String.valueOf(direction));
                        System.out.println(moveResult);
                    }
                } else {
                    switch (choice) {
                        case "help", "info" -> displayHelp();
                        case "look", "observe" -> lookAround(playerNavigation.getCurrentRoom());
                        case "health", "status" -> showHealth();
                        case "eat", "consume" -> useFood();
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
        System.out.println("'Help' - Displays this help menu.");
        System.out.println("'Look' - Shows details about the current room.");
        System.out.println("'Health - Show Health status.");
        System.out.println("'Inventory' - Show inventory");
        System.out.println("'Eat' - Uses consumable");
        System.out.println("'Pick up' - Pick up items");
        System.out.println("'Drop' - Drop items");
        System.out.println("'Go + [Direction]' - Moves you in the specified direction. Valid directions are N, S, E, W.");
        System.out.println("'Exit' - Exits the game.");
    }

    private void lookAround(Room currentRoom) {
        System.out.println("You are in: " + currentRoom.toString());
        System.out.println("You can go: " + currentRoom.availableDirections());

        ArrayList<Equippable> itemsInRoom = currentRoom.getItems();

        if (!itemsInRoom.isEmpty()) {
            System.out.println("Items in this room:");
            for (Equippable equippable : itemsInRoom) {
                System.out.println("- " + equippable.getItemName() + ": " + equippable.getDescription() + ", Weight: " + equippable.getWeight());
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
        ArrayList<Equippable> inventory = playerNavigation.getInventory();
        if (!inventory.isEmpty()) {
            System.out.println("Inventory:");
            for (Equippable equippable : inventory) {
                System.out.println("- " + equippable.getItemName() + ", Weight: " + equippable.getWeight());
            }
        } else {
            System.out.println("Inventory is empty.");
        }
    }

    private void showHealth() {
        int playerHealth = playerNavigation.getHealth();
        String healthStatus = getHealthStatus(playerHealth);

        System.out.println("Health: " + playerHealth + " - " + healthStatus);

        if (playerHealth <= 0) {
            System.out.println("Game Over. You have died!");
            System.exit(0);
        }
    }
    private String getHealthStatus(int health) {
        if (health >= 80) {
            return "Excellent";
        } else if (health >= 60) {
            return "Fine";
        } else if (health >= 40) {
            return "Caution";
        } else if (health >= 20) {
            return "Danger!";
        } else if( health > 0){
            return "Critical!";
        } else {
            return "You have died!";
        }
    }
    private void useFood() {
        System.out.print("Enter the name of the food you want to use: ");
        String foodName = keyboard.nextLine().trim();

        boolean success = playerNavigation.useFood(foodName);
        if (success) {
            System.out.println("You used " + foodName + " and your health has been updated.");
        } else {
            System.out.println("Invalid food item or not found in your inventory.");
        }
    }

}