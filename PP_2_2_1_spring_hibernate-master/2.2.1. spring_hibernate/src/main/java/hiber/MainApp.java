package hiber;

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


      User user1 = new User();
      user1.setFirstName("Аня");
      user1.setLastName("Романова");
      user1.setEmail("dgvfg");


      Car car = new Car();
      car.setModel("Honda");
      car.setSeries(123);


      User user2 = new User();
      user2.setFirstName("Коля");
      user2.setLastName("Иванов");
      user2.setEmail("dgvfg");


      Car car2 = new Car();
      car2.setModel("Mazda");
      car2.setSeries(123);

      user1.setCar(car);
      user2.setCar(car2);


      userService.add(user1);
      userService.add(user2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("car = "+user.getCar());
         System.out.println();
      }
      System.out.println("Найдем хозяина Mazda 123");
      System.out.println(userService.getOwner("Mazda", 123));
      context.close();


   }
}
