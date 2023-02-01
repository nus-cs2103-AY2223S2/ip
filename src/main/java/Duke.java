import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void addTaskToFile(String fileName, String task) {
        try {
            BufferedWriter temp = new BufferedWriter(new FileWriter(fileName, true));
            temp.write(task);
            temp.close();
        } catch (IOException e) {
            System.out.println("There is an error in saving tasks.");
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm Happie \nWhat can I do for you?");
        try {
            File taskSaved = new File("C:/Users/linwe/Documents/TaskSaved.txt");
            FileWriter myWriter = new FileWriter("C:/Users/linwe/Documents/TaskSaved.txt");
            myWriter.write("");
        } catch (IOException e) {
            System.out.println("There is an error in saving tasks.");
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();



        while(!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("Here are some tasks in your list:");
                for (int i = 1; i < taskList.size() + 1; i++) {
                    System.out.println(i + "." + (taskList.get(i - 1)).toString());
                }

            } else if(input.length() > 4 && (input.substring(0, 4)).equals("mark")) {
                String taskStr = input.substring(5);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task originalTask =  taskList.get(taskNum);
                originalTask.markTask();
                System.out.println("Nice! I've marked this task as done: \n  " + originalTask);

            } else if(input.length() > 6 && (input.substring(0, 6)).equals("unmark")) {
                String taskStr = input.substring(7);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task originalTask =  taskList.get(taskNum);
                originalTask.unmarkTask();
                System.out.println("Ok, I've marked this task as not done yet: \n  " + originalTask);

            }  else if(input.length() > 4 && (input.substring(0, 4)).equals("todo")) {
                ToDo task = new ToDo(input);
                taskList.add(task);
                addTaskToFile("C:/Users/linwe/Documents/TaskSaved.txt", task.getTaskType() + " | "
                        + task.currentTaskStatus()  + " | " + task.getTask() + "\n");

                System.out.println("Got it. I've added this task: \n  " + task +
                        "\nNow you have " + taskList.size() + " tasks in the list.");

            } else if(input.length() > 8 &&(input.substring(0, 8)).equals("deadline")) {
                LocalDate deadline = LocalDate.parse(input.substring(input.indexOf("/") + 4));
                Deadline task = new Deadline(input, deadline);
                taskList.add(task);
                addTaskToFile("C:/Users/linwe/Documents/TaskSaved.txt", task.getTaskType() + " | "
                        + task.currentTaskStatus()  + " | " + task.getTask() + " | " + task.getDeadline() + "\n");

                System.out.println("Got it. I've added this task: \n  " + task +
                        "\nNow you have " + taskList.size() + " tasks in the list.");

            } else if(input.length() > 5 && (input.substring(0, 5)).equals("event")) {
                LocalDate startDate = LocalDate.parse(input.substring(input.indexOf("/") + 6, input.lastIndexOf("/") - 1));
                LocalDate endDate = LocalDate.parse(input.substring(input.lastIndexOf("/") + 4));
                Event task = new Event(input, startDate, endDate);
                taskList.add(task);
                addTaskToFile("C:/Users/linwe/Documents/TaskSaved.txt", task.getTaskType() + " | "
                        + task.currentTaskStatus()  + " | " + task.getTask() + " | " + task.getTimeline() + "\n");

                System.out.println("Got it. I've added this task: \n  " + task +
                        "\nNow you have " + taskList.size() + " tasks in the list.");

            } else if(input.length() > 6 && (input.substring(0, 6)).equals("delete")) {
                String taskStr = input.substring(7);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task taskToRemove = taskList.get(taskNum);
                String removedTaskStr = taskToRemove.toString();
                taskList.remove(taskNum);
                System.out.println("Noted. I've removed this task: \n  " + removedTaskStr +
                        "\nNow you have " + taskList.size() + " tasks in the list.");
            }
            else {
                    try {
                        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
                            throw new EmptyDescriptionException();
                        } else {
                            throw new WrongCommandException();
                        }
                    } catch (EmptyDescriptionException | WrongCommandException e){
                        System.out.println(e.getMessage());
                    }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
