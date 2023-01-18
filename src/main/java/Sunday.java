import java.util.Scanner;
public class Sunday {
    private static State state = State.GREET;
    private static Record list = new Record();
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (state != State.EXIT) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                state = State.EXIT;
            } else if (input.equals("list")) {
                state = State.LIST;
                list();
            } else {
                state = State.ADD;
                add(input);

            }
        }
        exit();
    }
    private static void greet() {
        print("Hi! I'm Sunday, pleasure to meet you!\n    What can I do for you?");
    }
    private static void add(String input) {
        Task newTask = new Task(input);
        list.add(newTask);
        print("added: " + input);
    }
    private static void list() {
        print(list.toString());
    }
    private static void exit() {
        print("Goodbye and have a pleasant day!");
    }

    private static void print(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");
    }
}
