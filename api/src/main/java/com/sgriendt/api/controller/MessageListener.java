package com.sgriendt.api.controller;


import com.sgriendt.api.Config.MQConfig;
import com.sgriendt.api.dto.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageListener {

    @Autowired
    SimpMessagingTemplate template;
//
//    @PostMapping("/send")
//    public ResponseEntity<Void> sendMessage(@RequestBody Message textMessageDTO) {
//        template.convertAndSend("/topic/message", textMessageDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @MessageMapping("/sendMessage")
//    public void receiveMessage(@Payload Message textMessageDTO) {
//        // receive message from client
//    }

    @RabbitListener(queues = MQConfig.QUEUE)
    @SendTo("/ws-message")
    public void listener(Message message) {
        System.out.println(message.getMessage());
        template.convertAndSend("/topic/message", message.getMessage());
    }
}
