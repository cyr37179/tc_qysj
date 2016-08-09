package com.ly.bookAppoint.service.impl;

import com.ly.bookAppoint.dto.AppointExecution;
import com.ly.bookAppoint.enums.AppointStateEnum;
import com.ly.bookAppoint.mapper.AppointmentMapper;
import com.ly.bookAppoint.mapper.BookMapper;
import com.ly.bookAppoint.pojo.Appointment;
import com.ly.bookAppoint.pojo.Book;
import com.ly.bookAppoint.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cyr37179 on 2016/8/4.
 */
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AppointmentMapper appointMapper;

    public Book getById(long bookId) {
        return bookMapper.queryById(bookId);
    }

    public List<Book> getList() {
        return bookMapper.queryAll(0,1000);
    }

    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            // 减库存
            int update = bookMapper.reduceNumber(bookId);
            if (update <= 0) {// 库存不足
                return new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
            } else {
                // 执行预约操作
                int insert = appointMapper.insertAppointment(bookId, studentId);
                if (insert <= 0) {// 重复预约
                    return new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
                } else {// 预约成功
                    Appointment appointment = appointMapper.queryByKeyWithBook(bookId, studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
                }
            }
        } catch (Exception e) {
           // logger.error(e.getMessage(), e);
            // 所有编译期异常转换为运行期异常
            return new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
        }
    }

    public int insertBook(String name, Integer number) {
        return bookMapper.insertBook(name,number);
    }
}
