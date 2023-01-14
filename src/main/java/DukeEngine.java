public class DukeEngine {

    public static String divisionLine = "________________________________________";
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static String greetWord = "It's a pleasure to serve you!";
    public static String byeWord = "Goodbye. Hope you have a nice day!";
    public void greet() {
        // String divisionLine = "________________________________________";
        System.out.println(divisionLine);
        System.out.println("Hello from\n" + logo);
        System.out.println(greetWord);
        System.out.println(divisionLine);
    }

    public void echo(String command) {
        System.out.println(divisionLine);
        System.out.println(command);
        System.out.println(divisionLine);
    }

    public void goodbye() {
        System.out.println(divisionLine);
        System.out.println(byeWord);
        System.out.println(divisionLine);
    }
}
