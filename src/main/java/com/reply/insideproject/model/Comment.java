package com.reply.insideproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "articleId")
    private Article article;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "date")
    private Date date;

    public Comment(Article article, String nickname, String body) {
        this.article = article;
        this.nickname = nickname;
        this.body = body;
        date = new Date();
    }

}
