import java.util.Scanner;

public class Duke {
    private static final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private static final String outro = "Goodbye!";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        System.out.println();
        System.out.print("User: ");
        String input = sc.next();
        
        while(!input.equals("bye")) {
            System.out.print("Duke: ");
            System.out.println(input);
            System.out.println();
            System.out.print("User: ");
            input = sc.next();
        }

        System.out.print("Duke: ");
        System.out.println(outro);
    }
}
