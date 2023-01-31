package Week2.src.main;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A simple todo bot to manage my todo list.
 * It can mark done jobs or unmark.
 * A simple todo bot that helps you to manage your tasks with deadline or occuring time.
 * @author Park Hyunjin
 */
public class Duke extends Application {
    private static Storage storage;
    private Ui ui;
    private static TaskList tasklist;
    static boolean isBye = false;

    @Override
    public void start(Stage stage) {
        
    }
    /**
     * Constructor of Duke.
     * It begins the basic operation of the program when it is constructed.
     * Duke constructor.
     * Takes filePath from main and creates Ui and storage from it.
     * It can load previous data from the file path.
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ui.hello();
            tasklist = new TaskList(storage.load());
            run();
            ui.bye();
        } catch (IndexOutOfBoundsException e) {
            ui.showEmptyError();
        } catch (FileNotFoundException e) {
            ui.showFileError();
        } catch (Exception e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Prints out a line
     * @return a line to divide outcomes.
     */
    public static void lining() {
        System.out.println("____________________________________________________________");
    }

    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("saves/data.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes information of the task on the file
     * @param currtask Current task that user has entered
     * It uses a file writer to write the data on the file according to the user's input.
     * @param currtask takes the current task to write (todo, deadline, or event)
     * @throws IOException
     */
    public static void writeOn(Task currtask) throws IOException {
        fw.write(currtask.toString() +System.lineSeparator());
    }

    public static void run() throws IOException {
        String comm = "";
        Parser parser = new Parser();
        while(!isBye) {
            String c = parser.getCommand();
            if (c.equals("list")) {
                lining();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasklist.size(); i++) {
                    Task current = (Task) tasklist.get(i);
                    int curnum = i+1;
                    System.out.println(curnum +"."+current.toString());
                }
                lining();

            } else if (c.startsWith("mark")) {
                String str = c.substring(c.length() - 1);
                int marking = Integer.parseInt(str);
                Task current = (Task) tasklist.get(marking - 1);
                current.setDone();
                lining();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + current.content);
                lining();
                writeOn(current);

            } else if (c.startsWith("unmark")) {
                String str = c.substring(c.length() - 1);
                int marking = Integer.parseInt(str);
                Task current = (Task) tasklist.get(marking - 1);
                current.setNotDone();
                lining();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ]" +current.content);
                lining();
                writeOn(current);

            } else if(c.startsWith("delete")) {
                String str = c.substring(c.length() - 1);
                int marking = Integer.parseInt(str);
                Task current = (Task) tasklist.get(marking - 1);
                tasklist.remove(marking-1);
                lining();
                System.out.println("Noted. I've removed this task:");
                System.out.println(current.toString());
                System.out.println("Now you have " +tasklist.size()+ " tasks in the list");
                lining();

            }else if (c.startsWith("todo")) {
                String doit = c.substring(5, c.length());
                Task current = new Todo(doit);
                tasklist.add(current);
                lining();
                System.out.println("Got it. I've added this task:");
                System.out.println(current.toString());
                System.out.println("Now you have " + tasklist.size() + " tasks in the list");
                lining();
                writeOn(current);

            } else if (c.startsWith("deadline")) {
                String doit = c.substring(9, c.length());
                String[] parts = doit.split("/by");
                Task current = new Deadline(parts[0], parts[1]);
                tasklist.add(current);
                lining();
                System.out.println("Got it. I've added this task:");
                System.out.println(current.toString());
                System.out.println("Now you have " + tasklist.size() + " tasks in the list");
                lining();
                writeOn(current);

            } else if (c.startsWith("event")) {
                String doit = c.substring(6, c.length());
                String[] froms = doit.split("/from");
                String[] fromses = froms[1].split("/to");
                String[] tos = doit.split("/to");
                Task current = new Event(froms[0], fromses[0], tos[1]);
                tasklist.add(current);
                lining();
                System.out.println("Got it. I've added this task:");
                System.out.println(current.toString());
                lining();
                writeOn(current);

            } else if (c.startsWith("find")) {
                String keyword = c.substring(6, c.length());
                Search sr = new Search(tasklist);
                Search.find(keyword);

            } else if (!c.equals("bye")){
                lining();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                lining();
            }

            if(c.equals("bye")) {
                break;
            }
        }
    }
    /**
     * Main method to manage my todo list
     * Takes inputs from the user and send them to right classes.
     * Main method of the todo bot. It generally manages inputs and outputs.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("saves/data.txt").run();
    }
}
