import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Allow users to add, mark and un-mark items in a list
        userInputs();
    }

    private static void userInputs() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>(100);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            String[] part = input.split(" ");
            int index = 0;

            if (part[0].equals("mark") || part[0].equals("unmark")) {
                index = Integer.parseInt(part[1]) - 1;
            }
            
            switch (part[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + "." + list.get(i));
                    }
                    break;

                case "mark":
                    list.get(index).toBeMarked();
                    break;

                case "unmark":
                    list.get(index).toBeUnmarked();
                    break;

                default:
                    list.add(new Task (part[0]));
            }
        }
    }
}

class Task {
    private final String name;
    private boolean checkMark;

    public Task(String name) {
        this.name = name;
        this.checkMark = false;
    }

    public void toBeMarked() {
        this.checkMark = true;
    }

    public void toBeUnmarked() {
        this.checkMark = false;
    }

    @Override
    public String toString() {
        return (checkMark ? "[X] " : "[] ") + name;
    }
}

