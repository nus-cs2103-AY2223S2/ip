public class Greetings {

    public Greetings() {
        greetings();
    }

    private void greetings() {
        String helloLogo = "__   __   ______   __       __        _____ \n" +
                "| |  | |  | ____|  | |      | |      /  __  \\ \n" +
                "| |__| |  | |____  | |      | |      | |  | |\n" +
                "|  __  |  | ____|  | |      | |      | |  | |\n" +
                "| |  | |  | |____  | |____  | |____  | |__| |\n" +
                "|_|  |_|  |______| |______| |______| \\_____/\n";
        System.out.println(helloLogo);
        String greetingMessage = "Leo: Good day, I am Leo!\n     How can I assist you today?";
        System.out.println(greetingMessage);
    }
}
