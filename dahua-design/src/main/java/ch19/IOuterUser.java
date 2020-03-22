package ch19;

import java.util.Map;

/**
 * program: java-learn->IOuterUser
 * description:  劳动服务公司的人员信息接口
 * author: gerry
 * created: 2020-03-22 09:06
 **/
public interface IOuterUser {
    //基本信息，比如名称、性别、手机号码等
    public Map getUserBaseInfo();

    //工作区域信息
    public Map getUserOfficeInfo();

    //用户的家庭信息
    public Map getUserHomeInfo();
}
