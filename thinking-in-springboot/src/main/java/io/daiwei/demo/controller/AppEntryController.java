package io.daiwei.demo.controller;

import io.daiwei.entity.School;
import io.daiwei.entity.Student;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Daiwei on 2021/2/20
 */
@RestController
@RequestMapping("/hello")
public class AppEntryController {


    @Resource
    private School school;

    @GetMapping("/ding")
    public SimpleResult test() {
        return new SimpleResult(school);
    }

    @Getter
    class SimpleResult {

        private Integer code;

        private String msg;

        private Object data;

        public SimpleResult(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
            this.data = null;
        }

        public SimpleResult(Object data) {
            this.code = 1;
            this.msg = "ok";
            this.data = data;
        }
    }


}
