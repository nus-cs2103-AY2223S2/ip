import java.util.Scanner;
public class Duke {
    private static final Task[] records = new Task[100];
    private static int index = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Echo! I'm Bond.\nWhat is my mission?");
        boolean check = true;

        while (check) {
            String userinput = sc.nextLine();
            String[] parts = userinput.split(" ");
            String command = parts[0];

            System.out.println("-.-.-.-.-.-.-.-.-.-.-.-");

            switch (command) {
                case "bye":
                    System.out.println("Roger. Agent Bond signing off ~");
                    check = false;
                    break;

                case "missions":
                    for (int i = 0; i < index; i++) {
                        System.out.println((i+1) + ". " + records[i].toString());
                    }
                    break;

                case "unmark":
                    int a = Integer.parseInt(parts[1]) - 1;
                    records[a].incomplete();
                    break;

                case "mark":
                    int b = Integer.parseInt(parts[1]) - 1;
                    records[b].complete();
                    break;

                case "todo":
                    records[index] = new Todo(parts[1]);
                    index++;
                    System.out.println("Added to-do mission: ");
                    System.out.println(records[index-1].toString());
                    System.out.println("You have " + index + " missions in the list");
                    break;

                case "deadline":
                    parts = userinput.split("/");
                    records[index] = new Deadline(parts[0], parts[1]);
                    index++;
                    System.out.println("Added deadline mission: ");
                    System.out.println(records[index-1].toString());
                    System.out.println("You have " + index + " missions in the list");
                    break;

                case "event":
                    parts = userinput.split("/");
                    records[index] = new Event(parts[0], parts[1], parts[2]);
                    index++;
                    System.out.println("Added event mission: ");
                    System.out.println(records[index-1].toString());
                    System.out.println("You have " + index + " missions in the list");
                    break;

                default:
                    System.out.println("Command not recognised");
            }
        }
    }
}
