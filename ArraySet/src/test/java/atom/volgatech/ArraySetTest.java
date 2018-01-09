package atom.volgatech;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArraySetTest {
    List<Integer> arrayList = Arrays.asList(5, 4, 2, 2, 3, 1);
    List<Integer> shouldBeArrayList = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> shouldBeReversedArrayList = Arrays.asList(5, 4, 3, 2, 1);
    ArraySet<Integer> arraySet = new ArraySet<Integer>(arrayList);

    @Test
    void constructorBadArg() {
        try
        {
            ArraySet<Integer> arraySetTest = new ArraySet<Integer>(null);
            fail("Should throw an exception if argument is null");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void lowerMinObject() {
        assertEquals(null, arraySet.lower(1));
    }
    @Test
    void lowerNotExcistObject() {
        assertEquals(null, arraySet.lower(-5));
    }
    @Test
    void lowerObject() {
        assertEquals((int) 4, (int) arraySet.lower(5));
    }

    @Test
    void floorMinObject() {
        assertEquals((int) 1, (int) arraySet.floor(1));
    }

    @Test
    void floorNotExcistObject() {
        assertEquals(null, arraySet.floor(-5));
    }

    @Test
    void floorObject() {
        assertEquals((int) 4, (int) arraySet.floor(5));
    }

    @Test
    void ceilingMaxObject() {
        assertEquals((int) 5, (int) arraySet.ceiling(5));
    }

    @Test
    void ceilingNotExcistObject() {
    assertEquals(null, arraySet.ceiling(-5));
    }

    @Test
    void ceilingObject() {
        assertEquals((int) 5, (int) arraySet.ceiling(4));
    }

    @Test
    void higherMaxObject() {
        assertEquals(null,  arraySet.higher(5));
    }

    @Test
    void higherNotExcistObject() {
        assertEquals(null,  arraySet.higher(-5));
    }

    @Test
    void higherObject() {
        assertEquals((int) 5, (int) arraySet.higher(4));
    }

    @Test
    void pollFirst() {
        try{
            arraySet.pollFirst();
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void pollLast() {
        try{
            arraySet.pollLast();
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void size() {
        assertEquals(5, arraySet.size());
    }

    @Test
    void isEmpty() {
        assertFalse(arraySet.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(arraySet.contains(-5));
        assertTrue(arraySet.contains(1));
    }

    @Test
    void iteratorExist() {
        assertNotNull(arraySet.iterator());
    }

    @Test
    void descendingSet() {
        ArraySet<Integer> testArraySet = new ArraySet<Integer>(shouldBeReversedArrayList , false);
        ArraySet<Integer> testDescendingArraySet = arraySet.descendingSet();

        assertEquals(testArraySet.toList(), testDescendingArraySet.toList());
    }

    @Test
    void descendingIterator() {
        assertNotNull(arraySet.descendingIterator());
    }

    @Test
    void subSetIncludeBoth() {
        ArraySet<Integer> subset = arraySet.subSet(1, true, 2, true);
        List<Integer> shouldBe = Arrays.asList(1, 2);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void subSetIncludeFirst() {
        ArraySet<Integer> subset = arraySet.subSet(1, false, 2, true);
        List<Integer> shouldBe = Arrays.asList(2);
        assertEquals(shouldBe, subset.toList());
    }
    @Test
    void subSetIncludeLast() {
        ArraySet<Integer> subset = arraySet.subSet(1, true, 2, false);
        List<Integer> shouldBe = Arrays.asList(1);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void subSetNonExistElement() {
        try
        {
            ArraySet<Integer> subset = arraySet.subSet(0, true, 2, false);
            fail("should be exception");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    void subSetLastElementInclude() {
        ArraySet<Integer> subset = arraySet.subSet(1, true, 5, true);
        assertEquals(shouldBeArrayList, subset.toList());
    }

    @Test
    void headSetInclude() {
        ArraySet<Integer> subset = arraySet.headSet(2, true);
        List<Integer> shouldBe = Arrays.asList(1, 2);
        assertEquals(shouldBe, subset.toList());
    }
    @Test
    void headSetNotInclude() {
        ArraySet<Integer> subset = arraySet.headSet(2, false);
        List<Integer> shouldBe = Arrays.asList(1);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void tailSetInclude() {
        ArraySet<Integer> subset = arraySet.tailSet(2, true);
        List<Integer> shouldBe = Arrays.asList(2, 3, 4, 5);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void tailSetNotInclude() {
        ArraySet<Integer> subset = arraySet.tailSet(2, false);
        List<Integer> shouldBe = Arrays.asList(3, 4, 5);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void comparator() {
        assertNull(arraySet.comparator());
    }

    @Test
    void subSet1IncludeFirstExcludeSecond() {
        ArraySet<Integer> subset = arraySet.subSet(2, 3);
        List<Integer> shouldBe = Arrays.asList(2);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void subSet1Equal() {
        ArraySet<Integer> subset = arraySet.subSet(2, 2);
        List<Integer> shouldBe = new ArrayList<Integer>();
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void headSet1() {
        ArraySet<Integer> subset = arraySet.headSet(3);
        List<Integer> shouldBe = Arrays.asList(1, 2);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void headSet1Exception() {
        try
        {
            ArraySet<Integer> subset = arraySet.headSet(6);
            fail("should be exception");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void tailSet1() {
        ArraySet<Integer> subset = arraySet.tailSet(3);
        List<Integer> shouldBe = Arrays.asList(3, 4, 5);
        assertEquals(shouldBe, subset.toList());
    }

    @Test
    void toArray() {
        assertNotNull(arraySet.toArray());
    }

    @Test
    void toArray1() {
        assertNotNull(arraySet.toArray(shouldBeArrayList.toArray()));
    }

    @Test
    void add() {
        try{
            arraySet.add(2);
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void remove() {
        try{
            arraySet.remove(2);
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void containsAll() {
        assertTrue(arraySet.containsAll(shouldBeArrayList));
    }

    @Test
    void retainAll() {
        try{
            arraySet.retainAll(shouldBeArrayList);
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void addAll() {
        try{
            arraySet.addAll(shouldBeArrayList);
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void removeAll() {
        try{
            arraySet.removeAll(shouldBeArrayList);
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Test
    void Spliterator() {
        try{
            arraySet.spliterator();
            assert false;
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }


    @Test
    void clear() {
        try {
            arraySet.clear();
        }
        catch (Exception e)
        {
            fail("Ther shouldnt be an exception");
        }
    }

    @Test
    void first() {
        assertEquals(1, (int) arraySet.first());
    }

    @Test
    void last() {
        assertEquals(5, (int) arraySet.last());
    }
}