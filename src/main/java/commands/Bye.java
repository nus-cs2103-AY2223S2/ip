package commands;

import data.MyData;

public class Bye extends Commands {
    public void execute(MyData data) {
        System.out.print(getLine() +
                "    commands.Bye. Hope to see you again soon!\n" +
                getLine());
        data.setRun();
    }
}
