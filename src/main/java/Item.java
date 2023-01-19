public class Item {
    private String name;
    private Boolean done;

    public Item(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.name;
    }



}
