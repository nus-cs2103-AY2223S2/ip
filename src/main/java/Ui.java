public class Ui {
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        reply("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void reply(String s) {
        System.out.println("\t"
                + "____________________________________________________________");
        System.out.println("\t" + s.replace("\n", "\n    "));
        System.out.println("\t"
                + "____________________________________________________________");
    }

    public String formatAddTaskReply(TaskList taskList, Task task) {
        String formattedReply;
        formattedReply = String.format(
                "Got it. I've added this task:\n\t%s\n" +
                        "Now you have %d task(s) in the list.",
                task.toString(),
                taskList.getListSize());
        return formattedReply;
    }

    public void printExitMessage() {
        reply("Bye! Hope to see you again soon!");
    }

}
