import java.util.ArrayList;
import java.util.Scanner;

public class Lulu {
    private static String LINE = "____________________________________________________________";
    public static final String NAME = "./data/lulu.txt";
    //public static ArrayList<Task> list = new ArrayList<>();
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    public Lulu(String filePath) {
        this.ui = new UI();
        //this.list = list;
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
    }

    public void run() {
        ui.showGreetText();
        if (storage.isSavePresent()) {
            Command c = new LoadCommand();
            c.execute(tasks, ui, storage);
        }

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                //String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LuluException e) {
                ui.showLine();
                System.out.println(e);
                ui.showLine();
            } catch (IndexOutOfBoundsException e) {
                ui.showLine();
                System.out.println("(=ಠᆽಠ=) That is out of bounds!");
                ui.showLine();
            }
        }
    }

    /**
    public void run(String s) {
        try {
            String[] command = s.split(" ");
            switch (command[0]) {
                case "list":
                    this.list();
                    break;
                case "mark":
                    this.mark(Integer.valueOf(command[1]));
                    break;
                case "unmark":
                    this.unmark(Integer.valueOf(command[1]));
                    break;
                case "delete":
                    this.delete(Integer.valueOf(command[1]));
                    break;
                case "deadline":
                    if (command.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) The description of a deadline cannot be empty.");
                    }
                    String appendDeadline = s.substring(9);
                    String[] deadlineDetails = appendDeadline.split("/by");
                    if (deadlineDetails.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) Please include a deadline using /by");
                    }
                    this.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                    break;
                case "event":
                    if (command.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) The description of a event cannot be empty.");
                    }
                    String appendEvent = s.substring(6);
                    String[] eventDetails = appendEvent.split("/from");
                    if (eventDetails.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) Please include a timing using /from and /to");
                    }
                    String[] toFrom = eventDetails[1].split("/to");
                    if (toFrom.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) You included /from but missed out /to");
                    }
                    this.add(new Event(eventDetails[0], toFrom[0], toFrom[1]));
                    break;
                case "todo":
                    if (command.length == 1) {
                        throw new LuluException("(=✖ ᆺ ✖=) The description of a todo cannot be empty.");
                    }
                    String description = s.substring(5);
                    this.add(new Todo(description));
                    break;
                default:
                    throw new LuluException("(=✖ ᆺ ✖=) That's not a valid command.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Lulu.LINE);
            System.out.println("(=ಠᆽಠ=) That is out of bounds!");
            System.out.println(Lulu.LINE);
        } catch (LuluException e) {
            System.out.println(Lulu.LINE);
            System.out.println(e);
            System.out.println(Lulu.LINE);
        }
    }*/

    /**
    public void load(String s) {
        String[] command = s.split("`");
        switch (command[0]) {
            case "D":
                this.recall(new Deadline(command[2], command[3]));
                if (Integer.valueOf(command[1]) == 1) {
                    list.get(list.size()-1).markAsDone();
                }
                break;
            case "E":
                this.recall(new Event(command[2], command[3], command[4]));
                if (Integer.valueOf(command[1]) == 1) {
                    list.get(list.size()-1).markAsDone();
                }
                break;
            case "T":
                this.recall(new Todo(command[2]));
                if (Integer.valueOf(command[1]) == 1) {
                    list.get(list.size()-1).markAsDone();
                }
                break;
        }
    }*/

    /**
    public void greet() {
        this.ui.showGreetText();
    }*/

    /**
    public void exit() {
        ArrayList<String> toWrite = new ArrayList<>();
        int size = this.list.size();
        for (int i = 0; i < size; i ++) {
            toWrite.add(this.list.get(i).toMemory());
        }
        Storage.writeSave(toWrite);
        this.ui.showExitText();
    }*/

    /**
    public void recall(Task t) {
        this.tasks.add(t);
    }*/

    /**
    public void add(Task t) {
        this.tasks.add(t);
        this.ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize());
    }*/

    /**
    public void delete(int taskNumber) {
        this.ui.showDeleteText(tasks.getTaskDescription(taskNumber), tasks.getSize()-1);
        tasks.remove(taskNumber);
    }*/

    /**
    public void list() {
        System.out.println(LINE);
        System.out.println("ฅ(=චᆽච=ฅ) Here are the tasks in your list: ");
        int length = list.size();
        for (int i = 0; i < length; i++) {
            System.out.print(i+1);
            System.out.println(". " + list.get(i));
        }
        System.out.println(LINE);
    }*/

    /**
    public void mark(int taskNumber) {
        tasks.markTask(taskNumber);
        this.ui.showMarkText(tasks.getTaskDescription(taskNumber));
    }*/

    /**
    public void unmark(int taskNumber) {
        tasks.unmarkTask(taskNumber);
        this.ui.showUnmarkText(tasks.getTaskDescription(taskNumber).toString());
    }*/
}
