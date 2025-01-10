package com.ll.nextjs20250110.domain.post.post.dto;

import com.ll.nextjs20250110.domain.post.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostWithContentDto {
    private long id;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private long authorId;

    private String authorName;

    private String title;

    private String content;

    private boolean published;

    private boolean listed;

    public PostWithContentDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.published = post.isPublished();
        this.listed = post.isListed();
    }
}