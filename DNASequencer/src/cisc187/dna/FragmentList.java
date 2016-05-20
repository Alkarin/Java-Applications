package cisc187.dna;

import cisc187.util.Printable;

import java.util.*;


/**
 * Contains an array of DNA/RNA {@link BaseSequence} character fragments.  
 * A DNA "fragment" is a sequence of either DNA characters,
 * RNA characters, or an empty sequence.
 * {@link Sequencer} commands that manipulate DNA sequences 
 * refer to methods manipulating sequences in this fragment list. 
 * <p>
 * Fragment capacity is fixed at construction time.
 * Fragment entries with no BaseSequence characters within them will contain
 * an {@link SequenceType#EMPTY EMPTY} indicator.
 *
 * TODO: commands defined in the program could be an enum rather than naked strings.
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 03-25-2016
 */
public class FragmentList implements Printable,Editable  {

    /**
     * The number of sequences this fragment stores if no size is specified
     */
    static final int DEFAULT_SIZE = 16;

    /**
     * The number of sequences this fragment stores when specified
     */
    private int size;

    /**
     * The BaseSequences stored in the FragmentList
     */
    private BaseSequence[] frags = new BaseSequence[DEFAULT_SIZE];

    /**
     * Creates a new DNA fragment with a default capacity
     */
    FragmentList(){
        this.size = DEFAULT_SIZE;
    }

    /**
     * Creates a new DNA fragment with a specified capacity
     * @param size  The number of DNA fragments stored in this FragmentList
     */
    FragmentList(int size){
        this.size = size;
        frags = new BaseSequence[this.size];
    }

    /**
     * Replaces the current sequence at the specified fragment position with a version of what was clipped from it
     * @param pos   the position within the fragment list
     * @param start the position to start clipping
     * @return      returns the sequence clipped from the original fragmentlist sequence
     */
    public BaseSequence clip(int pos, int start){
        if(isPositionValid(pos, "clip")){
            List<Character> listseq = frags[pos].getSequence();
            List<Character> removed = new ArrayList<Character>();
            if(isPositionValid(pos, "clip")){
                if(start > listseq.size()-1){
                    int x = start-1;
                    System.out.println("Unable to clip fragment starting at " + start + ".");
                    System.out.println("Start must be between 0 and " + x + ".");
                } else if(start <= frags.length){
                    for(int i = listseq.size()-1; i >= start; i--){
                        char c = listseq.get(i);
                        removed.add(c);
                        listseq.remove(i);
                    }
                }
                Collections.reverse(removed);
                return new BaseSequence(removed, frags[pos].getType());
            } else {
                return new BaseSequence();//
            }
        } else {
            return new BaseSequence();//
        }
    }


    /**
     * Replaces the current sequence at the specified fragmnet position with a version of what was clipped from it
     * @param pos   the position within the fragment list
     * @param start the position to start clipping
     * @param end   the position to stop clipping
     * @return      returns the sequence clipped from the original fragment list sequence
     */
    public BaseSequence clip(int pos, int start, int end){
        if(isPositionValid(pos, "clip")){
            if(frags[pos].getSequence() == null){
                System.out.println("No Sequence found at position " + pos + "!");
            } else {
                List<Character> listseq = frags[pos].getSequence();
                List<Character> removed = new ArrayList<Character>();

                    try{
                        if(start < 1){
                            Exception e = new IllegalArgumentException();
                            throw e;
                        } else if (start > frags.length || end > frags.length){
                            Exception e = new IllegalArgumentException();
                            throw e;
                        }
                    } catch(Exception e){
                        System.out.println("Start (" + start +") may be less than 1! " +e);
                        System.out.println("Start (" + start +"), or End(" + end + ") may be larger than frags.length. " + e);
                    }
                    if(end < start){
                        removed.addAll(listseq);
                        listseq.removeAll(listseq);

                        return new BaseSequence(removed, frags[pos].getType());
                    }
                    for( int i = end; i >= start; i--){

                        char c = listseq.get(i);
                        removed.add(c);
                        listseq.remove(i);
                    }
                    Collections.reverse(removed);
                    return new BaseSequence(removed, frags[pos].getType());

            }
        }

        return  new BaseSequence();
    }

    /**
     * Get current size of this instance. Can not be set any time but at construction
     * @return  The size of this Fragment list
     */
    int getSize(){
        return this.size;
    }

    /**
     * Inserts a sequence to a specified position in the Fragment list. The SequenceType must be DNA or RNA and ensures
     * it only contains appropriate letters for its type. Otherwise it will display an error. If there is currently a
     * sequence in the specified position it is overwritten by the new sequence.
     * @param pos       The position within the fragment list
     * @param type      The sequence type of RNA or DNA to be added
     * @param sequence  The sequence being inserted
     */
     public void insert(int pos, SequenceType type, String sequence){

         if(isPositionValid(pos, "insert")){
             if(type == SequenceType.DNA) {
                 if(!sequence.toUpperCase().matches("[ACGT]+")){
                     System.out.println("One or more invalid characters in sequence.");
                 } else {
                     frags[pos] = new BaseSequence(sequence,type);
                 }

             }else if(type == SequenceType.RNA){
                 if(!sequence.toUpperCase().matches("[ACGU]+")){
                     System.out.println("One or more invalid characters in sequence.");
                 } else {
                     frags[pos] = new BaseSequence(sequence,type);
                 }
             } else if (type == SequenceType.EMPTY){
                 frags[pos] = new BaseSequence(sequence,type);
             }
         }
    }

