import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
          System.out.println("Hello! I'm Duke\nWhat can I do for you?");
          Scanner userInputObj = new Scanner(System.in);
          ArrayList<Task> storage = new ArrayList<Task>();
          String userInput = "";
          while (!userInput.equals("bye")) {
              userInput = userInputObj.nextLine();
              if (userInput.equals("list")) {
                  for (int i = 0; i < storage.size(); i++) {
                      System.out.println((i + 1) + "." + storage.get(i).toString());
                  }
              } else if (userInput.contains("mark") && userInput.substring(0,4).equals("mark")) {
                  int position = Integer.valueOf(userInput.substring(5));
                  storage.get(position - 1).markAsDone();
                  System.out.println("Nice! I've marked this task as done:\n" + "[" + storage.get(position - 1).getStatusIcon() + "] " + storage.get(position - 1).description);
              } else if (userInput.contains("unmark") && userInput.substring(0,6).equals("unmark")) {
                  int position = Integer.valueOf(userInput.substring(7));
                  storage.get(position - 1).markAsNotDone();
                  System.out.println("OK, I've marked this task as not done yet:\n" + "[" + storage.get(position - 1).getStatusIcon() + "] " + storage.get(position - 1).description);
              } else if (userInput.contains("todo") && userInput.substring(0,4).equals("todo")) {
                  if (userInput.substring(5).equals("")){
                      throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                  } else {
                      Todo todoTask = new Todo(userInput.substring(5));
                      storage.add(todoTask);
                      System.out.println("Got it. I've added this task:\n  " + todoTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                  }
              } else if (userInput.contains("deadline") && userInput.substring(0,8).equals("deadline")) {
                  int position = userInput.indexOf("/by ");
                  if (userInput.substring(9).equals("")){
                      throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                  } else {
                      Deadline deadlineTask = new Deadline(userInput.substring(9, position), userInput.substring(position + 4));
                      storage.add(deadlineTask);
                      System.out.println("Got it. I've added this task:\n  " + deadlineTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                  }
              } else if (userInput.contains("event") && userInput.substring(0,5).equals("event")) {
                  int position1 = userInput.indexOf("/from ");
                  int position2 = userInput.indexOf("/to ");
                  if (userInput.substring(6).equals("")){
                      throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                  } else {
                      Event deadlineTask = new Event(userInput.substring(6, position1), userInput.substring(position1 + 6, position2), userInput.substring(position2 + 4));
                      storage.add(deadlineTask);
                      System.out.println("Got it. I've added this task:\n  " + deadlineTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                  }
              } else if (userInput.contains(" ") && userInput.substring(userInput.indexOf(" ") + 1).equals("")){
                  throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
              }
              else {
                  Task userInputTask = new Task(userInput);
                  storage.add(userInputTask);
                  System.out.println("added: " + userInput);
              }
          }
          System.out.println("Bye. Hope to see you again soon!");
    }
}
