import java.util.ArrayList;

public class Adventure {
    private Player player;

    public Adventure() {
        MapCreator gameMapCreator = new MapCreator();
        Room currentRoom = gameMapCreator.buildMap();
        player = new Player(currentRoom, 10);
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public String goDirection(String direction) {
        return player.goDirection(direction);
    }

    public boolean pickUpItem(String itemName) {
        return player.pickUpItem(itemName);
    }

    public boolean dropItem(String itemName) {
        return player.dropItem(itemName);
    }

    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }

    public int getHealth() {
        return player.getHealth();
    }

    public int getCarryingWeight() {
        return player.getCarryingWeight();
    }

    public int getMaxCarryWeight() {
        return player.getMaxCarryWeight();
    }

    public boolean isPlayerAlive(int ignoredHealth) {
        return player.isPlayerAlive(player.getHealth());
    }

    public boolean useFood(String foodName) {
        return player.useFood(foodName);
    }

    public String equipItem(String weaponName) {
        return player.equip(weaponName);
    }

    public boolean weaponExistsInInventory(String weaponName) {
        return player.weaponExistsInInventory(weaponName);
    }
    public void performAttack() {
        player.attack();
    }
    public void unequip(){
        player.unequip();
    }
}
