package commands;

import data.MyData;
import tasks.Deadline;

public class AddDeadline extends Commands {
    protected Deadline deadline;

    public AddDeadline(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    public void execute(MyData data) {
        data.setData(deadline);
        System.out.print(getLine() +
                "     Got it. I've added this task:\n" +
                "       " + deadline + "\n" +
                "     Now you have " + data.len() + " tasks in the list.\n" +
                getLine());
    }
}
