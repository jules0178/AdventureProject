import java.util.ArrayList;

public class GameInitializer {
    private PlayerNavigation playerNavigation;

    public GameInitializer() {
        MapCreator gameMapCreator = new MapCreator();
        Room currentRoom = gameMapCreator.buildMap();
        playerNavigation = new PlayerNavigation(currentRoom, 10);
    }

    public Room getCurrentRoom() {
        return playerNavigation.getCurrentRoom();
    }

    public String goDirection(String direction) {
        return playerNavigation.goDirection(direction);
    }

    public boolean pickUpItem(String itemName) {
        return playerNavigation.pickUpItem(itemName);
    }

    public boolean dropItem(String itemName) {
        return playerNavigation.dropItem(itemName);
    }

    public ArrayList<Equippable> getInventory() {
        return playerNavigation.getInventory();
    }

    public int getHealth() {
        return playerNavigation.getHealth();
    }

    public int getCarryingWeight() {
        return playerNavigation.getCarryingWeight();
    }

    public int getMaxCarryWeight() {
        return playerNavigation.getMaxCarryWeight();
    }

    public boolean isPlayerAlive(int ignoredHealth) {
        return playerNavigation.isPlayerAlive(playerNavigation.getHealth());
    }

    public boolean useFood(String foodName) {
        return playerNavigation.useFood(foodName);
    }
    public boolean performAttack() {
        return playerNavigation.attack();
    }
}
