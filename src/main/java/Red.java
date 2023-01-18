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






        System.out.println(logo + " is ready to assist you\n");

        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        arrOfStr = input.split(" ", 2);


        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(tasks);
                reader();
            } else if(arrOfStr[0].equals("mark")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of which task to mark is missing\n");
                }
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).mark();
                reader();
            } else if(arrOfStr[0].equals("unmark")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of a which task to unmark is missing\n");
                }
                Integer index = Integer.valueOf(arrOfStr[1]) - 1;
                tasks.indexof(index).unmark();
                reader();
            } else if(arrOfStr[0].equals("deadline")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of the DeadlineTask is missing\n");
                }
                String[] deadstr = arrOfStr[1].split("/", 2);
                if(deadstr.length != 2) {
                    throw new RuntimeException("Specification of the DeadlineTask is missing\n");
                }
                DeadlineTask NewDeadlineTask = new DeadlineTask(deadstr[0],deadstr[1]);
                tasks.enq(NewDeadlineTask);
                reader();
            } else if(arrOfStr[0].equals("todo")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of the ToDoTask is missing\n");
                }
                ToDoTask NewToDoTask = new ToDoTask(arrOfStr[1]);
                tasks.enq(NewToDoTask);
                reader();
            } else if(arrOfStr[0].equals("event")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of the EventTask is missing\n");
                }
                String[] eventstr = arrOfStr[1].split("/", 3);
                if(eventstr.length != 3) {
                    throw new RuntimeException("Specification of the EventTask is missing\n");
                }
                EventTask NewEventTask = new EventTask(eventstr[0], eventstr[1], eventstr[2]);
                tasks.enq(NewEventTask);
                reader();
            }else if(arrOfStr[0].equals("delete")) {
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of the DeleteTask is missing\n");
                }
                tasks.delete(Integer.parseInt(arrOfStr[1]));
                reader();
            }  else {
                throw new RedDoesNotUnderstandException();
            }


        }

        System.out.println("Goodbye.");

    }




}
