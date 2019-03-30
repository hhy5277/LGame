/**
 * Copyright 2008 - 2019 The Loon Game Engine Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.5
 */
package loon.action.map.battle.behavior;

public abstract class IActor {

	protected int currentIndex;
	
	protected IMove moves;

	protected String name;

	protected IStatistics statistics;

	protected String title;
	
	protected int team ;
	
	protected IActorStatus actorStatus;
	
	protected boolean visible;
	
	protected boolean exist;
	
	protected float posX;
	
	protected float posY;

	public IMove getMoves() {
		return moves;
	}

	public void setMoves(IMove moves) {
		this.moves = moves;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(IStatistics statistics) {
		this.statistics = statistics;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public IActorStatus getActorStatus() {
		return actorStatus;
	}

	public void setActorStatus(IActorStatus actorStatus) {
		this.actorStatus = actorStatus;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public abstract boolean actionCheck();

	public abstract void attackCheck();
		
	public abstract void nextAction();

	public abstract IActor enemyCheck();
	
	
}
