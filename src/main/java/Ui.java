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
    public void printList(ArrayList<Task> list) throws RoodyException{
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
            throw new RoodyException("There doesn't seem to be any tasks in your list.");
        }
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
