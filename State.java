public interface State {

	/**
	 * Return an array of all of the children of this state.  The returned array 
	 * should be just large enough to hold all of the children of this state.
	 * @return Array of children of this state
	 */
	public State[] getChildren();
	
	/**
	 * Return the parent of this state (that is, the state that generated this
	 * state), or null if this is an initial state
	 * @return
	 */
	public State getParent();
	
	/**
	 * Returns true if this state is equal to another state, ignoring parents
	 * @param other The state to compare to 
	 * @return True if the states are equal
	 */
	
	public boolean equals(State other);
	
	/**
	 * String representation of the state
	 * @return
	 */
	public String toString();

	/**
	 * Return the distance of this state from the initial state.  
	 * @return
	 */
	public float gValue();
	
	/**
	 * Hash code for this state.  States that are equal (via the above equals) need
	 * to have the same HashCode
	 * @return
	 */
	public int hashCode();
	
	/**
	 * Returns a string representation of the solution path.  See concrete states for more information
	 * @return String representation of the standard solution path
	 */
	public String solutionPath();
	
	/**
	 * Returns a more verbose representation of the solution path.  See concrete states for more information.
	 * @return String representation of the verbose solution path
	 */
	public String solutionPathExtended();
	
	
	/**
	 * Returns the (estimated) distance from this state to an arbitrary different state.  
	 * The heuristic (h) value is the estimated distance of that state from the goal.
	 * @return Estimated distance to the state
	 */
	public float distanceToState(State otherState);
}