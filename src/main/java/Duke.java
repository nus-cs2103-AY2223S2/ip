import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();

    private void inputResponse(String userInput){
        String input;
        if(userInput.equals("bye")) {
            System.out.println("\tBye. Hope to see you again soon!");
            return;
        } else if(userInput.equals("list")) {
            this.displayList();
            input = this.sc.nextLine();
            this.inputResponse(input);
        } else {
            System.out.println("\tadded: " + userInput);
            this.list.add(userInput);
            input = this.sc.nextLine();
            this.inputResponse(input);
        }
    }

    private void displayList() {
        int listSize = this.list.size();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
    }


    public static void main(String[] args) {
        Duke dukeObj = new Duke();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");

        String input = dukeObj.sc.nextLine();
        dukeObj.inputResponse(input);
    }
}
