public class OpenList {

    private Node array[];
    private int maxsize;
    private int size;
    private State goal;
    private SearchType searchType;
    // Probably should make a dynamic sized list.


    public OpenList(State goal, SearchType searchType) {
        this.goal = goal;
        this.searchType = searchType;
        this.maxsize = 100;
        array = new Node[maxsize];
        // This is a dummy node, to always be on top.
        array[0] = new Node(new DummyState(), -1);
        this.size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void insert(State state) {
        int curr = size++;
        array[size] = new Node(state);

        while (array[curr].priority < array[parent(curr)].priority) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public State removeMin() {
        swap(1, size);
        size--;
        if (size != 0) {
            siftDown(1);
        }
        return array[size+1].data;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private boolean isLeaf(int pos) {
        return ((pos > size/2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {
        Node temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    private void siftDown(int pos) {
        int smallestChild;
        while (!isLeaf(pos)) {
            smallestChild = leftChild(pos);
            if ((smallestChild < size) && array[smallestChild].priority > array[smallestChild+1].priority) {
                smallestChild = smallestChild + 1;
            }
            if (array[pos].priority <= array[smallestChild].priority) {
                return;
            }
            swap(pos, smallestChild);
            pos = smallestChild;
        }
    }

    private class Node {

        private State data;
        private float priority;

        private Node(State data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        private Node(State data) {
            this.data = data;

            switch (searchType) {
                case ASTAR:
                    this.priority = this.data.gValue() + this.data.distanceToState(goal);
                    break;
                case GREEDY:
                    this.priority = this.data.distanceToState(goal);
                    break;
                case UNIFORMCOST:
                    this.priority = this.data.gValue();
                    break;
            }
        }
    }
}