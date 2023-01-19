import java.util.Scanner;
public class Baymax {
    public static void main(String[] args) {
        System.out.println("Hello, I am Baymax your personal Chatbot Companion. \nWhat can I do for you today?");
        returnSame();
        System.out.println("See you soon!");
    }
    public static void returnSame() {
        Scanner input = new Scanner(System.in);
        String exit = "bye";
        String currentInput;
        while (true) {
            currentInput = input.nextLine();
            if (exit.equals(currentInput)) {
                break;
            } else {
                System.out.println(currentInput);
            }
        }
    }

}


