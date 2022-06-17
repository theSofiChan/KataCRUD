package sof.dao;
import org.springframework.stereotype.Repository;
import sof.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDao {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("sof");

    @PersistenceContext
    private EntityManager entityManager;

    public UserDao() {

    }

    public void save(User user) {
        //entityManager.getTransaction().begin();
        entityManager.persist(user);
        //entityManager.getTransaction().commit();
    }

    public User show(Long id) {
        User user = entityManager.getReference(User.class, new Long(id));
        entityManager.detach(user);
        return user;
    }

    public void update(Long id, User updateduser){

        User userToBeUpdated=show(id);
        entityManager.detach(userToBeUpdated);
        userToBeUpdated.setAge(updateduser.getAge());
        userToBeUpdated.setName(updateduser.getName());
        userToBeUpdated.setCountry(updateduser.getCountry());

        //entityManager.getTransaction().begin();
        entityManager.merge(userToBeUpdated);
        //entityManager.getTransaction().commit();
    }

    public void delete(Long id){

        //entityManager.getTransaction().begin();
        User user=show(id);
        entityManager.remove(user);
        //entityManager.getTransaction().commit();

    }

    public List<User> index(){

        TypedQuery<User> q =
                entityManager.createQuery("select u from User u",User.class);

        List<User> listOfSUsers = q.getResultList();

        return listOfSUsers;
    }


}
