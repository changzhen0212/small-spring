package beans;

import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.context.ApplicationContext;

import java.util.Random;
import java.util.StringJoiner;

/** @author ChangZhen */
public class UserService implements IUserService {

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "cz,10001,北京";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
