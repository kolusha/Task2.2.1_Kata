package hiber;

//Не забудьте про toString
//Запустил только после:
//        hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
//        и
//        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//8 часов потрачено на
//        factoryBean.setAnnotatedClasses(User.class, Car.class);
//        hibernate.hbm2ddl.auto=create-drop
//По факту там Класс_1.Поле_в_классе_1_по_которому_JoinColumn.Поле_в_классе_2

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("model1", 10);
        Car car2 = new Car("model2", 20);
        Car car3 = new Car("model3", 30);
        Car car4 = new Car("model4", 40);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("model2", 20));

        context.close();
    }
}
