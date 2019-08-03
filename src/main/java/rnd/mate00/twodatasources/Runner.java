package rnd.mate00.twodatasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rnd.mate00.twodatasources.repo1.BookRepository;
import rnd.mate00.twodatasources.repo2.RoleRepository;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running application");

        System.out.println(bookRepository.count());
        bookRepository.findAll().forEach(b -> System.out.println(b.getTitle()));

        System.out.println(roleRepository.count());
        roleRepository.findAll().forEach(r -> System.out.println(r.getRoleName()));
    }
}
