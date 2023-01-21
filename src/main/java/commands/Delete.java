package commands;

import data.MyData;
import ui.Ui;

public class Delete extends Command {
    private final int id;

    public Delete(int id) {
        this.id = id;
    }

    public void execute(MyData data) {
        int itemCount = data.len() - 1;
        System.out.print(Ui.line() +
                "     Noted. I've removed this task:\n" +
                "       " + data.getData(this.id) + "\n" +
                "     Now you have " + itemCount + " tasks in the list.\n" +
                Ui.line());
        data.deleteData(id);
        data.saveToFile();
    }
}