package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController
{
    @Autowired
    MessageRespository messageRespository;

    @RequestMapping("/")
    public String listform(Model model)
    {
        model.addAttribute("messages", messageRespository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String messageform(Model model)
    {
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/process")
    public String processTvForm(@Valid Message message,
                                BindingResult result)
    {
        if(result.hasErrors())
        {
            return "messageform";
        }
        messageRespository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showmessage(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("message", messageRespository.findById(id).get());
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updatemessage(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("message",messageRespository.findById(id).get());
        return "messageform";

    }

    @RequestMapping("/delete/{id}")
    public String delmessage(@PathVariable("id") Long id)
    {
        messageRespository.deleteById(id);
        return "redirect:/";

    }
}
