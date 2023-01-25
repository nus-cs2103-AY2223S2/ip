public class UserInterface {
    private static String logo;
    private static String welcome;

    UserInterface(String logo, String welcome) {
        this.logo = logo;
        this.welcome = welcome;
    }

    public String showLogo() {
        return this.logo;
    }
    public String showWelcome() {
        return this.welcome;
    }
}
