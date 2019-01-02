package com.inhome.SandBox.domain.repository;

import com.inhome.SandBox.domain.Quest;
import com.inhome.SandBox.utils.Ids;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class QuestRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createQuest(String description){
        Quest quest = new Quest(description);
        em.persist(quest);
    }

    public void deleteQuest(Quest quest){
        em.remove(quest);
    }

    public  List<Quest> getAllQuests(){
        return em.createQuery("from Quest", Quest.class).getResultList();
    }

    @Scheduled(fixedDelayString = "${questCreationDelay}", initialDelay = 3000)
    @Transactional
    public void createRandomQuest(){
        List<String> descriptions = new ArrayList<>();
        Random rand = new Random();

        descriptions.add("Uratuj ksiezniczke");
        descriptions.add("Zabij smoka");
        descriptions.add("Zbierz zloto");
        descriptions.add("Zabij krola demonow");

        String description = descriptions.get(rand.nextInt(descriptions.size()));
        createQuest(description);

    }

    @Transactional
    public void update(Quest quest) {
        em.merge(quest);
    }

    public Quest getQuest(Integer id) {
         return em.find(Quest.class, id);
    }
}
