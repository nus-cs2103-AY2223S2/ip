import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String[] lst=new String[110];
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
            if(!cmd.equals("list")){
                lst[++len]=cmd;
                System.out.println(line);
                System.out.println("added: "+cmd);
                System.out.println(line);
            }else{
                System.out.println(line);
                for(int i=1;i<=len;++i){
                    System.out.println(i+". "+lst[i]);
                }
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
