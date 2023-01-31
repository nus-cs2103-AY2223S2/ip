public class Ui {
    public String userPrompt;
    public String systemPrompt;

    public Ui(String userPrompt, String systemPrompt) {
        this.userPrompt = userPrompt;
        this.systemPrompt = systemPrompt;
    }

    public String prettyPrint(String output) {
        return userPrompt + " " + output;
    }

    public String systemPrint(String output) {
        return systemPrompt + " " + output;
    }
}
