import java.util.Scanner;
import java.util.ArrayList;

public class GameUI {
    Scanner keyboard = new Scanner(System.in);
    GameInitializer gameInitializer = new GameInitializer();

    public void start() {
        System.out.println("""
        Welcome, brave adventurer, to the realms of mystery and peril!
        Your quest is filled with untold dangers and boundless opportunities.
        To aid you on your journey, you may seek guidance by typing 'help'.
        Prepare yourself, for the adventure begins now!
        """);

        while (true) {
            try {
                String choice = keyboard.nextLine().toLowerCase().trim();

                if (!gameInitializer.isPlayerAlive(gameInitializer.getHealth())) {
                    System.out.println("Game Over. You have died!");
                    break;
                }
                if (choice.startsWith("go ")) {
                    String[] parts = choice.split(" ");
                    if (parts.length > 1) {
                        char direction = parts[1].charAt(0);
                        String moveResult = gameInitializer.goDirection(String.valueOf(direction));
                        System.out.println(moveResult);
                    }
                } else {
                    switch (choice) {
                        case "help", "info", "h" -> displayHelp();
                        case "look", "observe", "l" -> lookAround(gameInitializer.getCurrentRoom());
                        case "health", "status", "c" -> showHealth();
                        case "attack", "strike", "shoot" -> attack();
                        case "eat", "consume", "drink", "e" -> useFood();
                        case "inventory", "i", "inv" -> showInventory();
                        case "pick up", "add", "a" -> pickupItems();
                        case "drop", "remove", "r" -> dropItems();
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
        System.out.println("'Help, Info, H' - Displays this help menu.");
        System.out.println("'Look, Observe, L' - Shows details about the current room.");
        System.out.println("'Health, Status, C' - Show Health status.");
        System.out.println("'Attakc, Strike, Shoot, - Attack target:");
        System.out.println("'Eat, Consume, Drink, E' - Uses consumable");
        System.out.println("'Inventory, I, Inv' - Show inventory");
        System.out.println("'Pick Up, Add, A' - Pick up items");
        System.out.println("'Drop, Remove, R' - Drop items");
        System.out.println("'Go + [Direction]' - Moves you in the specified direction. Valid directions are N, S, E, W.");
        System.out.println("'Exit, Bye, Quit' - Exits the game.");
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
        boolean success = gameInitializer.dropItem(itemName);
        if (success) {
            System.out.println(itemName + " has been dropped.");
        } else {
            System.out.println("Could not drop " + itemName);
        }
    }

    private void pickupItems() {
        System.out.print("Enter the name of the item you want to pick up: ");
        String itemName = keyboard.nextLine().trim();

        if (gameInitializer.getCarryingWeight() >= gameInitializer.getMaxCarryWeight()) {
            System.out.println("You cannot carry more items. Your inventory is full.");
        } else {
            boolean success = gameInitializer.pickUpItem(itemName);
            if (success) {
                System.out.println(itemName + " has been picked up.");
            } else {
                System.out.println("Item not found in the room.");
            }
        }
    }

    private void showInventory() {
        ArrayList<Equippable> inventory = gameInitializer.getInventory();
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
        int playerHealth = gameInitializer.getHealth();
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

        boolean success = gameInitializer.useFood(foodName);
        if (success) {
            System.out.println("You used " + foodName + " and your health has been updated.");
        } else {
            System.out.println("Invalid food item or not found in your inventory.");
        }
    }
    private void equipItem() {

    }
    private void attack(){
        gameInitializer.performAttack();
    }
}