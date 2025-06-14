package com.javaex.vendor.model;

import java.math.BigDecimal;

public class HyunVenderVo {

	private int memberId;
	private int memberPoints;
	private int venderId;
	private BigDecimal totalRevenue;
	private String alias;
	private String venderNumber;
	private String representativeName;
	private String venderAddress;
	private String district;
	private String venderDescription;
	private String kakaoUrl;
	private String venderName;

	public HyunVenderVo() {
		super();
	}

	public HyunVenderVo(int memberId, int memberPoints, int venderId, BigDecimal totalRevenue, String alias) {
		super();
		this.memberId = memberId;
		this.memberPoints = memberPoints;
		this.venderId = venderId;
		this.totalRevenue = totalRevenue;
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getMemberPoints() {
		return memberPoints;
	}

	public void setMemberPoints(int memberPoints) {
		this.memberPoints = memberPoints;
	}



    public String getVenderNumber() {
        return venderNumber;
    }

    public void setVenderNumber(String venderNumber) {
        this.venderNumber = venderNumber;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getVenderAddress() {
        return venderAddress;
    }

    public void setVenderAddress(String venderAddress) {
        this.venderAddress = venderAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVenderDescription() {
        return venderDescription;
    }

    public void setVenderDescription(String venderDescription) {
        this.venderDescription = venderDescription;
    }

    public String getKakaoUrl() {
        return kakaoUrl;
    }

    public void setKakaoUrl(String kakaoUrl) {
        this.kakaoUrl = kakaoUrl;
    }

	@Override
	public String toString() {
		return "HyunVenderVo [memberId=" + memberId + ", memberPoints=" + memberPoints + ", venderId=" + venderId
				+ ", totalRevenue=" + totalRevenue + "]";
	}

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }
}
