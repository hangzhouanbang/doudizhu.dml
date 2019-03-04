package com.dml.doudizhu.pan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.doudizhu.player.DoudizhuPlayer;
import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.position.Position;

public class Pan {
	private int no;
	private Map<String, DoudizhuPlayer> shuangkouPlayerIdMajiangPlayerMap = new HashMap<>();
	private Map<Position, String> positionPlayerIdMap = new HashMap<>();
	private List<PukePai> avaliablePaiList = new ArrayList<>();
	private Position actionPosition;
	private String latestDapaiPlayerId;
	private List<PanActionFrame> actionFrameList = new ArrayList<>();

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Map<String, DoudizhuPlayer> getShuangkouPlayerIdMajiangPlayerMap() {
		return shuangkouPlayerIdMajiangPlayerMap;
	}

	public void setShuangkouPlayerIdMajiangPlayerMap(Map<String, DoudizhuPlayer> shuangkouPlayerIdMajiangPlayerMap) {
		this.shuangkouPlayerIdMajiangPlayerMap = shuangkouPlayerIdMajiangPlayerMap;
	}

	public Map<Position, String> getPositionPlayerIdMap() {
		return positionPlayerIdMap;
	}

	public void setPositionPlayerIdMap(Map<Position, String> positionPlayerIdMap) {
		this.positionPlayerIdMap = positionPlayerIdMap;
	}

	public List<PukePai> getAvaliablePaiList() {
		return avaliablePaiList;
	}

	public void setAvaliablePaiList(List<PukePai> avaliablePaiList) {
		this.avaliablePaiList = avaliablePaiList;
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

	public List<PanActionFrame> getActionFrameList() {
		return actionFrameList;
	}

	public void setActionFrameList(List<PanActionFrame> actionFrameList) {
		this.actionFrameList = actionFrameList;
	}

}
