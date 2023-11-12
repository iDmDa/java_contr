import java.io.*;
import java.util.*;

public class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    private static Integer playCount = 0;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(int id, String name, int quantity, double weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    public void updateWeight(int toyId, double newWeight) {
        Toy toy = findToyById(toyId);
        if (toy != null) {
            toy.setWeight(newWeight);
        }
    }

    public static Integer getPlayCount() {
        return playCount;
    }

    public void play() throws nullItem {
        playCount++;
        prizeToys.clear();
        for (Toy item : toys) {
            if(item.getQuantity() > 0) {
                Toy forPrizeToyList = new Toy(item.getId(), item.getName(), 1, item.getWeight());
                prizeToys.add(forPrizeToyList);
            }
        }

        if(prizeToys.size() == 0) {
            throw new nullItem("Все призы закончились!");
        }

        Random random = new Random();
        double randomValue = random.nextDouble() * prizeToys.size();

        int count = 0;
        for (Toy item : prizeToys) {
            count++;
            if(count == (int) randomValue + 1) {
                prizeToys.clear();

                Random prizeRandom = new Random();
                double value = prizeRandom.nextDouble()*100;
                if(value <= item.getWeight()) {
                    prizeToys.add(item);
                    findToyById(item.getId()).decreaseQuantity();
                }
                break;
            }
        }
    }

    public Toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            Toy prizeToy = prizeToys.remove(0);
            writeToFile(prizeToy);
            return prizeToy;
        }
        return null;
    }

    private Toy findToyById(int id) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    private void writeToFile(Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt", true))) {
            writer.write("Розыгрыш № " + getPlayCount() + " id: " + toy.getId() + ", Name: " + toy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printToys() {
        System.out.println("—————————————————————————————————");
        System.out.println("Всего призов: ");
        for (Toy toy : toys) {
            System.out.println("*" + toy);
        }
        System.out.println("—————————————————————————————————");
    }
}

class nullItem extends Exception {
    public nullItem(String message) {
        super(message);
    }
}
