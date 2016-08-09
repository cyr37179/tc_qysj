package com.ly.bookAppoint.service;

import com.ly.bookAppoint.dto.AppointExecution;
import com.ly.bookAppoint.pojo.Book;

import java.util.List;

/**
 * Created by cyr37179 on 2016/8/4.
 */
public interface BookService {
    /**
     * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
     */

    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> getList();

    /**
     * 预约图书
     *
     * @param bookId
     * @param studentId
     * @return
     */
    AppointExecution appoint(long bookId, long studentId);

    /**
     * 插入图书记录
     */
    int insertBook(String name,Integer number);

}
