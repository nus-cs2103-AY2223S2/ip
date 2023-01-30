import java.util.*;


public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        List<Task> lst = new ArrayList<>();



        while (true) {
            try {
                String input = sc.nextLine().toLowerCase();
                String[] inputArr = input.split(" ", 2);
                String action = inputArr[0];

                if (action.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (action.equals("list")) { // list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= lst.size(); i++) {
                        System.out.println(i + ". " + lst.get(i - 1));
                    }
                } else if (action.equals("mark")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done" + '\n' + currTask);
                } else if (action.equals("unmark")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    currTask.unMark();
                    System.out.println("Nice! I've marked this task as not done yet" + '\n' + currTask);
                } else if (action.equals("todo")) {
                    String[] actionAndDetails = input.split(" ", 2);
                    if (actionAndDetails.length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String details = actionAndDetails[1];
                    Todo newTodo = new Todo(details);
                    lst.add(newTodo);
                    System.out.println("Got it. I've added this task:" + '\n' + newTodo + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("deadline")) {
                    String[] detailsAndDate = inputArr[1].split(" /by ");
                    String details = detailsAndDate[0];
                    String date = detailsAndDate[1];
                    Deadline newDeadline = new Deadline(details, date);
                    lst.add(newDeadline);
                    System.out.println("Got it. I've added this task:" + '\n' + newDeadline + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("event")) {
                    String[] detailsAndTime = inputArr[1].split(" /from ");
                    String details = detailsAndTime[0];
                    String[] Time = detailsAndTime[1].split(" /to ");
                    String To = Time[0];
                    String From = Time[1];
                    Event newEvent = new Event(details, To, From);
                    lst.add(newEvent);
                    System.out.println("Got it. I've added this task:" + '\n' + newEvent + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("delete")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    lst.remove(taskNum - 1);
                    System.out.println("Noted. I've removed this task:" + '\n' + currTask  + '\n' + "Now you have " + lst.size() + " tasks in the list");
                }else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                    System.out.println(e.getMessage());
            }

        }
    }

}

