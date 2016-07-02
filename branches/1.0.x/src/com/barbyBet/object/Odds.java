package com.barbyBet.object;

public class Odds {
	private float _home;
	private float _away;
	private float _draw;
	
	public Odds(float home, float away, float draw) {
		_home = home;
		_away = away;
		_draw = draw;
	}

	public float getHomeOdd() {
		return _home;
	}

	public void setHomeOdd(float home) {
		this._home = home;
	}

	public float getAwayOdd() {
		return _away;
	}

	public void setAwayOdd(float away) {
		this._away = away;
	}

	public float getDrawOdd() {
		return _draw;
	}

	public void setDrawOdd(float draw) {
		this._draw = draw;
	}
	
	public String toString() {
		return _home + " : " + _draw + " : " + _away;
	}
}
