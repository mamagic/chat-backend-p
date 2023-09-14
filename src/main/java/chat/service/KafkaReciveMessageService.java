package chat.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaReciveMessageService implements ListenMessageService {

    private List<String> listenMessages= new ArrayList<String>();
    @Override
    @KafkaListener(topics = "chat", groupId = "chat-group")
    public void listenMessage(String message) {
        listenMessages.add(message);
        System.out.println(message);
    }

    @Override
    public List<String> getListMessage(){
        return this.listenMessages;
    }
}
