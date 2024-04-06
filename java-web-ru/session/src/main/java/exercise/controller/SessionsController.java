package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import com.mifmif.common.regex.Main;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        LoginPage page = new LoginPage("", null);
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        String name = ctx.formParam("name");
        String password = ctx.formParam("password");
        User user = UsersRepository.findByName(name);
        if (user != null && encrypt(password).equals(user.getPassword())) {
            ctx.sessionAttribute("userName", user.getName());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("userName", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void root(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("userName"));
        ctx.render("index.jte", model("page", page));
    }

    // END
}
