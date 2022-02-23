public class LinkedBagTest{
    public static void main(String[] args){
        BagInterface<String> bag1= new LinkedBag<>();
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("d");
        bag1.add("e");
        BagInterface<String> bag2= new LinkedBag<>();
        bag2.add("c");
        bag2.add("g");
        bag2.add("f");
        bag2.add("a");
 
        System.out.println("Union of Bag1 and Bag2:");
        BagInterface<String> result = bag1.union(bag2);
       Object[] arr = result.toArray();
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
 
        System.out.print("\n");
        System.out.println("Intersection of Bag1 and Bag2:");
        BagInterface<String> result2 = bag1.intersect(bag2);
        arr = result2.toArray();
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
 
        System.out.print("\n");
        System.out.println("Difference of Bag1 and Bag2:");
        BagInterface<String> result3 = bag1.difference(bag2);
        arr = result3.toArray();
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}
