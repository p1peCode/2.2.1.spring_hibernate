package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
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

   @Override
   public void getUserByCarModelAndSeries(String model, int series) {
      List<User> users = sessionFactory.getCurrentSession()
              .createQuery("select user from User user JOIN user.car car where car.model = :model and car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series).getResultList();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First name " + user.getFirstName());
         System.out.println("Last name " + user.getLastName());
         System.out.println("Email " + user.getEmail());
         System.out.println("Car " + user.getCar());
      }
   }
}
