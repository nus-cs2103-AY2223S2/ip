import java.util.Scanner;
import java.util.ArrayList;
//if user put in wrong format for deadline events, error will occur
//need to handle them using wrong array index exception
class Task {
    boolean done;
    String name;
    public Task(String n) {
        name = n;
    }
    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
        this.done = true;
        System.out.println(this.toString());
    }
    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.done = false;
        System.out.println(this.toString());
    }
    public String toString(){
        return String.format("[%s] %s",this.done ? "X" : " ", this.name);
    }
    public static String[] parseCreate(String queryString) {
        String[] tokens = queryString.split(" ", 2);
        if (tokens.length != 2) return new String[] {"Error", "creating task requires a command and name minimally"};
        String type = tokens[0];
        if (type.equals("todo")) return tokens;
        //parse params
        String[] params = tokens[1].split(" /");
        if (type.equals("deadline") && 
            params.length == 2 && 
            params[1].substring(0,2).equals("by")) {
            
            String[] tmp = params[1].split(" ", 2);
            if (tmp.length != 2) return new String[] {"Error", "by cannot be empty"};
            return new String[] {type, params[0], tmp[1]};
        }
        if (type.equals("event") && 
            params.length == 3 && 
            params[1].substring(0,4).equals("from") && 
            params[2].substring(0,2).equals("to")) {
    
            String[] tmp1 = params[1].split(" ", 2);
            if (tmp1.length != 2) return new String[] {"Error", "from cannot be empty"};
            String[] tmp2 = params[2].split(" ", 2);
            if (tmp2.length != 2) return new String[] {"Error", "to cannot be empty"};
            return new String[] {type, params[0], tmp1[1], tmp2[1]};
        }
        return new String[] {"Error", "ensure proper format please"};
    }
}
class Todo extends Task {
    public Todo(String n) {
        super(n);
    }
    public String toString(){
        return String.format("[T][%s] %s",this.done ? "X" : " ", this.name);
    }
}
class Deadline extends Task {
    String by;
    public Deadline(String n, String b) {
        super(n);
        by = b;
    }
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",this.done ? "X" : " ", this.name, this.by);
    }
}
class Event extends Task {
    String by;
    String from;
    String to;
    public Event(String n, String f, String t) {
        super(n);
        from = f;
        to = t;
    }
    public String toString(){
        return String.format("[E][%s] %s (from: %s to %s)",this.done ? "X" : " ", this.name, this.from, this.to);
    }
}
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("_____");        
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i=0;i<tasks.size();i++) System.out.printf("%d. %s%n",i+1,tasks.get(i).toString());
            }
            
            else if (input.length() >= 5 && input.substring(0, 5).equals("mark ")) {
                try{
                    int index = Integer.parseInt(input.substring(5));
                    if (index > tasks.size() || index <= 0) System.out.println("wrong index");
                    else {
                        tasks.get(index-1).mark();
                    }
                }
                catch (NumberFormatException ex){
                    System.out.println("please mark corresponding task number");
                }
            }
            
            else if (input.length() >= 7 && input.substring(0, 7).equals("unmark ")) {
                try{
                    int index = Integer.parseInt(input.substring(7));
                    if (index > tasks.size() || index <= 0) System.out.println("wrong index");
                    else {
                        tasks.get(index-1).unmark();
                    }
                }
                catch (NumberFormatException ex){
                    System.out.println("please mark corresponding task number");
                }
            }

            else if (input.length() >= 7 && input.substring(0, 7).equals("delete ")) {
                try{
                    int index = Integer.parseInt(input.substring(7));
                    if (index > tasks.size() || index <= 0) System.out.println("wrong index");
                    else {
                        System.out.printf("Noted, task removed: %n%s%n",tasks.get(index-1).toString());
                        tasks.remove(index-1);
                    }
                }
                catch (NumberFormatException ex){
                    System.out.println("please mark corresponding task number");
                }
                
            }
            
            else {
                String[] parsed = Task.parseCreate(input);             
                String type = parsed[0];
                if (type == "Error") System.out.println(parsed[1]);
                if (type.equals("todo")) {
                    tasks.add(new Todo(parsed[1]));
                    System.out.printf("added: %s%n", parsed[1]);
                } 
                else if (type.equals("deadline")) {
                    tasks.add(new Deadline(parsed[1],parsed[2]));
                    System.out.printf("added: %s%n", parsed[1]);
                }
                else if (type.equals("event")) {
                    tasks.add(new Event(parsed[1],parsed[2], parsed[3]));
                    System.out.printf("added: %s%n", parsed[1]);
                }
            }
            System.out.println("_____");        
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
    }
}
