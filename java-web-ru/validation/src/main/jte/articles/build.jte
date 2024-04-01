@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`

		@if (page.getErrors() != null)
      	<ul>
    		@for (var validator : page.getErrors().values())
    			@for (var error : validator)
    				<li> ${error.getMessage()} </li>
    			@endfor
    		@endfor
    	</ul>
    	@endif
    	
		<form method="post" action="/articles">
    		<div>
    			<label>
    				Title: 
    				<input type="text" name="title" value="${page.getArticleName()}" />
    			</label>
    		</div>
    		<div>
    			<label>
    				Content:
    				<input type="text" name="content" value="${page.getArticleContent()}" />
    			</label>
    		</div>
    		<input type="submit" value="Submit" />
    	</form>
    `
)
