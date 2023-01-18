import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Roody {
    private ArrayList<Task> list;
    private List<String> printBuffer;

    public Roody(){
        // Assumed no more than 100 tasks
        this.list = new ArrayList<Task>();
        this.printBuffer = new ArrayList<String>();
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
        list.add(task);
        printBuffer.add("Got it. I've added this task:");
        printBuffer.add("  [" + task.getType() +"][ ] " + task.toString()); 
        printBuffer.add("Now you have " + list.size() + " tasks in the list.");
        speak(printBuffer);
    }

    // Prints entire list in this.list
    private void printList() {
        int count = 0;
        int listIndex = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (!list.isEmpty()) {
            printBuffer.add("Here are the tasks in your list:");
            while (count < list.size()) {

                listIndex = count + 1;
                stringBuilder.append(listIndex);
                stringBuilder.append(".[");
                // get type
                stringBuilder.append(list.get(count).getType());
                stringBuilder.append("][");
                // if is done, set as 'X'
                if (list.get(count).isDone()) {
                    stringBuilder.append("X] ");
                // not done, set as ' '
                } else {
                    stringBuilder.append(" ] ");
                }
                stringBuilder.append(list.get(count).toString());
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
        if (taskIndex > list.size() - 1 || list.get(taskIndex) == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(taskIndex);
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

    private void delete(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex > list.size() - 1 || list.get(taskIndex) == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(taskIndex);
            printBuffer.add("Noted. I've removed this task:");
            printBuffer.add("  [" + task.getType() + "][ ] " + task.toString());
            printBuffer.add("Now you have " + (list.size()-1) + " tasks in the list.");
            speak(printBuffer);
            list.remove(Integer.parseInt(index) - 1);
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
            } else if (inputs[0].equals("delete")) {
                if (inputs.length == 2) {                    
                    roody.delete(inputs[1]);
                } else {
                    new RoodyException("Please enter a index number to be deleted");
                }
            // Checks for second input
            } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")) {
                if (inputs.length == 2) {                    
                    roody.complete(inputs[1], inputs[0].equals("mark"));
                } else {
                    new RoodyException("Please enter a index number to be marked/unmarked");
                }
            } else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
                if (inputs.length > 1) {
                    roody.addToList(input);
                } else {
                    new RoodyException("Tasks require a description");
                }
            } else {
                new RoodyException("I don't quite understand that.");
            }
        }
        scanner.close();
        roody.bye();
    }
}
