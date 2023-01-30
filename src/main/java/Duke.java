import java.util.Scanner;

public class Duke {

    private Scanner input = new Scanner(System.in);
    private Task tasks[] = new Task[100];
    private int size = 0;

    public void start() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
    }

    public void mark(String command) {
        int taskindex = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[taskindex].mark();
    }

    public void unmark(String command) {
        int taskindex = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[taskindex].unmark();
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            System.out.print("\t " + (i + 1) + ".");
            tasks[i].showTask();
        }
    }

    public void run() {
        start();
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.startsWith("mark")) {
                mark(command);
            } else if (command.startsWith("unmark")) {
                unmark(command);
            } else {
                tasks[size] = new Task(command);
                size++;
                System.out.println("\t added: " + command);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}


