package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";
    private static int count = 0;
    private static ArrayList<Task> listname;
    //private static Task[] listname;
    public static void main(String[] args) {
        //show logo
        //logo();
        //greeting
        greet();
        //input command
        Scanner str = new Scanner(System.in);
        //no more than 100 tasks
        //listname = new Task[100];
        listname = new ArrayList<>();
        String command;
        String[] words;
        String info;
        Task task;

        do {
            command = str.nextLine().trim();
            words = command.split(" ");

            //if command is equal to bye, exit()
            //if command is not equal to bye, distinguish list or normal command
            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    list();
                } else if (words[0].equals("mark")) {
                    try {
                        done(words[1]);
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
                } else if (words[0].equals("unmark")) {
                    try {
                        undone(words[1]);
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else if (words[0].equals("delete")) {
                    try{
                        delete(words[1]);
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
//                    int index = Integer.parseInt(words[1]) - 1;
//                    System.out.println(index);
//                    Task.taskNum--;
//                    System.out.println(Task.taskNum);
//                    listname[count] = new Delete(words[0], index - 1);
//                    count--;

                } else if (words[0].equals("todo")) {
                    try {
                        if (words[1].equals(null)) {
                            System.out.println(Indentation + Horizontal);
                            System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(Indentation + Horizontal);
                        }
                        info = command.substring(command.indexOf(" ") + 1);
                        //listname[count] = new Todo(info);
                        task = new Todo(info);
                        listname.add(task);
                        count++;
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else if (words[0].equals("deadline")) {
                    try {
                        info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by "));
                        String deadline = command.substring(command.indexOf("/by") + 4);
                        //listname[count] = new Deadline(info, deadline);
                        task = new Deadline(info, deadline);
                        listname.add(task);
                        count++;

                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }


                } else if (words[0].equals("event")) {
                    try {
                        info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from "));
                        String fromtime = command.substring(command.indexOf(" /from ") + 6, command.indexOf(" /to "));
                        String totime = command.substring(command.indexOf(" /to ")  + 4);
                        //listname[count] = new Event(info, fromtime, totime);
                        task = new Event(info, fromtime, totime);
                        listname.add(task);
                        count++;
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else {
                    System.out.println(Indentation + Horizontal);
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(Indentation + Horizontal);
                }
            }

        } while (!command.equals("bye"));

        exit();

    }

    public static void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + Horizontal);
    }

    public static void exit() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + Horizontal);
    }

    public static void list() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println(Indentation + (i + 1) + "." + listname.get(i).toString());
        }

        System.out.println(Indentation + Horizontal);
    }

    public static void done (String num) {
        int number = Integer.parseInt(num) - 1;
        //listname[number].isDone = true;
        listname.get(number).isDone = true;

        System.out.println(Indentation + Horizontal);
        System.out.println("Nice! I've marked this task as done:");

//        System.out.println(Indentation +
//                    "[" + listname.get(number).getStatusIcon() + "] " + listname.get(number).getDescription());
        System.out.println(Indentation + listname.get(number).toString());

        System.out.println(Indentation + Horizontal);
    }

    public static void undone(String num) {
        int number = Integer.parseInt(num) - 1;
        listname.get(number).isDone = false;

        System.out.println(Indentation + Horizontal);
        System.out.println("OK, I've marked this task as not done yet:");

//        System.out.println(Indentation +
//                "[" + listname.get(number).getStatusIcon() + "] " + listname.get(number).getDescription());
        System.out.println(Indentation + listname.get(number).toString());

        System.out.println(Indentation + Horizontal);
    }

    public static void delete(String num) {
        int index = Integer.parseInt(num) - 1;
        try{
            if (!(listname.get(index)).equals(null)) {
                //System.out.println(index);
                //System.out.println(Task.taskNum);
                System.out.println(Indentation + Horizontal);
                System.out.println(Indentation + "Noted. I've removed this task:");

                System.out.println(Indentation + listname.get(index).toString());
                listname.remove(index);
                Task.taskNum--;
                count--;
                System.out.println(" Now you have " + Task.taskNum + " tasks in the list.");
                System.out.println(Indentation + Horizontal);
            }
        } catch (Exception e) {
            System.out.println(Indentation + Horizontal);
            System.out.println(" ☹ OOPS!!! I'm sorry, but the list is empty :-(");
            System.out.println(Indentation + Horizontal);
        }


    }

}
