package implementations;

import interfaces.Deque;
import interfaces.LinkedList;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;

    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = this.elements.length / 2;
        this.tail = this.head;
    }

    @Override
    public void add(E element) {
        ensureCapacity();
        this.elements[head--] = element;
        this.elements[tail++] = element;
        size++;
    }

    @Override
    public void offer(E element) {
        add(element);
    }

    @Override
    public void addFirst(E element) {
        add(element);
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public void push(E element) {
        add(element);
    }

    @Override
    public void insert(int index, E element) {
        checkIndex(index);
        ensureCapacity();
        int zeroElementIndex = this.elements.length / 2;
        int leftMostElementIndex = zeroElementIndex - size + 1;
        System.arraycopy(this.elements, leftMostElementIndex, this.elements, leftMostElementIndex - 1, size - index);
        int rightStartElementIndex = zeroElementIndex + index;
        System.arraycopy(this.elements, rightStartElementIndex, this.elements, rightStartElementIndex + 1, size - index);
        this.elements[zeroElementIndex - index] = element;
        this.elements[zeroElementIndex + index] = element;
        head--;
        tail++;
        size++;
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        int zeroElementIndex = this.elements.length / 2;
        this.elements[zeroElementIndex - index] = element;
        this.elements[zeroElementIndex + index] = element;
    }

    @Override
    public E peek() {
        return get(size == 0 ? null : elements[this.head]);
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E get(Object object) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(Object object) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void trimToSize() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private void ensureCapacity() {
        if (this.size == (this.elements.length / 2) + 1) {
            grow();
        }
    }

    private void grow() {
        Object[] newElements = new Object[this.elements.length * 2 + 1];
        int zeroElementIndex = newElements.length / 2;
        System.arraycopy(this.elements, 0, newElements, zeroElementIndex - size + 1, this.elements.length);
        this.head = zeroElementIndex - size;
        this.tail = zeroElementIndex + size;
        this.elements = newElements;
    }

    private void checkIndex(int index) {
        if (0 > index || index > size - 1)
            throw new IndexOutOfBoundsException();
    }
}
