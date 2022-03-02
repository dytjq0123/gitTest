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

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model){
        String sTitle = board.getTitle();
        String sContent = board.getContent();
        System.out.println("title : " + sTitle);
        System.out.println("content : " + sContent);

        if(!sTitle.isEmpty() && !sContent.isEmpty()){
            model.addAttribute("message", "글 작성이 완료되었습니다.");
            boardSerivce.write(board);
            model.addAttribute("searchUrl", "/board/list");
        }else {
            model.addAttribute("message", "글 작성이 실패하였습니다.");
            model.addAttribute("searchUrl", "/board/write");
        }

//        return "redirect:/board/list";
        return "message";
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

    @PostMapping("/board/update/{id}")
    public String boardupdate(@PathVariable("id") Integer id, Board board, Model model){
        Board boardTemp = boardSerivce.boardView(id);

        String sOriginTitle = boardTemp.getTitle();
        String sOriginContent = boardTemp.getContent();

        String sModTitle = board.getTitle();
        String sModContent = board.getContent();

        if(sOriginTitle.equals(sModTitle) && sOriginContent.equals(sOriginContent)){
            model.addAttribute("message","글 수정이 취소되었습니다.");
            model.addAttribute("searchUrl","/board/modify/"+id);
        }else {
            boardTemp.setTitle(board.getTitle());
            boardTemp.setContent(board.getContent());
            boardSerivce.write(boardTemp);
            model.addAttribute("message","글 수정이 완료되었습니다.");
            model.addAttribute("searchUrl","/board/list");
        }

        return "message";
//        return "redirect:/board/list";
    }
}

