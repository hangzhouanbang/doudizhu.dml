package com.dml.doudizhu.preparedapai.luanpai;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.dml.doudizhu.ju.Ju;
import com.dml.doudizhu.pan.Pan;
import com.dml.puke.pai.PukePai;

/**
 * 随机的乱牌策略
 * 
 * @author lsc
 *
 */
public class RandomLuanPaiStrategy implements LuanPaiStrategy {

	private long seed;

	@Override
	public void luanpai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<PukePai> allPaiList = currentPan.getAvaliablePaiList();
		Random rnm = new Random(seed);
		Collections.shuffle(allPaiList, rnm);
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

}
