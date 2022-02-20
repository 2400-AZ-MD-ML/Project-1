public class ArrayBagTest {
    public static void main(String[] args){
        BagInterface<String> bag1= new ResizableArrayBag<>();
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("c");
        bag1.add("d");
        bag1.add("e");
        BagInterface<String> bag2= new ResizableArrayBag<>();
        bag2.add("c");
        bag2.add("g");
        bag2.add("f");
        BagInterface<String> result = bag1.union(bag2);
        Object[] arr = result.toArray();
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.print("\n");
        BagInterface<String> result2 = bag1.difference(bag2);
        arr = result2.toArray();
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}
