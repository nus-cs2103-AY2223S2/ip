import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

        System.out.println(duke.greeting());

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            String[] expressions = userInput.split(" ");
            String command = expressions[0];
            if (userInput.equals("list")) {
                System.out.println(duke.separate(duke.print_curr_tasks()));
            } else if (userInput.matches("^mark\\s\\d*")) {
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                System.out.println(duke.separate(duke.mark_as_done(index)));
            } else if (userInput.matches("^unmark\\s\\d*")) {
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                System.out.println(duke.separate(duke.mark_as_undone(index)));
            } else if (userInput.equals("bye")) {
                break;
            } else if (command.equals("todo")) {
                Todo todo_task = new Todo(userInput);
                System.out.println(duke.separate(duke.msg_of_add(todo_task)));
            } else if (command.equals("deadline")) {
                Deadline ddl_task = new Deadline(userInput);
                duke.addTask(ddl_task);
                System.out.println(duke.separate(duke.msg_of_add(ddl_task)));
            } else if (command.equals("event")) {
                Event event_task = new Event(userInput);
                duke.addTask(event_task);
                System.out.println(duke.separate(duke.msg_of_add(event_task)));
            } else {
                Task task_given = new Task(userInput);
                duke.addTask(task_given);
                System.out.println(duke.separate(duke.msg_of_add(task_given)));
            }
        }
        System.out.println(duke.separate(duke.ending()));
    }
}
