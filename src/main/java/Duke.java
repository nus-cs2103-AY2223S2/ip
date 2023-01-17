import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "░██████╗░██╗░██████╗░░█████╗░░█████╗░██╗░░██╗░█████╗░██████╗░\n"
                +     "██╔════╝░██║██╔════╝░██╔══██╗██╔══██╗██║░░██║██╔══██╗██╔══██╗\n"
                +     "██║░░██╗░██║██║░░██╗░███████║██║░░╚═╝███████║███████║██║░░██║\n"
                +     "██║░░╚██╗██║██║░░╚██╗██╔══██║██║░░██╗██╔══██║██╔══██║██║░░██║\n"
                +     "╚██████╔╝██║╚██████╔╝██║░░██║╚█████╔╝██║░░██║██║░░██║██████╔╝\n"
                +     "░╚═════╝░╚═╝░╚═════╝░╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░\n";



        System.out.println("Greetings from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();

        }

        System.out.println("Goodbye. Hope to see you again soon!");

    }
}
