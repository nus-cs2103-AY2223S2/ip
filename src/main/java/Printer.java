public class Printer {
    private String bar = "____________________________________________________________";
    private String indent = "    ";
    public void printBar() {
        System.out.println(this.indent + this.bar);
    }
    public void printText(String text) {
        System.out.println(this.indent + text);
    }
    public void printException(SundayException e) {
        printBar();
        printText(e.getMessage());
        printBar();
    }
}
