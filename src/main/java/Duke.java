import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> records = new ArrayList<>();
    private static int index = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        System.out.println("Echo! I'm Bond.\nWhat is my mission?");
        System.out.println(".....");

        while (check) {
            String userinput = sc.nextLine();
            String[] parts = userinput.split(" ");
            String command = parts[0];

            try {
                switch (command) {
                    case "bye":
                        System.out.println("Roger. Agent Bond signing off ~");
                        check = false;
                        break;

                    case "missions":
                        for (int i = 0; i < index; i++) {
                            System.out.println((i + 1) + ". " + records.get(i).toString());
                        }
                        break;

                    case "unmark":
                        int a = Integer.parseInt(parts[1]) - 1;
                        records.get(a).incomplete();
                        break;

                    case "mark":
                        int b = Integer.parseInt(parts[1]) - 1;
                        records.get(b).complete();
                        break;

                    case "todo":
                        records.add(new Todo(userinput.substring(5)));
                        index++;
                        System.out.println("Added to-do mission:");
                        System.out.println(records.get(index-1).toString());
                        System.out.println("You have " + index + " missions in the list");
                        break;

                    case "deadline":
                        parts = userinput.split("/");
                        if (parts.length != 2) {
                            throw new DukeException("Please enter valid end date.");
                        }
                        records.add(new Deadline(parts[0], parts[1]));
                        index++;
                        System.out.println("Added deadline mission:");
                        System.out.println(records.get(index-1).toString());
                        System.out.println("You have " + index + " missions in the list");
                        break;

                    case "event":
                        parts = userinput.split("/");
                        if (parts.length != 3) {
                            throw new DukeException("Please enter valid start and end dates.");
                        }
                        records.add(new Event(parts[0], parts[1], parts[2]));
                        index++;
                        System.out.println("Added event mission:");
                        System.out.println(records.get(index-1).toString());
                        System.out.println("You have " + index + " missions in the list");
                        break;

                    case "delete":
                        int c = Integer.parseInt(parts[1]) - 1;
                        String msg = records.get(c).toString();
                        System.out.println("The following mission has been removed:");
                        System.out.println(msg);
                        records.remove(c);
                        index--;
                        break;

                    default:
                        System.out.println("Command not recognised");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Enter a number less than " + (index + 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Enter a number greater than 0");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }
}
