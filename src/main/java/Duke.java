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
        String[] arrOfStr = input.split(" ", 2);

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(tasks);
                input = scanner.nextLine();
                arrOfStr = input.split(" ", 2);
            } else if(arrOfStr[0].equals("mark")) {
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).mark();
                input = scanner.nextLine();
                arrOfStr = input.split(" ", 2);
            } else if(arrOfStr[0].equals("unmark")) {
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).unmark();
                input = scanner.nextLine();
                arrOfStr = input.split(" ", 2);
            } else {
                Task newtask = new Task(input);
                tasks.enq(newtask);
                input = scanner.nextLine();
                arrOfStr = input.split(" ", 2);
            }


        }

        System.out.println("Goodbye. Hope to see you again soon!");

    }
}
