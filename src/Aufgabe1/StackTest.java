package Aufgabe1;

public class StackTest {
    public static void main(String[] args) {
        //empty();
        full();
    }

    public static void full(){
        Stack myList = new Stack(3);
        myList.push(new Book("Harry Potter and the philosopher's stone", "Rowling"));
        myList.push(new Book("Harry Potter and the chamber of secrets", "Rowling"));
        myList.push(new Book("Harry Potter and the prisoner of Azkaban", "Rowling"));
        myList.push(new Book("Harry Potter and the goblet of fire", "Rowling"));
    }

    public static void empty(){
        Stack myList = new Stack(3);
        myList.push(new Book("Harry Potter", "Rowling"));
        myList.pop();
        myList.pop();
    }
}

class Book {
        private final String title;
        private final String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public void print() {
            System.out.println(title + " von " + author);
        }
    }

class Stack {
    Object [] array;

    private int top = 0;

    public Stack(int nr) {
        array = new Object[nr];
    }

    public void push(Book element) {
        if(top == array.length)
            throw new RuntimeException("Stack ist bereits voll");
        array[top++] = element;
    }

    public Object pop() {
        if(top <= 0)
            throw new RuntimeException("Stack ist bereits leer");
        return array[--top];
    }
}


