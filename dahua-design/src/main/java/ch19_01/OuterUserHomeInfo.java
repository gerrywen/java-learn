package ch19_01;

import java.util.HashMap;
import java.util.Map;

/**
 * program: java-learn->OuterUserHomeInfo
 * description:
 * author: gerry
 * created: 2020-03-22 09:22
 **/
public class OuterUserHomeInfo implements IOuterUserHomeInfo {
    /**
     * 员工的家庭信息
     *
     * @return
     */
    @Override
    public Map getUserHomeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeTelNumbner", "员工的家庭电话是...");
        homeInfo.put("homeAddress", "员工的家庭地址是...");
        return homeInfo;
    }
}
