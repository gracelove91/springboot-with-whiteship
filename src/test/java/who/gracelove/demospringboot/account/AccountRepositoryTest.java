package who.gracelove.demospringboot.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void di() {
        Account account = new Account();
        account.setUsername("grace");
        account.setPassword("1234");

        Account newAccount = accountRepository.save(account);
        assertNotNull(newAccount.getId());

        Account savedAccount = accountRepository.findByUsername("grace");
        assertNotNull(savedAccount);
    }
}