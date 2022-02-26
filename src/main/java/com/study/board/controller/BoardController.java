package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardSerivce boardSerivce;

    @GetMapping("/") // 주소창에 입력되는 경로
    @ResponseBody // 문자열 자체를 화면에 띄움
    public String main(){

        return "hello world";
    }

    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm(){

        return "boardWrite";
    }

//    @PostMapping("/board/writepro")
//    public String boardWritePro(String title, String content){
//        System.out.println("title : " + title);
//        System.out.println("content : " + content);
//
//        return "";
//    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){
        System.out.println("title : " + board.getTitle());
        System.out.println("content : " + board.getContent());

        boardSerivce.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        List<Board> boardList = boardSerivce.boardList();
        model.addAttribute("list", boardList);
        return "boardList";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1
    public String boardView(Model model, Integer id){
        Board board = boardSerivce.boardView(id);
        model.addAttribute("board", board);
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardSerivce.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        Board board = boardSerivce.boardView(id);
        model.addAttribute("board", board);
        return "boardModify";
    }
}

