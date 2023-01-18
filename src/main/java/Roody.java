import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Roody {
    private Task[] list;
    private int index;
    private List<String> printBuffer;

    public Roody(){
        // Assumed no more than 100 tasks
        this.list = new Task[100];
        this.printBuffer = new ArrayList<String>();
        this.index = 0;
    }

    // Provides basic line 
    private void line() {
        System.out.println("____________________________________________________________");
    }

    // Repeats the input 
    private void speak(String input) {
        line();
        System.out.println(input);
        line();
    }
    private void speak(List<String> inputs) {
        line();
        inputs.forEach(x -> System.out.println(x));
        line();
        inputs.clear();
    }

    // Initial Greeting
    public void greet() {
        this.printBuffer.add("Hello, I'm Roody!");
        this.printBuffer.add("What can I do for you?");
        speak(this.printBuffer);
    }

    // final greeting
    public void bye() {
        speak("Bye. Hope to see you again soon!");
    }
    
    // Stores input to string
    private void addToList(String input) {
        String[] inputs = input.split("/");
        Task task = new Todo(input.substring("todo ".length()));
        char type = input.charAt(0);
        if (type == 't') {
        } else if (type == 'd') {
            // more than one / detected,
            if (inputs.length > 2) {
                new RoodyException("I don't understand that. Don't use additonal \"/\" for deadlines.");
                return;
            } else {
                task = new Deadline(inputs[0].substring("deadline ".length()), inputs[1].substring("by ".length()));
            }
        } else if (type == 'e') {
            // more or less than two / detected,
            if (inputs.length != 3) {
                new RoodyException("I don't understand that. Don't use additonal \"/\" for events.");
                return;
            } else {
                task = new Event(inputs[0].substring("event ".length()), inputs[1].substring("from ".length()), inputs[2].substring("to ".length()));
            }
        } else {
            new RoodyException("Error, wrong input detected");
            return;
        }
        list[index] = task;
        index++;
        printBuffer.add("Got it. I've added this task:");
        printBuffer.add("  [" + task.getType() +"][ ] " + task.toString()); 
        printBuffer.add("Now you have " + index + " tasks in the list.");
        speak(printBuffer);
    }

    // Prints entire list in this.list
    private void printList() {
        int count = 0;
        int listIndex = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (index > 0) {
            printBuffer.add("Here are the tasks in your list:");
            while (count < this.index) {

                listIndex = count + 1;
                stringBuilder.append(listIndex);
                stringBuilder.append(".[");
                // get type
                stringBuilder.append(list[count].getType());
                stringBuilder.append("][");
                // if is done, set as 'X'
                if (this.list[count].isDone()) {
                    stringBuilder.append("X] ");
                // not done, set as ' '
                } else {
                    stringBuilder.append(" ] ");
                }
                stringBuilder.append(list[count].toString());
                printBuffer.add(stringBuilder.toString());
                
                // Clears and updates values
                stringBuilder.setLength(0);
                count++;
            }
            speak(printBuffer);
        } else {
            new RoodyException("There doesn't seem to be any tasks in your list.");
        }
    }

    // toggles completion status of tasks
    private void complete(String index, boolean complete){
        int taskIndex = Integer.parseInt(index) - 1; 
        if (taskIndex < list.length && list[taskIndex] == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list[taskIndex];
            if (complete) {
                task.setDone();
                printBuffer.add("Nice! I've marked this task as done:");
                printBuffer.add("["+ task.getType()+"][X] " + task.toString());
            } else {
                task.setUnDone();
                printBuffer.add("OK, I've marked this task as not done yet:");
                printBuffer.add("["+ task.getType()+"][ ] " + task.toString());
            }
            speak(printBuffer);
        }
    }

    public static void main(String[] args){
        Roody roody = new Roody();
        // Sends initial greeting
        roody.greet();
        String input = "";
        String[] inputs;
        Scanner scanner = new Scanner(System.in);
        // Loops until "bye" is input
        while (true) {
            System.out.print("=> ");
            // Read input
            input = scanner.nextLine();
            inputs = input.toLowerCase().split("\\s", 0);
            // If bye, break and print end message
            if (inputs[0].equals("bye")) {
                break;
            // else, repeat
            } else if (inputs[0].equals("list")) {
                roody.printList();
            // Checks for second input
            } else if (inputs.length > 1 && inputs[0].equals("mark")) {
                roody.complete(inputs[1], true);
            } else if (inputs.length > 1 && inputs[0].equals("unmark")) {
                roody.complete(inputs[1], false);
            } else if (inputs.length > 1 && (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event"))) {
                roody.addToList(input);
            } else {
                new RoodyException("I don't quite understand that.");
            }
        }
        scanner.close();
        roody.bye();
    }
}
