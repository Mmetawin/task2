public class DoubleLinkedList<T> {
    private static int size;
    private Node<T> first;
    private Node<T> last;
    //节点类
    private static class Node<T> {
        private T data;
        private Node<T> pri;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPri() {
            return pri;
        }

        public void setPri(Node<T> pri) {
            this.pri = pri;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    public static int getSize() {
        return size;
    }

    //添加节点到头部
    public boolean addFirst(T value){
        Node<T> node = new Node<>(value);
        if (size == 0) {
            first = node;
            last = first;
        }else {
            node.next = first;
            first.pri = node;
            first = node;
        }
        size++;
        return true;
    }
   //添加节点到尾部
    public boolean addLast(T value){
        if (size == 0){
            return addFirst(value);
        }else {
            Node<T> node = new Node<>(value);
            last.next = node;
            node.pri = last;
            last = node;
        }
        size++;
        return true;
    }
   //添加元素到指定位置
    public boolean add(int index,T value){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return addFirst(value);
        }else if (index == size){
            return addLast(value);
        }else {
            Node<T> node = new Node<>(value);
            Node<T> head = first;
            for (int i = 0; i < index-1; i++) {
                head = head.getNext();
            }
            node.next = head.next;
            head.next = node;
            node.pri = head;
            node.next.pri = node;
        }
        size++;
        return true;
    }

    //删除头节点
    public T removeFirst(){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        T data = first.getData();
        Node<T> node = first.next;
        node.setPri(null);
        first = node;
        return data;
    }

   //删除尾节点
    public T removeLast(){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        T data = last.getData();
        Node<T> node = last.pri;
        node.setNext(null);
        last = node;
        return data;
    }

   //删除指定下标节点
    public T remove(int index){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        //注意添加的时候，下标取不到size，但是添加的位置可以是size，但是删除的时候不行
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return removeFirst();
        }else {
            Node<T> node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            Node<T> temp = node.next;
            if (temp != last){
                T data = temp.getData();
                node.next = temp.next;
                temp.next.pri = node;
                temp.setNext(null);
                return data;
            }else {
                return removeLast();
            }
        }
    }

    //获取输入下标对应的数据
    public T getData(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        }else if (size == 0){
            throw new RuntimeException("链表为空");
        }else if (size == 1){
            return first.data;
        }else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }
    }

    //清空链表
    public void clear(){
        first.next = null;
        last = first;
    }

    //链表遍历输出
    public void print(){
        if (size == 0) {
            System.out.println("该链表为空!");
        }
        Node<T> node = first;
        while (node != null) {
            System.out.print(node.getData() + "\t");
            node = node.next;
        }
        System.out.println();
    }
    //遍历输出当前节点和前后节点值
    public void detailPrint(){
        if (size == 0) {
            System.out.println("该链表为空!");
        }
        Node<T> node = first;
        while (node != null) {
            System.out.print("前驱节点值：");
            System.out.printf("%-5s",node.pri == null ? "null\t" : node.pri.getData()+"\t");
            System.out.print("当前节点值：");
            System.out.printf("%-6s",node.getData() + "\t");
            System.out.print("后继节点值：");
            System.out.printf("%-5s",node.next == null ? "null\t" : node.next.getData()+"\t");
            System.out.println();
            node = node.getNext();
        }
        System.out.println();
    }
    public static void main(String[] args){
        //功能简单测试
        DoubleLinkedList node = new DoubleLinkedList();
        node.add(-1,5);
        node.add(1,2);
        node.add(2,"wt");
        node.add(3,"much");
        //插入节点测试
        node.add(3,"very");
        node.add(3,"very");
        node.add(6,"and");
        node.add(7," 威猛先生");
        node.add(8,"大");
        node.add(9,"帅逼");
        node.print();
        //删除节点测试
        System.out.println("删除“大");
        node.remove(8);
        node.print();
    }
}
