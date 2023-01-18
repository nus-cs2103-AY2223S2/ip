public class TextBorder {
    String text = "null";
    String border = "//  /  /  //  /  /  //  /  /  //  /  /  //  /  /  //  /  /  //  /";

    public TextBorder(String text) {
        this.text = text;
    }

    public TextBorder (String text, String border) {
        this.text = text;
        this.border = border;
    }

    @Override
    public String toString() {
        return border + "\n"
                + text + "\n";
    }

}
