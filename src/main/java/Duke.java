import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Task[] lst=new Task[110];
    static int len=0;
    public static void main(String[] args) {
        String line="____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm T-Rex. Roarrrrrrrrrrrrrr!");
        System.out.println("What do you need from me?");
        System.out.println(line);
        System.out.println();

        Scanner sc=new Scanner(System.in);
        String cmd=sc.nextLine();
        while(!cmd.equals("bye")){
            if(cmd.equals("list")){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrr! Task list shown below!");
                for (int i = 1; i <= len; ++i) {
                    System.out.println(i + "." + lst[i].toString());
                }
                System.out.println(line);
            }
            if(cmd.length()>5&&cmd.substring(0,4).equals("mark")){
                int i=Integer.parseInt(cmd.substring(5));
                lst[i].mark();
                System.out.println(line);
                System.out.println("Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!");
                System.out.println("  "+lst[i].toString());
                System.out.println(line);
            }
            if(cmd.length()>7&&cmd.substring(0,6).equals("unmark")){
                int i = Integer.parseInt(cmd.substring(7));
                lst[i].unmark();
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrr! You said you did not finish that? Fine! Unmarked!");
                System.out.println("  "+lst[i].toString());
                System.out.println(line);
            }
            if(cmd.length()>5&&cmd.substring(0,4).equals("todo")) {
                String task=cmd.substring(5);
                lst[++len] = new Todo(task);
                System.out.println(line);
                System.out.println("New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                System.out.println("  "+lst[len].toString());
                System.out.println("You save "+len+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                System.out.println(line);
            }
            if(cmd.length()>9&&cmd.substring(0,8).equals("deadline")){
                String task=cmd.substring(9);
                int pos=task.indexOf("/by");
                String time=task.substring(pos+4);
                task=task.substring(0,pos-1);
                lst[++len] = new Deadline(task,time);
                System.out.println(line);
                System.out.println("New Deadline task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                System.out.println("  "+lst[len].toString());
                System.out.println("You save "+len+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                System.out.println(line);
            }
            if(cmd.length()>6&&cmd.substring(0,5).equals("event")){
                String task=cmd.substring(6);
                int pos1=task.indexOf("/from");
                String time1=task.substring(pos1+6);
                int pos2=time1.indexOf("/to");
                String time2=time1.substring(pos2+4);
                time1=time1.substring(0,pos2-1);
                task=task.substring(0,pos1-1);
                lst[++len] = new Event(task,time1,time2);
                System.out.println(line);
                System.out.println("New Event task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                System.out.println("  "+lst[len].toString());
                System.out.println("You save "+len+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                System.out.println(line);
            }

            System.out.println();
            cmd=sc.nextLine();
        }

        System.out.println(line);
        System.out.println("See you! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!");
        System.out.println(line);
        System.out.println();
    }
}
