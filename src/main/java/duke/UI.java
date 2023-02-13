package duke;

public class UI {
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("ฅʕ•ᴥ•ʔฅ :: Hiii! I'm duke, here to help you with your tasks!");
        System.out.println("•──────────────────♛─────────────────•");
    }

    public void showList(TaskList list) {
        System.out.println("ʕ•ᴥ•ʔっ :: Here are the task(s) in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i-1).toString());
        }
    }

    public void showFoundTasks(TaskList list) {
        System.out.println("ʕ•ᴥ•ʔっ :: Here are the matching task(s) in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i-1).toString());
        }
    }

    public void mark(TaskList list, int index) {
        list.getTask(index - 1).setDone();
        System.out.println("ʕ•̀ω•́ʔ✧ :: Nice! I've marked this task as done:\n  " + list.getTask(index - 1).toString());
    }

    public void unmark(TaskList list, int index) {
        list.getTask(index - 1).setUndone();
        System.out.println("ʕ•̀ω•́ʔ✧ :: OK, I've marked this task as not done yet:\n  " + list.getTask(index - 1).toString());
    }
    public void addTodo(TaskList list, String name) {
        list.addTask(new ToDo(name));
        System.out.println("ฅʕ'ᴥ'ʔฅ :: Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void addDeadline(TaskList list, String name, String deadline) {
        list.addTask(new Deadline(name, deadline));
        System.out.println("ฅʕ'ᴥ'ʔฅ :: Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void addEvent(TaskList list, String name, String start, String end) {
        list.addTask(new Event(name, start, end));
        System.out.println("ฅʕ'ᴥ'ʔฅ :: Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void removeTask(TaskList list, int index) {
        System.out.println("ฅʕ'ᴥ'ʔฅ :: Noted. I've removed this task :");
        System.out.println("  " + list.removeTask(index - 1).toString());
        System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void showExit() {
        System.out.println("ʕっ￫ᴥ￩ʔっ :: Buhbyeee, hope to see you again soon!");
    }
}
