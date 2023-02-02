package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EitherTest {
    @Test
    public void testLeft() {
        var left = Either.left("test");
        var right = Either.right("oops");
        assertEquals(true, left.isLeft());
        assertEquals(false, right.isLeft());
    }
    
    @Test
    public void testRight() {
        var left = Either.left(69);
        var right = Either.right("error");
        assertEquals(false, left.isRight());
        assertEquals(true, right.isRight());
    }

    @Test
    public void mapLeft() {
        var left = Either.<Integer, String>left(69);
        assertEquals(Either.left(70).toString(), left.map(x -> x + 1).toString());
    }

    @Test
    public void mapRight() {
        var right = Either.<Integer, String>right("Random error");
        assertEquals(Either.right("Random error").toString(), right.map(x -> x + 1).toString());
    }

    @Test 
    public void flatMapLeftSuccess() {
        var actual = Either.left(20).flatMap(x -> Either.left(x + 20));
        var expected = Either.left(40);
        assertEquals(expected.toString(), actual.toString());
    }
}
