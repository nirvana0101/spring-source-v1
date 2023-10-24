package tuling.service.required;

import tuling.dao.AccountInfoDao;
import tuling.dao.ProductInfoDao;
import tuling.service.PayService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 1.Spring建立数据库连接conn1
 * 2.ThreaLocal(conn1)
 * 3.conn1.setAutoCommit(false);
 * 4.sql1
 * 5.sql2
 * 6.connection.commit();
 *
 * 验证：show status like 'Threads%';
 *      show variables like auto%';
 */
//@Component
public class PayServiceImpl implements PayService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Transactional()
    public void pay(String accountId, double money) {
        //查询余额
        double blance = accountInfoDao.qryBlanceByUserId(accountId);

        //余额不足正常逻辑
        if (new BigDecimal(blance).compareTo(new BigDecimal(money)) < 0) {
            throw new RuntimeException("余额不足");
        }

        //更新余额
        int retVal = accountInfoDao.updateAccountBlance(accountId, money);

        //库存-1
        ((PayService) AopContext.currentProxy()).updateProductStore(1);

        System.out.println(1 / 0);

    }

    @Transactional()
    public void updateProductStore(Integer productId) {
        try {
            productInfoDao.updateProductInfo(productId);
        } catch (Exception e) {
            throw new RuntimeException("内部异常");
        }
    }
}
