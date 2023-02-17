package meggy.exception;

public interface Consumer<I> {
    void accept(I in) throws MeggyException;
}
