import java.util.Scanner;

public class UserInterface {
    Scanner keyboard = new Scanner(System.in);
    Adventure adventure = new Adventure();

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
                String choice = keyboard.nextLine().toLowerCase().trim(); // Convert input to lowercase and trim spaces

                if (choice.startsWith("go ")) {
                    String[] parts = choice.split(" "); // Split the choice by space
                    if (parts.length > 1) {
                        char direction = parts[1].charAt(0);
                        adventure.goDirection(direction);
                    }
                } else {
                    switch (choice) {
                        case "start" -> {
                            System.out.println("Program starting.....");
                            System.out.println(adventure.getCurrentRoom().toString());
                        }
                        case "help" -> displayHelp();
                        case "look" -> lookAround();
                        case "exit" -> {
                            System.out.println("Exiting Program -------- ");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
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
        Room currentRoom = adventure.getCurrentRoom();
        System.out.println("You are in: " + currentRoom.toString());
        System.out.println("You can go: " + currentRoom.availableDirections());
    }
}