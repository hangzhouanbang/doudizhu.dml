package com.dml.doudizhu.pai.dianshuzu;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 带一个对子的飞机
 * 
 * @author lsc
 *
 */
public class FeijidaierDianShuZu implements DianShuZu {

	private DianShu[] lianxuDianshuArray;

	private DianShu[] chibangArray;

	public DianShu[] getLianxuDianshuArray() {
		return lianxuDianshuArray;
	}

	public void setLianxuDianshuArray(DianShu[] lianxuDianshuArray) {
		this.lianxuDianshuArray = lianxuDianshuArray;
	}

	public DianShu[] getChibangArray() {
		return chibangArray;
	}

	public void setChibangArray(DianShu[] chibangArray) {
		this.chibangArray = chibangArray;
	}

}
