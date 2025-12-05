import java.util.*;

class User {
    String name;
    String login;
    String password;
    List<Booking> bookings = new ArrayList<>();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}

class Room {
    int id;
    String name;
    int capacity;
    boolean isBooked;

    public Room(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.isBooked = false;
    }

    public String toString() {
        return id + ". " + name + " (мест: " + capacity + ") " + (isBooked ? "[Занято]" : "[Свободно]");
    }
}

class Booking {
    User user;
    Room room;
    String date;
    String status;

    public Booking(User user, Room room, String date) {
        this.user = user;
        this.room = room;
        this.date = date;
        this.status = "Ожидает подтверждения";
    }

    public String toString() {
        return "Зал: " + room.name + ", Дата: " + date + ", Статус: " + status;
    }
}

class Database {
    static List<User> users = new ArrayList<>();
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
}
public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Database.rooms.add(new Room(1, "Конференц-зал", 30));
        Database.rooms.add(new Room(2, "Кабинет 101", 10));
        Database.rooms.add(new Room(3, "Аудитория 202", 50));
        System.out.println("Добро пожаловать в систему бронирования залов!");
        while (true) {
            System.out.println("1. Войти\n2. Зарегистрироваться\n0. Выход");
            System.out.print("Выберите действие: ");
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 1) login();
            else if (choice == 2) register();
            else if (choice == 0) break;
        }
    }
    static void register() {
        System.out.print("Введите имя: ");
        String name = in.nextLine();
        System.out.print("Введите логин: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();
        Database.users.add(new User(name, login, password));
        System.out.println("Регистрация прошла успешно!");
    }
    static void login() {
        System.out.print("Логин: ");
        String login = in.nextLine();
        System.out.print("Пароль: ");
        String password = in.nextLine();

        for (User u : Database.users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                System.out.println("Добро пожаловать, " + u.name + "!");
                userMenu(u);
                return;
            }
        }
        System.out.println("Неверный логин или пароль!");
    }
    static void userMenu(User u) {
        while (true) {
            System.out.println("\n1. Просмотреть залы\n2. Забронировать зал\n3. Мои бронирования\n0. Выход");
            System.out.print("Выберите действие: ");
            int c = in.nextInt();
            in.nextLine();
            if (c == 1) {
                for (Room r : Database.rooms)
                    System.out.println(r);
            } else if (c == 2) {
                for (Room r : Database.rooms)
                    System.out.println(r);
                System.out.print("Введите ID зала для бронирования: ");
                int id = in.nextInt();
                in.nextLine();
                Room room = Database.rooms.get(id - 1);
                if (room.isBooked) {
                    System.out.println("Зал уже забронирован!");
                } else {
                    System.out.print("Введите дату (например 10.11.2025): ");
                    String date = in.nextLine();
                    Booking b = new Booking(u, room, date);
                    Database.bookings.add(b);
                    u.bookings.add(b);
                    room.isBooked = true;
                    System.out.println("Бронирование создано!");
                }
            } else if (c == 3) {
                if (u.bookings.isEmpty()) System.out.println("Нет бронирований");
                else for (Booking b : u.bookings) System.out.println(b);
            } else if (c == 0) break;
        }
    }
}
