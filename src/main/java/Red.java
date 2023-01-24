import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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

        TaskFile fi = new TaskFile();
        fi.createFile(tasks);










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
                DeadlineTask NewDeadlineTask = null;
                if(arrOfStr.length <= 1) {
                    throw new RuntimeException("Specification of the DeadlineTask is missing\n");
                }
                String[] deadstr = arrOfStr[1].split("/by ", 2);
                if(deadstr.length != 2) {
                    throw new RuntimeException("Specification of the DeadlineTask is missing\n");
                }
                String[] timestr = deadstr[1].split(" ", 2);
                if(timestr.length < 1) {
                    throw new RuntimeException("Specification of the DeadlineTask is missing\n");
                }

                if(timestr.length == 2 && timestr[1].isEmpty()) {
                    NewDeadlineTask = new DeadlineTask(deadstr[0],timestr[0]);
                } else if(timestr.length == 2) {
                    System.out.println("hello");
                    System.out.println(timestr[1].isEmpty());
                    NewDeadlineTask = new DeadlineTask(deadstr[0],timestr[0],timestr[1]);
                } else if(timestr.length == 1) {
                    NewDeadlineTask = new DeadlineTask(deadstr[0],timestr[0]);
                }

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
                String[] eventStr = arrOfStr[1].split("/from ", 2);
                if(eventStr.length != 2) {
                    throw new RuntimeException("Specification of the EventTask is missing\n");
                }
                String[] dateTimeStr = eventStr[1].split(" /to ", 2);
                if(dateTimeStr.length != 2) {
                    throw new RuntimeException("Specification of the EventTask is missing\n");
                }
                EventTask NewEventTask = new EventTask(eventStr[0], dateTimeStr[0], dateTimeStr[1]);
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

        String TaskListCopy = tasks.toString();
        try {
            fi.appendToFile(TaskListCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Goodbye.");

    }




}
