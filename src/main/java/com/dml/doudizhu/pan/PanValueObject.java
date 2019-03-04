package com.dml.doudizhu.pan;

import java.util.List;
import java.util.Map;

import com.dml.doudizhu.player.DoudizhuPlayerValueObject;
import com.dml.puke.pai.PaiListValueObject;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.puke.wanfa.position.Position;

public class PanValueObject {
	private int no;
	private List<DoudizhuPlayerValueObject> shuangkouPlayerList;
	private PaiListValueObject paiListValueObject;
	private Map<Position, String> positionPlayerIdMap;
	private List<DianShuZuPaiZu> dachuPaiZuList;
	private Position actionPosition;
	private String latestDapaiPlayerId;

}
