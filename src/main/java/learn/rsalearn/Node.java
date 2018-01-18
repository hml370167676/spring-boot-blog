package learn.rsalearn;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2018/1/17 16:07
 */
public class Node {

  private Node nextNode;
  private int value;

  public Node(int n) {
    this.nextNode = null;
    this.value = n;
  }

  public Node getNextNode() {
    return nextNode;
  }

  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
