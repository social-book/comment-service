package com.socialbook.comments.entities;

import javax.persistence.*;

@Entity(name = "comment_table")
@NamedQueries(value = {@NamedQuery(name = "Comm.getAll", query = "select c from comment_table c"),
                        @NamedQuery(name = "Comm.getAlbums", query = "select c from comment_table c where c.image_id = :image_id")})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer comment_id;

    @Column(name = "image_id")
    private String image_id;

    @Column(name = "comment_content")
    private String comment_content;

    @Column(name = "user_id")
    private String user_id;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
