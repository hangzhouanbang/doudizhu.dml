package com.dml.doudizhu.ju;

import java.util.ArrayList;
import java.util.List;

import com.dml.doudizhu.gameprocess.CurrentPanFinishiDeterminer;
import com.dml.doudizhu.gameprocess.JuFinishiDeterminer;
import com.dml.doudizhu.pai.waihao.WaihaoGenerator;
import com.dml.doudizhu.pan.CurrentPanResultBuilder;
import com.dml.doudizhu.pan.Pan;
import com.dml.doudizhu.pan.PanResult;
import com.dml.doudizhu.player.action.ActionStatisticsListenerManager;
import com.dml.doudizhu.player.action.da.AllKedaPaiSolutionsGenerator;
import com.dml.doudizhu.player.action.da.DaPaiSolutionsTipsFilter;
import com.dml.doudizhu.player.action.da.DianShuZuYaPaiSolutionCalculator;
import com.dml.doudizhu.player.action.da.YaPaiSolutionsTipsFilter;
import com.dml.doudizhu.player.action.da.ZaDanYaPaiSolutionCalculator;
import com.dml.doudizhu.preparedapai.avaliablepai.AvaliablePaiFiller;
import com.dml.doudizhu.preparedapai.dizhu.DizhuDeterminer;
import com.dml.doudizhu.preparedapai.fapai.FaPaiStrategy;
import com.dml.doudizhu.preparedapai.lipai.ShoupaiSortStrategy;
import com.dml.doudizhu.preparedapai.luanpai.LuanPaiStrategy;
import com.dml.doudizhu.preparedapai.position.MenfengDeterminer;
import com.dml.doudizhu.preparedapai.xianda.XiandaDeterminer;

public class Ju {

	private Pan currentPan;
	private List<PanResult> finishedPanResultList = new ArrayList<>();
	private JuResult juResult;

	private CurrentPanFinishiDeterminer panFinishiDeterminer;
	private JuFinishiDeterminer juFinishiDeterminer;
	private AvaliablePaiFiller avaliablePaiFiller;
	private LuanPaiStrategy luanPaiStrategyForFirstPan;
	private LuanPaiStrategy luanPaiStrategyForNextPan;
	private FaPaiStrategy faPaiStrategyForFirstPan;
	private FaPaiStrategy faPaiStrategyForNextPan;
	private DizhuDeterminer dizhuDeterminerForFirstPan;
	private DizhuDeterminer dizhuDeterminerForNextPan;
	private MenfengDeterminer menfengDeterminerForFirstPan;
	private MenfengDeterminer menfengDeterminerForNextPan;
	private MenfengDeterminer menfengDeterminer;
	private XiandaDeterminer xiandaDeterminerForFirstPan;
	private XiandaDeterminer xiandaDeterminerForNextPan;
	private ShoupaiSortStrategy shoupaiSortStrategy;
	private YaPaiSolutionsTipsFilter yaPaiSolutionsTipsFilter;
	private DaPaiSolutionsTipsFilter daPaiSolutionsTipsFilter;
	private AllKedaPaiSolutionsGenerator allKedaPaiSolutionsGenerator;

	private WaihaoGenerator waihaoGenerator;
	private ActionStatisticsListenerManager actionStatisticsListenerManager = new ActionStatisticsListenerManager();

	private CurrentPanResultBuilder currentPanResultBuilder;
	private JuResultBuilder juResultBuilder;

	private DianShuZuYaPaiSolutionCalculator dianShuZuYaPaiSolutionCalculator;
	private ZaDanYaPaiSolutionCalculator zaDanYaPaiSolutionCalculator;

	public Pan getCurrentPan() {
		return currentPan;
	}

	public void setCurrentPan(Pan currentPan) {
		this.currentPan = currentPan;
	}

	public List<PanResult> getFinishedPanResultList() {
		return finishedPanResultList;
	}

	public void setFinishedPanResultList(List<PanResult> finishedPanResultList) {
		this.finishedPanResultList = finishedPanResultList;
	}

	public JuResult getJuResult() {
		return juResult;
	}

