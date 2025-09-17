package yappy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yappy.command.ExitCommand;
import yappy.command.ListCommand;
import yappy.exception.YappyException;



public class ParserTest {

    private static final String COMMANDS_HELP =
            """
            \t I don't understand. Available commands:
            \t - bye
            \t - list
            \t - find <query1,query2,...>
            \t - mark <index>
            \t - unmark <index>
            \t - delete <index>
            \t - todo <description>
            \t - deadline <description> /by <date>
            \t - event <description> /from <date> /to <date>
            \t - reschedule <index> /from <date> /to <date>
            \t - postpone <index> /by <date>
            \t Note: dates must use the format yyyy-MM-dd HH:mm""";

    @Test
    public void testParse_exitCommand_returnExitCommand() throws YappyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void testParse_listCommand_returnListCommand() throws YappyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void testParseUnknownCommand() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("foobar"));
        assertEquals(COMMANDS_HELP, e.getMessage());
    }
}
