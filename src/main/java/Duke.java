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
                    System.out.println(i + ".[" + lst[i].checkStatus() + "] " + lst[i].getCont());
                }
                System.out.println(line);
            }else{
                if(cmd.length()>=6){
                    if(cmd.substring(0,4).equals("mark")){
                        int i=Integer.parseInt(cmd.substring(5));
                        lst[i].mark();
                        System.out.println(line);
                        System.out.println("Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!");
                        System.out.println("  ["+lst[i].checkStatus()+"] "+lst[i].getCont());
                        System.out.println(line);
                    }else {
                        if (cmd.substring(0, 6).equals("unmark")) {
                            int i = Integer.parseInt(cmd.substring(7));
                            lst[i].unmark();
                            System.out.println(line);
                            System.out.println("Roarrrrrrrrrrrrrr! You said you did not finish that? Fine! Unmarked!");
                            System.out.println("  [" + lst[i].checkStatus() + "] " + lst[i].getCont());
                            System.out.println(line);
                        } else {
                            lst[++len]=new Task(cmd);
                            System.out.println(line);
                            System.out.println(cmd+" added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                            System.out.println(line);
                        }
                    }
                }else{
                    lst[++len]=new Task(cmd);
                    System.out.println(line);
                    System.out.println(cmd+" added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                    System.out.println(line);
                }
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
