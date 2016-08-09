package com.ly.bookAppoint.pojo;

/**
 * Created by cyr37179 on 2016/8/4.
 */
public class Book {
    private long bookId;// 图书ID

    private String name;// 图书名称

    private int number;// 馆藏数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
