package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 四带二:四张相同的牌加两张单牌(或两张对子),如四张5+单6(对6)+单7(对7). 不算炸弹.
 * 
 * @author lsc
 *
 */
public class SidaierDianShuZu implements DianShuZu {
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
