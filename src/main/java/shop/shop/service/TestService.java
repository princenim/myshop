package shop.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author hazel
 */
@Service
@RequiredArgsConstructor
public class TestService {


    //@Async()
    //내가 설정한 쓰레드 풀로 교체
    @Async("threadPoolTaskExecutor")
    public void testAsync() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        System.out.println("Finish");

    }
}
