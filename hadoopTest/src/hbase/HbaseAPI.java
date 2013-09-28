package hbase;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

/**
 * @author grid
 * 
 */
public class HbaseAPI {

	private static HBaseAdmin admin;
	private static Configuration conf;

	HbaseAPI() throws MasterNotRunningException, ZooKeeperConnectionException {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorumtableName", "ubuntu1");
		admin = new HBaseAdmin(conf);

	}

	/**
	 * @param tableName
	 * @param columnFarily
	 * @throws Exception
	 */
	public static void cargsreateTable(String tableName, String columnFarily)
			throws Exception {

		if (admin.tableExists(tableName)) {
			System.out.println(tableName + " 存在！");
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			tableDesc.addFamily(new HColumnDescriptor(columnFarily));
			admin.createTable(tableDesc);
			System.out.println("创建表成功！");
		}
	}

	/**
	 * @param tableName
	 * @param row
	 * @param columnFamily
	 * @param column
	 * @param data
	 * @throws Exception
	 */
	public static void addData(String tableName, String row,
			String columnFamily, String column, String data) throws Exception {
		HTable table = new HTable(conf, tableName);
		Put putRow1 = new Put(row.getBytes());
		putRow1.add(columnFamily.getBytes(), column.getBytes(), data.getBytes());
		table.put(putRow1);
		System.out.println("data put in ！");
	}

	/**
	 * @param tableName
	 * @param family
	 * @throws IOException
	 */
	public static void Scanner(String tableName, String family)
			throws IOException {
		HTable table = new HTable(conf, tableName);
		for (Result row : table.getScanner(family.getBytes())) {
			System.out.format("ROW\t%s\n", new String(row.getRow()));
			for (Map.Entry<byte[], byte[]> E : row.getFamilyMap(
					family.getBytes()).entrySet()) {
				String column = new String(E.getKey());
				String value = new String(E.getValue());
				System.out.format("COLUMN\tfam1:%s\t%s\n", column, value);

			}
		}
	}

}
