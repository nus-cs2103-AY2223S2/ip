package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EitherTest {
    @Test
    public void testLeft() {
        var left = Either.left("test");
        var right = Either.right("oops");
        assertTrue(left.isLeft());
        assertFalse(right.isLeft());
    }

    @Test
    public void testRight() {
        var left = Either.left(69);
        var right = Either.right("error");
        assertFalse(left.isRight());
        assertTrue(right.isRight());
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

    @Test
    public void flatMapLeftFail() {
        var actual = Either.left(20).flatMap(x -> Either.right("Error"));
        var expected = Either.right("Error");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void flatMapRightSuccess() {
        var actual = Either.right("original error").flatMap(x -> Either.left(20));
        var expected = Either.right("original error");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void flatMapRightFail() {
        var actual = Either.right("original error").flatMap(x -> Either.right("new error"));
        var expected = Either.right("original error");
        assertEquals(expected.toString(), actual.toString());

    }
}
