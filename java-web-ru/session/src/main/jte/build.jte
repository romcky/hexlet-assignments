@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(
content = @`
    <main>
        <form method="post" action="${NamedRoutes.loginPath()}">
            <div>
                <label> Имя:
                    <input type="text" name="name" value="${page.getName()}" />
                </label>
            </div>
            <div><label> Пароль:
                    <input type="password" name="password" />
                 </label>
            </div>
            <div>
                <input type="submit" value="Войти" />
            </div>
        </form>

        @if (page.getError() != null)
            <div>
                ${page.getError()}
            </div>
        @endif
`
)