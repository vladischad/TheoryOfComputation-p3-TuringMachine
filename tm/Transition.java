package tm;

/**
 * This class serves as an easy way to keep transition
 * information together such as 'L' and 'R' movement
 * data as well as the next state.
 * 
 * @author  Vladyslav (Vlad) Maliutin
 *          Reggie Wade
 */

public class Transition {
    // store move and next state data
    char move;
    int writeSymb;
    TMState state;

    public Transition (char move, int writeSymb, TMState state) {
        this.move = move;
        this.writeSymb = writeSymb;
        this.state = state;
    }
}