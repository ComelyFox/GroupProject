package service;

import java.util.*;
import java.util.concurrent.*;

/**
 * Реализация динамического массива, аналогичная {@link java.util.ArrayList}.
 * <p>
 * Класс хранит элементы в массиве и автоматически увеличивает его размер
 * при добавлении новых элементов. Поддерживает базовые операции коллекции:
 * добавление, удаление, поиск, итерацию и преобразование в массив.
 * </p>
 *
 * <p>Особенности реализации:</p>
 * <ul>
 *   <li>Начальная ёмкость задаётся конструктором или используется значение по умолчанию.</li>
 *   <li>При превышении текущей ёмкости массив увеличивается на 50%.</li>
 *   <li>Поддерживаются операции {@code add}, {@code remove}, {@code get}, {@code set}, а также итераторы.</li>
 *   <li>Методы работают с обобщённым типом {@code <E>}.</li>
 *   <li>Класс реализует интерфейс {@link java.util.List}.</li>
 * </ul>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * MyArrayList<String> list = new MyArrayList<>();
 * list.add("Hello");
 * list.add("World");
 * System.out.println(list); // [Hello, World]
 * }</pre>
 *
 * @param <E> тип элементов, хранимых в списке
 */
public class MyArrayList<E> implements List<E> {
    /**
     * Начальная ёмкость списка, используемая по умолчанию
     * при создании без указания размера.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Пустой массив, используемый для оптимизации
     * при создании списка с нулевой ёмкостью.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Массив, в котором хранятся элементы списка.
     * Его размер может увеличиваться при добавлении новых элементов.
     */
    private Object[] elementData;

    /**
     * Текущее количество элементов в списке.
     */
    private int size;

    /**
     * Создаёт пустой список с начальной ёмкостью по умолчанию.
     * <p>
     * Внутренний массив инициализируется размером {@link #DEFAULT_CAPACITY},
     * количество элементов устанавливается в 0.
     * </p>
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Создаёт пустой список с указанной начальной ёмкостью.
     * <p>
     * Если значение {@code initialCapacity} больше 0 — создаётся массив указанного размера.
     * Если значение равно 0 — используется пустой массив {@link #EMPTY_ELEMENTDATA}.
     * Если значение отрицательное — выбрасывается {@link IllegalArgumentException}.
     * </p>
     *
     * @param initialCapacity начальная ёмкость списка
     * @throws IllegalArgumentException если {@code initialCapacity} меньше 0
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Неверный размер: " + initialCapacity);
        }
        this.size = 0;
    }

    /**
     * Создаёт новый список, копируя элементы из другого списка.
     * <p>
     * Если переданный список {@code other} равен {@code null} или пуст —
     * создаётся пустой список. В противном случае копируются все элементы
     * и размер нового списка совпадает с размером исходного.
     * </p>
     *
     * @param other исходный список, элементы которого будут скопированы
     */
    public MyArrayList(MyArrayList<? extends E> other) {
        if (other == null || other.size == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
            this.size = 0;
        } else {
            this.size = other.size;
            this.elementData = Arrays.copyOf(other.elementData, size, Object[].class);
        }
    }

    //Override methods group

