import java.io.InputStreamReader;
import java.io.*;
import java.util.*;


public class Duke {

    static List<Task> storedText = new ArrayList<Task>();
    public static void main(String[] args) throws IOException{
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        greeting();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        processUserInput(bReader);
        System.out.println("Bye! Hope to see you soon again!");
    }

    public static void greeting() {
        System.out.println("Hello, I am Duke!\nWhat can I do for you?");
    }

    public static void processUserInput(BufferedReader brToUse) throws IOException{
        String userInput = brToUse.readLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < storedText.size(); i++) {
                    Integer currIndex = i + 1;
                    Task currTask = storedText.get(i);
                    String toUse = "";
                    if (currTask.done) {
                        toUse = currIndex.toString() + ".[X] " + currTask.name;
                    } else {
                        toUse = currIndex.toString() + ".[ ] " + currTask.name;                        
                    }
                    System.out.println(toUse);
                }
                userInput = brToUse.readLine();
                continue;
            }

            if (userInput.startsWith("mark")) {
                int indexToUse = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task currTask = storedText.get(indexToUse);
                currTask.setDone();
                String toOutput = "Nice! I've marked this task as done:\n" + "[X] " + currTask.name;
                System.out.println(toOutput);
                userInput = brToUse.readLine();
                continue;
            }

            if (userInput.startsWith("unmark")) {
                int indexToUse = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task currTask = storedText.get(indexToUse);
                currTask.setUndone();
                String toOutput = "Ok, I've marked this task as not done yet:\n" + "[ ] " + currTask.name;
                System.out.println(toOutput);
                userInput = brToUse.readLine();
                continue;
            }

            Task toAdd = new Task(userInput);
            storedText.add(toAdd);
            String toOutput = "added: " + toAdd.name;
            System.out.println(toOutput);
            userInput = brToUse.readLine();
        }
    }

}

class Task {
    String name;
    boolean done;

    Task(String taskName) {
        this.name = taskName;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public void setUndone() {
        done = false;
    }

}
