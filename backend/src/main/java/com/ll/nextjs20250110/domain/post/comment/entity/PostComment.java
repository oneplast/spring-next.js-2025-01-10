package com.ll.nextjs20250110.domain.post.comment.entity;

import com.ll.nextjs20250110.domain.member.member.entity.Member;
import com.ll.nextjs20250110.domain.post.post.entity.Post;
import com.ll.nextjs20250110.global.exceptions.ServiceException;
import com.ll.nextjs20250110.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public void modify(String content) {
        this.content = content;
    }

    public void checkActorCanModify(Member actor) {
        if (actor == null) {
            throw new ServiceException("404-1", "로그인 후 이용해주세요.");
        }

        if (actor.equals(author)) {
            return;
        }

        throw new ServiceException("403-2", "작성자만 댓글을 수정할 수 있습니다.");
    }

    public void checkActorCanDelete(Member actor) {
        if (actor == null) {
            throw new ServiceException("404-1", "로그인 후 이용하세요.");
        }

        if (actor.isAdmin()) {
            return;
        }

        if (actor.equals(author)) {
            return;
        }

        throw new ServiceException("403-2", "작성자만 글을 삭제할 수 있습니다.");
    }
}