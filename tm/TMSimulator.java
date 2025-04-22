package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Turing Machine Simulator: Thsis program simulates a bi-inffnite TM.
 * It reads a TM description from a file and executes the TM on an input tape.
 *  
 * Input file format:
 * - First line: number of states (n), state 0 is the start and state n-1 is halting.
 * - Second line: number of symbols in input alphabet Σ (tape alphabet is {0} ∪ Σ).
 * - Next (n-1)×(|Σ|+1) lines: transitions in the form "next state,write symbol,move".
 * - Final line: input string (optional).
 * 
 * Output:
 * - Printed stirng representing the tape after halting
 * 
 * @author  Vladyslav (Vlad) Maliutin
 *          Reggie Wade
 */
public class TMSimulator {
    public static void main(String[] args) throws Exception {

        if (args.length != 1) 
        {
            System.err.println("usage: java TMSimulator <input_file>");
            System.exit(1);
        }

        String path = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        List<String> lines = new ArrayList<>();

        // Read all lines until the end of file
        while ((line = reader.readLine()) != null) {
            if (!line.trim().equals("")) {
                lines.add(line.trim());
            }
        }
        reader.close();
        
        // TM initilization
        TM tm = new TM();

        // Parse number of states and sigma length (number of non-blank input symbols)
        int numStates = Integer.parseInt(lines.get(0));
        int sigmaLen = Integer.parseInt(lines.get(1)) + 1;    // add 1 for extra 0

        // add states
        for (int i = 0; i < numStates; i++) {
            tm.addState(i);
        }

        // add sigma
        for (int i = 0; i < sigmaLen; i++) {
            tm.addSigma(i);
        }

        Integer currState = 0;
        int counter = 0;

        List<String> slicedList = lines.subList(2, lines.size());
        while ((currState + 1) != numStates) {
            for (int i = counter; i < sigmaLen + counter; i++) {
                int onSymb = i - counter;
                String[] parts = slicedList.get(i).split(",");
                tm.addTransition(currState, Integer.valueOf(parts[0]), onSymb, Integer.valueOf(parts[1]), parts[2].charAt(0));
            }
            currState++;
            counter += sigmaLen;
        }

        // check if starting tape
        String startingTape = null;
        if (!(lines.get(lines.size() - 1).contains(","))) {
            startingTape = lines.get(lines.size() - 1);
        }

        // set start and final state
        tm.setStart();
        tm.setFinal();
        // initialize TM
        // tm.printTMInfo();
        tm.runTM(startingTape);
        tm.printTape();
    }
}
