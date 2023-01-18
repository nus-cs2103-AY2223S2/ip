import java.util.Scanner;
public class Sunday {
    private static State state = State.GREET;
    private static Record list = new Record();
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
                    if (input.contains("mark")) {
                        state = State.UPDATE;
                        if (input.charAt(0) == 'm') {
                            mark(Integer.parseInt(String.valueOf(input.charAt(5))));
                        } else {
                            unmark(Integer.parseInt(String.valueOf(input.charAt(7))));
                        }
                    } else {
                        state = State.ADD;
                        add(input);
                    }
                    break;
            }
        }
        exit();
        sc.close();
    }
    private static void mark(int index) {
        index--;
        list.mark(index);
        print("Well Done! I've marked this task as done:\n    " + list.taskToString(index));
    }
    public static void unmark(int index) {
        index--;
        list.unmark(index);
        print("OK, I've marked this task as not done yet:\n    " + list.taskToString(index));
    }
    private static void greet() {
        print("Hi! I'm Sunday, pleasure to meet you!\n    What can I do for you?");
    }
    private static void add(String taskName) {
        list.add(taskName);
        print("added: " + taskName);
    }
    private static void list() {
        print(list.toString());
    }
    private static void exit() {
        print("Goodbye and have a pleasant day!");
    }

    private static void print(String text) {
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("  ____________________________________________________________");
    }
}
