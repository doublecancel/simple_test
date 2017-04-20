package test.config.routingDataSource;

/**
 * Created by Administrator on 2017/4/13.
 */
public enum DbContextHolder {
    MASTER,SLAVE;

    private static final ThreadLocal<DbContextHolder> contextHolder = new ThreadLocal<DbContextHolder>();

    public static void setDataSourceType(DbContextHolder holder){
        contextHolder.set(holder);
    }
    public static void master(){
        contextHolder.set(MASTER);
    }
    public static void slave(){
        contextHolder.set(SLAVE);
    }

    public static DbContextHolder getDbType(){
        return contextHolder.get() == null ? MASTER:contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }
}
