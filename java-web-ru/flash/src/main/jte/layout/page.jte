<!-- BEGIN -->
@import exercise.util.NamedRoutes
@import exercise.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Flash Exercise</title>
    </head>
    <body>
        @if (page != null && page.getFlash() != null)
            <div>
                ${page.getFlash()}
            </div>
        @endif
        <div>
            ${content}
        </div>
    </body>
</html>
<!-- END -->
