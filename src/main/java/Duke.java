import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private static final String outro = "Goodbye!";
    private static ArrayList<String> list = new ArrayList<>();

    private static void addToList(String str) {
        list.add(str);
        System.out.println("added " + str + "!");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        System.out.println();
        System.out.print("User: ");
        String input = sc.next();
        
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                if(list.isEmpty()) {
                    System.out.print("Duke: ");
                    System.out.println("List empty!");
                    System.out.println();
                    System.out.print("User: ");
                } else {
                    for(int i  = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                    System.out.println();
                    System.out.print("User: ");
                }
            } else {
                System.out.print("Duke: ");
                addToList(input);
                System.out.println();
                System.out.print("User: ");
            }
            input = sc.next();
        }
        System.out.print("Duke: ");
        System.out.println(outro);
    }

}
