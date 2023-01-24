import java.util.Scanner;

public class Execute {
    public static void checkEmpty(String input, String command) throws DukeException {
        if (input.length() < command.length() + 1) {
            switch (command) {
                case "todo":
                    throw new EmptyTodoException();
                case "deadline":
                    throw new EmptyDeadlineException();
                case "event":
                    throw new EmptyEventException();
                case "mark": case "unmark": case "delete":
                    throw new EmptyListException();
            }
        }
    }
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

            try {
                if (userInput.equals("list")) {
                    System.out.println(duke.separate(duke.print_curr_tasks()));
                } else if (command.equals("mark")) {
                    checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(duke.separate(duke.mark_as_done(index)));
                } else if (command.equals("unmark")) {
                    checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(duke.separate(duke.mark_as_undone(index)));
                } else if (userInput.equals("bye")) {
                    break;
                } else if (command.equals("todo")) {
                    checkEmpty(userInput, command);
                    Todo todo_task = new Todo(userInput);
                    duke.addTask(todo_task);
                    System.out.println(duke.separate(duke.msg_of_add(todo_task)));
                } else if (command.equals("deadline")) {
                    checkEmpty(userInput, command);
                    Deadline ddl_task = new Deadline(userInput);
                    duke.addTask(ddl_task);
                    System.out.println(duke.separate(duke.msg_of_add(ddl_task)));
                } else if (command.equals("event")) {
                    checkEmpty(userInput, command);
                    Event event_task = new Event(userInput);
                    duke.addTask(event_task);
                    System.out.println(duke.separate(duke.msg_of_add(event_task)));
                } else if (command.equals("delete")) {
                    checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(duke.separate(duke.delete_msg(index)));
                }
                else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc)  {
                System.out.println(duke.separate(exc.toString()));
            } catch (DukeException exc) {
                System.out.println(duke.separate(exc.toString()));
            }
        }
        System.out.println(duke.separate(duke.ending()));
    }
}
