package commands;

import data.MyData;
import ui.Ui;

public class Mark extends Command {
    private final int id;

    public Mark(int id) {
        this.id = id;
    }

    public void execute(MyData data) {
        data.markDone(this.id);
        System.out.print(Ui.line() +
                "    Nice! I've marked this task as done:\n" +
                "    " + data.getData(this.id) + "\n" +
                Ui.line());
    }
}
