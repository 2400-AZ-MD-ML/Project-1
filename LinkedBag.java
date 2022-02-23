/**
    A class of bags whose entries are stored in a chain of linked nodes.
    The bag is never full.
    INCOMPLETE DEFINITION; includes definitions for the methods add,
    toArray, isEmpty, and getCurrentSize.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public final class LinkedBag<T> implements BagInterface<T>
{
	private Node firstNode;       // Reference to first node
	private int numberOfEntries;
	private static final int MAX_CAPACITY = 10000;
	private boolean integrityOK = false;

	public LinkedBag()
	{
		firstNode = null;
      numberOfEntries = 0;
	  integrityOK = true;
	} // end default constructor

	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True. */
	public boolean add(T newEntry) // OutOfMemoryError possible
	{
      // Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode;  // Make new node reference rest of chain
                                 // (firstNode is null if chain is empty)
      firstNode = newNode;       // New node is at beginning of chain
		numberOfEntries++;
      
		return true;
	} // end add

	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
	public T[] toArray()
	{
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.data;
         index++;
         currentNode = currentNode.next;
      } // end while
      
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
	} // end toArray
   
	/** Sees whether this bag is empty.
       @return  True if the bag is empty, or false if not. */
	public boolean isEmpty()
	{
		return numberOfEntries == 0;
	} // end isEmpty
   
	/** Gets the number of entries currently in this bag.
       @return  The integer number of entries currently in the bag. */
	public int getCurrentSize()
	{
		return numberOfEntries;
	} // end getCurrentSize
   
// STUBS:

	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
   {
      return null; // STUB
   } // end remove
   
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false otherwise. */
   public boolean remove(T anEntry)
   {
      return false; // STUB
   } // end remove
	
	/** Removes all entries from this bag. */
	public void clear()
   {
      // STUB
   } // end clear
	
	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry)
   {
      int frequency = 0; //initalizes the variable that we will return
	  int counter = 0; //create variable that will make sure we do not go over the amount of elements in the bag
	  Node currentNode = firstNode; //create a Node that holds reference to the first node
	  while((counter<numberOfEntries)&& (currentNode != null)){ //loop through the bag and check if it equals to the entry, if it does increment
		  if(anEntry.equals(currentNode.data)){
			  frequency++;
		  }
		  counter++;
		  currentNode = currentNode.next;
	  }
	  return frequency; 
   } // end getFrequencyOf
	
	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false otherwise. */
	public boolean contains(T anEntry)
   {
      boolean found = false;
	  Node currentNode = firstNode;
	  while(!found && (currentNode != null)){
		  if(anEntry.equals(currentNode.data)){
			  found = true;
		  }
		  else{
			  currentNode = currentNode.next;
		  }
	  }
	  return found;
   } // end contains

    // Throws an exception if receiving object is not initialized.
	private void checkintegrity()
	{
	   if (!integrityOK)
		  throw new SecurityException ("ArrayBag object is corrupt.");
	} // end checkintegrity

	/**
	 * Combines all the elements in both bags.
	 * @param bag2 The bag being combined with.
	 * @return a bag with all the elements in bag1 and bag2.
	 */
   public BagInterface<T> union(BagInterface<T> bag2){
	   checkintegrity();
	   if(bag2== null){
		throw new IllegalStateException("Bag2 is null");
	 }
	BagInterface<T> result = new LinkedBag<>(); // creates empty bag
	int index = 0; // creates starting index variable
      Node currentNode = firstNode; //creates variable that will be at the first node in the LinkedBag
      while ((index < numberOfEntries) && (currentNode != null)) // loops through the LinkedBag and adds it to result
      {
		  result.add(currentNode.data);
         index++;
         currentNode = currentNode.next;
      } 
	  T[] arr = bag2.toArray(); //converts BagInterface into an array, so we can traverse it
	  if(arr.length + this.numberOfEntries > MAX_CAPACITY){ // checks if the bag is over the max capacity and throws an exception if it does
		throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
										"allowed maximum of " + MAX_CAPACITY);
	  }
	  for(int i =0; i<arr.length; i++){ //traverses the array and adds it into the result bag
		if(i+numberOfEntries >MAX_CAPACITY){
			throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
			"allowed maximum of " + MAX_CAPACITY);
		}
		  result.add(arr[i]);
	  }

	return result;
 }
	/**  finds difference between first bag and second bag.
	 * @param bag2 is second bag being compared to.
	 * @return will return a bag of items that are only in the first bag than the second bag, or the amount of times in the first bag
	 * subtracted by the amount of times in the second bag.
	 */
 public BagInterface<T> difference(BagInterface<T> bag2){
	 checkintegrity();
	 if(bag2== null){
		throw new IllegalStateException("Bag2 is null");
	 }
	BagInterface<T> result = new LinkedBag<>(); //creates empty bag
	int index = 0; // creates index to traverse the LinkedBag
		Node currentNode = firstNode; //creates variable to look into the first node, and then start traversing the LinkedBag
		int size = 0;
	while((index<numberOfEntries)&& (currentNode != null)){ // checks if the entry is not in result and if the entry occurs more than in the first bag than the second
		if(!result.contains(currentNode.data) && this.getFrequencyOf(currentNode.data)-bag2.getFrequencyOf(currentNode.data)>=1){
			int count = 0;
			while(count < this.getFrequencyOf(currentNode.data)-bag2.getFrequencyOf(currentNode.data)){
				if(size> MAX_CAPACITY){
					throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
										"allowed maximum of " + MAX_CAPACITY);
				}
				result.add(currentNode.data);
				count++;
				size++;
			}
		}
		currentNode = currentNode.next;
		index++;
	}
	return result;
 }

	/**
     * Finds the elements that both bags have in common.
     * @param bag2 the second bag the first bag is comparing to.
     * @return a new bag that contains all the elements that both bags have in common and a similar amount of occurances.
     */
 public BagInterface<T> intersect(BagInterface<T> bag2){
	checkintegrity();
	if(bag2== null){
		throw new IllegalStateException("Bag2 is null");
	 }
	BagInterface<T> result = new ResizableArrayBag<>(3);
	 int size = 0;
	Node currentNode = firstNode;

	for (int i = 0; i < getCurrentSize(); i++) {
		//creating variables which hold the frequency of variable i in each bag
		int bag1Freq = getFrequencyOf(currentNode.data);
		int bag2Freq = bag2.getFrequencyOf(currentNode.data);

		int numIntersect = 0;

		if (bag1Freq > 0 && bag2Freq > 0) {
            if (bag1Freq < bag2Freq) {
               numIntersect = bag1Freq;
            }
            else {
               numIntersect = bag2Freq;
            }
         }
		 //checks conditions to add it into the new bag
		 if(!result.contains(currentNode.data)){
            for (int j = 0; j < numIntersect; j++) {
				if(size> MAX_CAPACITY){
					throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
										"allowed maximum of " + MAX_CAPACITY);
				}
               result.add(currentNode.data);
			   size++;
            }
         }
		currentNode = currentNode.next;
	}

	return result;
 }
	private class Node
	{
	  private T    data; // Entry in bag
	  private Node next; // Link to next node

		private Node(T dataPortion)
		{
			this(dataPortion, null);	
		} // end constructor
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor
	} // end Node
} // end LinkedBag1



