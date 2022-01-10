package HomeWork4.dto;

//1. Создать класс DataContainer у которого есть один дженерик (обобщение). Например литерал T. Данный класс как раз и будет решать задачу поставленную перед нами: хранить неограниченное количество передаваемых объектов обобщённого типа.
//2. Внутри DataContainer должно быть поле T[] data, внутренний массив, которое предназначено для хранения передаваемых объектов. Да, именно T[] а не Object[]. Если использовать Object[] то это будет опасно и постоянно придётся делать приведеление типов.
//3. Из-за особенностей дженериков в данном классе обязательно будет присутствовать один конструктор DataContainer(T[]). Есть и другие способы, но в рамках обучения они будут сложными и с ними мы разбираться будем слишком сложно.

import java.util.Arrays;
import java.util.Comparator;

public class DataContainer <T> {
    private T [] data;

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public DataContainer(T[] data) {
        this.data = data;
    }

    /**
     * 4. В данном классе должен быть метод int add(T item) который добавляет данные во внутреннее поле data и возвращает номер позиции в которую были вставлены данные, начиная с 0.
     * 	4.1 Если поле data не переполнено то данные нужно добавлять в первую позицию (ячейку) после последней заполненной позиции (ячейки).
     * 		4.1.1 Пример: data = [1, 2, 3, null, null, null]. Вызвали add(777). Должно получиться data = [1, 2, 3, 777, null, null]. Метод add вернёт число 3.
     * 		4.1.2 Пример: data = [1, 2, 3, null, null, null]. Вызвали add(null). Должно получиться data = [1, 2, 3, null, null, null]. Метод add вернёт число -1. -1 будет индикатором того что данный элемент в наш контейнер вставлять нельзя.
     * 		4.1.3 Пример: data = [1, null, 3, null, null, null]. Вызвали add(777). Должно получиться data = [1, 777, 3, null, null, null]. Метод add вернёт число 1.
     * 	4.2 В случае если поле data переполнено, нужно придумать механизм который будет расширять пространство для новых элементов. Тут вам поможет Arrays.copyOf.
     * 		4.2.1 Пример: data = [1, 2, 3]. Вызвали add(777). Должно получиться data = [1, 2, 3, 777]. Метод add вернёт число 3.
     * 		4.2.2 Пример: data = []. Вызвали add(777). Должно получиться data = [777]. Метод add вернёт число 0.
     * @param item - это элемент типа <T>, который добавляем в массив
     * @return - возвращает номер позиции в которую были вставлены данные или -1, если в методе add передали null.
     */

     public int add (T item) {
         for (int i = 0; i < data.length; i++) {
             if (item == null) {
                 return -1;
             }
             else if ( data [i] == null) {
                 data [i] = item;
                 return i;
             }
         }

         T [] dataNew = Arrays.copyOf(data,data.length+1);
         dataNew [data.length]=item;
         this.data=dataNew;
         return data.length-1;
    }

    /**
     * 5. В данном классе должен быть метод T get(int index). Данный метод получает из DataContainer'а, из поля data, предварительно сохранённый объект который мы передали на предыдущем шаге через метод add.
     * 	5.1 Пример: data = []. Вызвали add(9999). Получили data = [9999]. Метод add вернул число 0. Вызываем get(0). Метод get возвращает 9999
     * 	5.2 Пример: data = [9999]. Вызываем get(1). Метод get возвращает null
     * @param index - номер позиции возвращаемого елемента массива
     * @return - возвращаем элемент массива, если заданный индекс < 0 или >= длинны массива, то возвращаем null
     */
    public T get (int index) {
         if (index >= data.length || index <0 ) {
             return null;
         }
        return data[index];

    }

    /**
     * 6. В данном классе должен быть метод T[] getItems(). Данный метод возвращает поле data.
     * @return возвращает массив (data)
     */
    public T [] getItems () {
        return data;
    }

