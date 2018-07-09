package com.vebdev.springhomework.controller;

import com.vebdev.springhomework.domain.Manufacturer;
import com.vebdev.springhomework.servise.jpa.ManufactorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManufactorersController extends BaseSecurityController{

    @Autowired
    private ManufactorerService manufactorerService;

    @GetMapping("/manufactorers")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("manufactorers");

        modelAndView.addObject("manufactorers", manufactorerService.getAll());

        return modelAndView;
    }

    @GetMapping("/manufactorer/delete")
    public String deleteManufactorer(@RequestParam long manufacturerId) {
        if (manufactorerService.exists(manufacturerId)) {
            Manufacturer manufacturer = manufactorerService.getById(manufacturerId);
            manufactorerService.delete(manufacturer);
        }
        return "redirect:/manufactorers";
    }

    @GetMapping("/manufactorer/add")
    public ModelAndView createManufactorer() {
        return modelAndViewSecurityBase("/manufactorer/create");
    }

    @PostMapping("/manufactorer/add")
    public String createManufactorer(@ModelAttribute Manufacturer manufacturer) {
        manufactorerService.save(manufacturer);

        return "redirect:/manufactorers";
    }

    @GetMapping("/manufactorer/edit")
    public ModelAndView editManufactorer(@RequestParam long manufacturerId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("manufactorer/edit");

        modelAndView.addObject("manufactorer", manufactorerService.getById(manufacturerId));

        return modelAndView;
    }

    @PostMapping("/manufactorer/edit")
    public String editManufactorer(@RequestParam(value = "manufactorerId", required = true) long manufactorerId,
                                   @RequestParam(value = "manufacturerName", required = false) String manufacturerName) {
        Manufacturer manufacturer = manufactorerService.getById(manufactorerId);
        manufacturer.setManufacturerName(manufacturerName);
        manufactorerService.update(manufacturer);
        return "redirect:/manufactorers";
    }

}
