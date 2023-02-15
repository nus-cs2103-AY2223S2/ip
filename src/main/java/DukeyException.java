public class DukeyException extends Exception {

    public DukeyException(String message) {
        super(message);
    }

    public void printMessage() {
        System.out.println(this.getMessage());
        System.out.println("__________________________(-o-)__________________________");
    }



}
