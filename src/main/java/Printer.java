public class Printer {
    private final String bar = "____________________________________________________________";
    private final String indent = "    ";
    public void printBar() {
        System.out.println(this.indent + this.bar);
    }
    public void printText(String text) {
        System.out.println(this.indent + text);
    }
}
