package com.mhh.book.springboot.web;

import com.mhh.book.springboot.service.posts.PostsService;
import com.mhh.book.springboot.web.dto.PostsResponseDto;
import com.mhh.book.springboot.web.dto.PostsSaveRequestDto;
import com.mhh.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final String postsPath = "/api/v1/posts";

    @PostMapping(postsPath)
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping(postsPath + "/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping(postsPath + "/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping(postsPath+"/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
