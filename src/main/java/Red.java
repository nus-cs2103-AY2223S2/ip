import java.util.Scanner;

public class Red{
    private static TaskList tasks = new TaskList(100);
    private static Scanner scanner;
    private static String input;
    private static String[] arrOfStr;

    public static void reader() {
        input = scanner.nextLine();
        arrOfStr = input.split(" ", 2);
    }




    public static void main(String[] args) {
        String logo = "██████╗░███████╗██████╗░\n"
                 +    "██╔══██╗██╔════╝██╔══██╗\n"
                 +    "██████╔╝█████╗░░██║░░██║\n"
                 +    "██╔══██╗██╔══╝░░██║░░██║\n"
                 +    "██║░░██║███████╗██████╔╝\n"
                 +    "╚═╝░░╚═╝╚══════╝╚═════╝░\n";






        System.out.println(logo + "is ready to assist you\n");

        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        arrOfStr = input.split(" ", 2);


        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(tasks);
                reader();
            } else if(arrOfStr[0].equals("mark")) {
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).mark();
                reader();
            } else if(arrOfStr[0].equals("unmark")) {
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).unmark();
                reader();
            } else if(arrOfStr[0].equals("deadline")) {
                String[] deadstr = arrOfStr[1].split("/", 2);
                DeadlineTask NewDeadlineTask = new DeadlineTask(deadstr[0],deadstr[1]);
                tasks.enq(NewDeadlineTask);
                reader();
            } else if(arrOfStr[0].equals("todo")) {
                ToDoTask NewToDoTask = new ToDoTask(arrOfStr[1]);
                tasks.enq(NewToDoTask);
                reader();
            } else if(arrOfStr[0].equals("event")) {
                String[] eventstr = arrOfStr[1].split("/", 3);
                EventTask NewEventTask = new EventTask(eventstr[0], eventstr[1], eventstr[2]);
                tasks.enq(NewEventTask);
                reader();
            } else {
                Task newtask = new Task(input);
                tasks.enq(newtask);
                reader();
            }


        }

        System.out.println("Goodbye.");

    }




}
