package who.gracelove.demospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import who.gracelove.demospringboot.account.Account;
import who.gracelove.demospringboot.account.AccountRepository;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("eunmo", "grace");
        values.set("springboot", "2.2");

        Account account = new Account();
        account.setUsername("grace");
        account.setEmail("govlmo91@gmail.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println("username = " + byId.get().getUsername());
        System.out.println("email = " + byId.get().getEmail());
    }
}
