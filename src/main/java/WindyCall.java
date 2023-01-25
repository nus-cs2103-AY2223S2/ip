import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class WindyCall {

      private List<Task> tasks;
      private Storage storage;
      private Ui ui;
      private Parser parser;


    public WindyCall() {
        // handle loading here
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage.handleLoad(this.tasks);
    }



    private void addTask(String message) throws WindyCallException{
        Ui.space();
        String[] parts = message.split(" ");

        Task newTask;
        if (parts[0].equals("todo")) {
            if (message.length() == 4 || message.substring(4).trim().isEmpty()) {
                throw new WindyCallException("☹ OOPS!!! The description of a todo cannot be empty!");
            }
            String description = message.substring(5);
            System.out.println("Got it. I've added this Todo task:");
            newTask = new Todo(description, false);
        }
        else if (parts[0].equals("deadline")) {
            int idx = message.indexOf("/by");
            if (message.length() == 8 || message.substring(8).trim().isEmpty()
                    || (idx != -1 && message.substring(8, idx).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! The description of a deadline cannot be empty!");
            }
            if (idx == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
            }
            if (message.length() == idx + 3 || message.substring(idx + 3).trim().isEmpty()) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
            }
            String description = message.substring(9, idx - 1);
            System.out.println("Got it. I've added this Deadline task:");
            String deadline = message.substring(idx + 4);
            newTask = new Deadline(description, false, deadline);
        }
        else if (parts[0].equals("event")) {
            int idxFrom = message.indexOf("/from");
            int idxTo = message.indexOf("/to");
            if (message.length() == 5 || message.substring(5).trim().isEmpty()
                    || (idxFrom != -1 && message.substring(5, idxFrom).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! The description of an event cannot be empty!");
            }
            if (idxFrom == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify start time of the event!");
            }
            if (message.length() == idxFrom + 5 || message.substring(idxFrom + 5).trim().isEmpty()
                    || (idxTo != -1 && message.substring(idxFrom, idxTo).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify start time of the event!");
            }
            if (idxTo == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify end time of the event!");
            }
            if (message.length() == idxTo + 3 || message.substring(idxTo + 3).trim().isEmpty()) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify end time of the event!");
            }
            String description = message.substring(6, idxFrom - 1);
            String from = message.substring(idxFrom + 6, idxTo - 1);
            String to = message.substring(idxTo + 4);
            System.out.println("Got it. I've added this Event task:");
            newTask = new Event(description, false, from, to);
        } else {
            throw new WindyCallException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Ui.space();
        System.out.println(newTask);
        this.tasks.add(newTask);
        this.storage.handleTaskChange(this.tasks);
        Ui.space();
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        WindyCall chatBox = new WindyCall();
        chatBox.ui.greeting();
        while(true) {
            String userCommand = scan.nextLine();
            if (userCommand.equals("bye")) {
                chatBox.ui.byeWords();
                break;
            }
            Ui.line();
            if (userCommand.equals("list")) chatBox.ui.displayTasks(chatBox.tasks);
            else {
                String[] parts = userCommand.split(" ");
                if (parts[0].equals("mark")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to mark/unmark a task");
                        Ui.line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        Ui.space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     Good job! I've marked this task as done:");
                        chatBox.tasks.get(num - 1).markAsDone();
                        Ui.space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.storage.handleTaskChange(chatBox.tasks);
                    } else if (num != -1) {
                        System.out.println("     Sorry, your index is out of range");
                    }
                } else if (parts[0].equals("unmark")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to mark/unmark a task");
                        Ui.line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        Ui.space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     OK, I've marked this task as not done yet:");
                        chatBox.tasks.get(num - 1).unmark();
                        Ui.space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.storage.handleTaskChange(chatBox.tasks);
                    } else if (num != -1){
                        System.out.println("     Sorry, your index is out of range");
                    }
                } else if (parts[0].equals("delete")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to delete a task");
                        Ui.line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        Ui.space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     Noted. I've removed this task:");
                        Ui.space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.tasks.remove(num - 1);
                        chatBox.storage.handleTaskChange(chatBox.tasks);
                        Ui.space();
                        System.out.println("Now you have " + chatBox.tasks.size() + " tasks in the list.");
                    } else if (num != -1) {
                        System.out.println("     Sorry, your index is out of range");
                    }
                } else {
                    try {
                        chatBox.addTask(userCommand);
                    }
                    catch (WindyCallException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            Ui.line();
        }
    }
}
