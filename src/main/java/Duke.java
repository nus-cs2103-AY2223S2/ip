import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {


    public static void main(String[] args) {
        System.out.println("Hello Boss.\n" + "How may i help you?\n");

        Scanner sc = new Scanner(System.in);

        String echo = sc.nextLine();

        List<Task> store = new ArrayList<>();

        while (true) {
            if (echo.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ":" + store.get(i) + "\n");
                }
                echo = sc.nextLine();
            } else if (echo.equals("bye")) {
                System.out.println("Bye, have a good day!");
            } else if (echo.contains("delete")) {
                int index = Integer.parseInt(echo.substring(7));
                index--;
                Task removedTask = store.get(index);
                store.remove(index);
                System.out.println("Noted. I've removed this task:\n" +
                        removedTask + "\n" + "Now you have " + store.size() + " tasks in the list.");
                echo = sc.nextLine();
            } else if (echo.startsWith("mark")) {
                int index = Integer.parseInt(echo.substring(5));
                index--;
                store.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + store.get(index).toString() + "\n");
                echo = sc.nextLine();
            } else if (echo.startsWith("unmark")) {
                int index = Integer.parseInt(echo.substring(7));
                index--;
                store.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + store.get(index).toString() + "\n");
                echo = sc.nextLine();
            } else {
                try {
                    if (echo.contains("todo")) {
                        String description = echo.replace("todo", "");
                        if ((description.equals("")) || (description.equals(" "))) {
                            throw new EmptyDescriptionException();
                        }
                        else {
                            ToDo toDo = new ToDo(description);
                            store.add(toDo);
                            System.out.println("Got it. I've added this task:\n" + toDo.toString() +
                                    "\nNow you have " + store.size() + " tasks in the list.\n");
                            echo = sc.nextLine();
                        }

                    } else if (echo.contains("deadline")) {
                        echo = echo.replace("deadline", "");
                        String description = echo.split("/by")[0];
                        String date = echo.split("/by ")[1];
                        Deadline deadline = new Deadline(description, date);
                        store.add(deadline);
                        System.out.println("Got it. I've added this task:\n" + deadline.toString() +
                                "\nNow you have " + store.size() + " tasks in the list.\n");
                        echo = sc.nextLine();

                    } else if (echo.contains("event")) {
                        echo = echo.replace("event", "");
                        String description = echo.split("/from")[0];
                        String temp = echo.split("/from")[1];
                        String from = temp.split("/to")[0];
                        String to = temp.split("/to")[1];
                        Event event = new Event(description, from, to);
                        store.add(event);
                        System.out.println("Got it. I've added this task:\n" + event.toString() +
                                "\nNow you have " + store.size() + " tasks in the list.\n");
                        echo = sc.nextLine();
                    }
                    else {
                        throw new UnknownCommandException();
                    }
                }
                catch (DukeException e){
                    System.out.print(e.toString());
                    break;
                }
            }
        }
        System.out.println("OOPS! Something went wrong.");
    }
}