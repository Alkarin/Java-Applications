package cisc187.util;


/**
 * Defines actions that can be taken on lists that print their contents.
 *
 * @author Alexander Vaughan
 */
public interface Printable {
  /**
   * Prints out all items.
   */
  void print();

  /**
   * Prints out a specific item.
   * @param pos the target position within the list.
   */
  void print(final int pos);

}
