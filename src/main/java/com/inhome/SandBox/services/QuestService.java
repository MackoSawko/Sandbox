package com.inhome.SandBox.services;

import com.inhome.SandBox.domain.Quest;
import com.inhome.SandBox.domain.repository.KnightRepository;
import com.inhome.SandBox.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestService {


    QuestRepository questRepository;

    @Autowired
    KnightRepository knightRepository;

    final static Random rand = new Random();

    public void assignRandomQuest(String knightName){
        List<Quest> allQuests = questRepository.getAllQuests();
        Quest randomQuest = allQuests.get(rand.nextInt(allQuests.size()));
        knightRepository.getKnight(knightName).ifPresent(knight -> knight.setQuest(randomQuest));
    }

    public List<Quest> getAllNotStartedQuests() {
        List<Quest> notStarted =  questRepository.getAllQuests().stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());
        return notStarted;
    }

    @Autowired
    public void setQuestRepository(QuestRepository questRepository){
        this.questRepository = questRepository;
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public boolean isQuestCompleted(Quest q){
        return  q.isFinished();
    }
}

