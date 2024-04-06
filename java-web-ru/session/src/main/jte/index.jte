@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    @if (page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <h3>${page.getName()}</h3>
        <form method="post" action="${NamedRoutes.logoutPath()}">
            <input type="submit" value="Выйти" />
        </form>

    @endif

`
)