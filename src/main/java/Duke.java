import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int counter = 0;

        while(true) {
            String message = scanner.nextLine();
            if(message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(message.equals("list")){
                for(int i = 0; i < counter; i++) {
                    int label = i + 1;
                    System.out.println(label + ". " + list[i]);
                }
            } else {
                list[counter] = message;
                counter++;
                System.out.println("added: " + message);
            }
        }
    }
}
