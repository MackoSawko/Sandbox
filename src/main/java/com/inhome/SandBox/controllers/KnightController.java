package com.inhome.SandBox.controllers;

import com.inhome.SandBox.components.TimeComponent;
import com.inhome.SandBox.domain.Knight;
import com.inhome.SandBox.domain.PlayerInformation;
import com.inhome.SandBox.domain.repository.PlayerInformationRepository;
import com.inhome.SandBox.services.KnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KnightController {

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Autowired
    KnightService knightService;

    @Autowired
    TimeComponent timeComponent;

    @RequestMapping("/knights")
    public String getKnights(Model model){
        List<Knight> allKnights = new ArrayList<>(knightService.getAllKnights());
        PlayerInformation pi = playerInformationRepository.getFirst();
        model.addAttribute("knights", allKnights);
        model.addAttribute("timecomponent",timeComponent);
        model.addAttribute("playerinformation",pi);
        return "knights";
    }

    @RequestMapping(value = "/knight/delete/{id}")
    public String deleteKnights(@PathVariable("id") Integer id){
        knightService.deleteKnight(id);
        return "redirect:/knights";
    }

    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model){
        Knight knight = knightService.getKnight(id);
        model.addAttribute("knight",knight);
        model.addAttribute("timecomponent",timeComponent);
        return "knight";
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model){
        model.addAttribute("knight", new Knight());
        model.addAttribute("timecomponent",timeComponent);
        return "knightform";
    }

    @RequestMapping(value = "/knights", method = RequestMethod.POST)
    public String saveKnight(@Valid  Knight knight, BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                System.out.println("Mamy błędy");
                bindingResult.getAllErrors().forEach(objectError -> {
                    System.out.println(objectError.getObjectName()+" "+ objectError.getDefaultMessage());
                });
                return "knightform";
            } else {
                knightService.saveKnight(knight);
                return "redirect:/knights";
            }
    }

}

