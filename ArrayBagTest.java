
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayBagTest {

    @Test
    public void unionShouldBeTooBig(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        for(int i =0; i<4500; i++){
            bag1.add("a");
        }
        for(int i =0; i<4500; i++){
            bag2.add("a");
        }
        
       Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.union(bag2);}, "Attempt to create a bag whose capacity exceeds " +
       "allowed maximum of " + 10000);
    }
    
    @Test
    public void unionNullItem(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add(null);
        bag2.add("a");
        Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.union(bag2);}, "Bag1 has a null element");
    }
    @Test
    public void unionNullBag(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = null;
        bag1.add("a");
        Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.union(bag2);}, "Bag2 is null");
    }
    @Test
    public void intersectNullItem(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add(null);
        bag2.add("a");
        Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.union(bag2);}, "Bag1 has a null element");
    }
    @Test
    public void differenceNullBag(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = null;
        bag1.add("a");
        Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.difference(bag2);}, "Bag2 is null");
    }
    @Test
    public void intersectNullBag(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = null;
        bag1.add("a");
        Assertions.assertThrows(IllegalStateException.class, ()-> {BagInterface<String> union = bag1.intersect(bag2);}, "Bag2 is null");
    }
    @Test
    public void testUnion(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add("a");
        bag2.add("b");
        BagInterface<String> bag3 = bag1.union(bag2);
        Object[] arr = bag3.toArray();
        String[] b = {"a","b"};
        assertArrayEquals(b, arr);
    }
    @Test
    public void testIntersect(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add("a");
        bag2.add("b");
        BagInterface<String> bag3 = bag1.intersect(bag2);
        Object[] arr = bag3.toArray();
        assertArrayEquals(new String[0], arr);
    }
    @Test
    public void testDifference(){
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add("a");
        bag1.add("c");
        bag2.add("b");
        BagInterface<String> bag3 = bag1.difference(bag2);
        Object[] arr = bag3.toArray();
        String[] arr2 = {"a","c"};
        assertArrayEquals(arr2, arr);
    }
}
