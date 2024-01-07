package info.nguyenanhdung.spingapi.demo.database;

import info.nguyenanhdung.spingapi.demo.repositories.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner intDatabase(IProductRepository IProductRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
            }
        };
    }
}
