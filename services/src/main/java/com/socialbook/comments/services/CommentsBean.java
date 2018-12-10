package com.socialbook.comments.services;

import com.socialbook.comments.entities.Comment;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class CommentsBean {

    private Logger logger = Logger.getLogger(CommentsBean.class.getName());

    @PersistenceContext(unitName = "comment-service-jpa")
    private EntityManager entityManager;

    public List<Comment> getComments() {
        return entityManager.createNamedQuery("Comm.getAll").getResultList();
    }

    public List<Comment> getImageComments(String imageId) {
        return entityManager.createNamedQuery("Comm.getAlbums").setParameter("image_id", imageId).getResultList();
    }

    @Transactional
    public void createComment(Comment comment) {
        logger.info("inserting new comment");
        if (comment != null){
            entityManager.persist(comment);
            logger.info("inserted comment");
        }
    }

    @Transactional
    public void deleteComment(Comment comment) {
        logger.info("deleting comment");
        if (comment != null){
            entityManager.remove(comment);
            logger.info("comment deleted");
        }
    }
}
