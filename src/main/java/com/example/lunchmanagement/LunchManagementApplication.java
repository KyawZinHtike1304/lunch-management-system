package com.example.lunchmanagement;

import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.dao.MenuDao;
import com.example.lunchmanagement.dao.RoleDao;
import com.example.lunchmanagement.entity.Employee;
import com.example.lunchmanagement.entity.Menu;
import com.example.lunchmanagement.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class LunchManagementApplication {

    private final RoleDao roleDao;
    private final MenuDao menuDao;
    private final EmployeeDao employeeDao;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    @Profile("security")
    public ApplicationRunner runner1(){
        return r -> {
            Role admin = new Role();
            admin.setRoleName("ROLE_ADMIN");

            Role user = new Role();
            user.setRoleName("ROLE_USER");

            roleDao.save(admin);
            roleDao.save(user);

            Employee supervisor = new Employee("root",passwordEncoder.encode("admin"),"Managing Department","manager@gmail.com");
            supervisor.addRole(admin);
            employeeDao.save(supervisor);

        };
    }

    @Transactional
    @Profile("data") // for don't add data
    @Bean
    public ApplicationRunner runner() {
        return r -> {
            Menu menu1 = new Menu("Frid rice",2000,"http://source.unsplash.com/400x300/?friedrice");
            Menu menu2 = new Menu("Noodle",3000,"http://source.unsplash.com/400x300/?noodle");
            Menu menu3 = new Menu("Spaghetti",4000,"http://source.unsplash.com/400x300/?spaghetti");
            Menu menu4 = new Menu("Pizza",5000,"http://source.unsplash.com/400x300/?pizza");
            Menu menu5 = new Menu("Sushi",1000,"http://source.unsplash.com/400x300/?sushi");
            Menu menu6 = new Menu("Hamburger",2000,"http://source.unsplash.com/400x300/?hamburger");

            menuDao.save(menu1);
            menuDao.save(menu2);
            menuDao.save(menu3);
            menuDao.save(menu4);
            menuDao.save(menu5);
            menuDao.save(menu6);

        };
    }

            public static void main(String[] args) {
        SpringApplication.run(LunchManagementApplication.class, args);
    }

}
