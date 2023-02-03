import java.util.ArrayList;
import java.util.List;

public class Ui {
    private List<String> printBuffer;
    public Ui(){
        this.printBuffer = new ArrayList<String>();
    }

     // Provides basic line 
    private void line() {
        System.out.println("____________________________________________________________");
    }

    // Repeats the input 
    public void speak(String input) {
        line();
        System.out.println(input);
        line();
        System.out.print("=> ");
    }
    public void speak(List<String> inputs) {
        line();
        inputs.forEach(x -> System.out.println(x));
        line();
        inputs.clear();
        System.out.print("=> ");
    }
    public void showAddTask(Task task, int listLength) {
        printBuffer.add("Got it. I've added this task:");
        printBuffer.add("  [" + task.getType() +"][ ] " + task.toString()); 
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }
    
    public void showDeleteTask(Task task, int listLength) {
        printBuffer.add("Noted. I've removed this task:");
        printBuffer.add("  [" + task.getType() + "][ ] " + task.toString());
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }

    public void showMarkStatus(boolean complete, Task task) {
        if (complete) {
            printBuffer.add("Nice! I've marked this task as done:");
            printBuffer.add("["+ task.getType()+"][X] " + task.toString());
        } else {
            printBuffer.add("OK, I've marked this task as not done yet:");
            printBuffer.add("["+ task.getType()+"][ ] " + task.toString());
        }
        speak(printBuffer);
    }

    // Prints entire list in this.list
    public void printList(ArrayList<Task> list) {
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
}