    /**
     * Validates the position in the fragment list
     * @param pos       The position being evaluated
     * @param command   The type of command being executed
     * @return          Returns a boolean value of whether or not the position is valid.
     */
    private boolean isPositionValid(int pos, String command){
        int x = size-1;
        if(pos < 0 || pos >= frags.length || pos > x){
            if(command.equals("clip")){
                System.out.println("Unable to " + command + "fragment starting at position " +pos +".");
                System.out.println("Start must be between 0 and " + x +".");
                return false;
            } else {
                System.out.println("Unable to " + command + " fragment at position " + pos + ".");
                System.out.println("Position must be between 0 and " + x +".");
                return false;
            }
        }
        return true;
    }

    /**
     * Prints out all sequences in the fragment list and their position and SequenceType
     */
    public void print(){
        System.out.println(toString().toUpperCase());
    }

    /**
     * Prints out a specified sequence and SequenceType from the fragment list.
     * @param pos   The position in the fragment to obtain the sequence from
     */
    public void print(final int pos){
        if(isPositionValid(pos, "print")){
            if (frags[pos] == null){
                System.out.println("[]");
            } else {
                System.out.println(frags[pos].toString().toUpperCase());
            }
        }
    }

    /**
     * Removes the sequence at the specified position in the fragment list
     * @param pos   The specified position in the fragment list
     */
    public void remove(int pos){
        //Really wish I was using Lists right about now
        if(isPositionValid(pos, "remove")){
            ArrayList<BaseSequence> bs = new ArrayList<>(Arrays.asList(frags));
            bs.set(pos,null);
            frags = new BaseSequence[bs.size()];
            bs.toArray(frags);
        }
    }

    /**
     * Returns the sequence from a specified position in the fragment list
     * @param pos   The specified position in the fragment list
     * @return      Returns the specified sequence. If the position is not valid then it returns BaseSequence.EMPTY
     */
     BaseSequence sequenceAt(int pos){
        if(isPositionValid(pos, "get")){
            return frags[pos];
        } else {
            return BaseSequence.EMPTY;
        }
    }

    /**
     * Swaps the ends of two sequences(pos1,pos2) with one another.
     * @param pos1      First sequence position within fragment list
     * @param start1    Starting position within pos1
     * @param pos2      Second sequence position within fragment list
     * @param start2    Starting position within pos2
     */
    public void swap(int pos1, int start1, int pos2, int start2){
        if(isPositionValid(pos1, "swap") && isPositionValid(pos2, "swap")){
            if(frags[pos1] == null){
                System.out.println("No Sequence found at position " + pos1 + "!");
            } else if (frags[pos2] == null){
                System.out.println("No Sequence found at position " + pos2 + "!");
            } else if (frags[pos1].getType() != frags[pos2].getType()){
                System.out.println("Sequences: " + pos1 +" and " + pos2 + ", have differing SequenceTypes!");
            } else {
                List<Character> seq1 = (frags[pos1].getSequence());
                List<Character> seq2 = (frags[pos2].getSequence());

                for(int i = start1, j = start2; i < seq1.size() && j < seq2.size(); i++, j++){
                    seq1.set(i, seq2.set(j, seq1.get(i)));
                }
            }
        }
    }

    /**
     * Creates a copy of the the sequence from pos1 to pos2. If there is no sequence at pos1, prints an error.
     * @param pos1  The position to be copied from
     * @param pos2  The position to copy to
     */
    public void copy (int pos1, int pos2){
        if(isPositionValid(pos1,"copy") && isPositionValid(pos2,"copy")){
            if(frags[pos1] == null){
                System.out.println("No Sequence found at position " + pos1 + "!");
            } else {
                List<Character> temp = new ArrayList<Character>(frags[pos1].getSequence());
                frags[pos2] = new BaseSequence(temp,frags[pos1].getType());
            }
        }
    }

    /**
     * Prints out all sequences in the fragment list along with their SequenceType.
     * @return  returns a string of all sequences and their SequenceTypes
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            if(frags[i] == null){
                sb.append("[]");
            } else {
                sb.append(frags[i]);
            }

        }
        return sb.toString();
    }

    /**
     * Transcribes the sequence at position pos in the fragment list, converting it from a DNA to RNA sequence,
     * replacing the DNA characters with RNA appropriate ones and reversing the sequence.
     * @param pos   The position in the fragment list to be transcribed
     */
    public void transcribe(int pos){
        if(isPositionValid(pos, "transcribe")){
            if(frags[pos] == null){
                System.out.println("No Sequence found at position " + pos + "!");
            }else if(frags[pos].getType() == SequenceType.DNA){
                String a = frags[pos].asString().toUpperCase();
                String b;
                String c;
                String d;
                b = a.replace("T","U");
                StringBuilder sb = new StringBuilder(b).reverse();
                c = sb.toString();
                d = c.replace("G","c").replace("C","g").replace("A","u").replace("U","a").toUpperCase();
                frags[pos] = new BaseSequence(d,SequenceType.RNA);
            } else {
                System.out.println("Can only transcribe DNA sequences.");
            }
        }
    }
}
