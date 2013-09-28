package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class HbaseAction {
	public static void main(String[] args) throws Exception{
		HbaseAPI HB = new HbaseAPI();
		HbaseAPI.cargsreateTable("tab1", "fam1");
		HbaseAPI.addData("tab1", "row1", "fam1", "col1", "val1");
		HbaseAPI.addData("tab1", "row2", "fam1", "col2", "val2");
		HbaseAPI.addData("tab1", "row2", "fam1", "col3", "val3");
		HbaseAPI.Scanner("tab1", "fam1");
	}

}
