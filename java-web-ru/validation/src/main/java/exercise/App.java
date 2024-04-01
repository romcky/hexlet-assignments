package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
        	var page = new BuildArticlePage();
        	ctx.render("articles/build.jte", model("page", page));
        });
        
        app.post("/articles", ctx -> {
        
        	try {
        	String articleName = ctx.formParamAsClass("articleName", String.class)
        		.check(value -> value.length() > 1, 
        			"Название не должно быть короче двух символов")
        		.check(value -> ArticleRepository.existsByTitle(value) == false, 
	        		"Статья с таким названием уже существует")
        		.get();
        	String articleContent = ctx.formParamAsClass("articleContent", String.class)
        		.check(value -> value.length() > 9, "Статья должна быть не короче 10 символов")
        		.get();
        	ArticleRepository.save(new Article(articleName, articleContent));
        	ctx.redirect("/articles");
        	} catch (ValidationException e) {
        		var page = new BuildArticlePage(
        			ctx.formParam("articleName"), ctx.formParam("articleContent"), e.getErrors());
        		ctx.render("articles/build.jte", model("page", page));
        	}
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
