package roody;
import java.util.ArrayList;
import java.util.List;

public class Ui {
    private List<String> printBuffer;
    public Ui(){
        this.printBuffer = new ArrayList<String>();
    }

     // Provides basic showLine 
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    public void startNextLine() {
        System.out.print("=> ");
    }

    // Repeats the input 
    public void speak(String input) {
        showLine();
        System.out.println(input);
    }
    public void speak(List<String> inputs) {
        showLine();
        inputs.forEach(x -> System.out.println(x));
        inputs.clear();
    }
    public void showAddTask(Task task, int listLength) {
        printBuffer.add("Got it. I've added this task:");
        printBuffer.add(task.toString());
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }
    
    public void showDeleteTask(Task task, int listLength) {
        printBuffer.add("Noted. I've removed this task:");
        printBuffer.add(task.toString());
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }

    public void showMarkStatus(boolean complete, Task task) {
        if (complete) {
            printBuffer.add("Nice! I've marked this task as done:");
        } else {
            printBuffer.add("OK, I've marked this task as not done yet:");
        }
        printBuffer.add(task.toString());
        speak(printBuffer);
    }



    public void showFoundTasks(ArrayList<Task> list) throws RoodyException {
        if (list.size() == 0) {
            throw new RoodyException("No matching tasks in your list!");
        }
        printBuffer.add("Here are the matching tasks in your list:");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            printBuffer.add(listIndex.toString() + '.' + task.toString());
        }
        speak(printBuffer);
    }

    // Prints entire list in this.list
    public void printList(ArrayList<Task> list) throws RoodyException{
        if (list.size() == 0) {
            throw new RoodyException("There doesn't seem to be any tasks in your list.");
        }
        printBuffer.add("Here are the tasks in your list:");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            printBuffer.add(listIndex.toString() + '.' + task.toString());
        }
        speak(printBuffer);
    }

    // Initial Greeting
    public void greet() {
        this.printBuffer.add("Hello, I'm Roody!");
        this.printBuffer.add("What can I do for you?");
        speak(this.printBuffer);
        showLine();
    }
    
    // final greeting
    public void bye() {
        speak("Bye. Hope to see you again soon!");
        showLine();
    }
}
