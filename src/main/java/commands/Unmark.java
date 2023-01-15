package commands;
import data.MyData;

public class Unmark extends Commands {
    private final int id;

    public Unmark(int id) {
        this.id = id;
    }
    public void execute(MyData data) {
        data.markUndone(this.id);
        System.out.print(getLine() +
                "    OK, I've marked this task as not done yet:\n" +
                "    " + data.getData(this.id) +
                getLine());
    }
}
