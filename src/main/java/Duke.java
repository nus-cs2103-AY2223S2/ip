import java.util.Scanner;

public class Duke {
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
            System.out.println(line);
            System.out.println(cmd);
            System.out.println(line);
            System.out.println();
            cmd=sc.nextLine();
        }

        System.out.println(line);
        System.out.println("See you! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!");
        System.out.println(line);
        System.out.println();
    }
}
