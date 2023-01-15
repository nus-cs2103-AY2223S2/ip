package commands;

import data.MyData;

public class Mark extends Commands {
    private final int id;

    public Mark(int id) {
        this.id = id;
    }

    public void execute(MyData data) {
        data.markDone(this.id);
        System.out.print(getLine() +
                "    Nice! I've marked this task as done:\n" +
                "    " + data.getData(this.id) + "\n" +
                getLine());
    }
}
