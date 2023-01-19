import java.util.*;
public class Baymax {
    public static void main(String[] args) {
        System.out.println("Hello, I am Baymax your personal Chatbot Companion. \nWhat can I do for you today?");
        returnValue();
        System.out.println("See you soon");
    }

    public static void returnValue() {
        Scanner input = new Scanner(System.in);
        String exit = "bye";
        String outputL = "list";
        String currentInput;
        List<String> myList = new ArrayList<String>();
        int Counter = 0;
        while (true) {
            currentInput = input.nextLine();
            if (exit.equals(currentInput)) {
                break;
            } else if (outputL.equals(currentInput)) {
                int index = 1;
                for (String s : myList) {
                    System.out.println((index++) + ": " + s);
                }
            } else {
                myList.add(Counter, currentInput);
                System.out.println("added: " + currentInput);
                Counter++;
            }
        }
    }
}


