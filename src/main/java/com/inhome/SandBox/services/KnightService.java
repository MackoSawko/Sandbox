package com.inhome.SandBox.services;

import com.inhome.SandBox.domain.Knight;
import com.inhome.SandBox.domain.repository.KnightRepository;
import com.inhome.SandBox.domain.PlayerInformation;
import com.inhome.SandBox.domain.repository.PlayerInformationRepository;
import com.inhome.SandBox.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KnightService {

    @Autowired
    KnightRepository knightRepository;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    public List<Knight> getAllKnights(){
        return new ArrayList<>(knightRepository.getAllKnights());
    }


    public void saveKnight(Knight knight) {
        knightRepository.createKnight(knight.getName(),knight.getAge());
    }

    public Knight getKnight(Integer id) {
        return knightRepository.getKnightById(id);
    }

    public void deleteKnight(Integer id) {
        System.out.println(id);
        knightRepository.deleteKnight(id);
    }

    public void updateKnight(Knight knight) {

        knightRepository.updateKnight(knight.getId(),knight);

    }

    public int collectRewards() {
        Predicate<Knight> knightPredicate = knight -> {
            if (knight.getQuest() != null) {
                return knight.getQuest().isFinished();
            } else {
                return false;
            }
        };

        int sum = knightRepository.getAllKnights().stream().filter(knightPredicate)
                .mapToInt(knight -> knight.getQuest().getReward())
                .sum();

        knightRepository.getAllKnights().stream().filter(knightPredicate).forEach(knight -> knight.setQuest(null));
        return sum;
    }

    @Transactional
    public void getMyGold() {

        List<Knight> allKnights = getAllKnights();
        allKnights.forEach(knight -> {
            if(knight.getQuest()!=null) {
                boolean finished = knight.getQuest().isFinished();
                if(finished){
                    questRepository.update(knight.getQuest());
                }

            }
        });
        PlayerInformation first = playerInformationRepository.getFirst();
        int currentGold = first.getGold();
        first.setGold(currentGold + collectRewards());
    }
}
