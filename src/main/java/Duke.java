import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    static final String DIRPATH = "./data";
    static final String FILEPATH = "./data/tasks.txt";
    private static final ArrayList<Task> al = new ArrayList<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final Scanner sc = new Scanner(System.in);

    public static void init() {
        System.out.println(DIVIDER + "Welcome hooman!\nCome add some tasks today!\n" + DIVIDER);
    }
    public static void welcomeBack() {
        String welcome = "Welcome back hooman!\n" +
                "Wat u want to do today?\n";
        System.out.println(DIVIDER + welcome + DIVIDER);
    }

    public static void bye() {
        System.out.println(DIVIDER + "Ah..... okkkk nehmind. GO. BYE. :)\n" + DIVIDER);
    }

    public static void mark(String i) {
        int cint = Integer.parseInt(i) - 1;
        try {
            Task curr = Task.getTaskList().get(cint);
            curr.mark();
            System.out.println(DIVIDER + "Congrats this has been d:\n"
                    + curr + "\n down, Leskooo!\n" + DIVIDER);
        } catch (Exception m){
            System.out.println("Number entered out of range, type the number again");
            String s = sc.nextLine();
            mark(s);
        }
    }

    public static void unmark(String i) throws Exception{
        int cint = Integer.parseInt(i) - 1 ;
        try {
            Task curr = Task.getTaskList().get(cint);
            curr.unmark();
            System.out.println(DIVIDER + "Alright, new task:\n" + curr
                    + "\nWe can do dis!\n" + DIVIDER);
        } catch (Exception m) {
            System.out.println("Number entered out of range, type the number again");
            String s = sc.nextLine();
            unmark(s);
        }
    }


    public static void main(String[] args) throws Exception {
        try {
            Files.createDirectory(Paths.get(DIRPATH));
            File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
            Task.taskFile(file, true);
            init();
        } catch (FileAlreadyExistsException m) {
            try {
                File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
                Task.taskFile(file, true);
            } catch (FileAlreadyExistsException n) {
                File file = new File(FILEPATH);
                Task.taskFile(file, false);
                welcomeBack();
            }
        }
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        String[] splited = in.split(" ");
        String bye = "bye";
        while (!in.equalsIgnoreCase(bye)) {
            switch (splited[0].toUpperCase()) {
                case "LIST":
                    Task.printList();
                    break;
                case "MARK":
                    mark(splited[1]);
                    break;
                case "UNMARK":
                    unmark(splited[1]);
                    break;
                case "TODO" :
                    Todo.createTodo(splited);
                    break;
                case "EVENT" :
                    Event.createEvent(splited);
                    break;
                case "DEADLINE" :
                    Deadline.createDeadline(splited);
                    break;
                case "DELETE" :
                    Task.delete(splited[1]);
                    break;
                default:
                    System.out.println(DIVIDER+ "I have no idea what is going on, try again?\n" + DIVIDER);
                    break;

            }
            in = sc.nextLine();
            splited = in.split(" ");
        }
        Task.writeToFile();
        bye();
    }
}
