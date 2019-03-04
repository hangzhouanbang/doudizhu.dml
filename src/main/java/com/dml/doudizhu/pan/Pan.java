package com.dml.doudizhu.pan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.doudizhu.pai.waihao.WaihaoGenerator;
import com.dml.doudizhu.player.DoudizhuPlayer;
import com.dml.doudizhu.player.PlayerNotFoundException;
import com.dml.doudizhu.player.action.DoudizhuPlayerAction;
import com.dml.doudizhu.player.action.PlayerCanNotActionException;
import com.dml.doudizhu.player.action.da.DaAction;
import com.dml.doudizhu.player.action.da.DianShuZuYaPaiSolutionCalculator;
import com.dml.doudizhu.player.action.da.YaPaiSolutionsTipsFilter;
import com.dml.doudizhu.player.action.da.ZaDanYaPaiSolutionCalculator;
import com.dml.doudizhu.player.action.guo.CanNotGuoException;
import com.dml.doudizhu.player.action.guo.GuoAction;
import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.puke.wanfa.position.Position;
import com.dml.puke.wanfa.position.PositionUtil;

public class Pan {
	private int no;
	private Map<String, DoudizhuPlayer> doudizhuPlayerIdMajiangPlayerMap = new HashMap<>();
	private Map<Position, String> positionPlayerIdMap = new HashMap<>();
	private List<PukePai> avaliablePaiList = new ArrayList<>();
	private List<DianShuZuPaiZu> dachuPaiZuList = new ArrayList<>();
	private Position actionPosition;
	private String latestDapaiPlayerId;
	private List<PanActionFrame> actionFrameList = new ArrayList<>();

	public PanActionFrame recordPanActionFrame(DoudizhuPlayerAction action, long actionTime) {
		PanActionFrame frame = new PanActionFrame(action, new PanValueObject(this), actionTime);
		frame.setNo(actionFrameList.size());
		actionFrameList.add(frame);
		return frame;
	}

	public PanActionFrame findLatestActionFrame() {
		if (!actionFrameList.isEmpty()) {
			return actionFrameList.get(actionFrameList.size() - 1);
		} else {
			return null;
		}
	}

	public boolean isNextActionNo(int actionNo) {
		return actionFrameList.size() == actionNo;
	}

	public void addPlayer(String playerId) {
		DoudizhuPlayer doudizhuPlayer = new DoudizhuPlayer();
		doudizhuPlayer.setId(playerId);
		doudizhuPlayerIdMajiangPlayerMap.put(playerId, doudizhuPlayer);
	}

	public List<String> sortedPlayerIdList() {
		List<String> list = new ArrayList<>(doudizhuPlayerIdMajiangPlayerMap.keySet());
		Collections.sort(list);
		return list;
	}

	public void updateActionPositionByActionPlayer(String playerId) throws Exception {
		DoudizhuPlayer player = doudizhuPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		actionPosition = player.getPosition();
	}

	public void updatePlayerPosition(String playerId, Position position) throws PlayerNotFoundException {
		DoudizhuPlayer player = doudizhuPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		player.setPosition(position);
		positionPlayerIdMap.put(position, playerId);
	}

	public String playerIdForPosition(Position position) {
		return positionPlayerIdMap.get(position);
	}

	public DaAction da(String playerId, List<Integer> paiIds, String dianshuZuheIdx, WaihaoGenerator waihaoGenerator)
			throws Exception {
		DoudizhuPlayer daPlayer = doudizhuPlayerIdMajiangPlayerMap.get(playerId);
		if (daPlayer == null) {
			throw new PlayerNotFoundException();
		}
		if (!actionPosition.equals(daPlayer.getPosition())) {
			throw new PlayerCanNotActionException();
		}
		// 如果是大的人打牌，那先要清桌
		if (latestDapaiPlayerId == null || ifStartYapai()) {
			doudizhuPlayerIdMajiangPlayerMap.values().forEach((player) -> {
				player.putPublicDachuPaiZuToLishi();
			});
		}
		daPlayer.da(paiIds, dianshuZuheIdx, waihaoGenerator);
		DianShuZuPaiZu publicDachuPaiZu = daPlayer.getPublicDachuPaiZu();
		dachuPaiZuList.add(publicDachuPaiZu);
		latestDapaiPlayerId = playerId;
		DaAction daAction = new DaAction(playerId);
		daAction.setDachuPaiZu(publicDachuPaiZu);
		return daAction;
	}

	public GuoAction guo(String playerId) throws Exception {
		DoudizhuPlayer player = doudizhuPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		if (!actionPosition.equals(player.getPosition())) {
			throw new PlayerCanNotActionException();
		}
		// 大的人必须出牌，不能过，第一手牌也不能过
		if (latestDapaiPlayerId == null || playerId.equals(latestDapaiPlayerId)) {
			throw new CanNotGuoException();
		}
		player.guo();
		return new GuoAction(player.getId());
	}

