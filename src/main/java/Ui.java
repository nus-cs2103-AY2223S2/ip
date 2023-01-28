public class Ui {
    private static String logo;
    private static String welcome;
    private static String divider;

    Ui() {
        this.logo = "GPTGPTGPT";
        this.welcome = "Hello! I'm GPT0.01!\nWhat can I do for you?";
        this.divider = "\n____________________________________________________________\n";

    }

    public void showLogo() {
        System.out.println(this.logo);
    }
    public void showWelcome() {
        display(this.welcome);
    };

    public void display(String str) {
        System.out.println(this.divider + str + this.divider);
    }
}
