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
		int numOfExpandedStates = 0;
		OpenList openList = new OpenList(goal, searchType);
		openList.insert(initial);
		ClosedList closedList = new ClosedList();

		State state = null;
		while (!openList.isEmpty()) {
			state = openList.removeMin();
			if (!closedList.contains(state)) {
				if (state.equals(goal)) {
					return state;
				}
				else {
					closedList.add(state);
					numOfExpandedStates++;
					for (State child : state.getChildren()) {
						if (!closedList.contains(child) && child != null) {
							openList.insert(child);
							numOfExpandedStates++;
						}
					}
				}
			}
		}

		if (printNumberOfExpandedStates) {
			System.out.println(numOfExpandedStates);
		}

		return state;
	}

}
