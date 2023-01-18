import java.io.*;
import java.util.*;
public class Duke {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    public static void welcome() {
        String welcome = "Welcome hooman!\n" +
                "Wat u want today?\n";
        System.out.println(divider + "Hello from\n" + welcome + divider);
    }

    public static void bye() {
        System.out.println(divider + "Ah..... okkkk nehmind. GO. BYE. :)");
    }

    public static void printList(ArrayList<String> al) {
        ListIterator<String> li = al.listIterator();
        if (!li.hasNext()) {
            System.out.println("List is empty.......\n" + divider);
        } else {
            int count = 1;
            while (li.hasNext()) {
                System.out.println(count + ". " + li.next());
                count++;
            }
            System.out.print(divider);
        }
    }
    public static void printDefault(String s) {
        System.out.println("added: " + s + "\n" + divider);
    }
    public static void main(String[] args) throws Exception {
        welcome();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> al = new ArrayList<>();
        String in = br.readLine();
        String bye = "bye";
        while(!in.equalsIgnoreCase(bye)){
            switch (in.toUpperCase()) {
                case "LIST" : printList(al);
                              break;
                default : printDefault(in);
                          al.add(in);
            }
            in = br.readLine();
        }
        bye();
    }
}
