package com.zr.tinyurl.controller;

import com.zr.tinyurl.repository.TinyUrlRepository;
import com.zr.tinyurl.vo.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TinyUrlController {
    @Autowired
    TinyUrlRepository tinyUrlRepository ;
    @GetMapping("/tinyurl")
    public  String helloWorld(Model model){
        model.addAttribute("urls",tinyUrlRepository.findAll());
        model.addAttribute("urls2",tinyUrlRepository.findAllByMin());
        return "tinyUrl";
    }
    @GetMapping("/get")
    public  String get(Model model){
        model.addAttribute("urls",tinyUrlRepository.findAll());
        model.addAttribute("urls2",tinyUrlRepository.findAllByMin());
        return "tinyUrl::table";
    }
    @PostMapping("/insert")
    @ResponseBody
    public  String insert(@RequestParam("oldUrl") String oldUrl,@RequestParam("min") Integer min){
        System.out.println("oldUrl=="+oldUrl);
        Url url=new Url();
        String newUrl=createUrl();
        url.setOldUrl(oldUrl);
        url.setNewUrl(newUrl);
        url.setMin(min);
        url.setCreateTime(System.currentTimeMillis());
        tinyUrlRepository.save(url);
        return newUrl;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public  String delete(@RequestParam("id") Integer id){
        tinyUrlRepository.deleteById(id);
        return "1";
    }
    @RequestMapping("/{newUrl}")
    public ModelAndView Redirect(@PathVariable("newUrl") String newUrl){
       // if(newUrl.length()!=5){return null;}
        String url=tinyUrlRepository.findOldUrl(newUrl);
        if(url==null||url.length()==0)
            return new ModelAndView("error");
        else{
            System.out.println("命中："+url);
            if (url.indexOf("http://")==-1){
                url="http://"+url;
            }
            return new ModelAndView("redirect:"+url);
        }
    }
    private String createUrl() {
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<5;i++){
                int number=random.nextInt(62);
                sb.append(str.charAt(number));
            }
            return sb.toString();
    }
}
