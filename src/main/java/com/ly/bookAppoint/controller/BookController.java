package com.ly.bookAppoint.controller;

import com.ly.bookAppoint.dto.AppointExecution;
import com.ly.bookAppoint.dto.Result;
import com.ly.bookAppoint.pojo.Book;
import com.ly.bookAppoint.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cyr37179 on 2016/8/4.
 */
@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /seckill/list
public class BookController {

        //private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private BookService bookService;

        @RequestMapping(value = "/list")
        private String list(Model model) {
            List<Book> list = bookService.getList();
            model.addAttribute("list", list);
            // list.jsp + model = ModelAndView
            return "list";// WEB-INF/jsp/"list".jsp
        }

        // ajax json
        @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
        @ResponseBody
        private String detail(@PathVariable("bookId") Long bookId, Model model) {
           System.out.println(bookId);
            if (bookId == null) {
                return "redirect:/book/list";
            }
            Book book = bookService.getById(bookId);
            if (book == null) {
                return "forward:/book/list";
            }
            model.addAttribute("book", book);
            return "detail";
        }

        @RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
                "application/json; charset=utf-8" })
        private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @Param("studentId") Long studentId) {
            if (studentId == null || studentId.equals("")) {
                return new Result<AppointExecution>(false, "学号不能为空");
            }
            AppointExecution execution = bookService.appoint(bookId, studentId);
            return new Result<AppointExecution>(true, execution);
        }

    //模拟ajax json 的 get/post
    @RequestMapping(value = "/list2",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getBookList(){
        String a = "";
        List<Book> list = bookService.getList();
        Map<String,Object> modelMap = new HashMap<String, Object>(3);
        modelMap.put("total",list.size());
        modelMap.put("data",list);
        modelMap.put("success","true");
        return modelMap;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addBook(@RequestBody Book book){
        String name = book.getName();
        int number = book.getNumber();
        bookService.insertBook(name,number);
        Map<String,String> map = new HashMap<String, String>(1);
        map.put("success","true");
        return map;
    }
}
