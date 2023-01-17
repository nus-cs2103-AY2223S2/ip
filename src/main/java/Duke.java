import java.util.Scanner;

public class Duke {
    private static TaskList tasks = new TaskList(100);

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
            if(input.equals("list")) {
                System.out.println(tasks);
                input = scanner.nextLine();
            } else {
                System.out.println(input);
                tasks.enq(input);
                input = scanner.nextLine();
            }


        }

        System.out.println("Goodbye. Hope to see you again soon!");

    }
}
