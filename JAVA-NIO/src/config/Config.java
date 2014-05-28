package config;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.14
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    public static final int test = 2;
    private static Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }
}
