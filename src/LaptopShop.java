import java.util.*;

public class LaptopShop {
    private static List<Laptop> laptops = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        laptops.add(new Laptop("Lenovo", "ThinkPad", 8, 256, "Windows 10", "Black", 899.99));
        laptops.add(new Laptop("Apple", "Macbook Air", 8, 256, "macOS", "Silver", 999.99));
        laptops.add(new Laptop("HP", "Pavilion", 4, 128, "Windows 10", "White", 449.99));
        laptops.add(new Laptop("Lenovo", "IdeaPad", 4, 512, "Windows 10", "Black", 549.99));
        laptops.add(new Laptop("Dell", "Inspiron", 16, 512, "Windows 10", "Gray", 899.99));
        laptops.add(new Laptop("HP", "ProBook", 8, 1024, "Windows 10", "Blue", 799.99));
        laptops.add(new Laptop("Asus", "Zenbook", 16, 256, "Windows 10", "Rose Gold", 1099.99));
        laptops.add(new Laptop("Acer", "Inspire", 8, 512, "Windows 10", "Red", 999.99));
        laptops.add(new Laptop("MSI", "Katana", 32, 1024, "Windows 11", "Black", 1299.99));

        Map<String, Object> filterCriteria = new HashMap<>();
        System.out.println("Enter filter criteria: ");
        System.out.println("1 - RAM");
        System.out.println("2 - Storage");
        System.out.println("3 - Operating System");
        System.out.println("4 - Color");
        System.out.println("Separate multiple criteria with a comma (i. e. 1, 2)");

        String input = scanner.next();
        if (!input.matches("[\\d,]+")) {
            System.out.println("Invalid input. Please enter only numbers and commas.");
            return;
        }
        String[] criteriaArray = input.split(",");
        for (String criteria : criteriaArray) {
            switch (criteria.trim()) {
                case "1":
                    System.out.println("Enter minimum RAM (in GB):");
                    if (scanner.hasNextInt()) {
                        int ram = scanner.nextInt();
                        filterCriteria.put("ram", ram);
                    } else {
                        System.out.println("Invalid input. Please enter an integer.");
                        return;
                    }
                    break;
                case "2":
                    System.out.println("Enter minimum storage (in GB):");
                    if (scanner.hasNextInt()) {
                        int storage = scanner.nextInt();
                        filterCriteria.put("storage", storage);
                    } else {
                        System.out.println("Invalid input. Please enter an integer.");
                        return;
                    }
                    break;
                case "3":
                    System.out.println("Enter operating system:");
                    String os = scanner.next();
                    filterCriteria.put("operatingSystem", os);
                    break;
                case "4":
                    System.out.println("Enter color:");
                    String color = scanner.next();
                    filterCriteria.put("color", color);
                    break;
                default:
                    System.out.println("Invalid input. Please enter valid criteria numbers.");
                    return;
            }
        }

        List<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);
        if (filteredLaptops.size() > 0) {
            System.out.println("Matching Laptops:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        } else {
            System.out.println("No laptops match given criteria.");
        }
    }

    private static List<Laptop> filterLaptops(List<Laptop> laptops, Map<String, Object> filterCriteria) {
        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean matchesCriteria = true;
            for (String filterKey : filterCriteria.keySet()) {
                switch (filterKey) {
                    case "ram":
                        if (laptop.getRamSize() < (int) filterCriteria.get(filterKey)) {
                            matchesCriteria = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getHddSize() < (int) filterCriteria.get(filterKey)) {
                            matchesCriteria = false;
                        }
                        break;
                    case "operatingSystem":
                        if (!laptop.getOs().equals(filterCriteria.get(filterKey))) {
                            matchesCriteria = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equals(filterCriteria.get(filterKey))) {
                            matchesCriteria = false;
                        }
                        break;
                }
                if (!matchesCriteria) {
                    break;
                }
            }
            if (matchesCriteria) {
                filteredLaptops.add(laptop);
            }
        }
        return filteredLaptops;
    }
}