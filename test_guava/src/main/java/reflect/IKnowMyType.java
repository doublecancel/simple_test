package reflect;

import com.google.common.reflect.TypeToken;

/**
 * Created by Administrator on 2017/4/21.
 */
public abstract class IKnowMyType<T> {
    TypeToken<T> type = new TypeToken<T>(getClass()) {};
}
