import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
//if user put in wrong format for deadline events, error will occur
//need to handle them using wrong array index exception
enum Query { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE }
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String input = scan.nextLine();
        while(!input.equals("bye")) {
            String[] tokens = input.split(" ",2);
            try {
                Query query = Query.valueOf(tokens[0].toUpperCase());
                switch (query) {
                    case LIST: 
                        list(tasks);
                        break; 
                    case MARK:
                        mark(true, tasks, tokens[1]);
                        break;
                    case UNMARK:
                        mark(false, tasks, tokens[1]);
                        break;
                    case TODO:
                        todo(tasks, tokens[1]);
                        break;
                    case DEADLINE:
                        deadline(tasks, tokens[1]);
                        break;
                    case EVENT:
                        event(tasks, tokens[1]);
                        break;
                    case DELETE:
                        delete(tasks, tokens[1]);
                        break;
                }
            } catch(IllegalArgumentException e) {System.out.println("please make sure your command is valid!");
            } catch(IndexOutOfBoundsException e) {System.out.println("please ensure there are arguments for particular commands!");}
            System.out.println("----------");
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
    }
    private static void list(ArrayList<Task> a) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < a.size(); i++) { System.out.format("%d.%s%n",i+1,a.get(i));}
    }
    private static void mark(boolean mark ,ArrayList<Task> a, String s) {
        try {
            int num = Integer.parseInt(s);
            Task t = a.get(num - 1);
            if (mark) {
                t.mark();
                System.out.println("Nice! I've marked this task as done:");
            }
            else {
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(t.toString());
        } catch (NumberFormatException e) {System.out.println("please only input numbers");
        } catch (IndexOutOfBoundsException e) {System.out.println("make sure the number is in range");}
    }
    private static void todo(ArrayList<Task> a, String s) {
        Todo t = new Todo(s);
        a.add(t);
        System.out.println("Got it I've added a todo!");
        System.out.println(t.toString());
    }
    private static void deadline(ArrayList<Task> a, String s) {
        try {
            String[] tokens = s.split(" /by ");
            String name = tokens[0];
            String by = tokens[1];
            Deadline t = new Deadline(name, parseDate(by));            
            a.add(t);
            System.out.println("Got it I've added a deadline");
            System.out.println(t.toString());
        } catch (IndexOutOfBoundsException e) { System.out.println("please ensure u have a /by option and that /by option argument exist");
        } catch (DateTimeParseException e) {System.out.println("please ensure yyyy-MM-dd format");}
        
    }
    private static void event(ArrayList<Task> a, String s) {
        try {
            String[] tokens = s.split(" /from ");
            String name = tokens[0];
            String tmptoken = tokens[1];
            String[] options = tmptoken.split(" /to ");
            String from = options[0];
            String to = options[1];
            Event t = new Event(name, parseDate(from), parseDate(to));            
            a.add(t);
            System.out.println("Got it I've added an event");
            System.out.println(t.toString());
        } catch (IndexOutOfBoundsException e) { System.out.println("please ensure u have a /from /to (in that order!) option and that their arguments exist");
        } catch (DateTimeParseException e) {System.out.println("please ensure yyyy-MM-dd format");}    
    }
    private static void delete(ArrayList<Task> a, String s) {
        try {
            int num = Integer.parseInt(s);
            Task t = a.remove(num - 1);
            System.out.println("I have removed this task");
            System.out.println(t);
        } catch (NumberFormatException e) {System.out.println("please only input numbers");
        } catch (IndexOutOfBoundsException e) {System.out.println("make sure the number is in range");}
    }
    private static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, format);
    }
}
