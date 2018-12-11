package com.socialbook.comments.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.socialbook.comments.entities.Like;
import com.socialbook.comments.services.LikesBean;
import com.socialbook.comments.services.interceptors.CollectRequests;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/likes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin
public class LikeResource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    LikesBean likesBean;

    @GET
    @CollectRequests
    public Response getAll() {
        List<Like> likes = likesBean.getLikes();
        if (likes != null) return Response.ok(likes).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{imageId}")
    @CollectRequests
    public Response getAlbumsLikes(@PathParam("imageId") String imageId) {
        List<Like> likes = likesBean.getImageLikes(imageId);
        if (likes != null) return Response.ok(likes).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{imageId}")
    @CollectRequests
    public Response likeAlbum(@PathParam("imageId") String imageId) {
        Like like = new Like();
        like.setImage_id(imageId);
        like.setLikeAmount(1);
        likesBean.createLike(like);
        return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }

    @DELETE
    @Path("/{imageId}")
    @CollectRequests
    public Response unlikeAlbum(@PathParam("imageId") String imageId) {
        Like like = new Like();
        like.setImage_id(imageId);
        likesBean.deleteLike(like);
        return Response.status(Response.Status.CREATED).build();
    }
}