package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;

/**
 * 四带二:四张相同的牌加两张单牌(或两张对子),如四张5+单6(对6)+单7(对7). 不算炸弹.
 * 
 * @author lsc
 *
 */
public class SidaierDianShuZu extends ChibangDianShuZu {

	public SidaierDianShuZu() {

	}

	public SidaierDianShuZu(DianShu dianshu, DianShu chibang) {
		super(dianshu, chibang);
	}
}