    /**
     * Возвращает количество элементов в списке.
     *
     * @return текущее число элементов
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список не содержит элементов;
     *         {@code false}, если хотя бы один элемент присутствует
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет, содержится ли указанный объект в списке.
     * <p>
     * Сравнение выполняется методом {@link Object#equals(Object)}.
     * Для {@code null} выполняется отдельная проверка.
     * </p>
     *
     * @param o объект для проверки
     * @return {@code true}, если объект найден в списке;
     *         {@code false}, если объект отсутствует
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Возвращает итератор по элементам списка.
     * <p>
     * Итератор реализует последовательный обход элементов от начала до конца.
     * Поддерживаются операции:
     * <ul>
     *   <li>{@link Iterator#hasNext()} — проверка наличия следующего элемента;</li>
     *   <li>{@link Iterator#next()} — получение следующего элемента;</li>
     *   <li>{@link Iterator#remove()} — удаление последнего возвращённого элемента из списка.</li>
     * </ul>
     * </p>
     *
     * <p>Особенности реализации:</p>
     * <ul>
     *   <li>Метод {@code next()} выбрасывает {@link NoSuchElementException}, если элементов больше нет.</li>
     *   <li>Метод {@code remove()} выбрасывает {@link IllegalStateException}, если вызван до {@code next()} или повторно после удаления.</li>
     *   <li>После удаления курсор корректируется, чтобы обход продолжался корректно.</li>
     * </ul>
     *
     * @return итератор для последовательного обхода элементов списка
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            private int lastRet = -1; //индекс последнего возвращенного элемента

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                lastRet = cursor;
                return (E) elementData[cursor++];
            }

            @Override
            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                MyArrayList.this.remove(lastRet);
                cursor = lastRet; //изменение курсора после удаления
                lastRet = -1;
            }
        };
    }

    /**
     * Возвращает массив, содержащий все элементы списка.
     * <p>
     * Размер результирующего массива равен текущему количеству элементов {@link #size}.
     * Элементы копируются в новый массив, поэтому изменения в исходном списке
     * не влияют на возвращённый массив.
     * </p>
     *
     * @return новый массив, содержащий все элементы списка
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Добавляет указанный элемент в конец списка.
     * <p>
     * При необходимости увеличивает внутренний массив для размещения нового элемента.
     * </p>
     *
     * @param e элемент для добавления
     * @return всегда {@code true}, так как список изменяется
     */
    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * Удаляет первое вхождение указанного объекта из списка, если он присутствует.
     * <p>
     * Сравнение выполняется методом {@link Object#equals(Object)}.
     * Для {@code null} выполняется отдельная проверка.
     * После удаления элементы сдвигаются, чтобы заполнить освободившееся место.
     * </p>
     *
     * @param o объект, который требуется удалить
     * @return {@code true}, если элемент был найден и удалён;
     *         {@code false}, если элемент отсутствует
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Удаляет все вхождения указанного объекта из списка.
     * <p>
     * Сравнение выполняется методом {@link Objects#equals(Object, Object)}.
     * После удаления элементы сдвигаются, чтобы заполнить освободившееся место.
     * </p>
     *
     * @param o объект, который требуется удалить из списка
     * @return {@code true}, если хотя бы один элемент был удалён;
     *         {@code false}, если список не изменился
     */
    public boolean removeAll(Object o) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], o)) {
                fastRemove(i);
                i--; //после сдвига проверка нового элемента на том-же индексе
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Добавляет все элементы из указанной коллекции в конец списка.
     * <p>
     * Элементы копируются в массив списка в порядке их следования в коллекции.
     * При необходимости увеличивается ёмкость внутреннего массива.
     * </p>
     *
     * @param c коллекция, элементы которой будут добавлены
     * @return {@code true}, если список изменился (коллекция не пуста);
     *         {@code false}, если коллекция пуста и список остался без изменений
     * @throws NullPointerException если переданная коллекция равна {@code null}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Вставляет все элементы из указанной коллекции в список,
     * начиная с позиции {@code index}.
     * <p>
     * Элементы вставляются в порядке их следования в коллекции.
     * При необходимости увеличивается ёмкость внутреннего массива.
     * </p>
     *
     * @param index позиция, начиная с которой будут вставлены элементы
     * @param c коллекция, элементы которой добавляются
     * @return {@code true}, если список изменился (коллекция не пуста);
     *         {@code false}, если коллекция пуста
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     * @throws NullPointerException если коллекция равна {@code null}
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        }

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Удаляет все элементы из списка.
     * <p>
     * После вызова метода список становится пустым,
     * а все ссылки на элементы обнуляются для освобождения памяти.
     * </p>
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index позиция элемента, который требуется получить
     * @return элемент, находящийся на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) get(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Вставляет указанный элемент в список на заданную позицию.
     * <p>
     * Все элементы, начиная с позиции {@code index}, сдвигаются вправо,
     * чтобы освободить место для нового элемента. При необходимости
     * увеличивается ёмкость внутреннего массива.
     * </p>
     *
     * @param index позиция, на которую будет вставлен элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Возвращает строковое представление списка.
     * <p>
     * Элементы выводятся в квадратных скобках и разделяются запятой и пробелом.
     * Если список пуст, возвращается строка {@code "[]"}.
     * </p>
     *
     * @return строковое представление элементов списка
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Удаляет элемент по указанному индексу.
     * <p>
     * После удаления все элементы, находящиеся справа от удалённого,
     * сдвигаются влево, чтобы заполнить освободившееся место.
     * Последний элемент массива обнуляется для освобождения памяти.
     * </p>
     *
     * @param index индекс удаляемого элемента
     * @return значение удалённого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        elementData[--size] = null;
        return oldValue;
    }

    /**
     * Возвращает индекс первого вхождения указанного объекта в списке.
     * <p>
     * Сравнение выполняется методом {@link Object#equals(Object)}.
     * Для {@code null} выполняется отдельная проверка.
     * </p>
     *
     * @param o объект для поиска
     * @return индекс первого найденного элемента;
     *         {@code -1}, если элемент отсутствует
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Возвращает индекс последнего вхождения указанного объекта в списке.
     * <p>
     * Сравнение выполняется методом {@link Object#equals(Object)}.
     * Для {@code null} выполняется отдельная проверка.
     * </p>
     *
     * @param o объект для поиска
     * @return индекс последнего найденного элемента;
     *         {@code -1}, если элемент отсутствует
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size-1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Возвращает итератор списка, начинающий обход с первого элемента.
     * <p>
     * Эквивалентен вызову {@link #listIterator(int)} с индексом {@code 0}.
     * </p>
     *
     * @return итератор списка, начинающий обход с начала
     */
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /**
     * Возвращает итератор списка, начинающий обход с указанной позиции.
     * <p>
     * Итератор поддерживает перемещение вперёд и назад по элементам списка,
     * а также методы {@code nextIndex()} и {@code previousIndex()} для получения
     * текущих индексов. Операции {@code remove()}, {@code set(E)} и {@code add(E)}
     * не поддерживаются и выбрасывают {@link UnsupportedOperationException}.
     * </p>
     *
     * @param index позиция, с которой начинается обход
     * @return итератор списка, начинающий обход с указанного индекса
     * @throws IndexOutOfBoundsException если индекс меньше 0 или больше размера списка
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return new ListIterator<E>() {
            private int cursor = index;

            @Override
            public boolean hasNext() { return cursor < size; }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) elementData[cursor++];
            }

            @Override
            public boolean hasPrevious() { return cursor > 0; }

            @Override
            public E previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                return (E) elementData[--cursor];
            }

            @Override
            public int nextIndex() { return cursor; }

            @Override
            public int previousIndex() { return cursor - 1; }

            @Override
            public void remove() { throw new UnsupportedOperationException(); }

            @Override
            public void set(E e) { throw new UnsupportedOperationException(); }

            @Override
            public void add(E e) { throw new UnsupportedOperationException(); }
        };
    }

    /**
     * Возвращает представление части списка в виде нового объекта {@code MyArrayList}.
     * <p>
     * В новый список копируются элементы из диапазона {@code [fromIndex, toIndex)} —
     * начиная с {@code fromIndex} (включительно) и заканчивая {@code toIndex} (не включительно).
     * </p>
     *
     * @param fromIndex начальный индекс (включительно)
     * @param toIndex конечный индекс (не включительно)
     * @return новый список, содержащий элементы указанного диапазона
     * @throws IndexOutOfBoundsException если индексы выходят за пределы списка
     *                                   или {@code fromIndex > toIndex}
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }

        int subListSize = toIndex - fromIndex;
        MyArrayList<E> subList = new MyArrayList<>(subListSize);
        System.arraycopy(elementData, fromIndex, subList.elementData, 0, subListSize);
        subList.size = subListSize;
        return subList;
    }

    /**
     * Удаляет из списка все элементы, которые отсутствуют в указанной коллекции.
     * <p>
     * После выполнения метода в списке остаются только те элементы,
     * которые содержатся в коллекции {@code c}.
     * </p>
     *
     * @param c коллекция, элементы которой должны быть сохранены в списке
     * @return {@code true}, если список был изменён;
     *         {@code false}, если список остался без изменений
     * @throws NullPointerException если коллекция равна {@code null}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elementData[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Удаляет из списка все элементы, которые содержатся в указанной коллекции.
     * <p>
     * Если элемент списка присутствует в коллекции {@code c}, он удаляется.
     * После удаления элементы сдвигаются, чтобы заполнить освободившееся место.
     * </p>
     *
     * @param c коллекция, элементы которой должны быть удалены из списка
     * @return {@code true}, если список был изменён (удалён хотя бы один элемент);
     *         {@code false}, если список остался без изменений
     * @throws NullPointerException если коллекция равна {@code null}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elementData[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Проверяет, содержит ли список все элементы указанной коллекции.
     * <p>
     * Для каждого элемента коллекции выполняется проверка наличия в списке
     * методом {@link #contains(Object)}.
     * </p>
     *
     * @param c коллекция, элементы которой должны присутствовать в списке
     * @return {@code true}, если список содержит все элементы коллекции;
     *         {@code false}, если хотя бы один элемент отсутствует
     * @throws NullPointerException если коллекция равна {@code null}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Возвращает массив, содержащий все элементы списка.
     * <p>
     * Если переданный массив {@code a} имеет достаточный размер,
     * элементы копируются в него. Если массив меньше текущего размера списка —
     * создаётся новый массив того же типа и возвращается.
     * Если массив больше размера списка, элемент на позиции {@code size}
     * устанавливается в {@code null}.
     * </p>
     *
     * @param <T> тип элементов результирующего массива
     * @param a массив, в который будут скопированы элементы, если он достаточно велик
     * @return массив, содержащий все элементы списка
     * @throws ArrayStoreException если тип массива несовместим с элементами списка
     * @throws NullPointerException если массив равен {@code null}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }


    //support methods
    /**
     * Обеспечивает достаточную ёмкость внутреннего массива для хранения
     * указанного минимального количества элементов.
     * <p>
     * Если текущая ёмкость меньше {@code minCapacity}, создаётся новый массив
     * увеличенного размера (на 50% больше старого). Если этого недостаточно —
     * используется значение {@code minCapacity}.
     * </p>
     *
     * @param minCapacity минимальная требуемая ёмкость массива
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = (int)(oldCapacity * 1.5); //увеличение на 50%
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Быстро удаляет элемент по указанному индексу без дополнительных проверок.
     * <p>
     * Элементы справа от удалённого сдвигаются влево, последний элемент массива
     * обнуляется для освобождения памяти, а размер списка уменьшается на единицу.
     * </p>
     *
     * @param index индекс удаляемого элемента
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    /**
     * Проверяет корректность индекса для операций вставки.
     * <p>
     * Индекс должен находиться в диапазоне от {@code 0} до {@code size} включительно.
     * </p>
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс меньше 0 или больше текущего размера списка
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Проверяет корректность индекса для операций доступа и удаления.
     * <p>
     * Индекс должен находиться в диапазоне от {@code 0} до {@code size - 1}.
     * </p>
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс меньше 0 или больше либо равен текущему размеру списка
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //Concurrent method(task 4)
    /**
     * Подсчитывает количество вхождений указанного элемента в коллекцию
     * с использованием параллельного стрима.
     * <p>
     * Метод создает "снэпшот" текущего состояния {@code MyArrayList}, чтобы
     * избежать проблем при параллельном доступе, и затем применяет
     * {@link java.util.stream.Stream#parallel()} для параллельной фильтрации
     * элементов. Подсчёт выполняется автоматически средствами Stream API.
     * </p>
     *
     * <p><b>Особенности:</b></p>
     * <ul>
     *   <li>Используется параллельный стрим, что позволяет задействовать
     *       несколько потоков без явного управления пулом потоков.</li>
     *   <li>Если коллекция пуста, метод возвращает {@code 0}.</li>
     *   <li>Сравнение элементов выполняется через {@link java.util.Objects#equals(Object, Object)},
     *       что корректно обрабатывает {@code null} значения.</li>
     * </ul>
     *
     * @param element элемент, количество вхождений которого требуется подсчитать
     * @return общее количество вхождений {@code element} в коллекцию
     */
    public int countParallelOccurrences(E element){
        MyArrayList<E> snapshot = new MyArrayList<>(this);
        return (int) snapshot.parallelStream()
                .filter(e -> Objects.equals(e, element))
                .count();
    }

    /**
     * Подсчитывает количество вхождений указанного элемента в коллекции
     * с использованием многопоточности. Коллекция делится на части,
     * каждая из которых обрабатывается отдельным потоком.
     *
     * <p>Метод создаёт пул потоков фиксированного размера, запускает задачи
     * для подсчёта вхождений элемента в каждой части коллекции и агрегирует
     * результаты. Если задачи не завершатся в течение 10 секунд, будет выброшено
     * исключение {@link java.util.concurrent.CompletionException} с причиной {@link java.util.concurrent.TimeoutException}.
     *
     * @param element      элемент, количество вхождений которого нужно подсчитать
     * @param threadAmount количество потоков, используемых для обработки.
     *                     Должно быть положительным числом; если оно больше размера коллекции,
     *                     будет ограничено этим размером.
     *
     * @return количество вхождений указанного элемента в коллекции
     *
     * @throws IllegalArgumentException если {@code threadAmount <= 0}
     * @throws java.util.concurrent.CompletionException если выполнение задач прервано
     *         или превышено время ожидания (10 секунд)
     */
    public int countOccurrences(E element, int threadAmount){
        if (threadAmount<=0){
            throw new IllegalArgumentException("Количество потоков не может быть отрицательным либо равным нулю!");
        }

        MyArrayList<E> snapshot = new MyArrayList<>(this);
        int size = snapshot.size();
        if (size == 0) return 0;
        threadAmount = Math.min(threadAmount, size);
        int chunkSize = (int) Math.ceil((double) size / threadAmount);

        ExecutorService executor = Executors.newFixedThreadPool(threadAmount);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < threadAmount; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, size);
            //для последнего потока
            if (start >= end) break;

            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int count = 0;
                for (int j = start; j < end; j++) {
                    if (Objects.equals(snapshot.get(j), element)) {
                        count++;
                    }
                }
                return count;
            }, executor);

            futures.add(future);
        }

        CompletableFuture<Integer> totalFuture = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]/*массив того же типа, что и переданный аргумент*/)
        )
                .orTimeout(10, TimeUnit.SECONDS)
                .thenApply(v -> futures.stream()
                .mapToInt(CompletableFuture::join)
                .sum()
        );

        try {
            return totalFuture.join();
        } finally {
            executor.shutdown();
        }
    }
}