	public void updateNextPlayersDaSolution(DianShuZuYaPaiSolutionCalculator dianShuZuYaPaiCalculator,
			ZaDanYaPaiSolutionCalculator zaDanYaPaiCalculator) {
		String dachuPlayerId = latestDapaiPlayerId;
		if (dachuPlayerId != null) {
			DoudizhuPlayer dachuPlayer = doudizhuPlayerIdMajiangPlayerMap.get(dachuPlayerId);
			if (dachuPlayer != null) {
				DianShuZuPaiZu dachuPaiZu = dachuPlayer.getPublicDachuPaiZu();
				if (dachuPaiZu != null) {
					DoudizhuPlayer yapaiPlayer = null;
					try {
						yapaiPlayer = findNextActionPlayer();
					} catch (PlayerNotFoundException e) {

					}
					if (yapaiPlayer != null) {
						yapaiPlayer.addDaPaiDianShuSolutions(dianShuZuYaPaiCalculator
								.calculate(dachuPaiZu.getDianShuZu(), yapaiPlayer.getShoupaiDianShuAmountArray()));
						yapaiPlayer.addDaPaiDianShuSolutions(zaDanYaPaiCalculator.calculate(dachuPaiZu.getDianShuZu(),
								yapaiPlayer.getShoupaiDianShuAmountArray()));
					}
				}
			}
		}

	}

	public void generateYaPaiSolutionsForTips(YaPaiSolutionsTipsFilter yaPaiSolutionsTipsFilter) {
		DoudizhuPlayer yapaiPlayer = null;
		try {
			yapaiPlayer = findNextActionPlayer();
		} catch (PlayerNotFoundException e) {

		}
		if (yapaiPlayer != null) {
			yapaiPlayer.generateYaPaiSolutionsForTips(yaPaiSolutionsTipsFilter);
		}
	}

	public void updateActionPositionToNextPlayer() {
		DoudizhuPlayer yapaiPlayer = null;
		try {
			yapaiPlayer = findNextActionPlayer();
		} catch (PlayerNotFoundException e) {

		}
		if (yapaiPlayer != null) {
			yapaiPlayer.putPublicDachuPaiZuToLishi();
			yapaiPlayer.setGuo(false);
			actionPosition = yapaiPlayer.getPosition();
		}
	}

	/**
	 * 查找下一个打牌的玩家
	 * 
	 * @throws PlayerNotFoundException
	 */
	public DoudizhuPlayer findNextActionPlayer() throws PlayerNotFoundException {
		Position nextPosition = PositionUtil.nextPositionClockwise(actionPosition);
		String yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		while (yapaiPlayerId == null || !yapaiPlayerId.equals(latestDapaiPlayerId)) {
			nextPosition = PositionUtil.nextPositionClockwise(nextPosition);
			yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		}
		DoudizhuPlayer yapaiPlayer = doudizhuPlayerIdMajiangPlayerMap.get(yapaiPlayerId);
		return yapaiPlayer;
	}

	public DoudizhuPlayer findPlayer(String dapaiPlayerId) {
		return doudizhuPlayerIdMajiangPlayerMap.get(dapaiPlayerId);
	}

	public DoudizhuPlayer findDuijiaPlayer(String playerId) {
		DoudizhuPlayer player = doudizhuPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			return null;
		}
		Position nextPosition = PositionUtil.nextPositionClockwise(player.getPosition());
		nextPosition = PositionUtil.nextPositionClockwise(nextPosition);
		String duijiaPlayerId = positionPlayerIdMap.get(nextPosition);
		if (duijiaPlayerId == null) {
			return null;
		}
		return doudizhuPlayerIdMajiangPlayerMap.get(duijiaPlayerId);
	}

	/**
	 * 是否是新一轮压牌
	 * 
	 * @throws PlayerNotFoundException
	 */
	public boolean ifStartYapai() throws PlayerNotFoundException {
		Position nextPosition = PositionUtil.nextPositionClockwise(actionPosition);
		String yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		while (yapaiPlayerId == null || !yapaiPlayerId.equals(latestDapaiPlayerId)) {
			nextPosition = PositionUtil.nextPositionClockwise(nextPosition);
			yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		}
		DoudizhuPlayer yapaiPlayer = doudizhuPlayerIdMajiangPlayerMap.get(yapaiPlayerId);
		return yapaiPlayer.getId().equals(latestDapaiPlayerId);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Map<String, DoudizhuPlayer> getDoudizhuPlayerIdMajiangPlayerMap() {
		return doudizhuPlayerIdMajiangPlayerMap;
	}

	public void setDoudizhuPlayerIdMajiangPlayerMap(Map<String, DoudizhuPlayer> doudizhuPlayerIdMajiangPlayerMap) {
		this.doudizhuPlayerIdMajiangPlayerMap = doudizhuPlayerIdMajiangPlayerMap;
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

	public List<PanActionFrame> getActionFrameList() {
		return actionFrameList;
	}

	public void setActionFrameList(List<PanActionFrame> actionFrameList) {
		this.actionFrameList = actionFrameList;
	}

}
