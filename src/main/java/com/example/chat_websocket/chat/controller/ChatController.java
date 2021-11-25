package com.example.chat_websocket.chat.controller;

import com.example.chat_websocket.chat.repository.ChattingRoom;
import com.example.chat_websocket.chat.repository.ChattingRoomForm;
import com.example.chat_websocket.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

//    @GetMapping("/")
//    public String chat(){
//        return "chat";
//    }

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/")
    public String rooms(Model model){
        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
        return "chattingrooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model){
        ChattingRoom room = chatRoomRepository.findRoomById(id);
        model.addAttribute("room",room);
        return "chatting";
    }

    @GetMapping("/new")
    public String make(Model model){
        ChattingRoomForm form = new ChattingRoomForm();
        model.addAttribute("form",form);
        return "createroom";
    }

    @PostMapping("/room/new")
    public String makeRoom(ChattingRoomForm form){
        chatRoomRepository.createChatRoom(form.getName());

        return "redirect:/";
    }
}
