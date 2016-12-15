package backend;

import backend.model.User;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        List<User> e = userRepository.findByLogin("admin");
        if (e.isEmpty()) {
            addAdmin();
        }
    }

    private void addAdmin() {
        User admin = new User();
        admin.setFirstName("");
        admin.setLastName("");
        admin.setEmail("");
        admin.setToken("f4f673cf-08d2-4e60-ad7c-5509d73a5688");
        admin.setType("admin");
        admin.setTelephone("");
        admin.setLogin("admin");
        admin.setPassword("c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec");
        userRepository.save(admin);
    }
}
