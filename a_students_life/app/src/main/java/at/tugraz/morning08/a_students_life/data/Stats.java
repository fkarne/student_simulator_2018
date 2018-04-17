package at.tugraz.morning08.a_students_life.data;

public class Stats {
    private static int MAX = 100;

    private int energy = MAX;
    private int stress = MAX;
    private int hunger = MAX;
    private int social = MAX;

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
}
