package test.bean;

/**
 * 定义一个 IUserDao 接口，之所这样做是为了通过 FactoryBean 做一个自定义对象的代理操作。
 *
 * @author ChangZhen
 */
public interface IUserDao {

    String queryUserName(String uId);

}
