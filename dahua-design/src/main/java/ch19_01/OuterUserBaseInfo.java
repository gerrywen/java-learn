package ch19_01;

import java.util.HashMap;
import java.util.Map;

/**
 * program: java-learn->OuterUserBaseInfo
 * description:
 * author: gerry
 * created: 2020-03-22 09:21
 **/
public class OuterUserBaseInfo implements IOuterUserBaseInfo {
    /**
     * 用户的基本信息
     *
     * @return
     */
    @Override
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王...");
        baseInfoMap.put("mobileNumber", "这个员工电话是...");
        return baseInfoMap;
    }
}
