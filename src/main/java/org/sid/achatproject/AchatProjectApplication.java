package org.sid.achatproject;

import org.sid.achatproject.Repository.ArticleRepository;
import org.sid.achatproject.entites.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AchatProjectApplication implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(AchatProjectApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContexte springApplicationContexte() {
        return new SpringApplicationContexte();
    }

    @Override
    public void run(String... args) throws Exception {

        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"A","lakhlifi"));
        articleRepository.save(new Article(null,"E","lakhlifi"));
        articleRepository.save(new Article(null,"D","lakhlifi"));
        articleRepository.save(new Article(null,"F","lakhlifi"));
        articleRepository.save(new Article(null,"G","lakhlifi"));
        articleRepository.save(new Article(null,"G","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"X","lakhlifi"));
        articleRepository.save(new Article(null,"C","lakhlifi"));
        articleRepository.save(new Article(null,"V","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"G","lakhlifi"));
        articleRepository.save(new Article(null,"B","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"V","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));
        articleRepository.save(new Article(null,"mehdi","lakhlifi"));

    }
}
