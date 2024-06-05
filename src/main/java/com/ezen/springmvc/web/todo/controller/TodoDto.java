package com.ezen.springmvc.web.todo.controller;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TodoDto {
    private int id;
    private String title;
    private boolean complete;
}
