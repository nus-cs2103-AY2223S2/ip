
public class Lulu {
    public static String LINE = "____________________________________________________________";
    public static String[] LIST = new String[100];
    public static int LIST_COUNTER = 0;

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu");
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
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void add(String s) {
        this.LIST[LIST_COUNTER] = s;
        LIST_COUNTER++;
        System.out.println(LINE);
        System.out.println("added: " + s);
        System.out.println(LINE);
    }

    public void list() {
        System.out.println(LINE);
        for (int i = 0; i < LIST_COUNTER; i++) {
            System.out.print(i+1);
            System.out.println(". " + LIST[i]);
        }
        System.out.println(LINE);
    }
}
