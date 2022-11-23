package ru.mail.polis.homework.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Необходимо реализовать метод reflectiveToString, который для произвольного объекта
 * возвращает его строковое описание в формате:
 * <p>
 * {field_1: value_1, field_2: value_2, ..., field_n: value_n}
 * <p>
 * где field_i - имя поля
 * value_i - его строковое представление (String.valueOf),
 * за исключением массивов, для которых value формируется как:
 * [element_1, element_2, ..., element_m]
 * где element_i - строковое представление элемента (String.valueOf)
 * элементы должны идти в том же порядке, что и в массиве.
 * <p>
 * Все null'ы следует представлять строкой "null".
 * <p>
 * Порядок полей
 * Сначала следует перечислить в алфавитном порядке поля, объявленные непосредственно в классе объекта,
 * потом в алфавитном порядке поля объявленные в родительском классе и так далее по иерархии наследования.
 * Примеры можно посмотреть в тестах.
 * <p>
 * Какие поля выводить
 * Необходимо включать только нестатические поля. Также нужно пропускать поля, помеченные аннотацией @SkipField
 * <p>
 * Упрощения
 * Чтобы не усложнять задание, предполагаем, что нет циклических ссылок, inner классов, и transient полей
 * <p>
 * Реализация
 * В пакете ru.mail.polis.homework.reflection можно редактировать только этот файл
 * или добавлять новые (не рекомендуется, т.к. решение вполне умещается тут в несколько методов).
 * Редактировать остальные файлы нельзя.
 * <p>
 * Баллы
 * В задании 3 уровня сложности, для каждого свой набор тестов:
 * Easy - простой класс, нет наследования, массивов, статических полей, аннотации SkipField (4 балла)
 * Easy + Medium - нет наследования, массивов, но есть статические поля и поля с аннотацией SkipField (6 баллов)
 * Easy + Medium + Hard - нужно реализовать все требования задания (10 баллов)
 * <p>
 * того, по заданию можно набрать 10 баллов
 * Баллы могут снижаться за неэффективный или неаккуратный код
 */
public class ReflectionToStringHelper {

    public static String reflectiveToString(Object object) {
        if (object == null) {
            return "null";
        }
        Class<?> aClass = object.getClass();
        StringBuilder out = new StringBuilder();
        while (aClass != null) {
            Stream.of(aClass.getDeclaredFields())
                    .filter(field -> !Modifier.isStatic(field.getModifiers())
                            && !field.isAnnotationPresent(SkipField.class))
                    .sorted(Comparator.comparing(Field::getName))
                    .forEach(classField -> reflectField(classField, object, out));
            aClass = aClass.getSuperclass();
        }
        if (out.length() > 2) {
            out.delete(out.length() - 2, out.length());
        }
        return "{" + out + "}";
    }

    private static void reflectField(Field classField, Object object, StringBuilder out) {
        out.append(classField.getName());
        out.append(": ");
        try {
            boolean changedAccessible = false;
            if (!classField.canAccess(object)) {
                classField.setAccessible(true);
                changedAccessible = true;
            }
            if (classField.getType().isArray()) {
                reflectArray(classField.get(object), out);
            } else {
                out.append(classField.get(object));
            }
            if (changedAccessible) {
                classField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        out.append(", ");
    }

    private static void reflectArray(Object object, StringBuilder out) {
        if (object == null) {
            out.append("null");
            return;
        }
        out.append("[");
        for (int i = 0; i < Array.getLength(object); i++) {
            out.append(Array.get(object, i));
            out.append(", ");
        }
        if (out.charAt(out.length() - 1) != '[') {
            out.delete(out.length() - 2, out.length());
        }
        out.append("]");
    }
}