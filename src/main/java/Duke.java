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
                System.out.println(lineBreak + "\n" + "    GoodBye, have a nice day!");
                break;
            } else if (firstWord.equals("list")) {
                for (Task t : tasks) {
                    System.out.println(t);
                }
            } else if (firstWord.equals("mark")) {
                int ind = Integer.parseInt(sc.next());
                Task updatedTask = tasks.get(ind).mark();
                tasks.set(ind, updatedTask);
            } else if (firstWord.equals("unmark")) {
                int ind = Integer.parseInt(sc.next());
                Task updatedTask = tasks.get(ind).unmark();
                tasks.set(ind, updatedTask);
            }
            else {
                index++;
                String remaining = sc.nextLine();
                Task task = new Task(index, firstWord + remaining, false);
                tasks.add(task);
            }
        }
    }
}
