package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Entity annotation을 지정하면 JPA가 자동으로 해당 클래스를 읽음
@Data // getter/setter를 작성하지 않아도 Data annotation을 지정하면 사용가능
public class Board {
    @Id // primary key를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 mysql or mariadb, SEQUENCE는 oracle에서 사용, AUTO는 자동 지정
    // MySql Workbench프로그램에서 테이블 생성시 AI(AUTO_INCREMENT)를 체크해야 자동으로 ID가 생성됨
    private Integer id;

    private String title;

    private String content;
}
