package com.inhome.SandBox.controllers;

import com.inhome.SandBox.domain.Knight;
import com.inhome.SandBox.domain.Quest;
import com.inhome.SandBox.domain.repository.PlayerInformationRepository;
import com.inhome.SandBox.services.KnightService;
import com.inhome.SandBox.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestController {

    @Autowired
    KnightService knightService;

    @Autowired
    QuestService questService;

    @Autowired
    PlayerInformationRepository playerInformationRepository;


    @RequestMapping("/assignQuest")
    public String assignQuest(@RequestParam("knightId") Integer id, Model model){

        Knight knight = knightService.getKnight(id);
        List<Quest> notStarted = questService.getAllNotStartedQuests();
        model.addAttribute("knight",knight);
        model.addAttribute("notStartedQuests",notStarted);
        return "assignQuest";
    }

    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assignQuest(Knight knight, BindingResult result) {
        knightService.updateKnight(knight);
        Quest quest = knight.getQuest();
        questService.update(quest);
        return "redirect:/knights";
    }

    @RequestMapping(value = "/checkQuests")
    public String checkQuests(){

        knightService.getMyGold();

        return "redirect:/knights";
    }

}
