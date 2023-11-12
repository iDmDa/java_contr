public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавление игрушек в розыгрыш
        toyStore.addToy(1, "Плюшевый мишка", 10, 20);
        toyStore.addToy(2, "Кукла RAINBOW", 15, 15);
        toyStore.addToy(3, "Конструктор LEGO", 5, 30);
        toyStore.addToy(4, "Железная дорога", 3, 10);
        toyStore.addToy(5, "Кукла Pretty Princess", 10, 10);

        toyStore.printToys();
        // Обновление веса
        toyStore.updateWeight(2, 10);

        // Розыгрыш
        try {
            for(int i = 0; i < 500; i++) {
                toyStore.play();
                System.out.printf("Розыгрыш № " + toyStore.getPlayCount() + " ");;
                Toy prizeToy = toyStore.getPrizeToy();
                if (prizeToy != null) {
                    System.out.println("Поздравляю! Вы выиграли: " + prizeToy.getName());
                } else {
                    System.out.println("Извините, вы ничего не выиграли.");
                }
            }
        } catch (nullItem e) {
            System.out.println(e);
        }

        toyStore.printToys();
    }
}

