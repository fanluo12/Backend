package week1;

import java.util.*;

public class Day2 {
}
/**
 * 1. immutable class
 *      String
 *      all the wrapper class: Integer, Long, Character...
 *      Threadsafe
 */

class Test5 {
    public static void main(String[] args) {
        Integer i1 = 1;
        i1 = 2;

        String s = "abc";
        s = s + "d";

        StringBuilder sb = new StringBuilder("abc");
        sb.append("d");

    }
}
//customer immutable class
// 1. class must be final
final class MyList{
    final List<Integer> internalList;
    MyList(List<Integer> input) {
        // deep copy input
        internalList = new LinkedList<>(input);
    }
    List<Integer> getInternalList() {
        // deep copy
        return new LinkedList<>(internalList);
    }

    public static void main(String[] args) {
        List<Integer> input = new LinkedList<>();
        MyList myList = new MyList(input);
    }
}

/**
 * 2. Exception
 *            Throwable
 *      /                 \
 *      Error            Exceptions
 *     stackoverflow       1. checked (compile time)
 *                                Class.forName
 *                         2. unchecked (detect run time)
 *
 *     Exception handling
 *     1. throws
 *     2. try/ catch/ finally
 */

class TestEx{
    public static void main(String[] args) throws Exception{
//        try {
//            Class.forName("week1.Day3");
//        } catch (ClassNotFoundException ex) {
//            System.out.println(ex);
//        } finally {
//            // execute no matter exceptions happened or not
//        }
//        System.out.println("here");
        try {
            System.out.println(1 / 0);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
/**
 * final/finally/finalize
 */
class Test{
    @Override
    protected void finalize() {

    }
}

/**
 * 3. Array
 *      fixed size
 *      contiguous memory allocation -- O(1) access time
 *      primitive type and object type array
 *
 */
class Test6 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        System.out.println(Arrays.toString(arr)); // default value
        Integer[] arr2 = new Integer[10];
        System.out.println((Arrays.toString(arr2))); // null
    }
}

/** 4. List
 *     ArrayList (array)
 *          contiguous memory allocation
 *          get: O(1)
 *          auto resize O(n)
 *     LinkedList (node)
 *          memory allocation is random
 *          node <-> node </-> node
 *          get element except head and tail: O(n)
 *          addFirst(), addLast(): O(1)
 *          add(index, ele) O(n) traverse to target pos, O(1) modify the pointer
 *
 */

/** 5. HashMap
 *      hashcode()
 *      equals()
 *      Node[]
 *      key value
 *      key located where
 *      key.hashcode()
 *      key.hashcode()%16 (0-15)
 *      Node[1]
 *      what if 17%16 also assigned to Node[1] ?
 *      this will cause hash collision
 *      get(k1) k1.equals(key)
 *      [Node(key, value, 1][] []
 *         \
 *          [Node(17)]
 *          get(key)
 *
 *      put: putVal(hash(key), key, value)
 *      use hashcode to find the bucket index
 *      then check whether there is collision
 *      if not we can assign a new node
 *      else go inside bucket to see if there is existed key
 *      equals() to see if the key is existed
 *      If it is a tree, just use tree method
 *      otherwise it is a linked list, traverse the linked list to see whether key is existed
 *      if surpass the threshold, resize the node array
 *
 *
 */
class Test8{
    public static void main(String[] args) {
        Person p1 = new Person("x", 10);
        Person p2 = new Person("x", 10);
        System.out.println(p1.equals(p2));

        HashMap<Person, Integer> map = new HashMap<>();
        map.put(p1, 10);
        p1.age = 20;
        /**
         * It returns null, because you have changed the number of num1 object,
         * thus changing it hashcode. Hash map uses hashcode() to store and retrieve objects,
         * so when you change it, you then look for an object on a wrong place,
         * since it is stored on place numbered 2500, but then you look it on place 100.
         * That's because your hashcode returns the number.
         */
        System.out.println(map.get(p1));
    }
}
class Person{
    int age;
    String name;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}

/**
 * Set
 *      HashSet
 *      HashMap(key, dummy)
 */
