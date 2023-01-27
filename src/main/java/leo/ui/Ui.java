package leo.ui;

import leo.storage.Task;

public class Ui {

    public void greetings() {
        String helloLogo = "__   __   ______   __       __        _____ \n" +
                "| |  | |  | ____|  | |      | |      /  __  \\ \n" +
                "| |__| |  | |____  | |      | |      | |  | |\n" +
                "|  __  |  | ____|  | |      | |      | |  | |\n" +
                "| |  | |  | |____  | |____  | |____  | |__| |\n" +
                "|_|  |_|  |______| |______| |______| \\_____/\n";
        displayMessage(helloLogo);
        String greetingMessage = "Good day, I am Leo!";
        String followUp = "How can I assist you today?";
        displayMessage(leoResponse(greetingMessage));
        displayMessage(notFirstLine(followUp));
    }

    public static void displayMessage(String str) {
        System.out.println(str);
    }

    public static String leoResponse(String str) {
        String response = "Leo: ";
        return response + str;
    }

    public static String notFirstLine(String str) {
        String spaces = "     ";
        return spaces + str;
    }

    public static String type(Task t) {
        switch (t.getType()) {
            case TODO:
                return "[T]";
            case DEADLINE:
                return "[D]";
            case EVENT:
                return "[E]";
        }
        return null;
    }

    public static String completion(Task t) {
        return (t.isDone() ? "[X] " : "[ ] ");
    }

}
