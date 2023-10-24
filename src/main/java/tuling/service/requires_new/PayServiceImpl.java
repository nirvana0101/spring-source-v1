package tuling.service.requires_new;

import tuling.dao.AccountInfoDao;
import tuling.dao.ProductInfoDao;
import tuling.service.PayService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 1.Spring建立数据库连接conn1
 * 2.ThreaLocal(conn1)
 * 3.conn1.setAutoCommit(false);
 * 4. sqll1
 *
 *  5. conn1挂起(conn1-->Object)
 *  6.Spring建立数据库连接conn2
 *  7.ThreaLocal(conn2)
 *  8.conn2.setAutoCommit(false);
 *  9. sql2
 *  10.conn2.commit();
 *  11.conn1恢复 conn1-->ThreaLocal
 *
 * 12. sqll111
 * 13. conn1.commit()
 */
@Component
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
        if(new BigDecimal(blance).compareTo(new BigDecimal(money))<0) {
            throw new RuntimeException("余额不足");
        }

        //更新余额
        int retVal = accountInfoDao.updateAccountBlance(accountId,money);

        //库存-1
        ((PayService)AopContext.currentProxy()).updateProductStore(1);

        //sqll111

        System.out.println(1/0);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProductStore(Integer productId) {
        try{
            productInfoDao.updateProductInfo(productId);
        }
        catch (Exception e) {
            throw new RuntimeException("内部异常");
        }
    }
}
