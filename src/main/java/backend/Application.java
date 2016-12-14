package backend;

import backend.model.User;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
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
        admin.setPassword("C7AD44CBAD762A5DA0A452F9E854FDC1E0E7A52A38015F23F3EAB1D80B931DD472634DFAC71CD34EBC35D16AB7FB8A90C81F975113D6C7538DC69DD8DE9077EC");
        userRepository.save(admin);
    }
}
