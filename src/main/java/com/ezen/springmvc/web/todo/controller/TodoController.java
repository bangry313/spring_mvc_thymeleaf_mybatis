package com.ezen.springmvc.web.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    @GetMapping
    public List<TodoDto> todoListAction() {
        log.info("일정 요청");
        List<TodoDto> list = new ArrayList<>();
        list.add(TodoDto.builder().id(1).title("리액트 기초 익히기").complete(true).build());
        list.add(TodoDto.builder().id(2).title("리액트 컴포넌트 스타일링하기").complete(true).build());
        list.add(TodoDto.builder().id(3).title("리액트 일정 관리 애플리케이션 구현").complete(false).build());
        return list;
    }
}





