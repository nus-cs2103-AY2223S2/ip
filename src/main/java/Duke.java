import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String intro = "  ________________________________\n"
                + "  Hello! I'm Mark\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);
        while(true){
            String str= sc.nextLine();

            if(str.equals("bye")){
                break;
            }

            String reply = "  ________________________________\n"
                    + "  " + str + "\n"
                    + "  ________________________________\n";
            System.out.print(reply);
        }

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}
