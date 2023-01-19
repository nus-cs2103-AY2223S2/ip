import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String greeting = """
                Hello! I'm Alpha Beast
                What can I do for you?
                """;

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            System.out.println(input);
        }
    }
}
