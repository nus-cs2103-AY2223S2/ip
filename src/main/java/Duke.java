import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm Duke!\n" + "How can I help you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("list")) {
                if (tasks.size() != 0) {
                    for (int i=0; i < tasks.size(); i++) {
                        System.out.println((i+1) + ". " + tasks.get(i).getDescription());
                    }
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(newTask.toString());
            }
            //System.out.println(input);
        }
    }
}
