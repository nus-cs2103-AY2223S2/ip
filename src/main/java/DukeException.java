/*
 * after reading
 * https://github.com/nus-cs2103-AY2223S2/forum/issues/11#issuecomment-1385051046,
 * I learnt that instead of having each method throw an exception that is
 * directly inherited from the `Exception` class, it would be better for all
 * Duke-related exceptions to inherit from a single DukeException class. This
 * way, in the try-catch block, I would only need to catch a single
 * DukeException, rather than explicitly catching each and every Duke-related 
 * exception.
 */
public class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }
}
