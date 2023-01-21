package commands;

import data.MyData;
import tasks.Deadline;
import ui.Ui;

public class AddDeadline extends Command {
    protected Deadline deadline;

    public AddDeadline(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    public void execute(MyData data) {
        data.setData(deadline);
        data.saveToFile();

        System.out.print(Ui.line() +
                "     Got it. I've added this task:\n" +
                "       " + deadline + "\n" +
                "     Now you have " + data.len() + " tasks in the list.\n" +
                Ui.line());
    }
}
