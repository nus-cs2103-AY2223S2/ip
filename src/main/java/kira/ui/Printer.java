package kira.ui;

public class Printer {
    
    private static void printFormatString(String raw) {
        StringBuilder response = 
                new StringBuilder("============= KiraBot ============= \n")
                .append(raw)
                .append("=================================== \n");
        System.out.println(response.toString());
    }

    protected static void printFormatErrorMsg(String msg) {
        StringBuilder response =
                new StringBuilder("There seems to be a problem...\n")
                .append(msg)
                .append("\n");
        printFormatString(response.toString());
    }

    protected static void printFormatReplyMsg(String msg) {
        printFormatString(msg + "\n");
    }
}
