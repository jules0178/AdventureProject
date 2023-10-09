import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Player player;
    Room startRoom;

    @BeforeEach
    public void setUp() {
        startRoom = new Room("Start Room");
        player = new Player(startRoom, 10);
    }

    // Test for pickUpItem method
    @Test
    public void testPickUpItem() {
        Item sword = new Weapon("Sword", "Sharp weapon", 3, 10);
        startRoom.addItem(sword);
        assertTrue(player.pickUpItem("Sword"));
    }

    // Test for dropItem method
    @Test
    public void testDropItem() {
        Item sword = new Weapon("Sword", "Sharp weapon", 3, 10);
        startRoom.addItem(sword);
        player.pickUpItem("Sword");
        assertTrue(player.dropItem("Sword"));
    }

    // Test for equip method
    @Test
    public void testEquipWeapon() {
        Item sword = new Weapon("Sword", "Sharp weapon", 3, 10);
        startRoom.addItem(sword);
        player.pickUpItem("Sword");
        assertEquals("Sword has been equipped.", player.equip("Sword"));
    }

    // Test for equip method when item does not exist
    @Test
    public void testEquipNonexistentWeapon() {
        assertEquals("Item not found in your inventory or cannot be equipped.", player.equip("Sword"));
    }

    // Test for equip method when item is not a weapon
    @Test
    public void testEquipNonWeaponItem() {
        Item apple = new Item("Apple", "Red fruit", 1);
        startRoom.addItem(apple);
        player.pickUpItem("Apple");
        assertEquals("Item not found in your inventory or cannot be equipped.", player.equip("Apple"));
    }

    // Test for useFood method
    @Test
    public void testUseFood() {
        Item apple = new Food("Apple", "Red fruit", 1, 5);
        startRoom.addItem(apple);
        player.pickUpItem("Apple");
        assertTrue(player.useFood("Apple"));
    }

    // Test for isPlayerAlive method
    @Test
    public void testIsPlayerAlive() {
        assertTrue(player.isPlayerAlive(5));
        assertFalse(player.isPlayerAlive(0));
    }

    // Test for getInventory method
    @Test
    public void testGetInventory() {
        Item sword = new Weapon("Sword", "Sharp weapon", 3, 10);
        startRoom.addItem(sword);
        player.pickUpItem("Sword");
        assertEquals(1, player.getInventory().size());
    }
}
