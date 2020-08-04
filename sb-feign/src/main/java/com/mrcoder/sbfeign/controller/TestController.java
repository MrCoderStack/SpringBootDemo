package com.mrcoder.sbfeign.controller;

import com.mrcoder.sbfeign.feign.ExampleControllerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TestController {


    @Autowired
    private ExampleControllerFeignClient exampleControllerFeignClient;


    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public String test(@RequestParam(value = "id") Long Id) {
        return exampleControllerFeignClient.getInfoById(Id);
    }

}
