package com.socialbook.comments.services;

import com.socialbook.comments.entities.Comment;
import com.socialbook.comments.entities.Like;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class LikesBean {

    private Logger logger = Logger.getLogger(LikesBean.class.getName());

    @PersistenceContext(unitName = "comment-service-jpa")
    private EntityManager entityManager;


    public List<Like> getLikes() {
        return entityManager.createNamedQuery("Like.getAll").getResultList();
    }

    public List<Like> getImageLikes(String imageId) {
        return entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", imageId).getResultList();
    }

    @Transactional
    public void createLike(Like like) {
        logger.info("inserting new like");
        if (like == null) return;
        List<Like> likes = entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", like.getImage_id()).getResultList();
        if (likes != null && likes.size() != 0) {
            Like like1 = likes.get(0);
            like1.setLikeAmount((like1.getLikeAmount()) + 1);
            entityManager.merge(like1);
            logger.info("inserted like");
        } else {
            like.setLikeAmount(1);
            entityManager.persist(like);
        }
    }

    @Transactional
    public void deleteLike(Like like) {
        logger.info("deleting like");
        List<Like> likes = entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", like.getImage_id()).getResultList();
        if (likes != null && likes.size() != 0) {
            Like like1 = likes.get(0);
            if (like1.getLikeAmount() != 0)
                like1.setLikeAmount((like1.getLikeAmount()) - 1);
            entityManager.merge(like1);
            logger.info("removed like");
        }
    }
}
