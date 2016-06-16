package com.barbyBet.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RankComponent {

	public RankComponent() 
	{
		
	}
	
	public Map<String, Map<String, String>> getMinimizedRank(Long groupId, String login)
	{
		SQLRankComponent rankComponent = new SQLRankComponent();
		Map<String, Map<String, String>> rank = new HashMap<String, Map<String,String>>();
		if (groupId == null)
		{
			rank = rankComponent.getGeneralRank();
		}
		else
		{
			rank = rankComponent.getGroupRank(groupId);
		}
		Map<String, Map<String, String>> newRank = new LinkedHashMap<String, Map<String,String>>();
		int index = 0;
		int nbUser = 0;
		boolean userFounded = false;
		List<String> users = new ArrayList<String>();
		users.addAll(rank.keySet());
		for (String userName : rank.keySet())
		{
			Map<String, String> attribute = new HashMap<String, String>();
			if (nbUser < 3 || (userFounded && (nbUser < 7)))
			{
				attribute = rank.get(userName);
//				attribute.put("rank", String.valueOf(index + 1));
				newRank.put(userName, attribute);
				nbUser++;
			}
			
			if (userName.equals(login))
			{
				if (index >= 3)
				{
					attribute = rank.get(users.get(index - 2));
					if (index - 2 > 3)
					{
						attribute.put("hasBefore", "false");
					}
//					attribute.put("rank", String.valueOf(index - 1));
					newRank.put(users.get(index - 2), attribute);
					
					attribute = rank.get(users.get(index - 1));
//					attribute.put("rank", String.valueOf(index));
					newRank.put(users.get(index - 1), attribute);
					
					if (index - 2 > 3)
				    {
						nbUser += 2;
				    }
				    else
				    {
				    	nbUser += (index - 3);
				    }
				}
				
				attribute = rank.get(login);
//				attribute.put("rank", String.valueOf(index + 1));
				attribute.put("currentUser", "true");
				newRank.put(login, attribute);
				
				userFounded = true;
			}
			index++;
		}
		
		return newRank;
	}
	
	public Map<String, Map<String, String>> getRank(Long groupId, String login, int page, int nbUser)
	{
		SQLRankComponent rankComponent = new SQLRankComponent();
		Map<String, Map<String, String>> rank = new HashMap<String, Map<String,String>>();
		if (groupId == null)
		{
			rank = rankComponent.getGeneralRank(page, nbUser);
		}
		else
		{
			rank = rankComponent.getGroupRank(groupId, page, nbUser);
		}
		Map<String, Map<String, String>> newRank = new LinkedHashMap<String, Map<String,String>>();

		int index = 0;
		for (String userName : rank.keySet())
		{
			Map<String, String> attribute = new HashMap<String, String>();
			attribute = rank.get(userName);
			attribute.put("rank", String.valueOf(index + 1 + (page - 1) * nbUser));
			if (userName.equals(login))
			{
				attribute.put("currentUser", "true");
			}
			newRank.put(userName, attribute);
			index++;
		}
		
		return newRank;
	}
	
	public int getSize(Long groupId)
	{
		SQLRankComponent rankComponent = new SQLRankComponent();
		if (groupId != null)
		{
			return rankComponent.getGroupSize(groupId);
		}
		else
		{
			return rankComponent.getGeneralSize();
		}
	}
}
