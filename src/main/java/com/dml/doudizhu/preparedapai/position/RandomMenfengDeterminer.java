package com.dml.doudizhu.preparedapai.position;

import com.dml.doudizhu.ju.Ju;
import com.dml.doudizhu.pan.Pan;
import com.dml.puke.wanfa.position.Position;
import com.dml.puke.wanfa.position.PositionUtil;

/**
 * 随机座位
 * 
 * @author lsc
 *
 */
public class RandomMenfengDeterminer implements MenfengDeterminer {

	@Override
	public void determineMenfengForPlayer(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		Position nextPosition = Position.dong;
		for (String playerId : currentPan.getDoudizhuPlayerIdMajiangPlayerMap().keySet()) {
			currentPan.updatePlayerPosition(playerId, nextPosition);
			nextPosition = PositionUtil.nextPositionClockwise(nextPosition);
		}

	}

}
