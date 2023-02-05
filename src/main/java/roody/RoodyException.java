package roody;
public class RoodyException extends Exception {
    public RoodyException(String s) {
        super("Oh no :( " + s);
    }
}
