import java.util.*;
public class Duke {
    private static void printDashes() {
        System.out.println ("****************************************");
    }
    private static void printList(ArrayList items) {
        for(int i = 1; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i - 1));
        }
    }
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        printDashes();

        while(true) {
            String userInput = sc.nextLine();
            if(!userInput.equals("bye")) {
                if(!userInput.equals("list")) {
                    System.out.println("*added: " + userInput);
                    list.add(userInput);
                    printDashes();
                } else {
                    printList(list);
                    printDashes();
                }
            } else {
                System.out.println("Bye. Hope to see you again soon! ^_^");
                printDashes();
                break;
            }
        }
    }
}
