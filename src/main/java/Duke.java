import java.io.*;
import java.util.*;
public class Duke {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static ArrayList<Task> al = new ArrayList<>();


    public static void welcome() {
        String welcome = "Welcome hooman!\n" +
                "Wat u want today?\n";
        System.out.println(divider + "Hello from\n" + welcome + divider);
    }

    public static void bye() {
        System.out.println(divider + "Ah..... okkkk nehmind. GO. BYE. :)");
    }

    public static void printList() {
        ListIterator<Task> li = al.listIterator();
        if (!li.hasNext()) {
            System.out.println(divider + "List is empty.......\n" + divider);
        } else {
            int count = 1;
            System.out.println(divider + "Hurry up and finish these tasks:");
            while (li.hasNext()) {
                Task curr = li.next();
                System.out.print(curr);
                count++;
            }
            System.out.print(divider);
        }
    }
    public static void printDefault(Task t) {
        System.out.println(divider + "Aite letsgetit you added: \n" + t.toString() + "\n" +
                "currently you have " + t.numberTask() + " tasks \n" + divider);
    }

    public static void mark(String i) {
        int cint = Integer.parseInt(i) - 1;
        Task curr = al.get(cint);
        curr.mark();
        System.out.println(divider + "Congrats this has been done:\n"
                        + curr.toString() + "\nOne down, Leskooo!\n" + divider);
    }

    public static void unmark(String i) {
        int cint = Integer.parseInt(i) - 1 ;
        Task curr = al.get(cint);
        curr.unmark();
        System.out.println(divider + "Alright, new task:\n" + curr.toString()
                + "\nWe can do dis!\n" + divider);
    }

    public static Event createEvent(String[] splited) {
        StringBuilder sb = new StringBuilder();
        boolean one = true;
        boolean two = false;
        boolean three = false;
        String n = " ", s = " ", e = " ";
        for (int i = 1; i < splited.length; i++) {
            if (one) {
                if (!splited[i].equalsIgnoreCase("/from")) {
                    sb.append(splited[i]);
                } else {
                    two = true;
                    n = sb.toString();
                    sb.setLength(0);
                    one = false;
                }
            } else if (two) {
                if (!splited[i].equalsIgnoreCase("/to")) {
                    sb.append(splited[i]);
                } else {
                    three = true;
                    s = sb.toString();
                    sb.setLength(0);
                    two = false;
                }
            } else if (three) {
                sb.append(splited[i]);
            }
            if (i + 1 != splited.length) {
                sb.append(" ");
            }
        }
        e = sb.toString();
        return new Event(n, s, e);
    }

    public static Deadline createDeadline(String[] splited) {
        StringBuilder sb = new StringBuilder();
        boolean one = true;
        String n = " ", e = " ";
        for (int i = 1; i < splited.length; i++) {
            if (one) {
                if (!splited[i].equalsIgnoreCase("/by")) {
                    sb.append(splited[i]);
                } else {
                    n = sb.toString();
                    sb.setLength(0);
                    one = false;
                }
            } else {
                sb.append(splited[i]);
            }
            if (i + 1 != splited.length) {
                sb.append(" ");
            }
        }
        e = sb.toString();
        return new Deadline(n, e);
    }
    public static void main(String[] args) throws IOException {
        welcome();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
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
                case "TODO" :
                    for (int i = 1; i < splited.length; i++) {
                        sb.append(splited[i]);
                        sb.append(" ");
                    }
                    Todo t = new Todo(sb.toString());
                    sb.setLength(0);
                    al.add(t);
                    printDefault(t);
                    break;
                case "EVENT" :
                    Event e = createEvent(splited);
                    al.add(e);
                    printDefault(e);
                    break;
                case "DEADLINE" :
                    Deadline d = createDeadline(splited);
                    al.add(d);
                    printDefault(d);
                    break;
                default:
                    break;

            }
            in = br.readLine();
            splited = in.split(" ");
        }
        bye();
    }
}
