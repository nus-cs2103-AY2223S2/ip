import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private static final String outro = "Goodbye!";
    private static ArrayList<Task> list = new ArrayList<>();

    private static void addToList(Task t) {
        list.add(t);
        System.out.println("added " + t.getName() + "!");
    }

    private static void emptyErr() {
        System.out.print("Duke: ");
        System.out.println("List empty!");
        System.out.println();
        System.out.print("User: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        System.out.println();
        System.out.print("User: ");
        String[] raw = sc.nextLine().split(" ");
        String input = raw[0];
        
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    System.out.println("Here's your list of tasks!");
                    System.out.println();
                    for(int i  = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                    System.out.println();
                    System.out.print("User: ");
                }
            } else if (input.equals("mark")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    int num = (Integer.parseInt(raw[1]) - 1);
                    list.get(num).mark();
                    System.out.println("Nice! It's marked!");
                    System.out.println(list.get(num));
                    System.out.println();
                    System.out.print("User: ");
                }
            } else if(input.equals("unmark")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    int num = (Integer.parseInt(raw[1]) - 1);
                    list.get(num).unmark();
                    System.out.println("Ok! It's unmarked!");
                    System.out.println(list.get(num));
                    System.out.println();
                    System.out.print("User: ");
                }
            } else {
                System.out.print("Duke: ");
                addToList(new Task(input));
                System.out.println();
                System.out.print("User: ");
            }
            raw = sc.nextLine().split(" ");
            input = raw[0];
        }
        sc.close();
        System.out.print("Duke: ");
        System.out.println(outro);
    }

}
