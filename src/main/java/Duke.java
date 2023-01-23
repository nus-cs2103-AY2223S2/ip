import items.Task;

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
            String[] parsedInput = input.split(" ");
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
                    Integer taskCode = Integer.parseInt(parsedInput[1]);
                    Task chosenTask = lst.get(taskCode - 1);
                    chosenTask.setDone();
                    System.out.println("I have set the following task to done: \n" + chosenTask.toString());
                    break;
                case "unmark":
                    Integer taskCodeUnmark = Integer.parseInt(parsedInput[1]);
                    Task chosenTaskUnmark = lst.get(taskCodeUnmark - 1);
                    chosenTaskUnmark.setNotDone();
                    System.out.println("I have set the following task to not done: \n" + chosenTaskUnmark.toString());
                    break;
                default:
                    System.out.println("added:" + input);
                    lst.add(new Task(input));
                    
            }
        }
    }

}
