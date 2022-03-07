package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> { // Integer는 Board table의 primary key

    // findBy(컬럼명)Containing 컬럼에서 키워드가 포함된 것을 찾음
    // findBy(컬럼명) 컬럼에서 해당 입력된 키워드와 같은 키워드를 가진 데이터를 찾음
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

}
