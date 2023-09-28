public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public String getItemDescription() {
        return description;
    }

    public void setItemDescription(String description) {
        this.description = description;
    }
}
