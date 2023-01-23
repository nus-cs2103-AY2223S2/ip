import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner usrInput = new Scanner(System.in);
        String input = "";
        List<Task> lst = new ArrayList<>();
        while(true){
            input = usrInput.nextLine();
            String[] parsedInput = input.split(" ", 2);
            switch (parsedInput[0]) {
                case "bye":
                    if (parsedInput.length > 1){
                        continue;
                    }
                    System.out.println("Goodbye!");
                    usrInput.close();
                    System.exit(0);
                case "list":
                    if (parsedInput.length > 1){
                        continue;
                    }
                    for (int j = 0; j < lst.size(); j++) {
                        System.out.println((j + 1) + ":" + lst.get(j).toString());
                    }
                    break;
                case "mark":
                    int taskCode = Integer.parseInt(parsedInput[1]);
                    Task chosenTask = lst.get(taskCode - 1);
                    chosenTask.setDone();
                    System.out.println("I have set the following task to done: \n" + chosenTask.toString());
                    break;
                case "unmark":
                    int taskCodeUnmark = Integer.parseInt(parsedInput[1]);
                    Task chosenTaskUnmark = lst.get(taskCodeUnmark - 1);
                    chosenTaskUnmark.setNotDone();
                    System.out.println("I have set the following task to not done: \n" + chosenTaskUnmark.toString());
                    break;
                case "todo":
                    Task newToDo = new ToDo(parsedInput[1]);
                    System.out.println("added:\n" + newToDo.toString());
                    lst.add(newToDo);
                    break;
                case "deadline":
                    String[] parsedCommand = parsedInput[1].split("/");
                    Task newDeadline = new Deadline(parsedCommand[0], parsedCommand[1]);
                    System.out.println("added:\n" + newDeadline.toString());
                    lst.add(newDeadline);
                    break;
                case "event":
                    String[] parsedCommandEvent = parsedInput[1].split("/");
                    Task newEvent = new Event(parsedCommandEvent[0], parsedCommandEvent[1], parsedCommandEvent[2]);
                    System.out.println("added:\n" + newEvent.toString());
                    lst.add(newEvent);
                    break;
            }
        }
    }

}
