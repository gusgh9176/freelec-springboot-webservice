package com.mhh.book.springboot.web;

import com.mhh.book.springboot.config.auth.LoginUser;
import com.mhh.book.springboot.config.auth.dto.SessionUser;
import com.mhh.book.springboot.service.posts.PostsService;
import com.mhh.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("author", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        // 현재 로그인한 유저의 이름과 게시글 작성자가 같다면
        if(user.getName().equals(dto.getAuthor())){
            model.addAttribute("writer", "write");
        }

        return "posts-update";
    }

}