	public void setJuResult(JuResult juResult) {
		this.juResult = juResult;
	}

	public CurrentPanFinishiDeterminer getPanFinishiDeterminer() {
		return panFinishiDeterminer;
	}

	public void setPanFinishiDeterminer(CurrentPanFinishiDeterminer panFinishiDeterminer) {
		this.panFinishiDeterminer = panFinishiDeterminer;
	}

	public JuFinishiDeterminer getJuFinishiDeterminer() {
		return juFinishiDeterminer;
	}

	public void setJuFinishiDeterminer(JuFinishiDeterminer juFinishiDeterminer) {
		this.juFinishiDeterminer = juFinishiDeterminer;
	}

	public AvaliablePaiFiller getAvaliablePaiFiller() {
		return avaliablePaiFiller;
	}

	public void setAvaliablePaiFiller(AvaliablePaiFiller avaliablePaiFiller) {
		this.avaliablePaiFiller = avaliablePaiFiller;
	}

	public LuanPaiStrategy getLuanPaiStrategyForFirstPan() {
		return luanPaiStrategyForFirstPan;
	}

	public void setLuanPaiStrategyForFirstPan(LuanPaiStrategy luanPaiStrategyForFirstPan) {
		this.luanPaiStrategyForFirstPan = luanPaiStrategyForFirstPan;
	}

	public LuanPaiStrategy getLuanPaiStrategyForNextPan() {
		return luanPaiStrategyForNextPan;
	}

	public void setLuanPaiStrategyForNextPan(LuanPaiStrategy luanPaiStrategyForNextPan) {
		this.luanPaiStrategyForNextPan = luanPaiStrategyForNextPan;
	}

	public FaPaiStrategy getFaPaiStrategyForFirstPan() {
		return faPaiStrategyForFirstPan;
	}

	public void setFaPaiStrategyForFirstPan(FaPaiStrategy faPaiStrategyForFirstPan) {
		this.faPaiStrategyForFirstPan = faPaiStrategyForFirstPan;
	}

	public FaPaiStrategy getFaPaiStrategyForNextPan() {
		return faPaiStrategyForNextPan;
	}

	public void setFaPaiStrategyForNextPan(FaPaiStrategy faPaiStrategyForNextPan) {
		this.faPaiStrategyForNextPan = faPaiStrategyForNextPan;
	}

	public DizhuDeterminer getDizhuDeterminerForFirstPan() {
		return dizhuDeterminerForFirstPan;
	}

	public void setDizhuDeterminerForFirstPan(DizhuDeterminer dizhuDeterminerForFirstPan) {
		this.dizhuDeterminerForFirstPan = dizhuDeterminerForFirstPan;
	}

	public DizhuDeterminer getDizhuDeterminerForNextPan() {
		return dizhuDeterminerForNextPan;
	}

	public void setDizhuDeterminerForNextPan(DizhuDeterminer dizhuDeterminerForNextPan) {
		this.dizhuDeterminerForNextPan = dizhuDeterminerForNextPan;
	}

	public MenfengDeterminer getMenfengDeterminerForFirstPan() {
		return menfengDeterminerForFirstPan;
	}

	public void setMenfengDeterminerForFirstPan(MenfengDeterminer menfengDeterminerForFirstPan) {
		this.menfengDeterminerForFirstPan = menfengDeterminerForFirstPan;
	}

	public MenfengDeterminer getMenfengDeterminerForNextPan() {
		return menfengDeterminerForNextPan;
	}

	public void setMenfengDeterminerForNextPan(MenfengDeterminer menfengDeterminerForNextPan) {
		this.menfengDeterminerForNextPan = menfengDeterminerForNextPan;
	}

	public MenfengDeterminer getMenfengDeterminer() {
		return menfengDeterminer;
	}

	public void setMenfengDeterminer(MenfengDeterminer menfengDeterminer) {
		this.menfengDeterminer = menfengDeterminer;
	}

	public XiandaDeterminer getXiandaDeterminerForFirstPan() {
		return xiandaDeterminerForFirstPan;
	}

