package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 三带二:三张点数相同的牌加一个对子,如三张6+对5.
 * 
 * @author lsc
 *
 */
public class SandaierDianShuZu implements DianShuZu {
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
