package groomingSalon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GroomingSalon {
    private int capacity;
    private final List<Pet> pets;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.pets = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (pets.size() < capacity) {
            pets.add(pet);
        }
    }

    public boolean remove(String name) {
        Pet pet = getPet(x -> x.getName().equals(name));
        if (pet == null) {
            return false;
        }

        pets.remove(pet);
        return true;
    }

    public Pet getPet(String name, String owner){
        return this.getPet(x->x.getName().equals(name) && x.getOwner().equals(owner));
    }

    public int getCount() {
        return pets.size();
    }

    private Pet getPet(Predicate<Pet> predicate) {
        return this.pets
                .stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }

    public String getStatistics(){
        return "The grooming salon has the following clients:" + (this.pets.isEmpty()?"":
                System.lineSeparator() +
                this.pets.stream().map(p-> p.getName() + " " + p.getOwner())
                        .collect(Collectors.joining(System.lineSeparator())));
    }
}
