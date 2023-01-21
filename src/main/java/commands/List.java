package commands;

import data.MyData;
import ui.Ui;

public class List extends Command {
    public void execute(MyData data) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            System.out.printf("    %d. %s%n", i + 1, data.getData(i));
        }
        System.out.print(Ui.line());
    }
}
