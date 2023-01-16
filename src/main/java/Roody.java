import java.util.Arrays;

public class Roody {
    private String[] list;
    private int index;

    public Roody(){
        // Assumed no more than 100 tasks
        list = new String[100];
        index = 0;
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
        list[index] = input;
        index++;
        speak("added: " + input);
    }

    // Repeats the input 
    private void speak(String input) {
        line();
        System.out.println(input);
        line();
        
    }

    private void printList() {
        line();
        int count = 0;
        int listIndex = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (count < index) {
            listIndex = count + 1;
            stringBuilder.append(listIndex);
            stringBuilder.append(". ");
            stringBuilder.append(list[count]);
            System.out.println(stringBuilder.toString());
            
            // Clears and updates values
            stringBuilder.setLength(0);
            count++;
        }
        line();
    }

    // Provides basic line 
    private void line() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Roody roody = new Roody();
        // Sends initial greeting
        roody.greet();
        String input = "";
        // Loops until "bye" is input
        while (true) {
            System.out.print("=> ");
            // Read input
            input = System.console().readLine();
            // If bye, break and print end message
            if (input.toLowerCase().equals("bye")) {
                break;
            // else, repeat
            } else if (input.toLowerCase().equals("list")) {
                roody.printList();
            } else {
                roody.addToList(input);
            }
        }
        roody.speak("Bye. Hope to see you again soon!");
    }
}
