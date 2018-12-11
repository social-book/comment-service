package com.socialbook.comments.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
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
@CrossOrigin
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
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }

    @GET
    @Path("/{imageId}")
    @CollectRequests
    public Response getAlbumComments(@PathParam("imageId") String imageId) {
        List<Comment> comments = commentsBean.getImageComments(imageId);
        if (comments != null) return Response.ok(comments).build();
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }

    @POST
    @CollectRequests
    public Response commentAlbum(Comment comment) {
        commentsBean.createComment(comment);
        return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }

    @DELETE
    @CollectRequests
    public Response deleteComment(Comment comment) {
        commentsBean.deleteComment(comment);
        return Response.status(Response.Status.GONE).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }
}
