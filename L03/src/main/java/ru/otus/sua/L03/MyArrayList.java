package ru.otus.sua.L03;

import java.util.*;

//Написать свою реализацию ArrayList на основе массива.
// class MyArrayList<E> implements List<E>{...}
//
//Проверить, что на ней работают методы
// addAll(Collection<? super T> c, T... elements)
// static <E> void copy(List<? super T> dest, List<? extends T> src)
// static <E> void sort(List<E> list, Comparator<? super T> c)
// из java.util.Collections

public class MyArrayList<E> implements List<E> {

    private Object[] myEl;
    private int mySize;

    private final int INIT_SIZE = 11;

    public MyArrayList() {
        clear();
    }

    public MyArrayList(int size) {
        myEl = new Object[size];
        mySize = 0;
    }

    public MyArrayList(Collection<? extends E> c) {
        myEl = c.toArray();
        mySize = myEl.length;
    }

    // ==============================================================================

    @Override
    public int size() {
        return mySize;
    }

    @Override
    public boolean isEmpty() {
        return myEl.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyArrayListIterator(index);
    }


    @Override
    public Object[] toArray() {
        Object[] a = new Object[mySize];
        System.arraycopy(myEl, 0, a, 0, mySize);
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < mySize) return (T[]) this.toArray();
        System.arraycopy(myEl, 0, a, 0, mySize);
        // If the list fits in the specified array with room to spare (i.e., the array has more elements than the list),
        // the element in the array immediately following the end of the list is set to null.
        // (This is useful in determining the length of the list only if the caller knows that the list does not
        // contain any null elements.)
        // __FROM__ https://docs.oracle.com/javase/10/docs/api/java/util/List.html#toArray(T%5B%5D)
        if (a.length > mySize) a[mySize] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        add(mySize, e);
        return true;
        // Returns: true if this collection changed as a result of the call
        // __FROM__ https://docs.oracle.com/javase/10/docs/api/java/util/Collection.html#add(E)
    }

    @Override
    public void add(int index, E e) {
        checkIndexForAdd(index);
        growResizingCheck();
        movingRight(index);
        myEl[index] = e;
        mySize++;
    }

    private void growResizingCheck() {
        if (mySize == myEl.length) {
            Object[] a = new Object[mySize + INIT_SIZE * 2]; // slow growing
            System.arraycopy(myEl, 0, a, 0, mySize);
            myEl = a;
        }
    }

    private void movingRight(int index) {
        for (int i = mySize; i > index; i--) {
            myEl[i] = myEl[i - 1];
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = (E) myEl[index];
        movingLeft(index);
        mySize--;
        return e;
    }

    private void movingLeft(int index) {
        for (int i = index; i < mySize - 1; i++) {
            myEl[i] = myEl[i + 1];
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        Object ro = remove(index);
        if (o.equals(ro)) return true;
        else return false;
    }

    @Override
    public void clear() {
        myEl = new Object[INIT_SIZE];
        mySize = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < mySize; i++) {
            if ((o == null && get(i) == null) || (o != null && get(i).equals(o)))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = mySize - 1; i >= 0; i--) {
            if ((o == null && get(i) == null) || (o != null && get(i).equals(o)))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) myEl[index];
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E re = (E) myEl[index];
        myEl[index] = e;
        return re;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < mySize; i++) {
            sb.append(", ");
            if (myEl[i] != null) {
                sb.append(((E) myEl[i]).toString());
            } else {
                sb.append("null");
            }
        }
        sb.delete(1, 3);
        sb.append("]");
        return sb.toString();
    }

    // -------------------------------- ALLs -------------------------------------

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        for (E e : c) {  // dumb inserting
            add(index, e);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = false;
        for (Object o : c) {
            int index = indexOf(o);
            if (index != -1) {
                ret = true;
                remove(index);
            }
        }
        return ret;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (indexOf(o) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean ret = false;
        int i = 0;
        do {
            i = 0;
            for (; i < mySize; i++) {
                Object o = myEl[i];
//                System.out.print(o.toString());
                if (!c.contains(o)) {
                    ret = true;
                    remove(o);
//                    System.out.print("r");
                    break;
                }
//                System.out.print(" ");
            }
//            System.out.println(" i="+i+" l="+mySize);
        } while (i < mySize);
        return ret;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex - 1);
        // Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
        // (If fromIndex and toIndex are equal, the returned list is empty.)
        List<E> le = new MyArrayList<>();
        if (fromIndex == toIndex) return le;
        for (int i = fromIndex; i < toIndex; i++) {
            le.add((E) myEl[i]);
        }
        return le;
    }

    // ================ IMPLEMENTED ABOVE THIS ========================

    // void 	trimToSize()
    // void 	forEach​(Consumer<? super E> action)
    // void 	ensureCapacity​(int minCapacity)
    // boolean 	removeIf​(Predicate<? super E> filter)
    // protected void 	removeRange​(int fromIndex, int toIndex)
    // Spliterator<E> 	spliterator()
    // Object 	clone()
    // static <E> List<E> 	of().................

    // ==================================== TOOLS  ==========================================
    private void checkIndex(int index) {
        if (index < 0 || index >= mySize) throw new IndexOutOfBoundsException();
    }

    private void checkIndexForAdd(int index) {
        if (index == 0) return;
        if (index < 0 || index > mySize) throw new IndexOutOfBoundsException();
    }

    // ==================================== ITERATOR ========================================
    private class MyArrayListIterator implements ListIterator {

        private int index = 0;
        private int lastIndex = -1;

        public MyArrayListIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastIndex = index;
            Object o = MyArrayList.this.get(index);
            index++;
            return o;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            index--;
            lastIndex = index;
            Object o = MyArrayList.this.get(index);
            return o;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1; //TODO what about <0 ?
        }

        @Override
        public void remove() {
            if (lastIndex < 0) throw new IllegalStateException();
            MyArrayList.this.remove(lastIndex);
            if (lastIndex < index) index--;
            lastIndex--;
        }

        @Override
        public void set(Object o) {
            if (lastIndex < 0) throw new IllegalStateException();
            MyArrayList.this.set(lastIndex, (E) o);
        }

        @Override
        public void add(Object o) {
            MyArrayList.this.add(index, (E) o);
            index++;
            lastIndex = -1;
        }
    }


}
