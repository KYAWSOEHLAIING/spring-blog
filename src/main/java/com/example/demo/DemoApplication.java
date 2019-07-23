package com.example.demo;

import com.example.demo.model.Author;
import com.example.demo.model.Gender;
import com.example.demo.model.Post;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean @Profile("dev")
    public CommandLineRunner runner(AuthorRepository authorRepository, PostRepository postRepository){
        return args -> {
//            logger.info("spring sample log");
//            logger.warn("spring sample log");
//            logger.trace("spring sample log");
//            logger.error("spring sample log");
//            logger.debug("spring sample log");
//            logger.info("Testing");
            Author author1 = new Author("Kyaw",LocalDate.of(1994,7,4),"Swimming", Gender.Male);

            Post post1 = new Post("How To Swim","Swimming is fit to Body",LocalDate.now());
            Post post2 = new Post("How To Swim","Swimming is fitting ",LocalDate.now());

            post1.setAuthor(author1);
            post2.setAuthor(author1);

            authorRepository.save(author1);
            postRepository.save(post1);
            postRepository.save(post2);

            logger.info("Success");

            };
    }

}
