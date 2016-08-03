package cashier.wizarpos.com.wizarposcashier.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易详情
 * Created by lixinchun on 16/8/2.
 */
public class TradeDetail implements Serializable{
    private int id;
    private String tradePrice;
    private String tradeStatus;
    private String tradeTime;
    private String tradeNumber;
    private String tradeType;
    private String tradeOperator;

    public TradeDetail(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeOperator() {
        return tradeOperator;
    }

    public void setTradeOperator(String tradeOperator) {
        this.tradeOperator = tradeOperator;
    }

    public static Map<String,String> getTradeDetailById(int id){

        for (Map<String,String> map: getTradeDetails()) {
            if (map.get("id").equals(String.valueOf(id))){
                return map;
            }
        }
        return null;
    }

    public static Map<String,String> getLastTradeDetail(){
        return getTradeDetails().get(getTradeDetails().size()-1);
    }

    /**
     * 根据凭证号和交易单号获取交易数据
     * @param code
     * @return
     */
    public static List<Map<String,String>> getTradeDetailByTradeNumber(String code){
        List<Map<String,String>> tradeDetails = new ArrayList<>();
        for (Map<String,String> map: getTradeDetails()) {
            if (map.get("tradeNumber").contains(code)){
               tradeDetails.add(map);
            }
        }
        return tradeDetails;
    }

    /**
     * 获取交易总额
     * @return
     */
    public static float getTradeAmount(){
        float amount = 0;
        for (Map<String,String> map: getTradeDetails()) {
            amount = amount+Float.parseFloat(map.get("tradePrice").replaceAll(" ","").replaceAll("¥",""));
        }
        BigDecimal b  =   new BigDecimal(amount);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 获取所有的交易
     * @return
     */
    public static List<Map<String,String>> getTradeDetails(){
        List<Map<String,String>> tradeDetails = new ArrayList<>();
        Map<String,String> tradeDetail = null;
        for (int i=0;i<6;i++){
            tradeDetail = new HashMap<>();
            tradeDetail.put("id",i+"");
            tradeDetail.put("tradeNumber","123333333333333333333333333333");
            tradeDetail.put("tradePrice","¥ 0.01");
            tradeDetail.put("tradeStatus","交易成功");
            tradeDetail.put("tradeTime","2016-3-31 16:50:33");
            tradeDetail.put("tradeOperator","001");
            tradeDetail.put("tradeType","微信支付");
            tradeDetails.add(tradeDetail);
        }
        for (int i=6;i<10;i++){
            tradeDetail = new HashMap<>();
            tradeDetail.put("id",i+"");
            tradeDetail.put("tradeNumber","123333333333333333333333333333");
            tradeDetail.put("tradePrice","¥ 1000.01");
            tradeDetail.put("tradeStatus","交易失败");
            tradeDetail.put("tradeTime","2016-3-30 16:50:33");
            tradeDetail.put("tradeOperator","001");
            tradeDetail.put("tradeType","微信支付");
            tradeDetails.add(tradeDetail);
        }
        return tradeDetails;
    }

    public static List<Map<String,String>> getTradeCodeNumbers(){
        List<Map<String,String>> tradeCodeNumbers = new ArrayList<>();
        Map<String,String> tradeCodeNumber = null;
        for (int i=0;i<6;i++){
            tradeCodeNumber = new HashMap<>();
            tradeCodeNumber.put("tradeNumber","100034885587577");
            tradeCodeNumbers.add(tradeCodeNumber);
        }
        return tradeCodeNumbers;
    }
}
