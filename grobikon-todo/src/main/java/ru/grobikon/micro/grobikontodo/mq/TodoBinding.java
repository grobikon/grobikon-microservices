package ru.grobikon.micro.grobikontodo.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

// интерфейс, который нужен для работы mq
// описывает каналы для работы с message broker
public interface TodoBinding {

    String INPUT_CHANNEL = "todoInputChannel"; // нужен, чтобы на него ссылались, а не везде использовать антипаттерн magic string

    //создает канал для отправки сообщений
    @Input(INPUT_CHANNEL)
    MessageChannel todoInputChannel(); //название канала может браться из названия метода (если не указано в аннотации)
}
