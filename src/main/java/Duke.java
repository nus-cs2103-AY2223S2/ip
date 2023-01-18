import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String UNDERLINE = "_________________________________";
    private static ArrayList<Task> list = new ArrayList<>();

    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private static void greet() {

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println((UNDERLINE));
    }

    private static void addTodo(String description) {
        ToDo tdItem = new ToDo(description);
        list.add(tdItem);
        System.out.println("\tGot it. I have added this task:\n" + tdItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }
    private static void addEvents(String description, String start, String end) {
        Events evItem = new Events(description, start, end);
        list.add(evItem);
        System.out.println("\tGot it. I have added this task:\n " + evItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    private static void addDeadline(String description, String doneBy) {
        Deadline dlItem =  new Deadline(description, doneBy);
        list.add(dlItem);
        System.out.println("\tGot it. I have added this task:\n "  + dlItem.toString());
        System.out.printf("\tYou have %d tasks in the list.\n\n", list.size());
    }


    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);

        System.out.println(logo);
        Duke.greet();

        // parse the command


            while (true) {
                try {
                    try {
                        String instct = text.nextLine();


                        if (instct.split(" ")[0].equals("list")) {
                            System.out.println("\t" + "Here are the tasks in your list:");
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
                            }
                        } else if (instct.split(" ")[0].equals("mark")) {
                            int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                            System.out.println("\t" + "Nice! I've marked this task as done:");
                            list.get(numbering).markDone();
                            System.out.println("\t" + list.get(numbering).toString());
                        } else if (instct.split(" ")[0].equals("unmark")) {
                            int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                            System.out.println("\t" + "OK, I've marked this task as not done yet:");
                            list.get(numbering).markNotDone();
                            System.out.println("\t" + list.get(numbering).toString());
                        } else if (instct.split(" ")[0].equals("todo")) {
                            String description = instct.split(" ")[1];
                            addTodo(description);

                        } else if (instct.split(" ")[0].equals("deadline")) {
                            String description = instct.split(" ")[1];
                            String doneBy = instct.split(" /by ")[1];
                            addDeadline(description, doneBy);

                        } else if (instct.split(" ")[0].equals("event")) {
                            String description = instct.split(" ")[1];
                            String[] temp = instct.split("/from | /to ");
                            String from = temp[1];
                            String to = temp[2];

                            addEvents(description, from, to);

                        } else if (instct.split(" ")[0].equals("bye")) {
                            System.out.println("Bye. Hope to see you again soon!\n");
                            break;
                        } else {
                            throw new unknownCommandException();

                        }

                        System.out.println((UNDERLINE));
                    }
                    catch(IndexOutOfBoundsException e){
                        throw new emptyDescriptionException();
                    }
                }
                catch (DukeException ex) {
                    System.out.printf("%s\n", ex);
                    System.out.println((UNDERLINE));
                }
        }




        }

    }

