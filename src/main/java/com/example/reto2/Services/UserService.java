package com.example.reto2.Services;

import java.util.List;
import java.util.Optional;
import com.example.reto2.CrudRepository.UserRepository;
import com.example.reto2.Models.Documents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author User
 */
@Service
public class UserService {

    /**
    * 
    * objeto tipo repositorio para crud
    */
    @Autowired
    private UserRepository userRepo;

    /**
     * 
     * @return regresa todos los valores de userRepo
     */
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    
    /**
     * 
     * @param monthBirthDay recibe una cadena de texto para monthBirthday
     * @return regresa un usuario con ese calor de monthBirthday
     */
    public List<User> findUsersByBirthday(String monthBirthDay) {
        return userRepo.findByMonthBirthtDay(monthBirthDay);
    }

    /**
     * 
     * @param user recibe un objeto de tipo User
     * @return regresa los nuevos valores del usuari a crear
     */
    public User save(User user) {
        if (user.getId() != null) {
            return userRepo.save(user);
        } else {
            Optional<User> elemento = userRepo.findById(user.getId());
            if (elemento == null) {
                return userRepo.save(user);
            } else {
                return user;
            }

        }
    }

    /**
     * 
     * @param userEmail recibe una cadena de texto para userEmail
     * @return regresa falso si el email no existe, verdadero si el email existe
     */
    public boolean getValidationEmail(String userEmail) {

        return userRepo.getEmail(userEmail).isPresent();
    
    }

    /**
     * 
     * @param userEmail recibe una cadena de texto para email
     * @param userPassword recibe una cadena de texto para password
     * @return regresa el usuario del Optional respuesta si  coinciden userEmail y userPassword
     */
    public User getValidationCredentials(String userEmail, String userPassword) {

        User respuestaCred;
        Optional<User> respuesta = userRepo.getEmailAndPassword(userEmail, userPassword);

        if (!respuesta.isPresent()) {
            respuestaCred = new User(null, null, null, null, null, null, null, null, null, null, null);

        } else {
            respuestaCred = respuesta.get();
        }

        return respuestaCred;
    }

    

    /**
     * 
     * @param userId recibe un valor entero para userId
     * @return regresa verdadero o falso
     */
    public boolean deleteUser(Integer userId) {
        return getUser(userId).map( usuario -> {
            userRepo.delete(usuario);
            return true;
        }).orElse(false);
        
    }
    
    /**
     * 
     * @param userid recibe un valor entero para userId
     * @return regresa un usuario con esa respectiva id
     */
    public Optional<User> getUser(Integer userid) {
        return userRepo.findById(userid);
    }

    /**
     * 
     * @return regresa la zona
     */
    List<User> getUserZone() {
        return userRepo.findAll();
    }

}
