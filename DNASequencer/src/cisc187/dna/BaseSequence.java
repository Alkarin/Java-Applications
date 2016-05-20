package cisc187.dna;

import java.util.*;

/**
 * Value class to store a BaseSequence.
 * <p>
 * A BaseSequence is a sequence of letters representing the 4 
 * amino acids found in DNA and RNA.
 * The class also stores the sequence type.
 * Two sequence types exist because the valid letters that can be stored
 * in a sequence of a particular type are not the same.
 * </p>
 * <pre>
 *    A DNA sequence can only contain the letters: ACGT
 *    An RNA sequence can only contain the letters: ACGU
 * </pre>
 * It is valid for a BaseSequence to have no characters, in which case
 * the SequenceType is EMPTY.
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 03-25-2016
 */
public class BaseSequence {

    /**
     * Empty BaseSequence object to be called from Fragmentlist.SequenceAt()
     */
    static  BaseSequence EMPTY = new BaseSequence();

    /**
     * Holds all characters of a BaseSequence's sequence.
     * IE: 'acgtgtaacttg'
     */
    private List<Character> seq;

    /**
     * Holds the value of this BaseSequences enum type.
     * Defining whether this BaseSequence is a strand of DNA, RNA, or EMPTY.
     */
    private SequenceType type;

    /**
     * No args constructor. Used to create an empty BaseSequence in the event of
     * having no args, allowing it to be declared as an RNA or DNA strand later.
     */
    BaseSequence(){
        type = SequenceType.EMPTY;
    }

    /**
     * BaseSequence() with args creates a BaseSequence with its specified Sequence type
     * and contents of said Sequence.
     * <p>Uses a specified Arraylist to initialise the Sequence</p>
     * @param sequence  initial sequence information to create
     * @param type      initial type information to create
     */
    BaseSequence(List<Character> sequence, SequenceType type){
        this.type = type;
        seq = sequence;
    }

    /**
     * BaseSequence() with args creates a BaseSequence with its specified Sequence type and
     * contents of said Sequence.
     *
     * <p>Uses a specified String to initialise the Sequence</p>
     *
     * @param str   initial sequence information to create
     * @param type  initial type information to create
     */
    BaseSequence(String str, SequenceType type){
        this.type = type;
        seq = new ArrayList<>();
        for(char c : str.toCharArray()) {
            seq.add(c);
        }
    }

    /**
     * Returns the current sequence as a String
     * @return  String version of current sequence
     */
    String asString(){
        StringBuilder sb = new StringBuilder();
        for (char c : seq){
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Removes all characters from the specified sequence
     * and initialises the SequenceType as EMPTY
     */
    void clear(){
        type = SequenceType.EMPTY;
        seq = new ArrayList<>();
    }

    /**
     * Replaces the current sequence with a clipped version of the sequence.
     * The clipped sequence is specified to be the section of the sequence from start to end.
     *
     * @param start initial position to start clipping sequence
     * @param end   ending position of sequence clipping
     * @return  returns a new sequence of the clipped contents or an empty BaseSequence if args are invalid
     */
    BaseSequence clip(int start, int end){
        List<Character> se = this.getSequence();

        if(start < 0){
            System.out.print("Unable to clip fragment starting at -1.\nStart must be between 0 and 6.\n");
            return new BaseSequence();
        } else if (start > end){
            System.out.print("Unable to clip fragment starting at 7.\nStart must be between 0 and 6.\n");
            this.seq = new ArrayList<>();
            return new BaseSequence();
        } else if (end > 6){
            System.out.print("Unable to clip fragment ending at 7.\nEnd must be less than or equal to 6.\n");
            return new BaseSequence();
        } else {
            for( int i = end; i >= start; i--){
                se.remove(i);
            }
        }
        return new BaseSequence(se, this.type);
    }

    /**
     * Creates a copy of this DNA sequence
     * @return  A deep copy of the specified type and sequence
     */
    BaseSequence copy(){
        return new BaseSequence(this.asString(),this.getType());
    }

    /**
     * Get the current sequence
     * @return  returns the current DNA sequence
     */
    List<Character> getSequence(){
        return seq;
    }

    /**
     * Get the current Sequence type
     * @return  returns the current Sequence type
     */
    SequenceType getType(){
        return  type;
    }

    /**
     * Gets the current sequence as a String
     * @return  returns the sequence type and sequence in a single String
     */
    @Override
    public String toString(){
        String t;
        String s = asString();
        String c;
        if(type == SequenceType.DNA){
            t = "DNA";
            if(s.toUpperCase().matches("[ACGT]+")){
                c = concatenate(s,t);
            } else if(s.equals("")){
                c = concatenate(s,t);
            } else {
                return "One or more invalid characters in sequence.\n";
            }
        } else if (type == SequenceType.RNA){
            t = "RNA";
            if(s.toUpperCase().matches("[ACGU]+")){
                c = concatenate(s,t);
            } else if(s.length() <= 0) {
                s = "";
                c = concatenate(s,t);
            } else {
                return "One or more invalid characters in sequence.\n";
            }
        } else {
            s = "";
            t = "";
            c = concatenate(s,t);
        }
        return c;
    }

    /**
     * Converts a DNA sequence into an RNA sequence and converts its sequence accordingly
     * <li>Changes sequence type to RNA</li>
     * <li>Coverts 'T' to 'U'</li>
     * <li>Complements 'ACGT' to 'UGCA' accordingly</li>
     * <li>Reverses sequence</li>
     */
    void transcribe(){
        String prevSeq = asString();
        String newSeq;
        String revSeq;
        String finSeq;

        if(this.type.equals(SequenceType.DNA)){

            this.type = SequenceType.RNA;

            prevSeq.toUpperCase();
            newSeq = prevSeq.replace("T","U");
            StringBuilder sb = new StringBuilder(newSeq).reverse();
            revSeq = sb.toString();
            finSeq = revSeq.replace("G","c").replace("C","g").replace("A","u").replace("U","a").toUpperCase();
            this.seq = new ArrayList<>();
            for(char c : finSeq.toCharArray()){
                this.seq.add(c);
            }
        }else if (this.type == SequenceType.RNA){
            System.out.print("Can only transcribe DNA sequences.\n");
        }
    }

    /**
     * Takes the current SequenceType and Sequence and concatenates them into a single String output
     * @param s The current Sequence to be printed
     * @param t The current SequenceType to be printed
     * @return  returns a single String containing both the Sequence and SequenceType
     */
    private String concatenate(String s, String t){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(t);
        if(!"".equals(t)){
            sb.append(": ");
        }
        sb.append(s);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks if a specified BaseSequence object has the same values of Sequence and SequenceTypes as another
     * specified BaseSequence
     * @param obj The BaseSequence to be compared
     * @return returns a boolean value of true if both BaseSequences have equal values
     */
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }else if(!BaseSequence.class.isAssignableFrom(obj.getClass())){
            return false;
        }
        final BaseSequence other = (BaseSequence) obj;

        if((this.seq == null) ? (other.getSequence() != null) : !this.seq.equals(other.getSequence())){
            return false;
        }else if(this.type != other.getType()){
            return false;
        } else {
            return true;
        }
    }
}
