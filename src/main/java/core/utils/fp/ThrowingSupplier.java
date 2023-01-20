package utils.fp;
@FunctionalInterface
public interface ThrowingSupplier<R, E extends Exception> {
    R supply() throws E;
}
