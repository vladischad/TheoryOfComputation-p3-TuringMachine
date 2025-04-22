package tm;

import java.util.HashMap;

/**
 * This class represents the tape for a bi-infinite Turing Machine.
 * It allows reading, writing, and moving the tape head left or right.
 * The tape is implemented as a HashMap that stores characters at integer positions.
 * The tape head starts at position 0.
 *
 * @author  Vladyslav (Vlad) Maliutin
 *          Reggie Wade
 */
public class Tape {
    private HashMap<Integer, Integer> tape = new HashMap<>();
    private int head = 0;
    
    /**
     * Constructs a new Tape with the head initialized at position 0.
     */
    public Tape () {
        this.head = 0;
    }

    public HashMap<Integer, Integer> getTape () {
        return tape;
    }


    public void initTape (String startingTape) {
        // if no starting string, initialize the tape to 0
        if (startingTape == null) {
            tape.put(head, 0);
            return;
        }
        for (int i = 0; i < startingTape.length(); i++) {
            // write to head
            tape.put(head, Character.getNumericValue(startingTape.charAt(i)));
            // move head 'R'
            moveRight();
        }
        // move head back to start
        for (int i = 0; i < startingTape.length(); i++) {
            moveLeft();
        }
        return;
    }

    /**
     * Reads the character under the tape head.
     *
     * @return the character at the current head position, 0 if blank
     */
    public int read() {
        return tape.getOrDefault(head, 0);
    }

    /**
     * Writes a character at the current head position.
     *
     * @param symbol the character to write to the tape
     */
    public void write(int symbol) {
        tape.put(head, symbol);
    }

    /**
     * Moves the tape head one position to the left.
     */
    public void moveLeft() {
        head--;
    }

    /**
     * Moves the tape head one position to the right.
     */
    public void moveRight() {
        head++;
    }

    /**
     * Gets the current head position on the tape.
     *
     * @return the current head index
     */
    public int getHeadPosition() {
        return head;
    }

    
}
