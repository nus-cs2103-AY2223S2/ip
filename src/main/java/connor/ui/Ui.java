package connor.ui;

public class Ui {
    public String LINE = "        ________________________________________________________\n";

    public void printMessage(String response) {
        System.out.println(LINE + "        " + response);
    }

    public void greet() {
        printMessage("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "        Please type in your command below.");
    }

    public void greetings(String responseType) {
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
