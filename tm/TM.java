package tm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a Turing Machine (TM) with states and transitions.
 * It supports adding states, transitions, and setting start and final states.
 * It uses integer-based tape symbols and state names.
 *
 * @author  Vladyslav (Vlad) Maliutin
 *          Reggie Wade
 */
public class TM {

    private Set<Integer> sigma;
    private TMState startState;
    private HashMap<Integer, TMState> states;
    private TMState finalState;
    private Tape tape;

    public TM () {
        sigma  = new LinkedHashSet<>();
        startState = null;
        states = new LinkedHashMap<>();
        finalState = null;
        tape = new Tape();
    }

    /**
     * Adds a symbol to the tape alphabet.
     *
     * @param a symbol to add
     */
    public void addSigma (Integer a) {
        sigma.add(a);
    }

    /**
     * Gets the start state.
     *
     * @return the start TMState
     */
    public TMState getStart () {
        return startState;
    }

    /**
     * Gets the start state.
     *
     * @return the final TMState
     */
    public TMState getFinal () {
        return finalState;
    }

    /**
     * Sets the start state to the state with the lowest key value.
     */
    public void setStart () {
        /*
         * set start state to state at lowest key val
         */
        Set<Integer> keys = states.keySet();
        Integer min = Collections.min(keys);
        startState = states.get(min);
    }

    /**
     * Sets the final state to the state with the highest key value.
     */
    public void setFinal () {
        Set<Integer> keys = states.keySet();
        Integer max = Collections.max(keys);
        finalState = states.get(max);
    }

    /**
     * Adds a new state to the TM.
     *
     * @param name the name (ID) of the state
     * @return true if the state was added, false if it already exists
     */
    public boolean addState(Integer name) {
        if (states.containsKey(name)) return false;
        states.put(name, new TMState(name));
        return true;
    }

    /**
     * Adds a transition from one state to another on a given symbol.
     *
     * @param fromState the current state
     * @param toState the next state
     * @param onSymb the symbol read on the tape
     * @param tranSymb the symbol to transition to next state
     * @param move the direction to move ('L' or 'R')
     * @return true if the transition was successfully added
     */
    public boolean addTransition(Integer fromState, Integer toState, Integer onSymb, Integer writeSymb, char move) {
        if (!states.containsKey(fromState) || !sigma.contains(onSymb)) return false;
        TMState from = states.get(fromState);
        if (!states.containsKey(toState)){
            return false;
        }
        from.addTransition(onSymb, new Transition(move, writeSymb, states.get(toState)));
        
        return true;
    }
    
    /**
     * Gets the next state based on the current state and the symbol read on the tape.
     *
     * @param currentState the current state
     * @param onSymb the symbol read on the tape
     * @return the next TMState, or null if no valid transition exists
     */
    public Transition getNextState(Integer currentState, Integer onSymb) {
        if (!states.containsKey(currentState)) return null;
        TMState state = states.get(currentState);
        return state.toState(onSymb);
    }

    /**
     * Checks if a given state is the final state.
     *
     * @param name the name (ID) of the state
     * @return true if it is the final state
     */
    public boolean isFinal(Integer name) {
        TMState state = states.get(name);
        return state != null && state.equals(finalState);
    }

    /**
     * Runs the Turing Machine with the given input tape.
     * Executes transitions until the final state is reached.
     *
     * @param startingTape the input string on the tape; null or empty means blank tape
     */
    public void runTM (String startingTape) 
    {
        tape.initTape(startingTape);
        int currState = startState.getName();

        while (!isFinal(currState)) 
        {
            int symb = tape.read();
            Transition info = getNextState(currState, symb);

            if (info == null) 
            {
                System.err.println("Error: No transition defined for state " + currState + " on symbol " + symb);
                return;
            }

            tape.write(info.writeSymb);
            switch (info.move) 
            {
                case 'R':
                    tape.moveRight();
                    break;
                case 'L':
                    tape.moveLeft();
                    break;
                default:
                    System.err.println("Error: Invalid move direction '" + info.move + "'");
                    return;
            }

            currState = info.state.getName();
        }
    }

    /**
     * Prints the contents of the visited tape cells from leftmost to rightmost.
     * The output is a single string followed by a newline.
     */
    public void printTape() {
        HashMap<Integer, Integer> finalTape = tape.getTape();
        // get keys
        List<Integer> keys = new ArrayList<>(finalTape.keySet());
        Collections.sort(keys);
        // iterate through keys and print
        for (int i = 0; i < keys.size(); i++) {
            System.out.print(finalTape.get(keys.get(i)));
        }
        System.out.println();
    }

    /**
     * Prints all useful information about the Turing Machine.
     */
    public void printTMInfo() {
        System.out.println("Turing Machine Information:");
        
        // Print tape alphabet
        System.out.println("Tape Alphabet (Sigma): " + sigma);
        
        // Print all states
        System.out.println("States:");
        for (Integer stateId : states.keySet()) {
            System.out.println("  State ID: " + stateId);
        }
        
        // Print start state
        if (startState != null) {
            System.out.println("Start State: " + startState.getName());
        } else {
            System.out.println("Start State: Not set");
        }
        
        // Print final state
        if (finalState != null) {
            System.out.println("Final State: " + finalState.getName());
        } else {
            System.out.println("Final State: Not set");
        }
        
        // Print transitions
        System.out.println("Transitions:");
        for (Integer stateId : states.keySet()) {
            TMState state = states.get(stateId);
            System.out.println("  From State " + stateId + ":");
            for (Integer symbol : state.getTransitions().keySet()) {
                Transition transition = state.getTransitions().get(symbol);
                System.out.println("    On Symbol " + symbol + " -> Write " + transition.writeSymb +
                                   ", Move " + transition.move + ", To State " + transition.state.getName());
            }
        }
    }

}
