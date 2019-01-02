package com.inhome.SandBox.domain;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private int reward = 100;

    protected int lenghtInSeconds = 10;

    private boolean started = false;

    private boolean finished = false;

    protected LocalDateTime startDate;

    public Quest(){

    }

    public Quest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getLenght() {
        return lenghtInSeconds;
    }

    public void setLenght(int lenght) {
        this.lenghtInSeconds = lenght;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        if(started){
            this.startDate = LocalDateTime.now();
        }
        this.started = started;
    }

    public boolean isFinished() {

        if(this.finished){
            return  this.finished;
        } else {

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime questEndDate = this.startDate.plusSeconds(lenghtInSeconds);
            now.isAfter(questEndDate);

            boolean isAfter = now.isAfter(questEndDate);

            if (isAfter) {
                this.finished = true;
            }

            return isAfter;
        }
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return description;
    }
}
