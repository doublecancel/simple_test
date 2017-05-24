package annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/5/24.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableYaml {
    String prefix();
    String path();
}
