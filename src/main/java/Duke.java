import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.Collections;

public class Duke {
    static int numOfTasks = 0;
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        String dog = "⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⣀⣀⣀⣀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣰⣿⣿⡻⠟⠋⠉⠉⣻⠟⠉⠉⠉⠛⢯⡛⢿⣿⣷⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⣠⣴⠾⠛⢋⣿⠟⠋⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠈⠂⣹⣿⡈⠙⠻⢶⣄⡀⠀\n" +
                "⣸⠏⠀⠀⠀⣾⣋⣀⣀⡀⠀⠀⠀⢸⠁⠀⠀⢀⣀⣀⣀⡀⠀⠈⠻⣧⠀⠀⠀⠉⠻⣦\n" +
                "⢿⡀⠀⣿⣿⠟⣫⣽⣿⣿⣿⣿⣶⣶⣶⡶⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⢀⣿\n" +
                "⠸⣧⠀⠈⣿⢸⣿⣿⣿⣿⣿⣿⣿⠁⢹⡇⣼⣿⣿⣿⣿⣿⣿⣿⠁⣼⡇⠀⠀⠀⣼⠇\n" +
                "⠀⠹⣷⡀⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⠏⠀⣿⡇⠀⣠⡾⠋⠀\n" +
                "⠀⠀⠈⢿⣿⢿⡿⠿⠿⣿⠟⠉⠀⠀⠀⠀⠙⠛⢿⡿⠿⠛⠉⠀⠀⡿⣷⣾⠏⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠋⠘⣷⠀⢀⡿⢰⣾⣟⣛⣿⣿⣷⡄⠀⢻⣆⠀⠀⠀⢰⡇⠘⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠹⣧⣼⠃⠈⣧⣼⣿⣇⣈⣿⠃⠀⠀⣿⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣆⠀⠈⠙⢿⡛⠉⠁⠀⠀⣠⡿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡿⢶⣶⣾⣿⣶⣤⣤⣶⢿⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⡼⠁⠉⠏⠁⠈⢹⣠⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⠀⣸⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣤⣤⡾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⢶⣾⣶⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("Good ta see yer dawg, Tom's at yer service.\n" + dog);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            String[] inputArr;
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                System.out.println("See yer again RUFF!");
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list dawg:");
                for (int i = 0; i < numOfTasks; i++) System.out.println( (i+1) + "." + taskList.get(i));
            } else if (inputArr[0].equals("mark")) {
                taskList.get(Integer.parseInt(inputArr[1]) - 1).markStatus(true);
                System.out.println("The task is marked, dawg");
            } else if (inputArr[0].equals("unmark")){
                taskList.get(Integer.parseInt(inputArr[1]) - 1).markStatus(false);
                System.out.println("Gotcha dawg, unmarked.");
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                try {
                    addTasks(taskList, inputArr, inputArr[0]);
                } catch (EmptyDescException e) {
                    System.out.println(e);
                }
            } else if (inputArr[0].equals("delete")) {
                int inputIndex = Integer.parseInt(inputArr[1]);
                Task task = taskList.get(inputIndex-1);
                taskList.remove(inputIndex-1);
                numOfTasks--;
                System.out.println("Removing your task? It's gone now RUFF:");
                System.out.println("  " + task);
                System.out.println("Now you have " + numOfTasks + " in the list!");
            } else {
                try {
                    throw new UnknownCommandException("test");
                } catch (UnknownCommandException e) {
                    System.out.println(e);
                }
            }
        }

    }

    public static void addTasks(ArrayList<Task> taskList, String[] inputArr, String taskType) throws EmptyDescException {
        String desc = "variable not initialised";
        StringBuilder sb = new StringBuilder();
        if (inputArr.length == 1) throw new EmptyDescException(taskType, "test");
        switch (taskType) {
            case "todo":
                // task is from index 1
                for (int i = 1; i < inputArr.length; i++) {
                    sb.append(inputArr[i]);
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Todo todo = new Todo(sb.toString());
                taskList.add(todo); numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + numOfTasks + " in the list!");
                break;

            case "deadline":
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Deadline deadline = new Deadline(desc, sb.toString());
                taskList.add(deadline); numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + numOfTasks + " in the list!");
                break;
            case "event":
                String from = "local variable not initialised";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/from")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    } else if (inputArr[i].equals("/to")) {
                        from = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Event event = new Event(desc, from, sb.toString());
                taskList.add(event); numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + numOfTasks + " in the list!");
                break;
        }
    }
}
