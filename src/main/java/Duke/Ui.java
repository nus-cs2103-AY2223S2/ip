package Duke;

public class Ui {

    public Ui() {

    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public void bye() {
        System.out.println("\t--------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------");
    }
}
