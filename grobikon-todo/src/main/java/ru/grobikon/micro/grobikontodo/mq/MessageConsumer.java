package ru.grobikon.micro.grobikontodo.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.grobikon.micro.grobikontodo.service.TestDataService;

@Component
@EnableBinding(TodoBinding.class)
public class MessageConsumer {

    private TestDataService testDataService;

    private MessageConsumer(TestDataService testDataService){
        this.testDataService = testDataService;
    }

    // метод вызывается автоматически как только появляется сообщение в канале
    @StreamListener(target = TodoBinding.INPUT_CHANNEL)
    public void initTestData(Long userId) throws Exception {
        //throw new Exception("test dlq");
        testDataService.initTestData(userId);
    }
}

