package cor.atomikos.noxa.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Administrator on 2017/4/18.
 */
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private Integer age;
}
