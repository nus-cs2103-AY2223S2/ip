import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class WindyCall {

//    private final Task[] tasks;
//    private int cnt;
      private List<Task> tasks;


    public WindyCall() {
        // handle loading here
        this.tasks = new ArrayList<>();
        handleLoad();
    }

    public void handleLoad() {
        //check if "./data" directory exist
        File file = new File("./data");
        File task_file = new File("./data/WindyCall.txt");;
        // if data file exists
        if (!file.isDirectory()) {
            file.mkdir();
        }
        try {
            if (task_file.createNewFile()) {
                space();
                System.out.println("File created: " + task_file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Can not load your tasks");
            e.printStackTrace();
        }

        // load data in task_file into this.tasks
        try {
            Scanner myReader = new Scanner(task_file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) == 'T') {
                    String description = data.substring(8);
                    Task task = new Todo(description, data.charAt(4) == 'X');
                    tasks.add(task);
                } else if (data.charAt(0) == 'D') {
                    int idx = data.indexOf("|", 8);
                    String description = data.substring(8, idx - 1);
                    String deadline = data.substring(idx + 2);
                    Task task = new Deadline(description, data.charAt(4) == 'X', deadline);
                    tasks.add(task);
                } else {
                    int idx1 = data.indexOf("|", 8);
                    int idx2 = data.indexOf("|", idx1 + 1);
                    String description = data.substring(8, idx1 - 1);
                    String from = data.substring(idx1 + 2, idx2 - 1);
                    String to = data.substring(idx2 + 2);
                    Task task = new Event(description, data.charAt(4) == 'X', from, to);
                    tasks.add(task);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void handleTaskChange() {
        // overwrite tasks back to data file to record the change
        try {
            FileWriter myWriter = new FileWriter("./data/WindyCall.txt");
            for (Task task : tasks) {
                myWriter.write(task.fileFormat());
            }
//            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            space();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void space() {
        System.out.print("     ");
    }

    private static void line() {
        space();
        System.out.println("--------------------------------------------------");
    }

    private static void greeting() {
        line();
        space();
        System.out.println("Hello! I'm WindyCall");
        space();
        System.out.println("How can I help you?");
        line();
    }

    private static void byeWords() {
        line();
        space();
        System.out.println("Bye. Always willing to provide my help for you!!!");
        line();
    }

    private static void echoCommand(String message) {
        space();
        System.out.println(message);
    }

    private void addTask(String message) throws WindyCallException{
        space();
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
        space();
        System.out.println(newTask);
//        this.tasks[cnt] = newTask;
//        this.cnt++;
        this.tasks.add(newTask);
        this.handleTaskChange();
        space();
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private void displayTasks() {
        space();
        System.out.println("Here are all of your tasks:");
        for (int i = 0; i < this.tasks.size(); i++) {
            space();
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greeting();
        WindyCall chatBox = new WindyCall();
        while(true) {
            String userCommand = scan.nextLine();
            if (userCommand.equals("bye")) {
                byeWords();
                break;
            }
            line();
            if (userCommand.equals("list")) chatBox.displayTasks();
            else {
                String[] parts = userCommand.split(" ");
                if (parts[0].equals("mark")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to mark/unmark a task");
                        line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     Good job! I've marked this task as done:");
                        chatBox.tasks.get(num - 1).markAsDone();
                        space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.handleTaskChange();
                    } else if (num != -1) {
                        System.out.println("     Sorry, your index is out of range");
                    }
                } else if (parts[0].equals("unmark")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to mark/unmark a task");
                        line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     OK, I've marked this task as not done yet:");
                        chatBox.tasks.get(num - 1).unmark();
                        space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.handleTaskChange();
                    } else if (num != -1){
                        System.out.println("     Sorry, your index is out of range");
                    }
                } else if (parts[0].equals("delete")) {
                    if (parts.length == 1) {
                        System.out.println("     You should input a number to delete a task");
                        line();
                        continue;
                    }
                    int num = -1;
                    try {
                        num = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        space();
                        System.out.println("☹ OOPS!!! You should input a number");
                    }
                    if (num >= 1 && num <= chatBox.tasks.size()) {
                        System.out.println("     Noted. I've removed this task:");
                        space();
                        System.out.println(chatBox.tasks.get(num - 1));
                        chatBox.tasks.remove(num - 1);
                        chatBox.handleTaskChange();
                        space();
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
            line();
        }
    }
}
