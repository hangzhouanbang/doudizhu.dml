package com.dml.doudizhu.player.action;

public abstract class DoudizhuPlayerAction {

	private DoudizhuPlayerActionType actionType;

	private String actionPlayerId;

	public DoudizhuPlayerAction() {

	}

	public DoudizhuPlayerAction(DoudizhuPlayerActionType da, String actionPlayerId) {
		actionType = da;
		this.actionPlayerId = actionPlayerId;
	}

	public DoudizhuPlayerActionType getActionType() {
		return actionType;
	}

	public void setActionType(DoudizhuPlayerActionType actionType) {
		this.actionType = actionType;
	}

	public String getActionPlayerId() {
		return actionPlayerId;
	}

	public void setActionPlayerId(String actionPlayerId) {
		this.actionPlayerId = actionPlayerId;
	}

}
