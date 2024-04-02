@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
   <table>
      <tr>
         <td>Id:</td>
         <td>${page.getPost().getId()}</td>
      </tr>
      <tr>
         <td>Name:</td>
         <td>${page.getPost().getName()}</td>
      </tr>
      <tr>
         <td>Body:</td>
         <td>${page.getPost().getBody()}</td>
      </tr>
   </table>

   <div>
      <a href="${NamedRoutes.postsPath()}">all posts</a>
   </div>

`
)