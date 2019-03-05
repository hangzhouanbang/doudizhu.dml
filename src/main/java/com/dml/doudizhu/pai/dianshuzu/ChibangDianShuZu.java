package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;

/**
 * 带翅膀的点数组
 * 
 * @author lsc
 *
 */
public abstract class ChibangDianShuZu {
	private DianShu dianshu;
	private DianShu chibang;

	public DianShu getDianshu() {
		return dianshu;
	}

	public void setDianshu(DianShu dianshu) {
		this.dianshu = dianshu;
	}

	public DianShu getChibang() {
		return chibang;
	}

	public void setChibang(DianShu chibang) {
		this.chibang = chibang;
	}

}
