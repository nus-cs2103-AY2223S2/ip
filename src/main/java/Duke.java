import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] storedList = new String[100];
        int index = 0;
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            String textInput = scan.nextLine();
            if (textInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scan.close();
                return;
            }
            if (textInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + storedList[i]);
                }
                continue;
            }
            storedList[index] = textInput;
            index++;
            System.out.println("added: " + textInput);
        }
    }
}
