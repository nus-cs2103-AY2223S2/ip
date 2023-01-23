import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private final static String logo =
                  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>(100);
    private static String PATH = "./data/duke.txt";

    public static void main(String[] args) throws DukeException{
        //System.out.println("Hello from\n" + logo + "\n");
        Duke duke = new Duke();
        duke.activate();
    }
    public void activate() throws DukeException {
        //this.printLine();
        System.out.println("Hello from\n" + logo);
        this.printLine();
        this.greet();
        loadData();
        this.printLine();
        String i = sc.nextLine();

        while (!i.equalsIgnoreCase("bye")) {
            try {
                if (i.equalsIgnoreCase("list")) {
                    this.printList();
                } else if (i.toLowerCase().startsWith("mark")) {
                    int num = Integer.parseInt(i.split(" ")[1]);
                    Task t = this.tasks.get(num - 1);
                    t.markDone();
                    getMarkDoneMessage(t);
                } else if (i.toLowerCase().startsWith("unmark")) {
                    int num = Integer.parseInt(i.split(" ")[1]);
                    Task t = this.tasks.get(num - 1);
                    t.unmarkDone();
                    getUnmarkDoneMessage(t);
                } else if(i.toLowerCase().startsWith("delete")) {
                    int num = Integer.parseInt(i.split(" ")[1]);
                    Task t = this.tasks.get(num - 1);
                    this.deleteTask(num);
                    this.deleteTaskMessage(t);
                } else if (i.toLowerCase().startsWith("todo")) {
                    if (i.split(" ").length == 1) {
                        throw new EmptyInputException();
                    }
                    this.addToDo(i);
                } else if (i.toLowerCase().startsWith("deadline")) {
                    this.addDeadline(i);
                } else if (i.toLowerCase().startsWith("event")) {
                    this.addEvent(i);
                } else {
                    throw new InvalidInputException();
                }
                writeListToFile();
            } catch (EmptyInputException | InvalidInputException | IOException e) {
                System.out.println(e.getMessage());
                this.printLine();
            }
            i = sc.nextLine();
        }
        this.terminate();
    }
    public void greet() {
        System.out.println("Hello! I'm Duke, a bot to help track your tasks.");
    }
    public void echoText(String s) {
        this.printLine();
        System.out.println(s);
        this.printLine();
    }
    public void terminate() {
        //this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
        sc.close();
    }
    public void printLine() {
        System.out.println("_____________________________________________________________________");
    }
    public void storeTask(Task t) {
        this.tasks.add(t);
    }
    public void printList() {
        //this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= this.tasks.size(); i++) {
            Task t = this.tasks.get(i-1);
            System.out.println(i + ". " + t.toString());
        }
        this.printLine();
    }
    public void addToDo(String i) throws IOException {
        //this.printLine();
        ToDo t = new ToDo(i.replace("todo ", ""));
        this.storeTask(t);
        this.addTaskMessage(t);
    }
    public void addDeadline(String i) throws IOException {
        String[] contents = i.split(" /by ");
        Deadline d = new Deadline(contents[0].replace("deadline ", ""), contents[1]);
        this.storeTask(d);
        this.addTaskMessage(d);
    }
    public void addEvent(String i) throws IOException {
        String[] contents = i.split(" /from ");
        String[] fromTo = contents[1].split(" /to ");
        Event e = new Event(contents[0].replace("event ", ""), fromTo[0], fromTo[1]);
        this.storeTask(e);
        this.addTaskMessage(e);
    }
    public void getMarkDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
        this.printLine();
    }
    public void addTaskMessage(Task t) {
        System.out.println("Got it. I've added this task:\n  " + t.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        this.printLine();
    }
    public void getUnmarkDoneMessage(Task t) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + "  " + t.toString());
        this.printLine();
    }
    public void deleteTaskMessage(Task t) {
        System.out.println("Noted. I've removed this task:\n" + "  " + t.toString() +
                "\nNow you have " + this.tasks.size() + " tasks in the list.");
        this.printLine();
    }
    public void deleteTask(int num) {

        this.tasks.remove(num - 1);
    }
    /* code reused from:
    https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
    author Aaron D
     */
    public void loadData() {
            File directory = new File("./data");
            if (! directory.exists()){
                directory.mkdir();
            }
            File file = new File(PATH);
            try{
                if(file.createNewFile()) {
                    System.out.println("Seems like you're new here. Welcome onboard and let's get started! ^-^");
                } else {
                    readTextFile(file);
                }
            } catch (IOException e){
                e.printStackTrace();
                System.exit(-1);
            }
    }
    public void readTextFile(File f) throws FileNotFoundException {
        Scanner fs = new Scanner(f);
        if(!fs.hasNext()) {
            System.out.println("Your task list is currently empty! Let's get started! ^-^");
        } else {
            System.out.println("Here, I've pulled up the most recent task list I have from you:");
            while(fs.hasNext()) {
                System.out.println("  " + fs.nextLine());
            }
        }
    }
    public void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter(PATH);
        for(Task t : tasks) {
            fw.write(t.toString());
            fw.write("\n");
        }
        fw.close();
    }
}
