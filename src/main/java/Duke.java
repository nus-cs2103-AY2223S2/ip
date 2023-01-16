import java.util.Scanner;
class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
