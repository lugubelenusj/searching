public class ClosedList {

    // TODO maybe I should do closed hashing instead, and make the size = 181,440?
    public static final int SIZE = 199;
    private Node[] array;

    public ClosedList() {
        this.array = new Node[SIZE];
    }

    public void add(State state) {
        int hashCode = state.hashCode();
        if (array[hashCode] == null) {
            array[hashCode] = new Node(state);
        }
        else {
            Node current = array[hashCode];
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(state);
        }
    }

    public boolean contains(State state) {
        int hashCode = state.hashCode();
        if (array[hashCode] != null) {
            if (array[hashCode].data.equals(state)) {
                return true;
            } else {
                Node current = array[hashCode];
                while (current.next != null) {
                    if (current.next.data.equals(state)) {
                        return true;
                    }
                    current = current.next;
                }
            }
        }
        return false;
    }

    private class Node {

        private State data;
        private Node next;

        private Node(State data) {
            this(data, null);
        }

        private Node(State data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

}