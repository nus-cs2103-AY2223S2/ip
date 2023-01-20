import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        int index = 0;
        String lineBreak = "***---***---***---***---***---***---***" + "\n" + "    ";

        while (sc.hasNext()) {
            String firstWord = sc.next();
            if (firstWord.equals("bye")) {
                System.out.println(lineBreak + "GoodBye, have a nice day!");
                break;
            } else if (firstWord.equals("list")) {
                System.out.println(lineBreak);
                for (Task t : tasks) {
                    System.out.println(t);
                }
            } else if (firstWord.equals("mark")) {
                int ind = Integer.parseInt(sc.next());
                if (ind > tasks.size()) {
                    System.out.println(lineBreak + "This task does not exist");
                } else {
                    Task updatedTask = tasks.get(ind - 1).mark();
                    tasks.set(ind - 1, updatedTask);
                    System.out.println(lineBreak + "Congrats on completing the following task:\n" + updatedTask);
                }
            } else if (firstWord.equals("unmark")) {
                int ind = Integer.parseInt(sc.next());
                if (ind > tasks.size()) {
                    System.out.println(lineBreak + "This task does not exist");
                } else {
                    Task updatedTask = tasks.get(ind - 1).unmark();
                    tasks.set(ind - 1, updatedTask);
                    System.out.println(lineBreak + "Unchecked the following task:\n" + updatedTask);
                }
            }
            else {
                index++;
                String remaining = sc.nextLine();
                Task task = new Task(index, firstWord + remaining, false);
                tasks.add(task);
                System.out.println(lineBreak + "Successfully added the following task:\n" + task);
            }
        }
    }
}

class Task {
    private final int number;
    private final String name;
    private final String status;

    public Task(int number, String name, boolean status) {
        this.number = number;
        this.name = name;
        if (status) {
            this.status = "[ ]";
        } else {
            this.status = "[X]";
        }
    }

    public String toString() {
        return number + "." + status + " " + name;
    }

    public Task mark() {
        return new Task(number, name, true);
    }

    public Task unmark() {
        return new Task(number, name, false);
    }
}