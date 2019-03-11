package com.dml.doudizhu.preparedapai.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dml.doudizhu.ju.Ju;
import com.dml.doudizhu.pan.Pan;
import com.dml.puke.wanfa.position.Position;

/**
 * 随机座位
 * 
 * @author lsc
 *
 */
public class RandomMenfengDeterminer implements MenfengDeterminer {

	private long seed;

	public RandomMenfengDeterminer() {

	}

	public RandomMenfengDeterminer(long seed) {
		this.seed = seed;
	}

	@Override
	public void determineMenfengForPlayer(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		Random r = new Random(seed);
		List<Position> positions = new ArrayList<>();
		positions.add(Position.dong);
		positions.add(Position.nan);
		positions.add(Position.xi);
		positions.add(Position.bei);
		for (String playerId : currentPan.getDoudizhuPlayerIdMajiangPlayerMap().keySet()) {
			currentPan.updatePlayerPosition(playerId, positions.remove(r.nextInt(positions.size())));
		}

	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

}
