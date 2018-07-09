package com.vebdev.springhomework.controller;

import com.vebdev.springhomework.servise.security.SecurityProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseSecurityController {
    @Autowired
    protected SecurityProcessor securityProcessor;

    public ModelAndView modelAndViewSecurityBase(String viewName) {
        return securityProcessor.modelAndViewSecurity(viewName);
    }
}