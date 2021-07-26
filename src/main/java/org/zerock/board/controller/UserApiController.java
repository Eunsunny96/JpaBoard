package org.zerock.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zerock.board.model.Board;
import org.zerock.board.model.User;
import org.zerock.board.repository.UserRepository;


import java.util.List;

@RestController
@RequestMapping("/api")
    public class UserApiController {

        @Autowired
        private UserRepository repository;


        @GetMapping("/users")
        List<User> all( ) {
            return repository.findAll();

            }

        // end::get-aggregate-root[]

        @PostMapping("/users")
        User newUser(@RequestBody User newUser) {
            return repository.save(newUser);
        }

        // Single item

        @GetMapping("/users/{id}")
        User one(@PathVariable Long id) {

            return repository.findById(id).orElse(null);

        }

        @PutMapping("/users/{id}")
        User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

            return repository.findById(id)
                    .map(user -> {
                      //  User.setTitle(newUser.getTitle());
                      //  User.setContent(newUser.getContent());
                       //     user.setBoards(newUser.getBoards());
                        user.getBoards().clear();
                        user.getBoards().addAll(newUser.getBoards());
                            for(Board board : user.getBoards()){
                                board.setUser(user);
                            }
                        return repository.save(user);
                    })
                    .orElseGet(() -> {
                        newUser.setId(id);
                        return repository.save(newUser);
                    });
        }

        @DeleteMapping("/users/{id}")
        void deleteUser(@PathVariable Long id) {
            repository.deleteById(id);
        }
    }
