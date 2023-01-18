public class ClippyException extends Exception {
    String msg;
    public ClippyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ">>> Hold up! Clippy is confused - " + this.msg;
    }
}
