public class Search {


	/** Do a search from the initial state to the goal state. 
	 * 
	 * @param initial The state to search from 
	 * @param goal The state to find
	 * @param searchType The type of search to do
	 * @return The final state (which will be equal to the goal state, and whose
	 *         path to the original state can be found by following the parent pointers)
	 */
	public static State doSearch(State initial, State goal, SearchType searchType, boolean printNumberOfExpandedStates)	{
	    OpenList openList = new OpenList(searchType);
		openList.insert(initial);
		ClosedList closedList = new ClosedList();
		
		while (!openList.isEmpty()) {
			State state = openList.removeMin();
			if (!closedList.contains(state)) {
				if (state.equals(goal)) {
					return state;
				}
				else {
					closedList.add(state);
					for (State child : state.getChildren()) {
						if (!closedList.contains(child)) {
							openList.add(child);
						}
					}
				}
			}
		}

		return null;
	}

}
