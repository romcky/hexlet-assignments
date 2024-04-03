@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(
content = @`
    <main>
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getPost().getName()}</h1>
        </div>

        <div class="mx-auto p-4 py-md-5">
            ${page.getPost().getBody()}
        </div>
        <!-- BEGIN -->
        @if(page.getErrors() != null)
            <div class="mb-3">
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
        @endif
        <form method="POST" action="${NamedRoutes.postPath(page.getPost().getId())}">
            <div>
                <label>
                    Edit Name:
                    <input type="text" name="newName" value="${page.getNewName()}" />
                </label>
            </div>
            <div>
                <label>
                    Edit Body:
                    <textarea name="newBody">${page.getNewBody()}</textarea>
                </label>
            </div>
            <input type="submit" value="Update" />
        </form>
        <!-- END -->
    </main>
`
)