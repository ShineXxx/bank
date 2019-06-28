package com.abc.bank;

public class Rate_curlist {
    String curNmEn;
    String curNm;
    String curNo;

    @Override
    public String toString() {
        return "Rate_curlist{" +
                "curNmEn='" + curNmEn + '\'' +
                ", curNm='" + curNm + '\'' +
                ", curNo='" + curNo + '\'' +
                '}';
    }

    public String getCurNmEn() {
        return curNmEn;
    }

    public void setCurNmEn(String curNmEn) {
        this.curNmEn = curNmEn;
    }

    public String getCurNm() {
        return curNm;
    }

    public void setCurNm(String curNm) {
        this.curNm = curNm;
    }

    public String getCurNo() {
        return curNo;
    }

    public void setCurNo(String curNo) {
        this.curNo = curNo;
    }
}
