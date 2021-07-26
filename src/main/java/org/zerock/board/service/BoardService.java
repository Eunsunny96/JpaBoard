package org.zerock.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.board.model.Board;
import org.zerock.board.model.User;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.UserRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board){
       User user = userRepository.findByUsername(username);
       board.setUser(user);
       return boardRepository.save(board);
    }
}
