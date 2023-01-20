import java.util.Scanner;
public class Dudu {
    public enum Command {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        DELETE("delete"),
        MARK("mark"),
        UNMARK("unmark"),
        BYE("bye");

        public final String action;

        Command(String action) {
            this.action = action;
        }

        boolean equals(String type) {
            return action.equals(type);
        }


    }
    private static final String DIVIDER = "___________________________________________\n";
    private static final String LOGO =
              " ____   _   _  ____   _   _\n"
            + "|  _ \\ | | | ||  _ \\ | | | |\n"
            + "| | | || | | || | | || | | |\n"
            + "| |_| || |_| || |_| || |_| |\n"
            + "|____/  \\___/ |____/  \\___/\n";
    private static final String GREETING = DIVIDER + LOGO + "Hello! I'm Dudu\n" + "What can I do for you?\n" + DIVIDER;

    public Dudu() {
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        System.out.print(GREETING);
        while (scanner.hasNext()) {
            String input = scanner.nextLine().strip();
            // skip the empty lines
            if (input.isBlank()) {
                continue;
            }
            String[] inputArr = input.split(" ");
            if (Command.BYE.equals(inputArr[0])) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (Command.LIST.equals(inputArr[0])) {
                list.printList();
            } else if (Command.DELETE.equals(inputArr[0])) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = list.getTask(index);
                    list.delete(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + currTask);
                    System.out.println(list.getTotalTask());
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (Command.MARK.equals(inputArr[0])) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = list.getTask(index);
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + currTask);
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (Command.UNMARK.equals(inputArr[0])) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = list.getTask(index);
                    currTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + currTask);
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (Command.DEADLINE.equals(inputArr[0])) {
                try {
                    list.addTask(Command.DEADLINE.action, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else if (Command.TODO.equals(inputArr[0])) {
                try {
                    list.addTask(Command.TODO.action, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else if (Command.EVENT.equals(inputArr[0])) {
                try {
                    list.addTask(Command.EVENT.action, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.print(DIVIDER);
        }
        System.out.print(DIVIDER);
    }
    public static void main(String[] args) {
        new Dudu();
    }

}
