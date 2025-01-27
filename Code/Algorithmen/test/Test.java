package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

record Book(String autor, String title, int year) {}

public class Test {

    public static void main(String[] args) {
        List<Book> bookList = new LinkedList<>();

        bookList.add(new Book("J.K. Rowling",
                "Harry Potter and the Philosopher's Stone", 1997));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter and the Chamber of Secrets", 1998));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter and the Prisoner of Azkaban", 1999));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter and the Goblet of Fire", 2000));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter and the Order of the Phoenix", 2003));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter test 1", 2005));
        bookList.add(new Book("J.K. Rowling",
                "Harry Potter test 2", 2005));
        Map<Integer, Set<Book>> m = bookList.stream().collect(
                Collectors.groupingBy(Book::year, Collectors.toSet()));

        System.out.println(m.get(2005));

        Function<Double, Double> f = x -> 1.0 - x;
        System.out.println(f.andThen(Math::abs).apply(4.0));

        Function<Function<Double, Double>, Function<Double, Double>> fun = g -> x -> Math
                .abs(g.apply(x));
        System.out.println(fun.apply(f).apply(4.0));

        Function<Function<Double, Double>, Function<Double, Double>> fun2 = g -> x -> g
                .andThen(Math::abs).apply(x);
        System.out.println(fun2.apply(f).apply(4.0));
    }

}
