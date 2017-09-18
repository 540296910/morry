package study.zookeeper;

import java.util.List;

/*import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;*/

public class Simple {
	public static final String ROOT = "/root-ktv";

	public static void main(String[] args) throws Exception {
		// 创建一个与服务器的连接
		/*
		 * ZooKeeper zk = new ZooKeeper("localhost:2181", 30000, new Watcher() {
		 * // 监控所有被触发的事件 public void process(WatchedEvent event) {
		 * System.out.println("状态:" + event.getState() + ":" + event.getType() +
		 * ":" + event.getWrapper() + ":" + event.getPath()); } });
		 */
	/*	ZooKeeper zk = new ZooKeeper("192.168.76.128:2181", 30000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println("状态:" + event.getState() + ":"
						+ event.getType() + ":" + event.getWrapper() + ":"
						+ event.getPath());
			}
		});
		System.out.println(new String(zk.getData(ROOT+"/杭州KTV0000000000", null, null)));
		List<String> ktvs = zk.getChildren(ROOT, true);
		for (String node : ktvs) {
			// 删除节点
			System.out.println(node);
		}*/
		// 创建一个总的目录ktv，并不控制权限，这里需要用持久化节点，不然下面的节点创建容易出错
	/*	System.out.println(Arrays.toString(ktvs.toArray()));
		for (String node : ktvs) {
			// 删除节点
			zk.delete(ROOT + "/" + node, -1);
		}
		// 根目录得最后删除的
		zk.delete(ROOT, -1);
		zk.close();*/
	}
}
