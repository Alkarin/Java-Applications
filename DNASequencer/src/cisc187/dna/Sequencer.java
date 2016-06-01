package cisc187.dna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Main DNA Sequencer / Splicer program.
 *<p>
 * A key element in many bioinformatics problems is the biological sequence. 
 * A biological sequence is just a list of characters chosen from some alphabet. 
 * Two of the common biological sequences are DNA (composed of the four characters 
 * A, C, G, and T) and RNA (composed of the four characters 
 * A, C, G, and U). 
 * </p>
 * This program implements some basic functionality for manipulating 
 * DNA and RNA sequence fragments.
 * <p>
 * Not that all methods are static and this class cannot be instantiated.
 * </p>
 *
 * @author Alexander Vaughan(5448832)
 * @version 1.0
 * @since 03-25-2016
 */
public final class Sequencer {

    /**
     * Fragment list created by the program
     */
    private static FragmentList frags;
    /**
     * The max size of the Fragment list
     */
    private static int maxSize;
    /**
     * The max size allowed to be set by user for the Fragment list size
     */
    private static final int MAX_SIZE = 100;

    /**
     * Not an instantiable class
     */
    private Sequencer() {
    }

    /**
     * Validates if the size defined by the user is a valid size. Otherwise prompts user and exits program
     * @param s The size input from the user
     * @return  returns the int size if valid
     */
    private static int getValidSize(String s) {
        int arg = 0;
        try {
            arg = Integer.parseInt(s);
            if (arg <= 0 || arg > MAX_SIZE) {
                usage();
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument " + s + " must be an integer");
            usage();
        }
        return arg;
    }

    /**
     * Processes the command file
     * @param filename  The file received from the command line.
     */
    private static void process(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                processCommand(new Scanner(sc.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("File could not be found, ensure you are using a valid filename.");
            usage();
        }
    }

    /**
     * Processes lines within the specified command file
     * @param line  A line of text from this file that may or may not contain valid commands
     */
    private static void processCommand(final Scanner line) {
        while (line.hasNext()) {
            String cmd = line.next().toLowerCase();
            switch (cmd) {
                case "clip":
                    System.out.println("Executing clip...");
                    parseClip(line);
                    break;
                case "copy":
                    System.out.println("Executing copy...");
                    parseCopy(line);
                    break;
                case "insert":
                    System.out.println("Executing insert...");
                    parseInsert(line);
                    break;
                case "print":
                    System.out.println("Executing print...");
                    parsePrint(line);
                    break;
                case "remove":
                    System.out.println("Executing remove...");
                    parseRemove(line);
                    break;
                case "swap":
                    System.out.println("Executing swap...");
                    parseSwap(line);
                    break;
                case "transcribe":
                    System.out.println("Executing transcribe...");
                    parseTranscribe(line);
                    break;
                default:
                    System.out.println("Erroring in process command, Unrecognized input of '" + cmd + "'.");
                    break;
            }
        }
    }

    /**
     * Show information on proper usage of the main method of this class. Exits program.
     */
    private static void usage() {
        System.out.println("Error running sequencer. Invalid command line arguments!");
        System.out.println("usage:");
        System.out.println("\tjava Sequencer <size> <filename>");
        System.out.println("\twhere");
        System.out.println("\tsize = maximum number of fragments this sequenceer can hold.");
        System.out.println("\t       size must be > 0 and <= " + MAX_SIZE);
        System.out.println("\tfilename = name of the commands file containing valid sequencer commands to process.");
        System.exit(1);
    }

    /**
     * Executes the insert command for this line
     * @param line  The line potentially containing arguments for the insert command
     */
    private static void parseInsert(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        int num = 0;
        try {
            num = Integer.parseInt(arg1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        String arg2 = line.next();
        String arg3 = line.next();
        if (arg3 == null) {
            System.out.println("arg3 is null");
        } else if (arg2 == null) {
            System.out.println("arg2 is null");
        } else if (arg1 == null) {
            System.out.println("arg1 is null");
        }
        if (arg2.toLowerCase().equals("dna")) {
            frags.insert(num, SequenceType.DNA, arg3);
        } else if (arg2.toLowerCase().equals("rna")) {
            frags.insert(num, SequenceType.RNA, arg3);
        }
    }

    /**
     * Executes the print command for this line
     * @param line  The line potentially containing arguments for the print command
     */
    private static void parsePrint(final Scanner line) {
        line.useDelimiter("\\s+");
        if (line.hasNext()) {
            String arg1 = line.next();
            if (arg1 != null) {
                int num = 0;
                try {
                    num = Integer.parseInt(arg1);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                frags.print(num);
            } else {
                frags.print();
            }
        } else {
            frags.print();
        }

    }

    /**
     * Executes the remove command for this line
     * @param line  The line potentially containing arguments for the remove command
     */
    private static void parseRemove(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        int num = 0;
        try {
            num = Integer.parseInt(arg1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        frags.remove(num);
    }

    /**
     * Executes the copy command for this line
     * @param line  The line potentially containing arguments for the copy command
     */
    private static void parseCopy(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        String arg2 = line.next();
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = Integer.parseInt(arg1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            num2 = Integer.parseInt(arg2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        frags.copy(num1, num2);
    }

    /**
     * Executes the transcribe command for this line
     * @param line  The line potentially containing arguments for the transcribe command
     */
    private static void parseTranscribe(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        int num = 0;
        try {
            num = Integer.parseInt(arg1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        frags.transcribe(num);
    }

    /**
     * Executes the clip command for this line
     * @param line  The line potentially containing arguments for the clip command
     */
    private static void parseClip(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        String arg2 = line.next();
        int num1 = parseArg(arg1);
        int num2 = parseArg(arg2);

        if (line.hasNext()) {
            String arg3 = line.next();
            int num3 = parseArg(arg3);
            frags.clip(num1,num2,num3);
        } else {
            frags.clip(num1,num2);
        }
    }

    /**
     * Executes the swap command for this line
     * @param line  The line potentially containing arguments for the swap command
     */
    private static void parseSwap(final Scanner line) {
        line.useDelimiter("\\s+");
        String arg1 = line.next();
        String arg2 = line.next();
        String arg3 = line.next();
        String arg4 = line.next();
        int num1 = parseArg(arg1);
        int num2 = parseArg(arg2);
        int num3 = parseArg(arg3);
        int num4 = parseArg(arg4);

        frags.swap(num1,num2,num3,num4);
    }

    /**
     * Takes a string and attempts to convert it into an int.
     * Throws exception if not possible
     * @param arg   The string to convert
     * @return      Returns the string as an int
     */
    private static int parseArg(String arg) {
        int num = 0;
        String arg1 = arg;
        try {
            num = Integer.parseInt(arg1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * Main DNA fragment program. Reads a text file containing DNA or RNA sequence fragment commands and performs
     * the specified actions
     * @param args  <li>args[0] The maximum number of DNA fragments the sequencer can hold</li>
     *              <li>args[1] The file from which to read sequencer commands from</li>
     */
    public static void main(String[] args) {
        if (args.length == 2){
          maxSize = getValidSize(args[0]);
          frags = new FragmentList(maxSize);
          } else{
          usage();
          }
          process(args[1]);
      }
  }
