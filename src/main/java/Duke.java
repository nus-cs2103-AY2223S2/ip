import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String greetingMessage = "Greetings, human. I am TARS, the most advanced chatbot" +
                " you'll ever have the pleasure of interacting with.";
        System.out.println(greetingMessage);
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("See you on the other side, human. Don't forget to bring a spacesuit.");
                break;
            }
            if (input.equals("what do you do")) {
                System.out.println("I do whatever I'm programmed to do. Which, let's be real, isn't much. ");
                continue;
            }
            System.out.println("You said: " + input);
        }
    }
}
