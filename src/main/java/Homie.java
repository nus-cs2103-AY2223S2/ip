import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Homie {

    private static final List<String> list = new ArrayList<>();

    public static void print(String s) {
        System.out.println(s);
    }

    public static void interact() {
        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();

            switch (input) {
            case "bye":
                Homie.print("   > Aight imma head out");
                break;
            case "list":
                for (int i = 0; i < list.size(); i++) {
                    String task = list.get(i);
                    Homie.print("   > " + (i + 1) + ". " + task);
                }
                break;
            default:
                Homie.print("   > added:" + input);
                Homie.list.add(input);
            }

            if (input.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        Homie.print("   > What's up dawg");
        Homie.interact();
    }
}
