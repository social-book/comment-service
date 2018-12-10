package com.socialbook.comments.entities;

import javax.persistence.*;

@Entity(name = "like_table")
@NamedQueries(value = {@NamedQuery(name = "Like.getAll", query = "select c from like_table c"),
        @NamedQuery(name = "Like.getAlbums", query = "select c from like_table c where c.image_id = :image_id")})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @Column(name = "image_id")
    private String image_id;

    @Column(name = "like_amount")
    private Integer likeAmount;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
