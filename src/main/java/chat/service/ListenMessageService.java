package chat.service;

import java.util.List;

public interface ListenMessageService {
    void listenMessage(String message);
    List<String> getListMessage();
}
