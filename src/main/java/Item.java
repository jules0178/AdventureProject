public class Item {
    private String name;
    private String description;
    private int weight;

    public Item(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getItemName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }
}
