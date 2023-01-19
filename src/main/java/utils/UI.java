package utils;

public class UI {
    public static void reply(String s) {
        if (s.equals("")) {
            return;
        }
        String linebreak = "    ___________________________________________________________________________________";
        System.out.println(linebreak);
        System.out.println(FormatHelper.indent(4, s));
        System.out.println(linebreak);
    }

    public static void greet() {
        reply("Hello! I'm Duke\n What can I do for you?");
    }

    public static void signOff() {
        reply("Bye. Hope to see you again!");
    }
}
