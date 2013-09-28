package hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LazyMapReduce {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();  
        if (args.length != 2) {  
            System.err.println("Usage: wordcount  "+args);  
            System.exit(2);  
        }  
        Job job = new Job(conf, "LazyMapReduce ");
        FileInputFormat.addInputPath(job, new Path(args[0]));  
        FileOutputFormat.setOutputPath(job, new Path(args[1])); 
        System.exit(job.waitForCompletion(true) ? 0 : 1);  
	}
}
