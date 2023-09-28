import java.util.Scanner;

public class GameUI {
    Scanner keyboard = new Scanner(System.in);
    GameInitializer gameInitializer = new GameInitializer();
    PlayerNavigation playerNavigation = new PlayerNavigation(new MapCreator().buildMap());

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
                        case "look", "observe" -> lookAround();
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
        System.out.println("1. 'Start' - Starts the game.");
        System.out.println("2. 'Help' - Displays this help menu.");
        System.out.println("3. 'Look' - Shows details about the current room.");
        System.out.println("4. 'Go + [Direction]' - Moves you in the specified direction. Valid directions are N, S, E, W.");
        System.out.println("5. 'Exit' - Exits the game.");
    }

    private void lookAround() {
        Room currentRoom = gameInitializer.getCurrentRoom();
        System.out.println("You are in: " + currentRoom.toString());
        System.out.println("You can go: " + currentRoom.availableDirections());
    }
}