import java.util.Scanner;
import java.util.ArrayList;

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
    public static String getType(String query) {
        if(query.length() >= 4 && query.substring(0,4).equals("todo")) return "todo";
        if(query.length() >= 8 && query.substring(0,8).equals("deadline")) return "deadline";
        if(query.length() >= 5 && query.substring(0,5).equals("event")) return "event";
        return "wrong";
    }
    public static String[] getInputs(String query) {
        String type = Task.getType(query);
        if (type.equals("todo")) return new String[] {query.substring(5)};
        if (type.equals("deadline")) return query.substring(9).split(" /by ");
        if (type.equals("event")) return query.substring(6).split(" /from | /to ");
        String[] emptyArray = {};
        return emptyArray;
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
            
            else {
                            
                String type = Task.getType(input);
                String[] params = Task.getInputs(input);
                for (String el: params) {
                    System.out.println(el);
                }
                if (type.equals("todo")) {
                    tasks.add(new Todo(params[0]));
                    System.out.printf("added: %s%n", params[0]);
                } 
                else if (type.equals("deadline")) {
                    tasks.add(new Deadline(params[0],params[1]));
                    System.out.printf("added: %s%n", params[0]);
                }
                else if (type.equals("event")) {
                    tasks.add(new Event(params[0],params[1], params[2]));
                    System.out.printf("added: %s%n", params[0]);
                }
                else {
                    System.out.println("wrong out put");
                }
            }
            System.out.println("_____");        
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
    }
}
