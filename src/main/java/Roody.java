import java.util.Arrays;

public class Roody {
    private Task[] list;
    private int index;

    public Roody(){
        // Assumed no more than 100 tasks
        this.list = new Task[100];
        this.index = 0;
    }

    // Provides basic line 
    private void line() {
        System.out.println("____________________________________________________________");
    }

    // Initial Greeting
    private void greet() {
        line();
        System.out.println("Hello, I'm Roody!");
        System.out.println("What can I do for you?");
        line();
    }
    
    // Stores input to string
    private void addToList(String input) {
        Task task = new Task(input);
        this.list[this.index] = task;
        this.index++;
        speak("added: " + input);
    }

    // Repeats the input 
    private void speak(String input) {
        line();
        System.out.println(input);
        line();
    }

    // Prints entire list in this.list
    private void printList() {
        line();
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
            System.out.println(stringBuilder.toString());
            
            // Clears and updates values
            stringBuilder.setLength(0);
            count++;
        }
        line();
    }

    private void mark(String index) {
        int taskIndex = Integer.parseInt(index) - 1; 
        line();
        if (taskIndex < this.list.length && this.list[taskIndex] == null) {
            System.out.println("Sorry, this task dosen't exist");
        } else {
            Task task = this.list[taskIndex];
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + task.getDescription());
        }
        line();
    }

    // Current issue: after marking/unmarking, i'm able to add "list" to list
    private void unmark(String index) {
        int taskIndex = Integer.parseInt(index) - 1; 
        line();
        if (taskIndex < this.list.length && this.list[taskIndex] == null) {
            System.out.println("Sorry, this task dosen't exist");
        } else {
            Task task = this.list[taskIndex];
            task.setUnDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + task.getDescription());
        }
        line();
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
                roody.mark(inputs[1]);
            } else if (inputs.length > 1 && inputs[0].equals("unmark")) {
                roody.unmark(inputs[1]);
            } else {
                roody.addToList(input);
            }
        }
        roody.speak("Bye. Hope to see you again soon!");
    }
}
