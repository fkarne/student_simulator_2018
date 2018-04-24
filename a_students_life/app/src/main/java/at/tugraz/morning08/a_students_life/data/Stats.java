package at.tugraz.morning08.a_students_life.data;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;

public class Stats {
    private static int MAX = 100;

    private int energy = MAX;
    private int stress = MAX;
    private int hunger = MAX;
    private int social = MAX;

    // [0.5 ; 2.0]
    private double energy_multiplicator = 1.0;
    private double stress_multiplicator = 1.0;
    private double hunger_multiplicator = 1.0;
    private double social_multiplicator = 1.0;

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void increaseEnergy(int energyChange) {
        energy += energyChange;
    }

    public void decreaseEnergy(int energyChange) {
        energy -= energyChange;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public void increaseStress(int stressChange) {
        stress += stressChange;
    }

    public void decreaseStress(int stressChange) {
        stress -= stressChange;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void increaseHunger(int hungerChange) {
        hunger += hungerChange;
    }

    public void decreaseHunger(int hungerChange) {
        hunger -= hungerChange;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public void increaseSocial(int socialChange) {
        social += socialChange;
    }

    public void decreaseSocial(int socialChange) {
        social -= socialChange;
    }

    public double getEnergy_multiplicator() {
        return energy_multiplicator;
    }

    public void setEnergy_multiplicator(double energy_multiplicator) {
        this.energy_multiplicator = energy_multiplicator;
    }

    public double getStress_multiplicator() {
        return stress_multiplicator;
    }

    public void setStress_multiplicator(double stress_multiplicator) {
        this.stress_multiplicator = stress_multiplicator;
    }

    public double getHunger_multiplicator() {
        return hunger_multiplicator;
    }

    public void setHunger_multiplicator(double hunger_multiplicator) {
        this.hunger_multiplicator = hunger_multiplicator;
    }

    public double getSocial_multiplicator() {
        return social_multiplicator;
    }

    public void setSocial_multiplicator(double social_multiplicator) {
        this.social_multiplicator = social_multiplicator;
    }


    // ---changes ---
    public double getConjugatedMultiplicator(double multiplicator){
        double result = 1/multiplicator;
        return Math.round(result*1e2)/1e2;
    }

}