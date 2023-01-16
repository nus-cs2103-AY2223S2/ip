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
                    String toUse = currIndex.toString() + "." + currTask.toString();
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

            if (userInput.startsWith("todo")) {
                String useForInit = userInput.substring(5);
                Task addTask = new ToDo(useForInit);
                storedText.add(addTask);
                String toPrint = "";
                if (storedText.size() == 1) {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " task in the list.";
                } else {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list.";                   
                }
                System.out.println(toPrint);
                userInput = brToUse.readLine();
                continue;
            }

            if (userInput.startsWith("deadline")) {
                String useForInit = userInput.substring(9);
                Task addTask = new Deadlines(useForInit);
                storedText.add(addTask);
                String toPrint = "";
                if (storedText.size() == 1) {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " task in the list.";
                } else {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list.";                   
                }
                System.out.println(toPrint);
                userInput = brToUse.readLine();
                continue;
            }

            if (userInput.startsWith("event")) {
                String useForInit = userInput.substring(6);
                Task addTask = new Events(useForInit);
                storedText.add(addTask);
                String toPrint = "";
                if (storedText.size() == 1) {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " task in the list.";
                } else {
                    toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list.";                   
                }
                System.out.println(toPrint);
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

class ToDo extends Task {
    
    ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[T][X] " + this.name;
        } else {
            toReturn = "[T][ ]" + this.name;
        }
        return toReturn;
    }
}

class Deadlines extends Task {

    String endsBy;

    Deadlines(String taskName) {
        super(taskName.split("/by ")[0]);
        this.endsBy = taskName.split("/by ")[1];
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[D][X] " + this.name + "(by: " + endsBy + ")";
        } else {
            toReturn = "[D][ ] " + this.name + "(by: " + endsBy + ")";            
        }
        return toReturn;
    }
}

class Events extends Task {

    String fromDetails;
    String toDetails;

    Events(String taskName) {
        super(taskName.split("/from ")[0]);
        String[] initialSplit = taskName.split("/from ");
        String[] nextSplit = initialSplit[1].split("/to ");
        this.fromDetails = nextSplit[0];
        this.toDetails = nextSplit[1];
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[E][X] " + this.name + " (from: " + this.fromDetails + "to: " + this.toDetails + ")";
        } else {
            toReturn = "[E][ ] " + this.name + " (from: " + this.fromDetails + "to: " + this.toDetails + ")";
        }
        return toReturn;
    }
}
