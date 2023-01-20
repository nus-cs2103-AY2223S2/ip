import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];

        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
        String cur = sc.nextLine();
            int i = 0;
            while (!cur.equals("bye")) {
                try {
                    if (cur.equals("list")) {
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; tasks[j] != null && j < 100; j++) {
                            int k = j + 1;
                            System.out.println(k + "." + tasks[j].toString());
                        }
                        System.out.println("------------------------------------------------------------------------------");
                    } else if (cur.split(" ")[0].equals("mark")) {
                        int index = Integer.valueOf(cur.split(" ")[1]);
                        tasks[index - 1].markAsDone();
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[index - 1].toString());
                        System.out.println("------------------------------------------------------------------------------");
                    } else if (cur.split(" ")[0].equals("unmark")) {
                        int index = Integer.valueOf(cur.split(" ")[1]);
                        tasks[index - 1].markAsUnDone();
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[index - 1].toString());
                        System.out.println("------------------------------------------------------------------------------");
                    } else if (cur.split(" ")[0].equals("event")) {
                        tasks[i] = new Event(cur);
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[i].toString());
                        System.out.println("Now you have " + Task.counter + " tasks in the list.");
                        System.out.println("------------------------------------------------------------------------------");
                        i += 1;

                    } else if (cur.split(" ")[0].equals("deadline")) {
                        tasks[i] = new Deadline(cur);
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[i].toString());
                        System.out.println("Now you have " + Task.counter + " tasks in the list.");
                        System.out.println("------------------------------------------------------------------------------");
                        i += 1;

                    } else if (cur.split(" ")[0].equals("todo")) {
                        if (cur.split(" ").length == 1) {
                            throw new todoException("____________________________________________________________\n" +
                                    "  ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                    "____________________________________________________________");
                        }
                        tasks[i] = new Todo(cur);
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[i]);
                        System.out.println("Now you have " + Task.counter + " tasks in the list.");
                        System.out.println("------------------------------------------------------------------------------");
                        i += 1;

                    } else if (!cur.split(" ")[0].equals("todo") || !cur.split(" ")[0].equals("deadline")
                            || !cur.split(" ")[0].equals("event")) {
                        throw new InvalidTask("____________________________________________________________\n" +
                                "  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "____________________________________________________________");

                    }
                }
                catch (InvalidTask e) {
                    System.out.println(e.toString());
                }
                catch(todoException e) {
                    System.out.println(e.toString());
                }
                cur = sc.nextLine();
            }


        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }
}
