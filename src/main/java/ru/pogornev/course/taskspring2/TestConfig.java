package ru.pogornev.course.taskspring2;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@TestConfiguration
@Profile("test")
public class TestConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        // Заглушка для источника данных
        return null;//new MockDataSource();
    }
}
