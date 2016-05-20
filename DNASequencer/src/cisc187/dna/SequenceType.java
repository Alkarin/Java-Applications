package cisc187.dna;

import java.util.HashMap;
import java.util.Map;

/**
 * Sequence Type indicator.
 * Defines the 3 kinds of sequence types as the character that are valid for each.
 * The valid characters defined are used by the {@link isValid} method.
 * @author Dave Parillo
 * @author Alexander Vaughan (5448832)
 */
public enum SequenceType {

    /**
    * A DNA sequence.
    * The four characters A, C, G, and T are the only possible values in a DNA sequence.
    */
    DNA("ACGT"),
    /**
   * An RNA sequence.
   * The four characters A, C, G, and U are the only possible values in a RNA sequence.
   */
    RNA("ACGU"),
    /**
   * A fragment in which no sequence is defined.
   * No characters are valid entries in an empty sequence.
   */
    EMPTY("");

    /**
     * A map in which to store mappings between SequenceTypes and their common names.
     * Currently, they are pretty redundant, but this gives us the flexibility to
     * add entries such as 'Messenger RNA' later with no impacts to users.
     */
    private static final Map<String, SequenceType> map = new HashMap<>();

    /**
     * Initialization block for enums
     */
    static {
      for(SequenceType type : values()){
        map.put(type.toString(), type);
      }
    }

    /**
     * String to contain valid characters for a given Enum
     */
    private String validChars;

    private SequenceType(){}

    /**
     * Create the enum.
     * @param chars the valid characters for this enum
     */
    SequenceType(String chars) {
        this.validChars = chars;
    }

    /**
     * Creates an Enum
     * @param type  the characters for this enum
     * @return  returns the map of this SequenceType enum
     */
    static SequenceType fromString(String type){
      return map.get(type.toUpperCase());
    }

    /**
     * Verifies if the validChars are indeed valid
     * @param c
     * @return
     */
    boolean isValid(char c){
        if(validChars.isEmpty()){
            return false;
        }
      return validChars.indexOf(Character.toUpperCase(c)) >= 0;
    }
}
