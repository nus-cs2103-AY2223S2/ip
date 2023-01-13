public class Roody {
    public Roody(){}

    // Initial Greeting
    private void greet() {
        line();
        System.out.println("Hello, I'm Roody!");
        System.out.println("What can I do for you?");
        line();
    }
    
    // Repeats the input 
    private void speak(String input) {
        line();
        System.out.println(input);
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
            } else {
                roody.speak(input);
            }
        }
        roody.speak("Bye. Hope to see you again soon!");
    }
}
