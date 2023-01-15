public class Response {

    public static String formMessage(String response) {
        return "        ________________________________________________________\n"
                + "        "
                + response
                + "\n"
                + "        _______________________________________________________\n";

    }

    public static void printMessage(String response) {
        System.out.println(formMessage(response));
    }

    public static void greetings(String responseType) {
        switch (responseType) {
            case "HI":
                printMessage("Hi, I hope that you are having a nice day.");
                break;

            case "BYE":
                printMessage("It was a good session Hank, Bye.");
                break;
        }
    }
}