	public void setXiandaDeterminerForFirstPan(XiandaDeterminer xiandaDeterminerForFirstPan) {
		this.xiandaDeterminerForFirstPan = xiandaDeterminerForFirstPan;
	}

	public XiandaDeterminer getXiandaDeterminerForNextPan() {
		return xiandaDeterminerForNextPan;
	}

	public void setXiandaDeterminerForNextPan(XiandaDeterminer xiandaDeterminerForNextPan) {
		this.xiandaDeterminerForNextPan = xiandaDeterminerForNextPan;
	}

	public YaPaiSolutionsTipsFilter getYaPaiSolutionsTipsFilter() {
		return yaPaiSolutionsTipsFilter;
	}

	public void setYaPaiSolutionsTipsFilter(YaPaiSolutionsTipsFilter yaPaiSolutionsTipsFilter) {
		this.yaPaiSolutionsTipsFilter = yaPaiSolutionsTipsFilter;
	}

	public AllKedaPaiSolutionsGenerator getAllKedaPaiSolutionsGenerator() {
		return allKedaPaiSolutionsGenerator;
	}

	public void setAllKedaPaiSolutionsGenerator(AllKedaPaiSolutionsGenerator allKedaPaiSolutionsGenerator) {
		this.allKedaPaiSolutionsGenerator = allKedaPaiSolutionsGenerator;
	}

	public WaihaoGenerator getWaihaoGenerator() {
		return waihaoGenerator;
	}

	public void setWaihaoGenerator(WaihaoGenerator waihaoGenerator) {
		this.waihaoGenerator = waihaoGenerator;
	}

	public ActionStatisticsListenerManager getActionStatisticsListenerManager() {
		return actionStatisticsListenerManager;
	}

	public void setActionStatisticsListenerManager(ActionStatisticsListenerManager actionStatisticsListenerManager) {
		this.actionStatisticsListenerManager = actionStatisticsListenerManager;
	}

	public CurrentPanResultBuilder getCurrentPanResultBuilder() {
		return currentPanResultBuilder;
	}

	public void setCurrentPanResultBuilder(CurrentPanResultBuilder currentPanResultBuilder) {
		this.currentPanResultBuilder = currentPanResultBuilder;
	}

	public JuResultBuilder getJuResultBuilder() {
		return juResultBuilder;
	}

	public void setJuResultBuilder(JuResultBuilder juResultBuilder) {
		this.juResultBuilder = juResultBuilder;
	}

	public DianShuZuYaPaiSolutionCalculator getDianShuZuYaPaiSolutionCalculator() {
		return dianShuZuYaPaiSolutionCalculator;
	}

	public void setDianShuZuYaPaiSolutionCalculator(DianShuZuYaPaiSolutionCalculator dianShuZuYaPaiSolutionCalculator) {
		this.dianShuZuYaPaiSolutionCalculator = dianShuZuYaPaiSolutionCalculator;
	}

	public ZaDanYaPaiSolutionCalculator getZaDanYaPaiSolutionCalculator() {
		return zaDanYaPaiSolutionCalculator;
	}

	public void setZaDanYaPaiSolutionCalculator(ZaDanYaPaiSolutionCalculator zaDanYaPaiSolutionCalculator) {
		this.zaDanYaPaiSolutionCalculator = zaDanYaPaiSolutionCalculator;
	}

	public DaPaiSolutionsTipsFilter getDaPaiSolutionsTipsFilter() {
		return daPaiSolutionsTipsFilter;
	}

	public void setDaPaiSolutionsTipsFilter(DaPaiSolutionsTipsFilter daPaiSolutionsTipsFilter) {
		this.daPaiSolutionsTipsFilter = daPaiSolutionsTipsFilter;
	}

	public ShoupaiSortStrategy getShoupaiSortStrategy() {
		return shoupaiSortStrategy;
	}

	public void setShoupaiSortStrategy(ShoupaiSortStrategy shoupaiSortStrategy) {
		this.shoupaiSortStrategy = shoupaiSortStrategy;
	}

}
