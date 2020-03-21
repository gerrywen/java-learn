package ch18;

public enum CashEnum {
    CASH_NORMAL(0, "正常收费"),
    CASH_RETURN(1, "满300减100"),
    CASH_REBATE(2, "打8折");

    private int code;
    private String description;

    CashEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
