import java.util.ArrayList;

public class Lulu {
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<>();

    public Lulu() {
        this.list = list;
    }

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
    }

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
    }

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu (=◕ᆽ◕ฺ=)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void echo(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    public void exit() {
        ArrayList<String> toWrite = new ArrayList<>();
        int size = this.list.size();
        for (int i = 0; i < size; i ++) {
            toWrite.add(this.list.get(i).toMemory());
        }
        Save.writeSave(toWrite);
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon! (=◉ᆽ◉=)");
        System.out.println(LINE);
    }

    public void add(Task t) {
        this.list.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.get(list.size()-1).toString());
        System.out.println("Now you have " + list.size() + " tasks in the list. ฅ(=චᆽච=ฅ)");
        System.out.println(LINE);
    }

    public void recall(Task t) {
        this.list.add(t);
    }

    public void list() {
        System.out.println(LINE);
        System.out.println("ฅ(=චᆽච=ฅ) Here are the tasks in your list: ");
        int length = list.size();
        for (int i = 0; i < length; i++) {
            System.out.print(i+1);
            System.out.println(". " + list.get(i));
        }
        System.out.println(LINE);
    }

    public void mark(int taskNumber) {
        list.get(taskNumber-1).markAsDone();
        System.out.println(LINE);
        System.out.println("(₌♥ᆽ♥₌) Nice! I've marked this task as done:");
        System.out.println(" " + list.get(taskNumber-1).toString());
        System.out.println(LINE);
    }

    public void unmark(int taskNumber) {
        list.get(taskNumber-1).markAsUndone();
        System.out.println(LINE);
        System.out.println("(₌ ᵕ̣̣̣̣̣ ᆽ ᵕ̣̣̣̣̣₌) OK, I've marked this task as not done yet:");
        System.out.println(" " + list.get(taskNumber-1).toString());
        System.out.println(LINE);
    }
    public void delete(int taskNumber) {
        System.out.println(LINE);
        System.out.println("Noted! I've removed this task:");
        System.out.println("  " + list.get(taskNumber-1).toString());
        System.out.println("Now you have " + (list.size()-1) + " tasks in the list. ฅ(=චᆽච=ฅ)");
        System.out.println(LINE);
        list.remove(taskNumber-1);
    }
}
