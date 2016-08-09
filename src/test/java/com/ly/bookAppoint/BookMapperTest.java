package com.ly.bookAppoint;

import com.ly.bookAppoint.mapper.BookMapper;
import com.ly.bookAppoint.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cyr37179 on 2016/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-transaction.xml" })
public class BookMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testQueryById() throws Exception{

        long bookId = 1000;
        Book book = bookMapper.queryById(bookId);
        System.out.println(book);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Book> books = bookMapper.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        long bookId = 1000;
        int update = bookMapper.reduceNumber(bookId);
        System.out.println("update=" + update);
        System.out.println(bookMapper.queryById(bookId));
    }
}
