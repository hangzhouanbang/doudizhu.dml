package com.dml.doudizhu.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Position;

import com.dml.doudizhu.player.action.da.solution.DaPaiDianShuSolution;
import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;

public class DoudizhuPlayer {

	private String id;
	private Position position;
	private Map<Integer, PukePai> allShoupai = new HashMap<>();
	private int[] shoupaiDianShuAmountArray = new int[15];
	/**
	 * 可以有多种理牌方案
	 */
	private List<List<Integer>> shoupaiIdListForSortList = new ArrayList<>();

	/**
	 * 历史打出牌组，不包含还在公示的打出牌组
	 */
	private List<DianShuZuPaiZu> lishiDachuPaiZuList = new ArrayList<>();

	/**
	 * 还在公示的打出牌组
	 */
	private DianShuZuPaiZu publicDachuPaiZu;

	/**
	 * 可以压上一手牌的所有候选方案,key是基于“算术基本定理”的dianshuZuheIdx
	 */
	private Map<String, DaPaiDianShuSolution> yaPaiSolutionCandidates = new HashMap<>();

	/**
	 * 用于给用户提示的可以压上一手牌的方案，是yaPaiSolutionCandidates的子集。顺序有意义，就是提示顺序。
	 */
	private List<DaPaiDianShuSolution> yaPaiSolutionsForTips = new ArrayList<>();

	private boolean guo;// 是否选择"过"

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Map<Integer, PukePai> getAllShoupai() {
		return allShoupai;
	}

	public void setAllShoupai(Map<Integer, PukePai> allShoupai) {
		this.allShoupai = allShoupai;
	}

	public int[] getShoupaiDianShuAmountArray() {
		return shoupaiDianShuAmountArray;
	}

	public void setShoupaiDianShuAmountArray(int[] shoupaiDianShuAmountArray) {
		this.shoupaiDianShuAmountArray = shoupaiDianShuAmountArray;
	}

	public List<List<Integer>> getShoupaiIdListForSortList() {
		return shoupaiIdListForSortList;
	}

	public void setShoupaiIdListForSortList(List<List<Integer>> shoupaiIdListForSortList) {
		this.shoupaiIdListForSortList = shoupaiIdListForSortList;
	}

	public List<DianShuZuPaiZu> getLishiDachuPaiZuList() {
		return lishiDachuPaiZuList;
	}

	public void setLishiDachuPaiZuList(List<DianShuZuPaiZu> lishiDachuPaiZuList) {
		this.lishiDachuPaiZuList = lishiDachuPaiZuList;
	}

	public DianShuZuPaiZu getPublicDachuPaiZu() {
		return publicDachuPaiZu;
	}

	public void setPublicDachuPaiZu(DianShuZuPaiZu publicDachuPaiZu) {
		this.publicDachuPaiZu = publicDachuPaiZu;
	}

	public Map<String, DaPaiDianShuSolution> getYaPaiSolutionCandidates() {
		return yaPaiSolutionCandidates;
	}

	public void setYaPaiSolutionCandidates(Map<String, DaPaiDianShuSolution> yaPaiSolutionCandidates) {
		this.yaPaiSolutionCandidates = yaPaiSolutionCandidates;
	}

	public List<DaPaiDianShuSolution> getYaPaiSolutionsForTips() {
		return yaPaiSolutionsForTips;
	}

	public void setYaPaiSolutionsForTips(List<DaPaiDianShuSolution> yaPaiSolutionsForTips) {
		this.yaPaiSolutionsForTips = yaPaiSolutionsForTips;
	}

	public boolean isGuo() {
		return guo;
	}

	public void setGuo(boolean guo) {
		this.guo = guo;
	}

}
