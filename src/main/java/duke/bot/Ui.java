package duke.bot;

public class Ui {
    public Ui () {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
    }
    public static void Greet() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you? "+
                "\n('bye' to terminate duke.Duke)" +
                "\n('list' to access list of tasks)" +
                "\n('un/mark X' to un/mark X task on list)" +
                "\n('todo/deadline/event' for keeping note of different tasks)");
    }
    public void showLoadingError(){
        System.out.println("There is no file of that name, a new file has been created");
    }
}
