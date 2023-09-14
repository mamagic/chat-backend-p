package chat.controller;

import chat.service.ListenMessageService;
import chat.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(path="/",
                //produces="application/json"
                produces="text/plain;charset=UTF-8"
)
@CrossOrigin(origins="http://localhost:4200")
public class OrderApiController {

  private SendMessageService sendMessageService;
  private ListenMessageService listenMessageService;
  private SimpMessagingTemplate messagingTemplate;


  public OrderApiController(SendMessageService sendMessageService,
                            ListenMessageService listenMessageService,
                            SimpMessagingTemplate messagingTemplate) {
    this.sendMessageService = sendMessageService;
    this.listenMessageService = listenMessageService;
    this.messagingTemplate = messagingTemplate;
  }

  @PostMapping(consumes = "text/plain;charset=UTF-8", produces = "text/plain;charset=UTF-8")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> postOrder(@RequestBody String message) {
	  log.info("KAFKA SERVER SENDED MESSAGE:  " + message);
      sendMessageService.sendMessage(message);
      log.info("CLIENT BROADCAST MESSAGE:  " + message);
      messagingTemplate.convertAndSend("/topic/broadcast-message", message);
      return ResponseEntity.ok("send ok");
  }
}
