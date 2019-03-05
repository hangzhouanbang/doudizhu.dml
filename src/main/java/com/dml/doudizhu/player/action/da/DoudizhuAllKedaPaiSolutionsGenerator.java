package com.dml.doudizhu.player.action.da;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.doudizhu.player.action.da.solution.DaPaiDianShuSolution;
import com.dml.puke.pai.DianShu;
import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.dianshuzu.DanGeZhadanDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZuGenerator;
import com.dml.puke.wanfa.dianshu.dianshuzu.DuiziDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LianduiDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LiansanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.SanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.ShunziDianShuZu;

/**
 * 最普通的斗地主打法
 * 
 * @author lsc
 *
 */
public class DoudizhuAllKedaPaiSolutionsGenerator implements AllKedaPaiSolutionsGenerator {

	@Override
	public Map<String, DaPaiDianShuSolution> generateAllKedaPaiSolutions(Map<Integer, PukePai> allShoupai) {
		Map<String, DaPaiDianShuSolution> kedaPaiSolutions = new HashMap<>();
		int[] dianShuAmountArray = new int[15];
		for (PukePai pukePai : allShoupai.values()) {
			dianShuAmountArray[pukePai.getPaiMian().dianShu().ordinal()]++;
		}
		// 单张点数组
		List<DanzhangDianShuZu> danzhangList = DianShuZuGenerator.generateAllDanzhangDianShuZu(dianShuAmountArray);
		List<DaPaiDianShuSolution> danzhangSolution = calculateDanzhangDaPaiDianShuSolution(danzhangList);
		danzhangSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 对子点数组
		List<DuiziDianShuZu> duiziList = DianShuZuGenerator.generateAllDuiziDianShuZu(dianShuAmountArray);
		List<DaPaiDianShuSolution> duiziSolution = calculateDuiziDaPaiDianShuSolution(duiziList);
		duiziSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 三张点数组
		List<SanzhangDianShuZu> sanzhangList = DianShuZuGenerator.generateAllSanzhangDianShuZu(dianShuAmountArray);
		List<DaPaiDianShuSolution> sanzhangSolution = calculateSanzhangDaPaiDianShuSolution(sanzhangList);
		sanzhangSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 顺子点数组
		List<ShunziDianShuZu> shunziList = new ArrayList<>();
		for (int length = 5; length < 12; length++) {// 顺子:五张或更多的连续单牌,不包括2与双王,如单5+单6+单7+单8+单9.
			shunziList.addAll(DianShuZuGenerator.generateAllShunziDianShuZu(dianShuAmountArray, length));
		}
		List<DaPaiDianShuSolution> shunziSolution = calculateShunziDaPaiDianShuSolution(shunziList);
		shunziSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 连对点数组
		List<LianduiDianShuZu> lianduiList = new ArrayList<>();
		for (int length = 3; length < 12; length++) {// 连对:三对或更多的连续对子,不包括2与双王,如对5+对6+对7.
			lianduiList.addAll(DianShuZuGenerator.generateAllLianduiDianShuZu(dianShuAmountArray, length));
		}
		List<DaPaiDianShuSolution> lianduiSolution = calculateLianduiDaPaiDianShuSolution(lianduiList);
		lianduiSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 连三张点数组
		List<LiansanzhangDianShuZu> lianSanZhangList = new ArrayList<>();
		for (int length = 3; length < 12; length++) {// 连三张:三或更多的连续三张,不包括2与双王
			lianSanZhangList.addAll(DianShuZuGenerator.generateAllLiansanzhangDianShuZu(dianShuAmountArray, length));
		}
		List<DaPaiDianShuSolution> liansanzhangSolution = calculateLiansanzhangDaPaiDianShuSolution(lianSanZhangList);
		liansanzhangSolution.forEach((solution) -> {
			kedaPaiSolutions.put(solution.getDianshuZuheIdx(), solution);
		});
		// 单个炸弹点数组
		List<DanGeZhadanDianShuZu> zhadanList = DianShuZuGenerator.generateAllZhadanDianShuZu(dianShuAmountArray);
		// TODO 王炸
		// TODO 飞机
		// TODO 翅膀
		return kedaPaiSolutions;
	}

	/**
	 * 单张打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateDanzhangDaPaiDianShuSolution(List<DanzhangDianShuZu> danzhangList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 单张
		for (DanzhangDianShuZu danzhangDianShuZu : danzhangList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(danzhangDianShuZu);
			DianShu[] dachuDianShuArray = { danzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 对子打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateDuiziDaPaiDianShuSolution(List<DuiziDianShuZu> duiziList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 对子
		for (DuiziDianShuZu duiziDianShuZu : duiziList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(duiziDianShuZu);
			DianShu[] dachuDianShuArray = { duiziDianShuZu.getDianShu(), duiziDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 三张打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateSanzhangDaPaiDianShuSolution(List<SanzhangDianShuZu> sanzhangList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 三张
		for (SanzhangDianShuZu sanzhangDianShuZu : sanzhangList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(sanzhangDianShuZu);
			DianShu[] dachuDianShuArray = { sanzhangDianShuZu.getDianShu(), sanzhangDianShuZu.getDianShu(),
					sanzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 顺子打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateShunziDaPaiDianShuSolution(List<ShunziDianShuZu> shunziList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 顺子
		for (ShunziDianShuZu shunziDianShuZu : shunziList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(shunziDianShuZu);
			DianShu[] lianXuDianShuArray = shunziDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 连对打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateLianduiDaPaiDianShuSolution(List<LianduiDianShuZu> lianduiList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 连对
		for (LianduiDianShuZu lianduiDianShuZu : lianduiList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(lianduiDianShuZu);
			DianShu[] lianXuDianShuArray = lianduiDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[2 * lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i * 2] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 2 + 1] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 连三张打牌方案
	 */
	private List<DaPaiDianShuSolution> calculateLiansanzhangDaPaiDianShuSolution(
			List<LiansanzhangDianShuZu> lianSanZhangList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 连三张
		for (LiansanzhangDianShuZu liansanzhangDianShuZu : lianSanZhangList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(liansanzhangDianShuZu);
			DianShu[] lianXuDianShuArray = liansanzhangDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[3 * lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i * 3] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 3 + 1] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 3 + 2] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

}
