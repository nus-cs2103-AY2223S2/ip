import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String greeting = formatStr("Hello! I'm Muse\n"
                + "What can I do for you?");
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<String>();


        String input = sc.nextLine();
        while (!input.equals("bye")) {
            tasks.add(input);
            System.out.println(formatStr(listThings(tasks)));
            input = sc.nextLine();
        }
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

    public static String formatStr(String str) {
        String returnstr =  "############################\n"
                            + str + "\n"
                            + "############################";
        return returnstr;
    }

    public static String addReport(String str) {
        return "added: " + str;
    }

    public static String listThings(List<String> arrList) {
        String returnstr = "";
        for (int i = 0; i < arrList.size(); i++) {
            returnstr += "added: " + arrList.get(i) + "\n";
        } return returnstr;
    }
}

