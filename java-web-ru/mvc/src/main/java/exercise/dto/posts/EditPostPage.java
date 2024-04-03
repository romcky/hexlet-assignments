package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostPage {
    Post post;
    private Map<String, List<ValidationError<Object>>> errors;
    private String newName;
    private String newBody;
}
// END