    /**
     * 7. Добавить метод boolean delete(int index) который будет удалять элемент из нашего поля data по индексу.
     * 	7.1 Метод возвращает true если у нас получилось удалить данные.
     * 		7.1.1 Пример data = [1, 2, 3, 777]. Вызывают delete(3). Должно получиться data = [1, 2, 3]. Метод delete вернёт true
     * 	7.2 Метод возвращает false если нет такого индекса.
     * 		7.2.1 Пример data = [1, 2, 3, 777]. Вызывают delete(9). Должно получиться data = [1, 2, 3, 777]. Метод delete вернёт false
     * 	7.3. Освободившуюся ячейку в поеле data необходимо удалить полностью. Пустых элементов не должно быть.
     * 	    7.3.1 Пример data = [1, 2, 3, 777]. Вызывают delete(2). Должно получиться data = [1, 2, 777]. Метод delete вернёт true
     * @param index - номер позиции элемента массива, который надо удалить
     * @return - возвращает true (если заданный индекс есть в массиве и уделен из него) или false (если заданного индекса нет в массиве)
     */
    public boolean delete (int index){
        if (index >= data.length || index <0 ) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (i == index) {
                data [i] = data [i+1];
            }
        }

        T [] dataNew2 = Arrays.copyOf(data,data.length-1);
        this.data=dataNew2;
        //System.out.println("новый массив после удаления элемента по индексу= " + Arrays.toString(dataNew2));
        return true;
    }

    /**
     * 8. Добавить метод boolean delete(T item) который будет удалять элемент из нашего поля data.
     * 	8.1 Метод возвращает true если у нас получилось удалить данные.
     * 		8.1.1 Пример data = [1, 2, 3, 777]. Вызывают delete(777). Должно получиться data = [1, 2, 3]. Метод delete вернёт true
     * 	8.2 Метод возвращает false если нет такого элемента.
     * 		8.2.1 Пример data = [1, 2, 3, 777]. Вызывают delete(111). Должно получиться data = [1, 2, 3, 777]. Метод delete вернёт false
     * 	8.3 Освободившуюся ячейку необходимо удалить полностью. Пустых элементов не должно быть.
     * @param item - элемент массива, который надо удалить.
     * @return возвращает true, если элемент найдем и удален. Возвращает false, если такого элемента нет в массиве.
     */
    public boolean delete (T item) {
        for (int i = 0; i < data.length; i++) {
            if ( data [i].equals(item)) {
                for (int j = i; j <data.length-1 ; j++) {
                    data [j] = data [j+1];
                }
                T [] dataNew3 = Arrays.copyOf(data,data.length-1);
                this.data=dataNew3;
                // System.out.println("новый массив после удаления элемента= " + Arrays.toString(dataNew3));
                return true;
            }
        }
        return false;
    }


    /**
     * 9. Добавить НЕ СТАТИЧЕСКИЙ метод void sort(Comparator<.......> comparator). Данный метод занимается сортировкой данных записанных в поле data используя реализацию сравнения из ПЕРЕДАННОГО объекта comparator. Классом Arrays пользоваться запрещено.
     * @param comparator - сравнивает 2 элемента массива.
     */
    public void sort (Comparator<T> comparator) {
        boolean isSort = false;
        T a;
        while (isSort=true) {
            isSort= true;
            for (int i = 0; i < data.length-1; i++) {
                if (comparator.compare(data [i], data [i+1])>0){
                    isSort = false;

                    a=data[i];
                    data [i]=data [i+1];
                    data [i+1] = a;
                }
            }
        }
    }

    //10. Переопределить метод toString() в классе и выводить содержимое data без ячеек в которых хранится null.

    @Override
    public String toString() {
        for (int i = 0; i < data.length; i++) {
            if (data [i] == null) {
                for (int j = i; j < data.length-1; j++) {
                    data [j] = data[j+1];
                }
                T [] dataNew3 = Arrays.copyOf(data,data.length-1);
                this.data=dataNew3;
                i--;
            }
        }
        return "DataContainer{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
