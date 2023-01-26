package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String STARTING = "    ____________________________________________________________\n";
    private final String ENDING = "    ____________________________________________________________\n";
    private final String SPACING = "     ";
    private static final String INTRO = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static final String OUTRO = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n";

    public Ui() {}

    public void showIntro() {
        System.out.println(Ui.INTRO);
    }

    public void showOutro() {
        System.out.println(Ui.OUTRO);
    }

    public String wrap(String s, int size) {
        //wrap string with the correct indentation and lines when returning add task string
        //assumes s contains the nextline character
        StringBuilder sb = new StringBuilder();
        sb.append(this.STARTING).append(this.SPACING).append("Got it. I've added this task:\n  ");
        sb.append(this.SPACING).append(s).append(this.SPACING);
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append(this.ENDING);
        return sb.toString();
    }

    public void showAddDeadlineResult(String taskString, int size) {
        System.out.println(this.wrap(taskString, size));
    }

    public void showAddEventResult(String taskString, int size) {
        System.out.println(this.wrap(taskString, size));
    }

    public void showAddTodoResult(String taskString, int size) {
        System.out.println(this.wrap(taskString, size));
    }

    public void showMarkResult(String taskString, int index) {
        String s = this.STARTING + this.SPACING + "Nice! I've marked this task as done:\n";
        s += this.SPACING + "  " + taskString + this.ENDING;
        System.out.println(s);
    }

    public void showUnmarkResult(String taskString, int index) {
        String s = this.STARTING + this.SPACING + "OK, I've marked this task as not done yet:\n";
        s += this.SPACING + "  " + taskString + this.ENDING;
        System.out.println(s);
    }

    public void showDeleteResult(String taskString, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.STARTING).append(this.SPACING).append("Noted. I've removed this task:\n  ");
        sb.append(this.SPACING).append(taskString).append(this.SPACING);
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append(this.ENDING);
        System.out.println(sb);
    }

    public void showFindTaskResult(ArrayList<Task> taskArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.STARTING).append(this.SPACING).append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task task : taskArr) {
            sb.append(String.format("%s%d.%s", this.SPACING, counter, task.toString()));
            counter += 1;
        }
        sb.append(this.ENDING);
        System.out.println(sb);
    }

    public void showGetAllTaskResult(TaskList tl) {
        StringBuilder s = new StringBuilder();
        s.append(this.STARTING);
        s.append(this.SPACING).append("Here are the tasks in your list:\n");
        for (int i = 0; i < tl.getSize(); i++) {
            s.append(String.format("%s%d.%s", this.SPACING, i + 1, tl.getTask(i).toString()));
        }
        s.append(this.ENDING);
        System.out.println(s);
    }
}
