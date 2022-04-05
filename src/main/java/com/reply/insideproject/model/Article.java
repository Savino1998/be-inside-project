package com.reply.insideproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "body", nullable = false)
    private String body;

    public Article(Category category, String name, String body) {
        this.category = category;
        this.name = name;
        this.body = body;
    }

}
