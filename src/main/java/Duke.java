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
            if (input.equals("list")) {
                System.out.println(formatStr(listThings(tasks)));
            } else {
                tasks.add(input);
                System.out.println(formatStr(addReport(input)));
            }
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
        String returnStr = "added: " + str;
        return returnStr;
    }

    public static String listThings(List<String> arrList) {
        String returnstr = "";
        for (int i = 0; i < arrList.size(); i++) {
            if (i == arrList.size() - 1) {
                returnstr += Integer.toString(i+1) + ". " + arrList.get(i);
            } else {
                returnstr += Integer.toString(i+1) + ". " + arrList.get(i) + "\n";
            }
        } return returnstr;
    }
}

