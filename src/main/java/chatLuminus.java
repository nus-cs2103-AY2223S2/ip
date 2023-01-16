import java.util.Scanner;
import java.util.ArrayList;
public class chatLuminus {
    private ArrayList<String> list;
    chatLuminus() {
        list = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        chatLuminus a = new chatLuminus();
        System.out.println(a.greeting());
        a.echo();
    }

    private String greeting() {
        return "Hello! I'm Luminus\nWhat can I do for you?";
    }

    private void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                for (int i = 1; i <= this.list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            }
            else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
