package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 三带:三张点数相同的牌加一张单牌,如三张6+单5.
 * 
 * @author lsc
 *
 */
public class SandaiyiDianShuZu implements DianShuZu {
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
