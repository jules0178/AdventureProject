public class GameInitializer {
    private Room currentRoom;

    public GameInitializer() {
        MapCreator gameMapCreator = new MapCreator();
        currentRoom = gameMapCreator.buildMap();
    }

    public Room getCurrentRoom(){
        return currentRoom;
}

}
