package at.tugraz.morning08.a_students_life.data;

import java.math.BigDecimal;


public class Stats {
    private static int MAX = 100;

    private int energy = MAX;
    private int stress = MAX;
    private int hunger = MAX;
    private int social = MAX;

    // [0.5 ; 2.0]
    private float energy_multiplicator = 1;
    private float stress_multiplicator = 1;
    private float hunger_multiplicator = 1;
    private float social_multiplicator = 1;

    public void initializeStudent(){
        energy = MAX;
        stress = MAX;
        hunger = MAX;
        social = MAX;
        Student.getInstance().setEcts(0);
        Student.getInstance().setCash(0);
        Student.getInstance().getTime().setDay(1);
        Student.getInstance().getTime().setTimeUnit(16);

        energy_multiplicator = 1;
        stress_multiplicator = 1;
        hunger_multiplicator = 1;
        social_multiplicator = 1;
    }

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

    public float getEnergy_multiplicator() {
        return energy_multiplicator;
    }

    public void setEnergy_multiplicator(float energy_multiplicator) {
        this.energy_multiplicator = energy_multiplicator;
    }

    public float getStress_multiplicator() {
        return stress_multiplicator;
    }

    public void setStress_multiplicator(float stress_multiplicator) {
        this.stress_multiplicator = stress_multiplicator;
    }

    public float getHunger_multiplicator() {
        return hunger_multiplicator;
    }

    public void setHunger_multiplicator(float hunger_multiplicator) {
        this.hunger_multiplicator = hunger_multiplicator;
    }

    public float getSocial_multiplicator() {
        return social_multiplicator;
    }

    public void setSocial_multiplicator(float social_multiplicator) {
        this.social_multiplicator = social_multiplicator;
    }


    public float getConjugatedMultiplicator(float multiplicator){
        float result = 1/multiplicator;
        result = result * 100;
        return ((float) ( (int) ((result - (int) result) >= 0.5f ? result + 1 : result))) / 100;
    }

}