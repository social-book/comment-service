package com.socialbook.comments.api.v1.resources;

import com.socialbook.comments.entities.Comment;
import com.socialbook.comments.services.CommentsBean;
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
@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    CommentsBean commentsBean;

    @GET
    @CollectRequests
    public Response getAll() {
        List<Comment> comments = commentsBean.getComments();
        if (comments != null) return Response.ok(comments).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/albums/{albumId}")
    @CollectRequests
    public Response getAlbumComments(@PathParam("albumId") String albumId) {
        List<Comment> comments = commentsBean.getAlbumComments(albumId);
        if (comments != null) return Response.ok(comments).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @CollectRequests
    public Response commentAlbum(Comment comment) {
        commentsBean.createComment(comment);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @CollectRequests
    public Response deleteComment(Comment comment) {
        commentsBean.deleteComment(comment);
        return Response.status(Response.Status.GONE).build();
    }
}
