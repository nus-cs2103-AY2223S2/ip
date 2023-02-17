package kude.tui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import kude.processor.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void empty() {
        var parser = new Parser("");
        assertEquals("", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
    }

    @Test
    public void one() {
        var parser = new Parser("list");
        assertEquals("list", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());

        parser = new Parser("list ");
        assertEquals("list", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
    }

    @Test
    public void namedArgs() {
        var parser = new Parser("cmd arg /arg0 v /arg1 v2");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v2"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_noArg() {
        var parser = new Parser("cmd /arg0 v /arg1 v2");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
        assertEquals(Optional.of("v"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v2"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_incomplete() {
        var parser = new Parser("cmd arg /arg0 v /arg1");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v /arg1"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_incompleteWithSpace() {
        var parser = new Parser("cmd arg /arg0 v /arg1 ");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v /arg1"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_withSlashValues() {
        var parser = new Parser("cmd arg /arg0 1/2/3");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_withSlashValues_space() {
        var parser = new Parser("cmd arg /arg0 1/2/3 ");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgs_withSlashValues_anotherArg() {
        var parser = new Parser("cmd arg /arg0 1/2/3 /arg1 v");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }
}
