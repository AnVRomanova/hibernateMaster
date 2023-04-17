package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public void deleteAllUsers(){
      try (Session session = sessionFactory.openSession()) {
         Transaction transaction = session.beginTransaction();

         String sql = "TRUNCATE TABLE users";


         NativeQuery query = session.createNativeQuery(sql,User.class);


         query.executeUpdate();

         transaction.commit();
         session.close();
      }
   }

   public List<User> getOwner(String model, int series){

      String hql = "SELECT user FROM User as user WHERE car.model = :model and car.series = :series";
      try (Session session = sessionFactory.openSession()) {
         Transaction transaction = session.beginTransaction();
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql)
                 .setParameter("model", model)
                 .setParameter("series", series);





         return query.getResultList();
      }



   }



}
