package com.socialbook.comments.services;

import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.socialbook.comments.entities.Comment;
import com.socialbook.comments.entities.Like;
import com.socialbook.comments.services.configuration.AppProperties;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class LikesBean {

    private Logger logger = Logger.getLogger(LikesBean.class.getName());

    @PersistenceContext(unitName = "comment-service-jpa")
    private EntityManager entityManager;

    @Inject
    AppProperties appProperties;

    public List<Like> getLikes() {
        return entityManager.createNamedQuery("Like.getAll").getResultList();
    }

    public String generateFunFact(String number) {
        Client httpClient = ClientBuilder.newClient();
        return  httpClient
                .target("https://numbersapi.p.rapidapi.com/" + number + "/trivia?fragment=true&notfound=floor&json=true")
                .request()
                .header("X-RapidAPI-Key", "UTPJYeCzZmmshR7bsAyRUYQUdc5Hp11SOBLjsnBOxncKjeEYut")
                .get(String.class);
    }

    public List<Like> getImageLikes(String imageId) {
        List<Like> likes = entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", imageId).getResultList();
        if (likes != null && likes.size() > 0 && appProperties.isExternalApiEnabled()) {
            Like like = likes.get(0);
            String funFact = generateFunFact(like.getLikeAmount());
            JSONObject jsonObject = new JSONObject(funFact);
            String fact = jsonObject.getString("text");
            like.setLikeAmount(like.getLikeAmount() + " | " +fact);
            likes = new ArrayList<>();
            likes.add(like);
            return likes;
        }
        return entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", imageId).getResultList();
    }

    @Transactional
    public void createLike(Like like) {
        logger.info("inserting new like");
        if (like == null) return;
        List<Like> likes = entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", like.getImage_id()).getResultList();
        if (likes != null && likes.size() != 0) {
            Like like1 = likes.get(0);
            like1.setLikeAmount(((Integer.parseInt(like1.getLikeAmount())) + 1) + "");
            entityManager.merge(like1);
            logger.info("inserted like");
        } else {
            like.setLikeAmount("1");
            entityManager.persist(like);
        }
    }

    @Transactional
    public void deleteLike(Like like) {
        logger.info("deleting like");
        List<Like> likes = entityManager.createNamedQuery("Like.getAlbums").setParameter("image_id", like.getImage_id()).getResultList();
        if (likes != null && likes.size() != 0) {
            Like like1 = likes.get(0);
            if (Integer.parseInt(like1.getLikeAmount()) != 0)
                like1.setLikeAmount(((Integer.parseInt(like1.getLikeAmount())) - 1) + "");
            entityManager.merge(like1);
            logger.info("removed like");
        }
    }
}
