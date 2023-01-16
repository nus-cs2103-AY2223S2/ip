package commands;

import data.MyData;
import ui.Ui;

public class Bye extends Commands {
    public void execute(MyData data) {
        System.out.print(Ui.line() +
                "    Bye. Hope to see you again soon!\n" +
                Ui.line());
    }
}
