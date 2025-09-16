package yappy.ui;

import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;


import yappy.command.ExitCommand;
import yappy.command.ListCommand;
import yappy.exception.YappyException;
import org.junit.jupiter.api.Test;


public class ParserTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testParse_ExitCommand_returnExitCommand() throws YappyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void testParse_ListCommand_returnListCommand() throws YappyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void testParseUnknownCommand() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("foobar"));
        assertEquals("\t Sorry!! I do not know what that command is.", e.getMessage());
    }
}
