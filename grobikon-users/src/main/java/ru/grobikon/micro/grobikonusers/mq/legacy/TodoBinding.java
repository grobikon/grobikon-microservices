package ru.grobikon.micro.grobikonusers.mq.legacy;

// интерфейс, который нужен для работы mq
// создаем нужные нам каналы
public interface TodoBinding {

//    String OUTPUT_CHANNEL = "todoOutputChannel"; // нужен, чтобы на него ссылались, а не везде использовать антипаттерн magic string
//
//    //создает канал для отправки сообщений
//    @Output(OUTPUT_CHANNEL)
//    MessageChannel todoOutputChannel(); //название канала может браться из названия метода (если не указано в аннотации)
}
