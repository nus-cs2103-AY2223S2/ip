public class Ui {
    private final String starting = "    ____________________________________________________________\n";
    private final String ending = "    ____________________________________________________________\n";
    private final String spacing = "     ";
    private static String intro = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static String outro = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n";

    public Ui() {}

    public void showIntro() {
        System.out.println(Ui.intro);
    }

    public void showOutro() {
        System.out.println(Ui.outro);
    }

    public String wrap(String s, int size) {
        //wrap string with the correct indentation and lines when returning add task string
        //assumes s contains the nextline character
        StringBuilder sb = new StringBuilder();
        sb.append(this.starting).append(this.spacing).append("Got it. I've added this task:\n  ");
        sb.append(this.spacing).append(s).append(this.spacing);
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append(this.ending);
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
        String s = this.starting + this.spacing + "Nice! I've marked this task as done:\n";
        s += this.spacing + "  " + taskString + this.ending;
        System.out.println(s);
    }

    public void showUnmarkResult(String taskString, int index) {
        String s = this.starting + this.spacing + "OK, I've marked this task as not done yet:\n";
        s += this.spacing + "  " + taskString + this.ending;
        System.out.println(s);
    }

    public void showDeleteResult(String taskString, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.starting).append(this.spacing).append("Noted. I've removed this task:\n  ");
        sb.append(this.spacing).append(taskString).append(this.spacing);
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append(this.ending);
        System.out.println(sb);
    }

    public void showGetAllTaskResult(TaskList tl) {
        StringBuilder s = new StringBuilder();
        s.append(this.starting);
        s.append(this.spacing).append("Here are the tasks in your list:\n");
        for (int i = 0; i < tl.getSize(); i++) {
            s.append(String.format("%s%d.%s", this.spacing, i + 1, tl.getTask(i).toString()));
        }
        s.append(this.ending);
        System.out.println(s);
    }
}
