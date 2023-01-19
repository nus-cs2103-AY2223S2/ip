import java.util.Scanner;
public class Sunday {
    private static State state = State.GREET;
    private static Record list = new Record();
    private final static Printer printer = new Printer();
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (state != State.EXIT) {
            String input = sc.nextLine();

            switch (input) {
                case "bye":
                    state = State.EXIT;
                    break;
                case "list":
                    state = State.LIST;
                    list();
                    break;
                default:
                    state = State.UPDATE;
                    update(input);
                    break;
            }
        }
        exit();
        sc.close();
    }
    private static void update(String input) {
        if (input.startsWith("mark")) {
            mark(Integer.parseInt(String.valueOf(input.charAt(5))));
        } else if (input.startsWith("unmark")) {
            unmark(Integer.parseInt(String.valueOf(input.charAt(7))));
        } else {
            add(input);
        }
    }
    private static void mark(int index) {
        index--;
        list.mark(index);

        printer.printBar();
        printer.printText("Well Done! I've marked this task as done:");
        printer.printText("  " + list.taskToString(index));
        printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
        printer.printBar();
    }
    public static void unmark(int index) {
        index--;
        list.unmark(index);
        printer.printBar();
        printer.printText("OK, I've marked this task as not done yet:");
        printer.printText("  " + list.taskToString(index));
        printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
        printer.printBar();
    }
    private static void greet() {
        printer.printBar();
        printer.printText("Hi! I'm Sunday, pleasure to meet you!");
        printer.printText("How can I help?");
        printer.printBar();
    }
    private static void add(String input) {
        list.add(input);

        printer.printBar();
        printer.printText("Got it. I've added this task:");
        printer.printText("  " + list.latestTaskToString());
        printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
        printer.printBar();
    }
    private static void list() {
        printer.printBar();
        printer.printText("Here's everything I've noted down for you:");
        printer.printText(list.toString());
        printer.printBar();
    }
    private static void exit() {
        printer.printBar();
        printer.printText("Goodbye and have a pleasant day!");
        printer.printBar();
    }
}
