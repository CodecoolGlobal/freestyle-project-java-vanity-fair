package com.codecool.termlib;

public class Terminal {
    /**
     * The beginning of control sequences.
     */
    // HINT: In \033 the '0' means it's an octal number. And 33 in octal equals 0x1B in hexadecimal.
    // Now you have some info to decode that page where the control codes are explained ;)
    private static final String CONTROL_CODE = "\033[";
    /**
     * Command for whole screen clearing.
     * <p>
     * Might be partitioned if needed.
     */
    private static final String CLEAR = "2J";
    /**
     * Command for moving the cursor.
     */
    private static final String MOVE = "H";
    /**
     * Command for printing style settings.
     * <p>
     * Handles foreground color, background color, and any other
     * styles, for example color brightness, or underlines.
     */
    private static final String STYLE = "m";

    /**
     * Reset printing rules in effect to terminal defaults.
     * <p>
     * Reset the color, background color, and any other style
     * (i.e.: underlined, dim, bright) to the terminal defaults.
     */
    public void resetStyle() {
        command(0 + STYLE);
    }

    /**
     * Clear the whole screen.
     * <p>
     * Might reset cursor position.
     */
    public void clearScreen() {
        command(CLEAR);
    }

    /**
     * Move cursor to the given position.
     * <p>
     * Positions are counted from one.  Cursor position 1,1 is at
     * the top left corner of the screen.
     *
     * @param x Column number.
     * @param y Row number.
     */
    public void moveTo(Integer x, Integer y) {
        command(x + ";" + y + MOVE);
    }

    /**
     * Set the foreground printing color.
     * <p>
     * Already printed text is not affected.
     *
     * @param color The color to set.
     */
    public void setColor(Color color) {
        command(30 + color.ordinal() + STYLE);
    }

    /**
     * Set the background printing color.
     * <p>
     * Already printed text is not affected.
     *
     * @param color The background color to set.
     */
    public void setBgColor(Color color) {
        command(40 + color.ordinal() + STYLE);
    }

    /**
     * Apply style attributes to text.
     */
    public void setStyle(Attribute attribute) {
        command(attribute.ordinal() + STYLE);
    }

    /**
     * Move the cursor relatively.
     * <p>
     * Move the cursor amount from its current position in the given
     * direction.
     *
     * @param direction Step the cursor in this direction.
     * @param amount    Step the cursor this many times.
     */
    public void moveCursor(Direction direction, Integer amount) {
        command(amount + String.valueOf((char) ('A' + direction.ordinal())));
    }

    /**
     * Set the character diplayed under the current cursor position.
     * <p>
     * The actual cursor position after calling this method is the
     * same as beforehand.
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     *          position.
     */
    public void setChar(char c) {
        System.out.print(c);
    }

    /**
     * Helper function for sending commands to the terminal.
     * <p>
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandCode The unique part of a command sequence.
     */
    private void command(String commandCode) {
        System.out.print(CONTROL_CODE + (commandCode));
    }


}
