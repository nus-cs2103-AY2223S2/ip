import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100];
        int listLen = 0;

        String intro = "  ________________________________\n"
                + "  Hello! I'm Mark\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);
        while(true){
            String str= sc.nextLine();

            if(str.equals("bye")) {
                break;
            }

            if(str.equals("list")) {
                System.out.print("  ________________________________\n");
                for(int i = 0; i < listLen ; i++){
                    int index = i + 1;
                    String item = "  " + index + ". " + list[i];
                    System.out.print(item + "\n");
                }
                System.out.print("  ________________________________\n");
                continue;
            }

            list[listLen] = str;
            listLen++;

            String reply = "  ________________________________\n"
                    + "  added: " + str + "\n"
                    + "  ________________________________\n";
            System.out.print(reply);


        }

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}
