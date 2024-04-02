package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import java.util.List;
import java.util.ArrayList;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {

        var allPosts = PostRepository.getEntities();
        int currPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var currPosts = new ArrayList<Post>();

        for (int i = (currPage-1) * 5; i < currPage * 5; i++) {
            if (i >= 0 && i < allPosts.size()) {
                currPosts.add(allPosts.get(i));
            }
        }

        var page = new PostsPage(currPosts, currPage);
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id).orElseThrow(
                () -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
    // END
}
