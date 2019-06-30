package com.abc.bank.service.iml;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.nowapi.ApiConstants;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.AccountCurrency;
import com.abc.bank.pojo.Bill;
import com.abc.bank.repository.AccountCurrencyMapper;
import com.abc.bank.repository.AccountMapper;
import com.abc.bank.repository.BillMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Service
public class AccountCurrencyServiceImpl {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    AccountCurrencyMapper accountCurrencyMapper;


    public Float getMoneyByrate(Float dollor, @NotEmpty @NotNull String type) {
        Float rate=null;
        try {
            rate=getRateFromapi(type,"CNY");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (rate==null){
            return null;
        }

        return  dollor*rate;
    }

    private Float getRateFromapi(String scur,String tcur) throws IOException {
        URL u = new URL("http://api.k780.com/?" +
                "app=finance.rate&" +
                "scur=" +
                scur +
                "&tcur=" +
                tcur +
                /*"app=finance.rate_curlist&curType=rateRealtime" +*/
                "&"+
                ApiConstants.APP_KEY.getName()+"="+ApiConstants.APP_KEY.getValue()+
                "&" +
                ApiConstants.SIGN.getName()+"="+ApiConstants.SIGN.getValue()+
                "&format=json");
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte[] b = out.toByteArray();
        JSONObject ratestring= JSON.parseObject(new String(b, "utf-8"));
        String ratevalue = ratestring.getJSONObject("result").getString("rate");
        Float rate = Float.valueOf(ratevalue);
        return rate;
    }

    /**
     * 更新外币余额
     * @param accountCurrency
     * @return
     */
    public boolean updateAccCurrency(AccountCurrency accountCurrency){
        return accountCurrencyMapper.updateByPrimaryKey(accountCurrency)>0;

    }

    /**
     * 创建外币信息
     * @param accountCurrency
     * @return
     */
    public boolean createAccCurrency(AccountCurrency accountCurrency){
        return accountCurrencyMapper.insert(accountCurrency)>0;
    }

    /**
     * 根据卡号获取外币信息
     * @param accountCurrency
     * @return
     */
    public AccountCurrency getAccCurrency(AccountCurrency accountCurrency){
        return accountCurrencyMapper.selectByPrimaryKey(accountCurrency);
    }
}
