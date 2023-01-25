import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class Duke {
    static String STR = "------------------------------------------------------------";
    static ArrayList<Task> arr = new ArrayList<Task>();

    public static void main(String[] args) {
        // Introduction
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n");

        // Load data
        try {
            loadData("./data/duke.txt");
            System.out.println("I've successfully retrieved your past task list!");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry! Unfortunately, I'm unable to retrieve your past task list!");
        } finally {
            listTask();
            printResponse("What can I do for you?");
        }

        Scanner scanner = new Scanner(System.in);
        boolean hasMore = true;
        while (hasMore) {
            // Formatting String input
            String input = scanner.nextLine();
            String[] arrStr = input.split(" ", 2);
            String command = arrStr[0];

            switch (command) {
                // Bye - Save program and exit
                case "bye":
                    try {
                        saveData("./data/duke.txt");
                    } catch (IOException e) {
                        System.out.println("Sorry, memory cannot be saved!");
                    } finally {
                        printResponse("Bye. Hope to see you again soon!");
                        hasMore = false;
                        break;
                    }

                    // List - Print list of task
                case "list":
                    listTask();
                    break;

                // Mark - mark task as done
                case "mark":
                    try {
                        markTask(Integer.parseInt(arrStr[1]), true);
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to mark.");
                    } finally {
                        break;
                    }

                // Unmark - unmark a task as undone
                case "unmark":
                    try {
                        markTask(Integer.parseInt(arrStr[1]), false);
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to unmark.");
                    } finally {
                        break;
                    }

                // todo - add a task with type todo
                case "todo":
                    try {
                        addTask(arrStr[1], 'T');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this todo is incomplete.");
                    } finally {
                        break;
                    }

                // deadline - add a task with type deadline
                case "deadline":
                    try {
                        addTask(arrStr[1], 'D');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this deadline is incomplete.");
                    } finally {
                        break;
                    }

                // event - add a task with type event
                case "event":
                    try {
                        addTask(arrStr[1], 'E');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this deadline is incomplete.");
                    } finally {
                        break;
                    }

                // delete - delete task
                case "delete":
                    try {
                        deleteTask(Integer.parseInt(arrStr[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to delete.");
                    } finally {
                        break;
                    }

                // incorrect input
                default:
                    printResponse("I'm sorry, but I don't understand what that means! Try re-typing your instruction!");
            }
        }
    }

    public static void printResponse(String response) {
        System.out.println(STR);
        System.out.println(response);
        System.out.println(STR);
    }

    public static void loadData(String pathName) throws FileNotFoundException {
        File f = new File(pathName);
        Scanner s = new Scanner(f);
        while(s.hasNextLine()) {
            String[] parts = s.nextLine().split(Pattern.quote(" | "));
            switch (parts[0]) {
                case "T":
                    arr.add(new Todo(parts[2], Boolean.parseBoolean(parts[1])));
                    break;
                case "D":
                    arr.add(new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]));
                    break;
                case "E":
                    arr.add(new Event(parts[2], Boolean.parseBoolean(parts[1]), parts[3], parts[4]));
                    break;
            }
        }
    }

    public static void saveData(String pathName) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String str = "";
        for(int i = 0 ; i < arr.size(); i++) {
            if(str.isEmpty()) {
                str = str + arr.get(i);
            } else {
                str = str + "\n" + arr.get(i);
            }
        }
        fw.write(str);
        fw.close();
    }

    public static void markTask(int taskNum, boolean isDone) {
        Task taskToMark = arr.get(taskNum - 1);
        if(isDone){
            taskToMark.mark();
            printResponse("Nice! I've marked this task as done: \n " + taskToMark);
        } else {
            taskToMark.unmark();
            printResponse("OK, I've marked this task as not done yet \n" + taskToMark);
        }
    }

    public static void addTask(String response, char type) throws ArrayIndexOutOfBoundsException {
       if(type == 'T') {
           Todo newTask = new Todo(response);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type == 'D') {
           String[] strArr = response.split(" /by ", 2);
           Deadline newTask = new Deadline(strArr[0], strArr[1]);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type =='E') {
           String[] strArr = response.split(" /from ", 2);
           String[] timings = strArr[1].split(" /to ", 2);
           Event newTask = new Event(strArr[0], timings[0], timings[1]);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       }
    }

    public static void deleteTask(int taskNum) {
        Task deleteTask = arr.remove(taskNum);
        printResponse("Noted. I've removed this task: \n" + deleteTask + "\nNow you have " + arr.size() + " "
                + "tasks in the list.");
    }

    public static void listTask() {
        if (arr.isEmpty()) {
            printResponse("You have 0 task to complete at the moment!");
        } else {
            int x = 1;
            String lst = "";
            for (int i = 0; i < arr.size(); i++) {
                if (!lst.equals("")) {
                    lst = lst + "\n";
                }
                lst = lst + x + ". " + arr.get(i);
                x++;
            }
            printResponse("Here are the task in your list: \n" + lst + "\nYou have " + arr.size()
                    + " tasks in the list.");
        }
    }
}
