import java.io.*;
import java.util.*;
public class Duke {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static ArrayList<String> al = new ArrayList<>();
    private static Map<String, Boolean> boolmap = new HashMap<>();

    public static void welcome() {
        String welcome = "Welcome hooman!\n" +
                "Wat u want today?\n";
        System.out.println(divider + "Hello from\n" + welcome + divider);
    }

    public static void bye() {
        System.out.println(divider + "Ah..... okkkk nehmind. GO. BYE. :)");
    }

    public static void printList() {
        ListIterator<String> li = al.listIterator();
        if (!li.hasNext()) {
            System.out.println(divider + "List is empty.......\n" + divider);
        } else {
            int count = 1;
            System.out.println(divider + "Hurry up and finish these tasks:");
            while (li.hasNext()) {
                String curr = li.next();
                boolean check = boolmap.get(curr);
                if (check) {
                    System.out.println(count + ".[X] " + curr);
                } else {
                    System.out.println(count + ".[ ] " + curr);
                }
                count++;
            }
            System.out.print(divider);
        }
    }
    public static void printDefault(String s) {
        System.out.println(divider + "added: " + s + "\n" + divider);
    }

    public static void mark(String i) {
        int cint = Integer.parseInt(i) - 1;
        String curr = al.get(cint);
        boolmap.replace(curr, true);
        System.out.println(divider + "Congrats this has been done:\n"
                        +"   [X] " + curr + "\nOne down, Leskooo!\n" + divider);
    }

    public static void unmark(String i) {
        int cint = Integer.parseInt(i) - 1 ;
        String curr = al.get(cint);
        boolmap.replace(curr, false);
        System.out.println(divider + "Alright, new task:\n" + "   [ ] "
                + curr + "\nWe can do dis!\n" + divider);
    }
    public static void main(String[] args) throws IOException {
        welcome();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        String[] splited = in.split(" ");
        String bye = "bye";
        while (!in.equalsIgnoreCase(bye)) {
            switch (splited[0].toUpperCase()) {
                case "LIST":
                    printList();
                    break;
                case "MARK":
                    mark(splited[1]);
                    break;
                case "UNMARK":
                    unmark(splited[1]);
                    break;
                default:
                    printDefault(in);
                    al.add(in);
                    boolmap.put(in, false);
            }
            in = br.readLine();
            splited = in.split(" ");
        }
        bye();
    }
}
