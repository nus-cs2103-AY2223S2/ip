import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Joe joe = new Joe();
        Scanner sc = new Scanner(System.in);

        String logo = "  ╔╗        \n" +
                "  ║║        \n" +
                "  ║║╔══╗╔══╗\n" +
                "╔╗║║║╔╗║║╔╗║\n" +
                "║╚╝║║╚╝║║║═╣\n" +
                "╚══╝╚══╝╚══╝\n" +
                "            \n";
        logo = "     |             \n" +
            "     |  _ \\    _ \\ \n" +
            " \\   | (   |   __/ \n" +
            "\\___/ \\___/  \\___|\n";



                String greeting = "\tHello! I'm Joe\n" +
                "\tWhat Can I do for you?";

        System.out.println("Hello from\n" + logo);
        Joe.printNewLine();
        System.out.println(greeting);
        Joe.printNewLine();

        while(true) {
            joe.getResponse(sc);
        }
    }

}
