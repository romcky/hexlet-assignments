@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`

    @if (page.getPosts().isEmpty())
        <p>No posts on page ${page.getPage()}</p>
    @else
        <ul>
            @for (var post : page.getPosts())
                <li>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </li>
            @endfor
        </ul>
    @endif

    <div>
        <a href="${NamedRoutes.postsPath()}?page=${page.getPage() - 1}">prevPage</a>
    </div>
    <div>
        <a href="${NamedRoutes.postsPath()}?page=${page.getPage() + 1}">nextPage</a>
    </div>

`
)