import java.util.ArrayList;

public class Lulu {
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<>();

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
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon! (=◉ᆽ◉=)");
        System.out.println(LINE);
    }

    public void add(String s) {
        try {
            list.add(Task.of(s));
            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + list.get(list.size()-1).toString());
            System.out.println("Now you have " + list.size() + " tasks in the list. ฅ(=චᆽච=ฅ)");
            System.out.println(LINE);
        } catch (LuluException e) {
            System.out.println(LINE);
            System.out.println(e);
            System.out.println(LINE);
        }
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
