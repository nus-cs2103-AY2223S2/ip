package tasks;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    public String toSaveFormat() {
        return String.format("T,%s", this.name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
