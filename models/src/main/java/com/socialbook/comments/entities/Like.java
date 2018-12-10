package com.socialbook.comments.entities;

import javax.persistence.*;

@Entity(name = "like_table")
@NamedQueries(value = {@NamedQuery(name = "Like.getAll", query = "select c from like_table c"),
        @NamedQuery(name = "Like.getAlbums", query = "select c from like_table c where c.albumId = :album_id")})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @Column(name = "album_id")
    private String albumId;

    @Column(name = "like_amount")
    private Integer likeAmount;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }
}
