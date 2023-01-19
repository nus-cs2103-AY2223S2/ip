import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String echo = "";
        Scanner sc = new Scanner(System.in);
        String logo = "\n____________________________________________________________\n";

        System.out.println(logo + "Hello! I'm GPT-1!");
        System.out.println("What can I do for you?" + logo);

        ArrayList<Task> storer = new ArrayList<>();
        while (!echo.equals("bye")) {
            echo = sc.nextLine();
            String[] commands = echo.split(" ");
            if (echo.equals("list")) {
                System.out.println(logo);
                for (int i = 1; i <= storer.size(); i++) {
                    int j = i - 1;
                    System.out.println(i + ". " + storer.get(j));
                }
                System.out.println(logo);

            } else if (commands[0].equals("mark")) {
                int num = Integer.valueOf(commands[1]);
                storer.get(num - 1).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storer.get(num - 1));

            } else {
                Task taskNew;




                if (commands[0].equals("todo")) {

                    try {

                        if (commands.length == 1) {
                            throw new NoArgsException("todo");
                        } else {
                            String description = echo.substring(5);

                            addTask(new Todos(description), storer, logo);
                        }

                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }




                } else if (commands[0].equals("deadline")) {
                    try {
                        if (commands.length == 1) {

                            throw new NoArgsException("deadline");
                        } else {


                            String[] queries = echo.split(" /");

                            try {

                                if (queries.length < 2) {
                                    throw new IncompleteException();
                                } else {
                                    String description = queries[0].substring(9);
                                    String deadline = queries[1];

                                    addTask(new Deadlines(description, deadline), storer, logo);
                                }
                            } catch (IncompleteException err) {
                                System.out.println(err.getMessage());
                            }


                        }

                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }




                } else if (commands[0].equals("event")) {

                    try {
                        if (commands.length == 1) {
                            throw new NoArgsException("event");
                        } else {

                            try {
                                String[] queries = echo.split(" /");
                                if (queries.length < 3){
                                    throw new IncompleteException();
                                } else {
                                    String description = queries[0].substring(6);
                                    String from = queries[1];
                                    String to = queries[2];
                                    addTask(new Events(description, from, to), storer, logo);
                                }


                            } catch (IncompleteException err) {
                                System.out.println(err.getMessage());
                            }

                        }

                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }




                } else if (echo.equals("bye")){
                    break;

                } else {
                    try {
                        throw new EmptyException();
                    } catch (DukeException err) {
                         System.out.println(err.getMessage());
                    }
                }



            }





        }
        System.out.println(logo + "Bye. Hope to see you again soon!" + logo);
    }

    static void addTask(Task taskNew, ArrayList<Task> storer, String logo) {
        System.out.println(logo);
        System.out.println("Got it. I've added this task:");
        storer.add(taskNew);
        System.out.println(taskNew);
        System.out.println(String.format("Now you have %s tasks in the list.", storer.size()));
        System.out.println(logo);
    }
}

