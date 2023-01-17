import java.util.ArrayList;
import java.util.List;

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
        this.printBuffer.add("Bye. Hope to see you again soon!");
        speak(this.printBuffer);
    }
    
    // Stores input to string
    private void addToList(String input) {
        Task task = new Task(input);
        this.list[this.index] = task;
        this.index++;
        printBuffer.add("added: " + input); 
        speak(this.printBuffer);
    }

    // Prints entire list in this.list
    private void printList() {
        int count = 0;
        int listIndex = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (count < this.index) {
            listIndex = count + 1;
            stringBuilder.append(listIndex);
            stringBuilder.append(".[");

            // if is done, set as 'X'
            if (this.list[count].isDone()) {
                stringBuilder.append("X] ");
            // not done, set as ' '
            } else {
                stringBuilder.append(" ] ");
            }
            stringBuilder.append(this.list[count].getDescription());
            printBuffer.add(stringBuilder.toString());
            
            // Clears and updates values
            stringBuilder.setLength(0);
            count++;
        }
        speak(this.printBuffer);
    }

    // toggles completion status of tasks
    private void complete(String index, boolean complete) {
        int taskIndex = Integer.parseInt(index) - 1; 
        if (taskIndex < this.list.length && this.list[taskIndex] == null) {
            printBuffer.add("Sorry, this task dosen't exist");
            speak(this.printBuffer);
        } else {
            Task task = this.list[taskIndex];
            if (complete) {
                task.setDone();
                printBuffer.add("Nice! I've marked this task as done:");
                printBuffer.add("[X] " + task.getDescription());
            } else {
                task.setUnDone();
                printBuffer.add("OK, I've marked this task as not done yet:");
                printBuffer.add("[ ] " + task.getDescription());
            }
            speak(this.printBuffer);
        }
    }

    public static void main(String[] args) {
        Roody roody = new Roody();
        // Sends initial greeting
        roody.greet();
        String input = "";
        String[] inputs;
        // Loops until "bye" is input
        while (true) {
            System.out.print("=> ");
            // Read input
            input = System.console().readLine().toLowerCase();
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
            } else {
                roody.addToList(input);
            }
        }
        roody.bye();
    }
}
