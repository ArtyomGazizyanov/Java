package atom.volgatech;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

public class ArraySet<T extends Comparable<T>> implements NavigableSet<T>{
    ArraySet(List<T> objectList){
        if(objectList == null)
        {
            throw new IllegalArgumentException("List can`t be null");
        }
        _array = objectList.stream().distinct().collect(Collectors.toList());
        Collections.sort(_array);
    }

    ArraySet(List<T> objectList, boolean doSort){
        if(objectList == null)
        {
            throw new IllegalArgumentException("List can`t be null");
        }
        if(doSort) {
            _array = objectList.stream().distinct().collect(Collectors.toList());
            Collections.sort(_array);
        }
        else
        {
            _array = objectList;
        }
    }

    @Override
    public T lower(T objectToFind) {
        Integer index = _array.indexOf(objectToFind);
        if(index == 0 || index == -1)
        {
            return null;
        }
        return _array.get(index - 1);
    }

    @Override
    public T floor(T objectToFind) {

        Integer index = _array.indexOf(objectToFind);
        if(index == -1)
        {
            return null;
        }
        if(index == 0)
        {
            return _array.get(index);
        }
        return _array.get(index - 1);
    }

    @Override
    public T ceiling(T objectToFind) {

        Integer index = _array.indexOf(objectToFind);
        if(index == -1)
        {
            return null;
        }
        if(index == _array.size() - 1)
        {
            return _array.get(index);
        }
        return _array.get(index + 1);
    }

    @Override
    public T higher(T objectToFind) {

        Integer index = _array.indexOf(objectToFind);
        if(index == -1 || index == _array.size() - 1)
        {
            return null;
        }

        return _array.get(index + 1);
    }

    @Override
    public T pollFirst() {
        throw new NotImplementedException();
    }

    @Override
    public T pollLast() {

        throw new NotImplementedException();
    }

    @Override
    public int size() {
        return _array.size();
    }

    @Override
    public boolean isEmpty() {
        return _array.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return _array.contains(o);
    }

    @Override
    public Iterator iterator() {
        return _array.iterator();
    }

    @Override
    public ArraySet<T> descendingSet() {
        List<T> copyArray = _array;
        Collections.reverse(copyArray);
        return new ArraySet<T>(copyArray, false);
    }

    @Override
    public Iterator<T> descendingIterator() {
        final Iterator<T> itr = _array.iterator();
        T lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }
        return itr;
    }

    @Override
    public ArraySet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        Integer beginigIndex = _array.indexOf(fromElement);
        Integer finishIndex = _array.indexOf(toElement);
        if(beginigIndex >= _array.size() || finishIndex >= _array.size()
                || beginigIndex < 0 || finishIndex < 0
                || beginigIndex == _array.size()  && fromInclusive
                || finishIndex == _array.size() && toInclusive) {
            throw new IndexOutOfBoundsException("Indexes out of bounds");
        }

        if(beginigIndex > finishIndex ) {
            throw new IndexOutOfBoundsException("from element must be lower than toElement");
        }
        if(!fromInclusive)
        {
            beginigIndex++;
        }
        if(toInclusive)
        {
            finishIndex++;
        }
       return  new ArraySet<T>( _array.subList(beginigIndex, finishIndex) );
    }

    @Override
    public ArraySet<T> headSet(T toElement, boolean inclusive) {
        Integer finishIndex = _array.indexOf(toElement);
        if(finishIndex >= _array.size()
           || finishIndex < 0
           || (finishIndex == 0 && inclusive)) {
            throw new IndexOutOfBoundsException("Indexes out of bounds");
        }
        if(inclusive)
        {
            finishIndex++;
        }
        return new ArraySet<T>( _array.subList(0, finishIndex) );
    }

    @Override
    public ArraySet<T> tailSet(T fromElement, boolean inclusive) {
        Integer startIndex = _array.indexOf(fromElement);
        if(startIndex >= _array.size()
                || startIndex < 0
                || (startIndex == _array.size() - 1 && inclusive)) {
            throw new IndexOutOfBoundsException("Indexes out of bounds");
        }
        if(!inclusive)
        {
            startIndex++;
        }
        return new ArraySet<T>( _array.subList(startIndex, _array.size()) );
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public ArraySet<T> subSet(T fromElement, T toElement) {
        Integer beginigIndex = _array.indexOf(fromElement);
        Integer finishIndex = _array.indexOf(toElement);

        return new ArraySet<T>( _array.subList(beginigIndex, finishIndex) );
    }

    @Override
    public ArraySet<T> headSet(T toElement) {
        Integer finishIndex = _array.indexOf(toElement);

        return new ArraySet<T>( _array.subList(0, finishIndex) );
    }

    @Override
    public ArraySet<T> tailSet(T fromElement) {
        Integer from = _array.indexOf(fromElement);

        return  new ArraySet<T>( _array.subList(from, _array.size()) );
    }

    @Override
    public Object[] toArray() {
        return _array.toArray();
    }

    public List<T> toList() {
        return _array;
    }
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return _array.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new NotImplementedException();
    }

    @Override
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return _array.containsAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
    }

    @Override
    public T first() {
        if(_array.size() == 0)
        {
            return  null;
        }
        return (T) _array.get(0);
    }

    @Override
    public T last() {
        if(_array.size() == 0)
        {
            return  null;
        }
        return (T) _array.get(_array.size() - 1);
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new NotImplementedException();
    }

    List<T> _array = new ArrayList<>();
}
