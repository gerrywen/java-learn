package ch19;

import java.util.Map;

/**
 * program: java-learn->OuterUserInfo
 * description:
 * author: gerry
 * created: 2020-03-22 09:09
 **/
public class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo = super.getUserBaseInfo(); //员工的基本信息
    private Map homeInfo = super.getUserHomeInfo(); //员工的家庭信息
    private Map officeInfo = super.getUserOfficeInfo(); //工作信息

    /**
     * 员工的名称
     *
     * @return
     */
    @Override
    public String getUserName() {
        String userName = (String) this.baseInfo.get("userName");
        System.out.println(userName);
        return userName;
    }

    /**
     * 家庭地址
     *
     * @return
     */
    @Override
    public String getHomeAddress() {
        String homeAddress = (String) this.homeInfo.get("homeAddress");
        System.out.println(homeAddress);
        return homeAddress;
    }

    /**
     * 手机号码
     *
     * @return
     */
    @Override
    public String getMobileNumber() {
        String mobileNumber = (String) this.baseInfo.get("mobileNumber");
        System.out.println(mobileNumber);
        return mobileNumber;
    }

    /**
     * 办公电话
     *
     * @return
     */
    @Override
    public String getOfficeTelNumber() {
        String officeTelNumber = (String) this.officeInfo.get("officeTelNumber");
        System.out.println(officeTelNumber);
        return officeTelNumber;
    }

    /**
     * 职位信息
     *
     * @return
     */
    @Override
    public String getJobPosition() {
        String jobPosition = (String) this.officeInfo.get("jobPosition");
        System.out.println(jobPosition);
        return jobPosition;
    }

    /**
     * 家庭电话号码
     *
     * @return
     */
    @Override
    public String getHomeTelNumber() {
        String homeTelNumber = (String) this.homeInfo.get("homeTelNumber");
        System.out.println(homeTelNumber);
        return homeTelNumber;
    }
}
