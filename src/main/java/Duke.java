import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String echo = "";
        Scanner sc = new Scanner(System.in);
        String logo = "\n____________________________________________________________\n";


        say("Hello! I'm GPT0.01!\nWhat can I do for you?", logo);

        ArrayList<Task> storer = new ArrayList<>();
        while (!echo.equals("bye")) {
            try {
                echo = sc.nextLine();
                String[] commands = echo.split(" ");
                if (echo.equals("list")) {

                    String concat = "";
                    for (int i = 1; i <= storer.size(); i++) {
                        int j = i - 1;
                        concat = concat + "\n" + i + ". " + storer.get(j);

                    }

                    if (concat == "") {
                        throw new StorerEmptyException();
                    } else {
                        say(concat, logo);
                    }


                } else if (commands[0].equals("mark")) {
                    int num = Integer.valueOf(commands[1]);
                    storer.get(num - 1).mark();
                    say(String.format(
                            "Nice! I've marked this task as done:\n %s", storer.get(num - 1)), logo);

                } else {
                    Task taskNew;

                    if (commands[0].equals("todo")) {

                        if (commands.length == 1) {
                            throw new NoArgsException("todo");
                        } else {
                            String description = echo.substring(5);
                            addTask(new Todos(description), storer, logo);
                        }

                    } else if (commands[0].equals("deadline")) {

                        if (commands.length == 1) {
                            throw new NoArgsException("deadline");
                        } else {
                            String[] queries = echo.split(" /");
                            if (queries.length < 2) {
                                throw new IncompleteException();
                            } else {
                                String description = queries[0].substring(9);
                                String deadline = queries[1];
                                addTask(new Deadlines(description, deadline), storer, logo);
                            }
                        }
                    } else if (commands[0].equals("event")) {
                        if (commands.length == 1) {
                            throw new NoArgsException("event");
                        } else {
                            String[] queries = echo.split(" /");
                            if (queries.length < 3){
                                throw new IncompleteException();
                            } else {
                                String description = queries[0].substring(6);
                                String from = queries[1];
                                String to = queries[2];
                                addTask(new Events(description, from, to), storer, logo);
                            }
                        }

                    } else if (commands[0].equals("delete")) {

                        if (commands.length == 1) {
                            throw new NoArgsException("delete command");
                        } else if (commands.length > 1 && !commands[1].matches("\\d")) {
                            throw new DukeException("☹☹☹☹☹☹ OOPS!!! Provide a number!");
                        } else if (storer.size() == 0) {
                            throw new StorerEmptyException();
                        } else {
                            int index = Integer.valueOf(commands[1]);
                            Task E = storer.remove(index - 1);
                            String speech = "Noted. I've removed this task:\n" +
                                    E + "\n Now you have " + storer.size() + " tasks in the list.";
                            say(speech, logo);

                        }
                    } else if (echo.equals("bye")){
                        break;

                    } else {
                        throw new EmptyException();

                    }
                }
            } catch (Exception err) {
                say(err.getMessage(), logo);
            }


        }
        say("Bye. Hope to see you again soon!", logo);
    }

    static void addTask(Task taskNew, ArrayList<Task> storer, String logo) {
        storer.add(taskNew);
        say("Got it. I've added this task:\n" + taskNew +
                String.format("\nNow you have %s tasks in the list.", storer.size()), logo);


    }

    static void say(String str, String logo) {
        System.out.println(logo + str + logo);
    }
}

