package com.example.calc;

public class HistoryCard {
    private String operation;
    private String equation;
    private String timeStamp;

    public HistoryCard(String operation, String equation, String timeStamp){
        this.operation = operation;
        this.equation = equation;
        this.timeStamp = timeStamp;
    }

    public String getOperation(){return operation;}
    public String getEquation(){return equation;}
    public String getTimeStamp(){return timeStamp;}

    public void setOperation(String operation){this.operation=operation;}
    public void setEquation(String equation){this.equation = equation;}
}
