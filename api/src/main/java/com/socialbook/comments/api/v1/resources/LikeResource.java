package com.socialbook.comments.api.v1.resources;

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
    @Path("/albums/{albumId}")
    @CollectRequests
    public Response getAlbumsLikes(@PathParam("albumId") String albumId) {
        List<Like> likes = likesBean.getAlbumLikes(albumId);
        if (likes != null) return Response.ok(likes).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/albums/{albumId}")
    @CollectRequests
    public Response likeAlbum(@PathParam("albumId") String albumId) {
        Like like = new Like();
        like.setAlbumId(albumId);
        like.setLikeAmount(0);
        likesBean.createLike(like);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/albums/{albumId}")
    @CollectRequests
    public Response unlikeAlbum(@PathParam("albumId") String albumId) {
        Like like = new Like();
        like.setAlbumId(albumId);
        likesBean.deleteLike(like);
        return Response.status(Response.Status.CREATED).build();
    }
}