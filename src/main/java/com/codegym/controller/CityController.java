package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("cities")
    public ModelAndView listCity(){
        List<City> cityList = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city-list");
        modelAndView.addObject("city", cityList);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create-city");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create-city")
    public ModelAndView saveProvince(@ModelAttribute("city") City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/create-city");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "create successfully");
        return modelAndView;
    }
    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView;
        if (city != null){
            modelAndView = new ModelAndView("/edit-city");
            modelAndView.addObject("city", city);
        }else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }
    @PostMapping("/edit-city")
    public ModelAndView updateCustomer(@ModelAttribute("city")City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/edit-city");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "Update successfully");
        return modelAndView;
    }

    //xoa
    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView;
        if (city != null){
            modelAndView = new ModelAndView("/delete-city");
            modelAndView.addObject("city", city);
        }else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }
    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city")City city){
        cityService.remove(city.getId());
        return "redirect:cities";
    }

    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable("id")Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView;
        if (city == null){
            return new ModelAndView("/error.404");
        }
        else {
            modelAndView = new ModelAndView("/view-city");
            modelAndView.addObject("city", city);
        }
        return modelAndView;
    }
}
