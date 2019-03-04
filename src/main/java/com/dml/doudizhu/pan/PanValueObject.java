package com.dml.doudizhu.pan;

import java.util.ArrayList;
import java.util.HashMap;
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

	public PanValueObject() {

	}

	public PanValueObject(Pan pan) {
		no = pan.getNo();
		shuangkouPlayerList = new ArrayList<>();
		pan.getDoudizhuPlayerIdMajiangPlayerMap().values()
				.forEach((doudizhuPlayer) -> shuangkouPlayerList.add(new DoudizhuPlayerValueObject(doudizhuPlayer)));
		paiListValueObject = new PaiListValueObject(pan.getAvaliablePaiList());
		positionPlayerIdMap = new HashMap<>(pan.getPositionPlayerIdMap());
		dachuPaiZuList = new ArrayList<>(pan.getDachuPaiZuList());
		actionPosition = pan.getActionPosition();
		latestDapaiPlayerId = pan.getLatestDapaiPlayerId();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<DoudizhuPlayerValueObject> getShuangkouPlayerList() {
		return shuangkouPlayerList;
	}

	public void setShuangkouPlayerList(List<DoudizhuPlayerValueObject> shuangkouPlayerList) {
		this.shuangkouPlayerList = shuangkouPlayerList;
	}

	public PaiListValueObject getPaiListValueObject() {
		return paiListValueObject;
	}

	public void setPaiListValueObject(PaiListValueObject paiListValueObject) {
		this.paiListValueObject = paiListValueObject;
	}

	public Map<Position, String> getPositionPlayerIdMap() {
		return positionPlayerIdMap;
	}

	public void setPositionPlayerIdMap(Map<Position, String> positionPlayerIdMap) {
		this.positionPlayerIdMap = positionPlayerIdMap;
	}

	public List<DianShuZuPaiZu> getDachuPaiZuList() {
		return dachuPaiZuList;
	}

	public void setDachuPaiZuList(List<DianShuZuPaiZu> dachuPaiZuList) {
		this.dachuPaiZuList = dachuPaiZuList;
	}

	public Position getActionPosition() {
		return actionPosition;
	}

	public void setActionPosition(Position actionPosition) {
		this.actionPosition = actionPosition;
	}

	public String getLatestDapaiPlayerId() {
		return latestDapaiPlayerId;
	}

	public void setLatestDapaiPlayerId(String latestDapaiPlayerId) {
		this.latestDapaiPlayerId = latestDapaiPlayerId;
	}

}
